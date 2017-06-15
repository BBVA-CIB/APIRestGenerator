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

package com.bbva.kltt.apirest.generator.test.example.velocity.example;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.generator.IGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;
import com.bbva.kltt.apirest.generator.test.example.velocity.TranslatorGeneratorTestExampleExampleBase;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorTestExampleExample extends TranslatorGeneratorTestExampleExampleBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorTestExampleExample.class);

    /**
     * Creates the JavaScript Example Translator
     *
     * @param generationParams  with the generator parameters for the JavaScript
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorTestExampleExample(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(generationParams, parsedInfoHandler);
    }

    @Override
    public void generateExampleLauncher(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorTestExampleExample.LOGGER.info("Generating the JavaScript example launcher class");

        final IGenerator exampleLauncherGenerator = new ExampleLauncherTestExampleGenerator(destDir,
                                                                                           this.getGenerationParams(),
                                                                                           this.getParsedInfoHandler());
        exampleLauncherGenerator.generate();

        TranslatorGeneratorTestExampleExample.LOGGER.info("Generated the JavaScript example launcher class");
    }

    @Override
    public void generateExampleCalls(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorTestExampleExample.LOGGER.info("Generating the JavaScript example calls file");

        final IGenerator exampleTesterGenerator = new ExampleTesterTestExampleGenerator(destDir,
                                                                                       this.getGenerationParams(),
                                                                                       this.getParsedInfoHandler());
        exampleTesterGenerator.generate();

        TranslatorGeneratorTestExampleExample.LOGGER.info("Generated the JavaScript example calls file");
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_EXAMPLE;
    }
    
	@Override
	public String getGeneratorInfoProjectsChildrenWeb()
	{
		return FilesUtility.loadFileContentFromClasspath(ConstantsOutputTestExample.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
