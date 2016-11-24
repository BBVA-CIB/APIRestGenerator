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

package com.bbva.kltt.generator.clientTypescript.typescript.example;

import com.bbva.kltt.core.generator.IGenerator;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.clientTypescript.typescript.TranslatorGeneratorTypescriptExampleBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorTypescriptExample extends TranslatorGeneratorTypescriptExampleBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorTypescriptExample.class);

    /**
     * Creates the TypeScript Example Translator
     *
     * @param generationParams  with the generator parameters for the TypeScript
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorTypescriptExample(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(generationParams, parsedInfoHandler);
    }

    @Override
    public void generateExampleLauncher(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorTypescriptExample.LOGGER.info("Generating the TypeScript example launcher class");

        final IGenerator exampleLauncherGenerator = new ExampleLauncherTypescriptGenerator(destDir,
                                                                                           this.getGenerationParams(),
                                                                                           this.getParsedInfoHandler());
        exampleLauncherGenerator.generate();

        TranslatorGeneratorTypescriptExample.LOGGER.info("Generated the TypeScript example launcher class");
    }

    @Override
    public void generateExampleCalls(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorTypescriptExample.LOGGER.info("Generating the TypeScript example calls file");

        final IGenerator exampleTesterGenerator = new ExampleTesterTypescriptGenerator(destDir,
                                                                                       this.getGenerationParams(),
                                                                                       this.getParsedInfoHandler());
        exampleTesterGenerator.generate();

        TranslatorGeneratorTypescriptExample.LOGGER.info("Generated the TypeScript example calls file");
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_EXAMPLE;
    }
}
