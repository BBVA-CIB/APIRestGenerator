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

package com.bbva.kltt.apirest.generator.test.example ;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.generator.GeneratorGlobalLanguage;
import com.bbva.kltt.apirest.core.generator.IGenerator;
import com.bbva.kltt.apirest.core.generator.ITranslatorGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;
import com.bbva.kltt.apirest.generator.test.example.velocity.example.TranslatorGeneratorTestExampleExample;
import com.bbva.kltt.apirest.generator.test.example.velocity.models.TranslatorGeneratorTestExampleModels;
import com.bbva.kltt.apirest.generator.test.example.velocity.rest.TranslatorGeneratorTestExampleRest;
import com.bbva.kltt.apirest.generator.test.example.velocity.top.TopLevelTestExampleConfig;
import com.bbva.kltt.apirest.generator.test.example.velocity.utils.TranslatorGeneratorTestExampleUtils;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorGlobalTestExample extends GeneratorGlobalLanguage
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorGlobalTestExample.class);

    /**
     * Directory for the top level configuration file
     */
    private File topLevelCfgFileDir;

    /**
     * Constructs a new global generator for Java Spring Boot
     *
     * @param genParams         with the parameters for the generation for the TypScript
     * @param parsedInfoHandler with the parsed information handler to generate from
     */
    public GeneratorGlobalTestExample(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(genParams, parsedInfoHandler, ConstantsOutputTestExample.PACKAGE_VM_RESOURCES);
    }

    /**
     * @param translatorType with the translator type
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void generate(final String translatorType) throws APIRestGeneratorException
    {
        // Initialize file directories
        this.initializeFileDirectories();

        // Generate the JavaScript configuration file
        this.generateConfigFileJavaScript();

        // Generate the JavaScript code
        this.generateCodeGeneratorJavaScript();

        // Generate the Spring Boot example project
        this.generateExampleProjectJavaScript();
    }

    /**
     * Initialize the file references and directory references for the rest of the generation.
     * It will also create the required directory paths for the generation.
     *
     * @throws APIRestGeneratorException exception thrown if there is any problem.
     */
    private void initializeFileDirectories() throws APIRestGeneratorException
    {
        GeneratorGlobalTestExample.LOGGER.info("Initializing files and creating directories with base directory {}",
                                              this.getGenerationParams().getCodeGenOutputDirectory());

        // Generate the directory paths
        final String topLevelCfgFilePath = this.getGenerationParams().getCodeGenOutputDirectory();

        // Create the configuration file
        this.topLevelCfgFileDir = new File(topLevelCfgFilePath);
    }

    /**
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateConfigFileJavaScript() throws APIRestGeneratorException
    {
        GeneratorGlobalTestExample.LOGGER.info("Generating configuration file for top level...");

        final IGenerator generator = new TopLevelTestExampleConfig(this.topLevelCfgFileDir,
                                                                  this.getGenerationParams(),
                                                                  this.getParsedInfoHandler());

        generator.generate();

        GeneratorGlobalTestExample.LOGGER.info("Generating configuration file for top level...");
    }

    /**
     * Generate the Spring Boot classes
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateCodeGeneratorJavaScript() throws APIRestGeneratorException
    {
        final ITranslatorGenerator javaScriptUtilsGenerator = new TranslatorGeneratorTestExampleUtils(this.getGenerationParams(),
                                                                                                     this.getParsedInfoHandler());
        javaScriptUtilsGenerator.generate();

        final ITranslatorGenerator javaScriptModelsGenerator = new TranslatorGeneratorTestExampleModels(this.getGenerationParams(),
                                                                                                       this.getParsedInfoHandler());
        javaScriptModelsGenerator.generate();

        final ITranslatorGenerator javaScriptRestGenerator = new TranslatorGeneratorTestExampleRest(this.getGenerationParams(),
                                                                                                   this.getParsedInfoHandler());
        javaScriptRestGenerator.generate();
    }

    /**
     * Generate the JavaScript Example Project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateExampleProjectJavaScript() throws APIRestGeneratorException
    {
        final ITranslatorGenerator javaScriptExampleGenerator = new TranslatorGeneratorTestExampleExample(this.getGenerationParams(),
                                                                                                         this.getParsedInfoHandler());
        javaScriptExampleGenerator.generate();
    }
}
