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
import com.bbva.kltt.apirest.generator.java.velocity.TranslatorGeneratorJavaExampleBase;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorTestExampleExample extends TranslatorGeneratorJavaExampleBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorTestExampleExample.class);

    /**
     * Creates the Spring Translator
     *
     * @param generationParams  with the generator parameters for spring
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorTestExampleExample(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(ConstantsOutputTestExample.MODULE_NAME, generationParams, parsedInfoHandler, ConstantsOutputTestExample.PACKAGE_VM_RESOURCES);
    }

    @Override
    public void generateExampleLauncher(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorTestExampleExample.LOGGER.info("Generating the Spring (server) example launcher class");

        final IGenerator exampleLauncherGenerator = new ExampleLauncherTestExampleGenerator(destDir,
                this.getGenerationParams(),
                this.getParsedInfoHandler());

        exampleLauncherGenerator.generate();

        TranslatorGeneratorTestExampleExample.LOGGER.info("Generated the Spring (server) example launcher class");
    }

    @Override
    public void generateExampleListener(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorTestExampleExample.LOGGER.info("Generating the Spring example listener class");

        final IGenerator exampleListenerGenerator = new ExampleListenerTestExampleGenerator(destDir,
                                                                                             this.getGenerationParams(),
                                                                                             this.getParsedInfoHandler());

        exampleListenerGenerator.generate();

        TranslatorGeneratorTestExampleExample.LOGGER.info("Generated the Spring example listener class");
    }

    @Override
    public void generateExamplePOM(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorTestExampleExample.LOGGER.info("Generating the Spring pom files for the example...");

        // Generate the pom.xml file
        final IGenerator gen = new ExamplePOMTestExampleGenerator(destDir,
                                                                   this.getGenerationParams(),
                                                                   this.getParsedInfoHandler(),
                                                                   ConstantsOutput.EXTENSION_POM);
        gen.generate();

        TranslatorGeneratorTestExampleExample.LOGGER.info("Generated the Spring pom files for the example...");
    }

    @Override
    public void generateSpecificJavaFramework(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorTestExampleExample.LOGGER.info("Generating the Spring Application Properties for the example...");

        // Generate the Spring Application Properties
        final IGenerator gen = new ApplicationPropertiesGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler());
        gen.generate();

        TranslatorGeneratorTestExampleExample.LOGGER.info("Generated the Spring Application Properties for the example...");
    }

	@Override
	public String getGeneratorInfoProjectsChildrenJava()
	{
		return FilesUtility.loadFileContentFromClasspath(ConstantsOutputTestExample.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
