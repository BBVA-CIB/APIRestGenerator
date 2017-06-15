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

package com.bbva.kltt.apirest.generator.client.jaxrs.velocity.rest.impl;

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
import com.bbva.kltt.apirest.generator.client.jaxrs.util.ConstantsOutputClientJaxrs;
import com.bbva.kltt.apirest.generator.java.velocity.TranslatorGeneratorJavaRestImplBase;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorClientJaxrsImplRest extends TranslatorGeneratorJavaRestImplBase
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorClientJaxrsImplRest.class);

    /**
     * Creates the JAX-RS Rest Translator
     *
     * @param generationParams  with the generator parameters for jaxrs
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
    public TranslatorGeneratorClientJaxrsImplRest(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(ConstantsOutputClientJaxrs.MODULE_NAME, generationParams, parsedInfoHandler, ConstantsOutputClientJaxrs.PACKAGE_VM_RESOURCES);
    }

    @Override
    public void generateRestHandler(final File destDir) throws APIRestGeneratorException
    {
        // Generate the rest handler class
        TranslatorGeneratorClientJaxrsImplRest.LOGGER.info("Generating JAXRS (client) Rest handler implementation");

        // Rest Handler Generator - Implementation
        final IGenerator restHandlerImplementGenerator = new RestHandlerClientJaxrsImplGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler());
        restHandlerImplementGenerator.generate();

        TranslatorGeneratorClientJaxrsImplRest.LOGGER.info("Generated JAXRS (client) Rest handler implementation");
    }

    @Override
    public void generateRestHandlerTests(final File destDir) throws APIRestGeneratorException
    {
//		// Generate the rest handler test class
//		TranslatorGeneratorClientJaxrsImplRest.LOGGER.info("Generating JAXRS (client) Rest handler test classes") ;
//		
//		// Rest Handler Listener test class
//		final IGenerator restListenerGeneratorTest = new RestListenerServerJaxrsImplGeneratorTest(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
//		restListenerGeneratorTest.generate() ;
//		
//		// Rest Handler test Generator
//		final IGenerator restHandlerGeneratorTest = new RestHandlerServerJaxrsImplGeneratorTest(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
//		restHandlerGeneratorTest.generate() ;
//		
//		TranslatorGeneratorClientJaxrsImplRest.LOGGER.info("Generated JAXRS (client) Rest handler test classes") ;
    }

    @Override
    public void generatePOM(final File destDir) throws APIRestGeneratorException
    {
        TranslatorGeneratorClientJaxrsImplRest.LOGGER.info("Generating JAXRS (client) pom files for the rest implementation");

        // Generate the pom.xml file
        final IGenerator normalPom = new RestPOMClientJaxrsImplGenerator(destDir,
                                                                         this.getGenerationParams(),
                                                                         this.getParsedInfoHandler(),
                                                                         ConstantsOutput.EXTENSION_POM);
        normalPom.generate();

        // Generate the pom file again but using the name that correspond to the pom stored in nexus / artifactory
        final IGenerator artifactoryPom = new RestPOMClientJaxrsImplGenerator(destDir,
                                                                              this.getGenerationParams(),
                                                                              this.getParsedInfoHandler(),
                                                                              this.getArtifactoryPomName());
        artifactoryPom.generate();

        TranslatorGeneratorClientJaxrsImplRest.LOGGER.info("Generated JAXRS (client) pom files for the rest implementation");
    }

    @Override
    public void generateSpecificJavaFramework(final File destDir) throws APIRestGeneratorException
    {
        // Nothing to be implemented
    }
    
    @Override
	public String getGeneratorInfoProjectsChildrenJava()
	{
    	return FilesUtility.loadFileContentFromClasspath(ConstantsOutputClientJaxrs.PROJECT_NAME + ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
