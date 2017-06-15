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

package com.bbva.kltt.apirest.generator.java;

import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.java.velocity.models.TranslatorGeneratorJavaModels;
import com.bbva.kltt.apirest.generator.java.velocity.top.MvnInstallFileLinuxGenerator;
import com.bbva.kltt.apirest.generator.java.velocity.top.MvnInstallFileWindowsGenerator;
import com.bbva.kltt.apirest.generator.java.velocity.top.TopLevelJavaPOM;
import com.bbva.kltt.apirest.core.generator.GeneratorGlobalLanguage;
import com.bbva.kltt.apirest.core.generator.IGenerator;
import com.bbva.kltt.apirest.core.generator.ITranslatorGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorGlobalJava extends GeneratorGlobalLanguage
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorGlobalJava.class);

    /**
     * Directory for the top level
     */
    private File topLevelDir;

    /**
     * Constructs a new global generator for Java
     *
     * @param genParams         with the parameters for the generation for java
     * @param parsedInfoHandler with the parsed information handler to generate from
     * @param generationPackage with the generation package
     */
    public GeneratorGlobalJava(final GenerationParameters genParams,
                               final ParsedInfoHandler parsedInfoHandler,
                               final String generationPackage)
    {
        super(genParams, parsedInfoHandler, generationPackage);
    }

    /**
     * @param translatorType with the translator type
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void generate(final String translatorType) throws APIRestGeneratorException
    {
        // Verify the restrictions
        this.verifyRestrictions();

        // Initialize file directories
        this.initializeFileDirectories();

        // Generate the top level POM file
        this.generateTopLevelPom(translatorType);

        // Generate the Java Models - Project
        this.generateModelsProject();

        // Generate the Java Rest interface - Project
        this.generateRestInterfaceProject();

        // Generate the Java Rest interface - Project
        this.generateRestImplementationProject();

        // Generate the Java example project
        this.generateExampleProject();

        // Finally, generates the top level POM file
        this.generateMavenInstallFile(translatorType);
    }

    /**
     * Verify the restrictions
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    protected abstract void verifyRestrictions() throws APIRestGeneratorException;

    /**
     * Initialize the file references and directory references for the rest of the generation.
     * It will also create the required directory paths for the generation.
     *
     * @throws APIRestGeneratorException exception thrown if there is any problem.
     */
    private void initializeFileDirectories() throws APIRestGeneratorException
    {
        GeneratorGlobalJava.LOGGER.info("Initializing files and creating directories with base directory {}",
                                        this.getGenerationParams().getCodeGenOutputDirectory());

        // Generate the directory paths
        final String topLevelPomPath = this.getGenerationParams().getCodeGenOutputDirectory();

        // Create the file objects
        this.topLevelDir = new File(topLevelPomPath);
    }

    /**
     * Generate the top level pom that contains the model, controllers, etc.
     *
     * @param translatorType with the translator type
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateTopLevelPom(final String translatorType) throws APIRestGeneratorException
    {
        GeneratorGlobalJava.LOGGER.info("Generating pom for top level...");

        final IGenerator generator = this.generateInstanceTopLevelPOM(this.topLevelDir,
                                                                      translatorType,
                                                                      this.getGenerationParams(),
                                                                      this.getParsedInfoHandler(),
                                                                      ConstantsOutputJava.TOP_LEVEL_POM_FILE_NAME);
        generator.generate();

        GeneratorGlobalJava.LOGGER.info("Generated pom for top level...");
    }

    /**
     * @param topLevelPomDir    with the top level POM directory
     * @param translatorName    with the translator name
     * @param genParams         with the generation parameters
     * @param parsedInfoHandler with the parsed info handler
     * @param pomFileName       with the POM file name
     * @return a new Top Level POM Generator instance
     */
    protected IGenerator generateInstanceTopLevelPOM(final File topLevelPomDir,
                                                     final String translatorName,
                                                     final GenerationParameters genParams,
                                                     final ParsedInfoHandler parsedInfoHandler,
                                                     final String pomFileName)
    {
        return new TopLevelJavaPOM(topLevelPomDir, translatorName, genParams, parsedInfoHandler, pomFileName) ;
    }

    /**
     * Generate the models project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateModelsProject() throws APIRestGeneratorException
    {
        final ITranslatorGenerator javaModelsGenerator = new TranslatorGeneratorJavaModels(this.getGenerationParams(),
                                                                                           this.getParsedInfoHandler(),
                                                                                           this.getGenerationPackage());
        javaModelsGenerator.generate();
    }

    /**
     * Generate the rest interface project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    protected abstract void generateRestInterfaceProject() throws APIRestGeneratorException;

    /**
     * Generate the rest implementation project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    protected abstract void generateRestImplementationProject() throws APIRestGeneratorException;

    /**
     * Generate the Java Example Project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    protected abstract void generateExampleProject() throws APIRestGeneratorException;

    /**
     * Generate the MAVEN install file to be used by the developer
     *
     * @param translatorType with the translator type
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateMavenInstallFile(final String translatorType) throws APIRestGeneratorException
    {
        GeneratorGlobalJava.LOGGER.info("Generating maven install file...");

        final IGenerator generatorWindows = new MvnInstallFileWindowsGenerator(this.topLevelDir,
                                                                               translatorType,
                                                                               this.getGenerationParams(),
                                                                               this.getParsedInfoHandler()) ;
        generatorWindows.generate();

        final IGenerator generatorLinux = new MvnInstallFileLinuxGenerator(this.topLevelDir,
                                                                           translatorType,
                                                                           this.getGenerationParams(),
                                                                           this.getParsedInfoHandler()) ;
        generatorLinux.generate();

        GeneratorGlobalJava.LOGGER.info("Generated maven install file...");
    }
}
