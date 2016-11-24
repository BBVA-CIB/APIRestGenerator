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

import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.FilesUtility;
import com.bbva.kltt.generator.clientTypescript.typescript.interfaces.IOperationsExampleTypescript;
import com.bbva.kltt.generator.clientTypescript.util.ConstantsOutputTypescript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Base class that gives the common methods for every Translator example generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorTypescriptExampleBase extends TranslatorGeneratorBaseTypescript implements IOperationsExampleTypescript
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorTypescriptExampleBase.class);

    /**
     * Constructor of the class
     *
     * @param generationParams  with the generation parameters information for the TypeScript
     * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
     */
    public TranslatorGeneratorTypescriptExampleBase(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(ConstantsOutputTypescript.MODULE_NAME, generationParams, parsedInfoHandler);
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

        TranslatorGeneratorTypescriptExampleBase.LOGGER.info("Initializing files and creating directories with base [{}] for translator [{}] for the examples",
                                                             this.getGenerationParams().getCodeGenOutputDirectory(),
                                                             translatorName);
        // Build the paths
        final String sourcePath = this.getGenerationParams().getCodeGenOutputDirectory() + File.separator +
                                  translatorName;
        // Resource path
        final String resourcePath = this.getGenerationParams().getCodeGenOutputDirectory() + File.separator +
                                    translatorName + File.separator +
                                    ConstantsOutputTypescript.GEN_RESOURCES_DIR_TYPESCRIPT;
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
        // Generate the example launcher
        this.generateExampleLauncher(this.getSourceDir());

        // Generate the example calls
        this.generateExampleCalls(this.getSourceDir());
    }
}
