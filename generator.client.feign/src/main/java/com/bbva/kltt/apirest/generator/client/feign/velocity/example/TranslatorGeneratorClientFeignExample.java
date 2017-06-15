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

package com.bbva.kltt.apirest.generator.client.feign.velocity.example;

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
import com.bbva.kltt.apirest.generator.client.feign.util.ConstantsOutputClientFeign;
import com.bbva.kltt.apirest.generator.java.velocity.TranslatorGeneratorJavaExampleBase;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorClientFeignExample extends TranslatorGeneratorJavaExampleBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorClientFeignExample.class);

    /**
     * Creates the Feign Translator
     *
     * @param generationParams  with the generator parameters for Feign
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorClientFeignExample(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(ConstantsOutputClientFeign.MODULE_NAME, generationParams, parsedInfoHandler, ConstantsOutputClientFeign.PACKAGE_VM_RESOURCES);
    }

    @Override
    public void generateExampleLauncher(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorClientFeignExample.LOGGER.info("Generating the Feign (client) example launcher class");

        final IGenerator exampleLauncherGenerator = new ExampleLauncherClientFeignGenerator(destDir,
                this.getGenerationParams(),
                this.getParsedInfoHandler());

        exampleLauncherGenerator.generate();

        TranslatorGeneratorClientFeignExample.LOGGER.info("Generated the Feign (client) example launcher class");
    }

    @Override
    public void generateExampleListener(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorClientFeignExample.LOGGER.info("Generating the Feign example listener class");

        final IGenerator exampleListenerGenerator = new ExampleListenerClientFeignGenerator(destDir,
                                                                                             this.getGenerationParams(),
                                                                                             this.getParsedInfoHandler());

        exampleListenerGenerator.generate();
        
        TranslatorGeneratorClientFeignExample.LOGGER.info("Generated the Feign example listener class");
    }

    @Override
    public void generateExamplePOM(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorClientFeignExample.LOGGER.info("Generating the Feign pom files for the example...");

        // Generate the pom.xml file
        final IGenerator gen = new ExamplePOMClientFeignGenerator(destDir,
                                                                   this.getGenerationParams(),
                                                                   this.getParsedInfoHandler(),
                                                                   ConstantsOutput.EXTENSION_POM);
        gen.generate();

        TranslatorGeneratorClientFeignExample.LOGGER.info("Generated the Feign pom files for the example...");
    }

    @Override
    public void generateSpecificJavaFramework(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorClientFeignExample.LOGGER.info("Generating the Feign Application Properties for the example...");

        // Generate the Feign Application Properties
        final IGenerator applicationProperties = new ApplicationPropertiesClientFeignGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler());
        applicationProperties.generate();
        
        TranslatorGeneratorClientFeignExample.LOGGER.info("Generated the Feign Application Properties for the example...");
    }
    
    @Override
	public String getGeneratorInfoProjectsChildrenJava()
	{
    	return FilesUtility.loadFileContentFromClasspath(ConstantsOutputClientFeign.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
