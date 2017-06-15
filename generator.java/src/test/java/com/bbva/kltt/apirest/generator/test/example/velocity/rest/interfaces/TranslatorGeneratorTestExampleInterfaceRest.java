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

package com.bbva.kltt.apirest.generator.test.example.velocity.rest.interfaces;

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
import com.bbva.kltt.apirest.generator.java.velocity.TranslatorGeneratorJavaRestInterfaceBase;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorTestExampleInterfaceRest extends TranslatorGeneratorJavaRestInterfaceBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorTestExampleInterfaceRest.class);

    /**
     * Creates the Spring Rest Translator
     *
     * @param generationParams  with the generator parameters for spring
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     * @param packageName		with the package name
     */
    public TranslatorGeneratorTestExampleInterfaceRest(final GenerationParameters generationParams,
                                                        final ParsedInfoHandler parsedInfoHandler,
                                                        final String packageName)
    {
        super(ConstantsOutputTestExample.MODULE_NAME, generationParams, parsedInfoHandler, packageName);
    }

    @Override
    public void generateRestHandler(final File destDir) throws APIRestGeneratorException
    {
        // Generate the rest handler class
        TranslatorGeneratorTestExampleInterfaceRest.LOGGER.info("Generating Spring (server) Rest - Two interfaces: handler and listener");

        // Rest Handler Listener class
        final IGenerator restListenerInterfaceGenerator = new RestListenerTestExampleInterfaceGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler());
        restListenerInterfaceGenerator.generate();

        // Rest Handler Generator
        final IGenerator restHandlerInterfaceGenerator = new RestHandlerTestExampleInterfaceGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler());
        restHandlerInterfaceGenerator.generate();

        TranslatorGeneratorTestExampleInterfaceRest.LOGGER.info("Generated Spring (server) Rest - Two interfaces: handler and listener");
    }

    @Override
    public void generateRestHandlerUtils(final File destDir) throws APIRestGeneratorException
    {
        // Nothing to be implemented
    }

    @Override
    public void generatePOM(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorTestExampleInterfaceRest.LOGGER.info("Generating Spring (server) pom files for the rest interface project");

        // Generate the pom.xml file
        final IGenerator normalPom = new RestPOMTestExampleInterfaceGenerator(destDir,
                                                                               this.getGenerationParams(),
                                                                               this.getParsedInfoHandler(),
                                                                               ConstantsOutput.EXTENSION_POM);
        normalPom.generate();

        // Generate the pom file again but using the name that correspond to the pom stored in nexus / artifactory
        final IGenerator artifactoryPom = new RestPOMTestExampleInterfaceGenerator(destDir,
                                                                                    this.getGenerationParams(),
                                                                                    this.getParsedInfoHandler(),
                                                                                    this.getArtifactoryPomName());
        artifactoryPom.generate();

        TranslatorGeneratorTestExampleInterfaceRest.LOGGER.info("Generated Spring (server) pom files for the rest interface project");
    }

    @Override
    public void generateSpecificJavaFramework(final File destDir) throws APIRestGeneratorException
    {
        // Nothing specific to implement with this Spring MVC framework
    }
    
	@Override
	public String getGeneratorInfoProjectsChildrenJava()
	{
		return FilesUtility.loadFileContentFromClasspath(ConstantsOutputTestExample.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
