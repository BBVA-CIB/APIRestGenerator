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

package com.bbva.kltt.generator.clientTypescript;

import com.bbva.kltt.core.generator.GeneratorGlobalLanguage;
import com.bbva.kltt.core.generator.IGenerator;
import com.bbva.kltt.core.generator.ITranslatorGenerator;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.generator.clientTypescript.typescript.example.TranslatorGeneratorTypescriptExample;
import com.bbva.kltt.generator.clientTypescript.typescript.models.TranslatorGeneratorTypescriptModels;
import com.bbva.kltt.generator.clientTypescript.typescript.rest.TranslatorGeneratorTypescriptRest;
import com.bbva.kltt.generator.clientTypescript.typescript.top.TopLevelTypescriptConfig;
import com.bbva.kltt.generator.clientTypescript.typescript.utils.TranslatorGeneratorTypescriptUtils;
import com.bbva.kltt.generator.clientTypescript.util.ConstantsOutputTypescript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorGlobalTypescript extends GeneratorGlobalLanguage
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorGlobalTypescript.class);

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
    public GeneratorGlobalTypescript(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(genParams, parsedInfoHandler, ConstantsOutputTypescript.PACKAGE_VM_RESOURCES);
    }

    /**
     * @param translatorType with the translator type
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void generate(final String translatorType) throws APIRestGeneratorException
    {
        // Initialize file directories
        this.initializeFileDirectories();

        // Generate the TypeScript configuration file
        this.generateConfigFileTypeScript();

        // Generate the TypeScript code
        this.generateCodeGeneratorTypeScript();

        // Generate the Spring Boot example project
        this.generateExampleProjectTypeScript();
    }

    /**
     * Initialize the file references and directory references for the rest of the generation.
     * It will also create the required directory paths for the generation.
     *
     * @throws APIRestGeneratorException exception thrown if there is any problem.
     */
    private void initializeFileDirectories() throws APIRestGeneratorException
    {
        GeneratorGlobalTypescript.LOGGER.info("Initializing files and creating directories with base directory {}",
                                              this.getGenerationParams().getCodeGenOutputDirectory());

        // Generate the directory paths
        final String topLevelCfgFilePath = this.getGenerationParams().getCodeGenOutputDirectory();

        // Create the configuration file
        this.topLevelCfgFileDir = new File(topLevelCfgFilePath);
    }

    /**
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateConfigFileTypeScript() throws APIRestGeneratorException
    {
        GeneratorGlobalTypescript.LOGGER.info("Generating configuration file for top level...");

        final IGenerator generator = new TopLevelTypescriptConfig(this.topLevelCfgFileDir,
                                                                  this.getGenerationParams(),
                                                                  this.getParsedInfoHandler());

        generator.generate();

        GeneratorGlobalTypescript.LOGGER.info("Generating configuration file for top level...");
    }

    /**
     * Generate the Spring Boot classes
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateCodeGeneratorTypeScript() throws APIRestGeneratorException
    {
        final ITranslatorGenerator typeScriptUtilsGenerator = new TranslatorGeneratorTypescriptUtils(this.getGenerationParams(),
                                                                                                     this.getParsedInfoHandler());
        typeScriptUtilsGenerator.generate();

        final ITranslatorGenerator typeScriptModelsGenerator = new TranslatorGeneratorTypescriptModels(this.getGenerationParams(),
                                                                                                       this.getParsedInfoHandler());
        typeScriptModelsGenerator.generate();

        final ITranslatorGenerator typeScriptRestGenerator = new TranslatorGeneratorTypescriptRest(this.getGenerationParams(),
                                                                                                   this.getParsedInfoHandler());
        typeScriptRestGenerator.generate();
    }

    /**
     * Generate the TypeScript Example Project
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void generateExampleProjectTypeScript() throws APIRestGeneratorException
    {
        final ITranslatorGenerator typeScriptExampleGenerator = new TranslatorGeneratorTypescriptExample(this.getGenerationParams(),
                                                                                                         this.getParsedInfoHandler());
        typeScriptExampleGenerator.generate();
    }
}
