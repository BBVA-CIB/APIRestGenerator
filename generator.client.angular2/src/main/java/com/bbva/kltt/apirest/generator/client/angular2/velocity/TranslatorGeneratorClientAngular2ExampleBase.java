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

package com.bbva.kltt.apirest.generator.client.angular2.velocity;

import java.io.File;

import com.bbva.kltt.apirest.generator.client.angular2.velocity.interfaces.IOperationsExampleClientAngular2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;

/**
 * Base class that gives the common methods for every Translator example generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorClientAngular2ExampleBase extends TranslatorGeneratorBaseClientAngular2 implements IOperationsExampleClientAngular2
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorClientAngular2ExampleBase.class);

    /**
     * Constructor of the class
     *
     * @param generationParams  with the generation parameters information for the Angular2
     * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
     */
    public TranslatorGeneratorClientAngular2ExampleBase(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(ConstantsOutputClientAngular2.MODULE_NAME, generationParams, parsedInfoHandler);
    }

    /**
     * This method is override because the example files must be generated in the root folder
     * It will also create the required directory paths for the generation.
     *
     * @throws APIRestGeneratorException exception thrown if there is any problem.
     */
    @Override
    public void initializeFileDirectories() throws APIRestGeneratorException
    {
        // Get the name of the translator that is going to be generated
        final String translatorName = this.getTranslatorType();

        TranslatorGeneratorClientAngular2ExampleBase.LOGGER.info("Initializing files and creating directories with base [{}] for translator [{}] for the examples",
                                                             this.getGenerationParams().getCodeGenOutputDirectory(),
                                                             translatorName);
        // Build the paths
        final String sourcePath = this.getGenerationParams().getCodeGenOutputDirectory() + File.separator +
                                  translatorName;
        // Resource path
        final String resourcePath = this.getGenerationParams().getCodeGenOutputDirectory() + File.separator +
                                    translatorName + File.separator +
                                    ConstantsOutputClientWeb.GEN_RESOURCES_DIR_WEB;
        // Initialize the files
        this.setSourceDir(new File(sourcePath));
        this.setResourceDir(new File(resourcePath));

        // Make the directories
        FilesUtility.createFullDirectoryTree(this.getSourceDir());
        FilesUtility.createFullDirectoryTree(this.getResourceDir());
    }

    /**
     * Generate the specific code
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void generateSpecific() throws APIRestGeneratorException
    {
        // Generate the example configuration
        this.generateExampleConfiguration(this.getSourceDir());
    	
        // Generate the example launcher
        this.generateExampleLauncher(this.getSourceDir());

        // Generate the example calls
        this.generateExampleCalls(this.getSourceDir());
    }
}
