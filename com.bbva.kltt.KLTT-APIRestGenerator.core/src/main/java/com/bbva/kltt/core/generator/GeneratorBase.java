/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.bbva.kltt.core.generator;

import com.bbva.kltt.core.generator.output.language.*;
import com.bbva.kltt.core.generator.velocity.VelocityManager;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.core.util.FilesUtility;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * This class is the base class for any file generation
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBase implements IGenerator
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorBase.class);

    /**
     * The velocity engine
     */
    private static final VelocityEngine VELOCITY_ENGINE = VelocityManager.getEngine();

    /**
     * Attribute - Base output directory for the generation
     */
    private final File baseOutputDirectory;

    /**
     * Global generation parameters
     */
    private final GenerationParameters generationParams;

    /**
     * Package Generation Resources
     */
    private final String generationPackage;

    /**
     * Attribute - Output Language Naming
     */
    private final IOutputLanguageNaming outputLanguageNaming;
    /**
     * Attribute - Output Language Generator properties parameters
     */
    private final IOutputLanguageGeneratorParams outputLanguageGeneratorPa;
    /**
     * Attribute - Output Language Root values
     */
    private final IOutputLanguageRootValues outputLanguageRootVa;
    /**
     * Attribute - Output Language Info Values
     */
    private final IOutputLanguageInfoValues outputLanguageInfoVa;
    /**
     * Attribute - Output Language Paths
     */
    private final IOutputLanguagePaths outputLanguagePaths;
    /**
     * Attribute - Output Language Operations
     */
    private final IOutputLanguageOperations outputLanguageOperat;

    /**
     * Creates a new generator
     *
     * @param baseOutputDirectory with the File that represents the base directory where the file will be generated.
     * @param generationParams    with the parameters for the generation
     * @param parsedInfoHandler   with the parsed info handler
     */
    public GeneratorBase(final File baseOutputDirectory,
                         final GenerationParameters generationParams,
                         final ParsedInfoHandler parsedInfoHandler,
                         final String generationPackage)
    {
        this.baseOutputDirectory    = baseOutputDirectory;
        this.generationParams       = generationParams;
        this.generationPackage      = generationPackage;

        this.outputLanguageNaming        = new OutputLanguageNaming();
        this.outputLanguageGeneratorPa   = new OutputLanguageGeneratorParams(parsedInfoHandler);
        this.outputLanguageRootVa        = new OutputLanguageRootValues(parsedInfoHandler,
                                                                        this.outputLanguageNaming,
                                                                        this.outputLanguageGeneratorPa);
        this.outputLanguageInfoVa        = new OutputLanguageInfoValues(parsedInfoHandler);
        this.outputLanguagePaths         = new OutputLanguagePaths(parsedInfoHandler);
        this.outputLanguageOperat        = new OutputLanguageOperations(parsedInfoHandler);
    }

    /**
     * Generates the code, this is the entry point of the generator
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void generate() throws APIRestGeneratorException
    {
        this.generateFile(this.createVelocityContext());
    }

    /**
     * @return the Velocity Context for the generation
     */
    protected abstract VelocityContext createVelocityContext();

    /**
     * Return the package where the new file should be generated into
     *
     * @return the output package
     */
    protected abstract String getOutputPackage();

    /**
     * Return the name of the output file to generate without extension
     *
     * @return the output file name
     */
    protected abstract String getOutputFileName();

    /**
     * Return the extension for the output generated file, it should include the '.'
     *
     * @return the output extension
     */
    protected abstract String getOutputExtension();

    /**
     * This method generates the file.
     * <p>
     * It will look for the template on the same package the generator is located.
     * It will look for a template that has the same name than the generator class but ended in .vm.
     * <p>
     * The output file will be in "this.baseOutputDirectory" + getOutputPackage() + getOutputFileName() + getOutputExtension() where the package will be converted
     * to a directory by changing the '.' for '/' or '\\'
     * <p>
     * The output final directory will be automatically created if it doesn't exists
     *
     * @param context velocity context that will be merged with the template
     * @throws APIRestGeneratorException exception thrown if there is any problem
     */
    protected void generateFile(final VelocityContext context) throws APIRestGeneratorException
    {
        // Add the common parameters like the current time to the context
        this.addCommonParametersToContext(context);

        // Get the name of the template, it should be the same than the class name
        final String templateName           = this.getClass().getSimpleName();

        // Get the full file path of the template
        final String templateFilePath       = this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, templateName);

        // Initialize the output directory
        final String outputDirectoryPath    = FilesUtility.initializeOutputDirectory(this.baseOutputDirectory, this.getOutputPackage());

        // Create the output file
        final File outputFile               = FilesUtility.createOutputFile(outputDirectoryPath,
                                                                            this.getOutputFileName(),
                                                                            this.getOutputExtension());

        // Finally merge the template with the context and generate the file

        FileWriter fileWriter               = null;
        BufferedWriter bufferedWriter       = null;

        try
        {
            fileWriter              = new FileWriter(outputFile);
            bufferedWriter          = new BufferedWriter(fileWriter);

            final Template template = VELOCITY_ENGINE.getTemplate(templateFilePath);
            template.merge(context, bufferedWriter);
            bufferedWriter.flush();
        }
        catch (Exception exception)
        {
            GeneratorBase.LOGGER.error("Error while merging the template in output file", exception);
            throw new APIRestGeneratorException(exception);
        }
        finally
        {
            this.closeStreams(fileWriter, bufferedWriter);
        }
    }

    /**
     * Given the name of the template it returns the relative path of the template file
     * <p>
     * It will look for the template on the same package the generator is located.
     *
     * @param relativeFolder with the relative folder
     * @param templateName   with the name of the template, without extension.
     * @return the real path for the template
     */
    protected String getTemplateResourceName(final String relativeFolder, final String templateName)
    {
        // Firstly, find the template, it should be in the same package than the generation class
        final String templatePackage 	= this.getClass().getPackage().getName() ;

        // Now convert the package to template file location
        return this.getRealPath(templatePackage.replace(ConstantsCommon.STRING_DOT, ConstantsCommon.STRING_SLASH),
                relativeFolder +
                        templateName   + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_VELOCITY, "") ;
    }

    /**
     * @param filePackage with the file package separated by SLASH
     * @param fileName    with the file name with possible ../ strings
     * @return a new string with the path
     */
    protected String getFileResourceName(final String filePackage, final String fileName)
    {
        // Now convert the package to the file location
        return this.getRealPath(filePackage, fileName, this.generationPackage.replace(ConstantsCommon.STRING_DOT, ConstantsCommon.STRING_SLASH));
    }

    /**
     * Get the real path of the file
     *
     * @param filePackage with the file package separated by SLASH
     * @param fileName    with the file name with possible ../ strings
     * @return a new string without relative path
     */
    protected String getRealPath(final String filePackage, final String fileName, final String currentPackage)
    {
        final String[] filePackageSplit    = filePackage.split(ConstantsCommon.STRING_SLASH);
        final String[] fileNameSplit       = fileName.split(ConstantsCommon.STRING_SLASH);

        int backDirectories = filePackageSplit.length;
        for (final String fileNameSplitItem : fileNameSplit)
        {
            if (ConstantsCommon.PREVIOUS_DIRECTORY.equals(fileNameSplitItem))
            {
                backDirectories--;
            }
        }

        final StringBuffer outcome = new StringBuffer();

        // Get the directory path from the filePackage
        for (int i = 0; i < backDirectories; i++)
        {
            outcome.append(filePackageSplit[i] + ConstantsCommon.STRING_SLASH);
        }

        // Get the directory path from the fileName
        final int fileNameSplitL = fileNameSplit.length;
        for (int j = (filePackageSplit.length - backDirectories); j < fileNameSplitL; j++)
        {
            outcome.append(fileNameSplit[j]);

            if (j + 1 < fileNameSplitL)
            {
                outcome.append(ConstantsCommon.STRING_SLASH);
            }
        }

        return outcome.toString();
    }

    /**
     * Return the title of the API project in CamelCase format
     *
     * @return A String with the title in CamelCase format
     */
    protected String getTitleCamelCase()
    {
        return this.getOutputLanguageNaming().setCamelCaseName(this.getOutputLanguageInfoValues().getTitle());
    }

    /**
     * Add common parameters for all templates to the context
     *
     * @param context the context to add values into
     */
    private void addCommonParametersToContext(final VelocityContext context)
    {
        // Add now to the velocity context
        context.put(ConstantsOutput.COMMON_TEMP_PARAM_NOW, new Date());

        context.put(ConstantsOutput.COMMON_TEMP_OUT_LANG_ITEMS,   this.getOutputLanguageItems());
        context.put(ConstantsOutput.COMMON_TEMP_OUT_LANG_PARAMS,  this.getOutputLanguageParameters());
        context.put(ConstantsOutput.COMMON_TEMP_OUT_LANG_CON_PRO, this.getOutputLanguageConsProd());
        context.put(ConstantsOutput.COMMON_TEMP_OUT_LANG_NAMING,  this.getOutputLanguageNaming());
        context.put(ConstantsOutput.COMMON_TEMP_OUT_LANG_GEN_P,   this.getOutputLanguageGeneratorParams());
        context.put(ConstantsOutput.COMMON_TEMP_OUT_LANG_ROOT_V,  this.getOutputLanguageRootValues());
        context.put(ConstantsOutput.COMMON_TEMP_OUT_LANG_PATHS,   this.getOutputLanguagePaths());
        context.put(ConstantsOutput.COMMON_TEMP_OUT_LANG_OPERAT,  this.getOutputLanguageOperations());

        context.put(ConstantsOutput.VEL_PAR_TAB, ConstantsOutput.VEL_TABULATOR);
    }

    /**
     * Close the opened streams
     *
     * @param fileWriter     with the file writer
     * @param bufferedWriter with the buffered writer
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void closeStreams(final FileWriter fileWriter, final BufferedWriter bufferedWriter) throws APIRestGeneratorException
    {
        if (bufferedWriter != null)
        {
            try
            {
                bufferedWriter.close();
            }
            catch (IOException ioException)
            {
                GeneratorBase.LOGGER.error("Error while closing the bufferedWriter related to the template in output file", ioException);
                throw new APIRestGeneratorException(ioException);
            }
        }

        if (fileWriter != null)
        {
            try
            {
                fileWriter.close();
            }
            catch (IOException ioException)
            {
                GeneratorBase.LOGGER.error("Error while closing the fileWriter related to the template in output file", ioException);
                throw new APIRestGeneratorException(ioException);
            }
        }
    }

    @Override
    public GenerationParameters getGenerationParams()
    {
        return this.generationParams;
    }

    @Override
    public IOutputLanguageNaming getOutputLanguageNaming()
    {
        return this.outputLanguageNaming;
    }

    @Override
    public IOutputLanguageGeneratorParams getOutputLanguageGeneratorParams()
    {
        return this.outputLanguageGeneratorPa;
    }

    @Override
    public IOutputLanguageRootValues getOutputLanguageRootValues()
    {
        return this.outputLanguageRootVa;
    }

    @Override
    public IOutputLanguageInfoValues getOutputLanguageInfoValues()
    {
        return this.outputLanguageInfoVa;
    }

    @Override
    public IOutputLanguagePaths getOutputLanguagePaths()
    {
        return this.outputLanguagePaths;
    }

    @Override
    public IOutputLanguageOperations getOutputLanguageOperations()
    {
        return this.outputLanguageOperat;
    }

}
