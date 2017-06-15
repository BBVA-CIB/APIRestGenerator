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

package com.bbva.kltt.apirest.generator.server.spring.velocity.example;

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
import com.bbva.kltt.apirest.generator.server.spring.util.ConstantsOutputServerSpring;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorServerSpringExample extends TranslatorGeneratorJavaExampleBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorServerSpringExample.class);

    /**
     * Creates the Spring Translator
     *
     * @param generationParams  with the generator parameters for spring
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorServerSpringExample(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(ConstantsOutputServerSpring.MODULE_NAME, generationParams, parsedInfoHandler, ConstantsOutputServerSpring.PACKAGE_VM_RESOURCES);
    }

    @Override
    public void generateExampleLauncher(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorServerSpringExample.LOGGER.info("Generating the Spring (server) example launcher class");

        final IGenerator exampleLauncherGenerator = new ExampleLauncherServerSpringGenerator(destDir,
                this.getGenerationParams(),
                this.getParsedInfoHandler());

        exampleLauncherGenerator.generate();

        TranslatorGeneratorServerSpringExample.LOGGER.info("Generated the Spring (server) example launcher class");
    }

    @Override
    public void generateExampleListener(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorServerSpringExample.LOGGER.info("Generating the Spring example listener class");

        final IGenerator exampleListenerGenerator = new ExampleListenerServerSpringGenerator(destDir,
                                                                                             this.getGenerationParams(),
                                                                                             this.getParsedInfoHandler());

        exampleListenerGenerator.generate();

        TranslatorGeneratorServerSpringExample.LOGGER.info("Generated the Spring example listener class");
    }

    @Override
    public void generateExamplePOM(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorServerSpringExample.LOGGER.info("Generating the Spring pom files for the example...");

        // Generate the pom.xml file
        final IGenerator gen = new ExamplePOMServerSpringGenerator(destDir,
                                                                   this.getGenerationParams(),
                                                                   this.getParsedInfoHandler(),
                                                                   ConstantsOutput.EXTENSION_POM);
        gen.generate();

        TranslatorGeneratorServerSpringExample.LOGGER.info("Generated the Spring pom files for the example...");
    }

    @Override
    public void generateSpecificJavaFramework(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorServerSpringExample.LOGGER.info("Generating the Spring Application Properties for the example...");

        // Generate the Spring Application Properties
        final IGenerator gen = new ApplicationPropertiesGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler());
        gen.generate();

        TranslatorGeneratorServerSpringExample.LOGGER.info("Generated the Spring Application Properties for the example...");
    }

	@Override
	public String getGeneratorInfoProjectsChildrenJava()
	{
		return FilesUtility.loadFileContentFromClasspath(ConstantsOutputServerSpring.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
