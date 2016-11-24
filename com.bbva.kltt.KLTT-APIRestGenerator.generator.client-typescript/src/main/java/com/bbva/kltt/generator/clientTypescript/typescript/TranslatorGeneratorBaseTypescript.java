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

package com.bbva.kltt.generator.clientTypescript.typescript;

import com.bbva.kltt.core.generator.TranslatorGeneratorBase;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.FilesUtility;
import com.bbva.kltt.generator.clientTypescript.util.ConstantsOutputTypescript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Base class that gives the common methods for every Translator java generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorBaseTypescript extends TranslatorGeneratorBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorBaseTypescript.class);

    /**
     * Base directory for source files
     */
    private File sourceDir;
    /**
     * Base directory for resource files
     */
    private File resourceDir;

    /**
     * Constructor of the class
     *
     * @param translatorType    with the translator type
     * @param generationParams  with the generation parameters information for the TypeScript
     * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
     */
    public TranslatorGeneratorBaseTypescript(final String translatorType,
                                             final GenerationParameters generationParams,
                                             final ParsedInfoHandler parsedInfoHandler)
    {
        super(translatorType, generationParams, parsedInfoHandler, ConstantsOutputTypescript.PACKAGE_VM_RESOURCES);
    }

    /**
     * Initialize the file references and directory references for the rest of the generation.
     * It will also create the required directory paths for the generation.
     *
     * @throws APIRestGeneratorException exception thrown if there is any problem.
     */
    public void initializeFileDirectories() throws APIRestGeneratorException
    {
        // Get the name of the translator that is going to be generated
        final String translatorName = this.getTranslatorType();

        TranslatorGeneratorBaseTypescript.LOGGER.info("Initializing files and creating directories with base [{}] for translator [{}]",
                this.getGenerationParams().getCodeGenOutputDirectory(), translatorName);
        // Build the paths
        final String sourcePath = this.getGenerationParams().getCodeGenOutputDirectory() + File.separator +
                                  translatorName + File.separator +
                                  ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + File.separator +
                                  ConstantsOutputTypescript.TYPESCRIPT_GENERATED_FOLDER + File.separator +
                                  this.getOutputCodeType();
        // Resource path
        final String resourcePath = this.getGenerationParams().getCodeGenOutputDirectory() + File.separator +
                                    translatorName + File.separator +
                                    ConstantsOutputTypescript.GEN_RESOURCES_DIR_TYPESCRIPT;
        // Initialize the files
        this.sourceDir = new File(sourcePath);
        this.resourceDir = new File(resourcePath);

        // Make the directories
        FilesUtility.createFullDirectoryTree(this.sourceDir);
        FilesUtility.createFullDirectoryTree(this.resourceDir);
    }

    /**
     * @return the code type to be generated (model or rest)
     */
    public abstract String getOutputCodeType();

    @Override
    public File getSourceDir()
    {
        return this.sourceDir;
    }

    @Override
    public File getResourceDir()
    {
        return this.resourceDir;
    }

    /**
     * @param sourceDir the sourceDir to set
     */
    protected void setSourceDir(final File sourceDir)
    {
        this.sourceDir = sourceDir;
    }

    /**
     * @param resourceDir the resourceDir to set
     */
    protected void setResourceDir(final File resourceDir)
    {
        this.resourceDir = resourceDir;
    }
}
