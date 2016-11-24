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

package com.bbva.kltt.generator.clientTypescript.typescript.utils;

import com.bbva.kltt.core.generator.IGenerator;
import com.bbva.kltt.generator.clientTypescript.typescript.TranslatorGeneratorTypescriptUtilsBase;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorTypescriptUtils extends TranslatorGeneratorTypescriptUtilsBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorTypescriptUtils.class);

    /**
     * Creates the utilities generator
     *
     * @param generationParams  with the generator parameters for the TypeScript
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorTypescriptUtils(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(generationParams, parsedInfoHandler);
    }

    @Override
    public void generateUtilities(final File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        TranslatorGeneratorTypescriptUtils.LOGGER.info("Generating the TypeScript utility classes");

        // Generate the random values
        IGenerator randomValuesGenerator = new RandomValuesTypescriptGenerator(destDir,
                                                                               this.getGenerationParams(),
                                                                               this.getParsedInfoHandler());
        randomValuesGenerator.generate();

        // Generate the parser values
        IGenerator parserValuesGenerator = new ParserValuesTypescriptGenerator(destDir,
                                                                               this.getGenerationParams(),
                                                                               this.getParsedInfoHandler());
        parserValuesGenerator.generate();

        // Generate the scheme values
        IGenerator schemesValuesGenerator = new SchemesValuesTypescriptGenerator(destDir,
                                                                                 this.getGenerationParams(),
                                                                                 this.getParsedInfoHandler());
        schemesValuesGenerator.generate();

        TranslatorGeneratorTypescriptUtils.LOGGER.info("Generated the TypeScript utility classes");
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_UTILS;
    }
}
