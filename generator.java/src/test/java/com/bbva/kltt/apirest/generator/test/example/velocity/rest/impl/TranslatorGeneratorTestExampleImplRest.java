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

package com.bbva.kltt.apirest.generator.test.example.velocity.rest.impl;

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
import com.bbva.kltt.apirest.generator.java.velocity.TranslatorGeneratorJavaRestImplBase;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;
import com.bbva.kltt.apirest.generator.test.example.velocity.rest.impl.test.RestHandlerTestExampleImplGeneratorT;
import com.bbva.kltt.apirest.generator.test.example.velocity.rest.impl.test.RestListenerTestExampleImplGeneratorT;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorTestExampleImplRest extends TranslatorGeneratorJavaRestImplBase
{
    /** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorTestExampleImplRest.class) ;
    
    /**
     * Creates the Spring Rest Translator
     * @param generationParams  with the generator parameters for spring
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     */
	public TranslatorGeneratorTestExampleImplRest(final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
	{
		super(ConstantsOutputTestExample.MODULE_NAME, generationParams, parsedInfoHandler, ConstantsOutputTestExample.PACKAGE_VM_RESOURCES);
	}
	
	@Override
	public void generateRestHandler(final File destDir) throws APIRestGeneratorException
	{
		// Generate the rest handler class
		TranslatorGeneratorTestExampleImplRest.LOGGER.info("Generating Spring (server) Rest handler implementation") ;

		// Rest Handler Generator
		final IGenerator restHandlerGenerator = new RestHandlerTestExampleImplGenerator(destDir,
				                                                                         this.getGenerationParams(),
				                                                                         this.getParsedInfoHandler()) ;
		restHandlerGenerator.generate() ;
		
		TranslatorGeneratorTestExampleImplRest.LOGGER.info("Generated Spring (server) Rest handler implementation") ;
	}
	
	@Override
	public void generateRestHandlerTests(final File destDir) throws APIRestGeneratorException
	{
		// Generate the rest handler test class
		TranslatorGeneratorTestExampleImplRest.LOGGER.info("Generating Spring (server) Rest handler test classes") ;
		
		// Rest Handler Listener test class
		final IGenerator restListenerGeneratorTest = new RestListenerTestExampleImplGeneratorT(destDir,
				                                                                                   this.getGenerationParams(),
				                                                                                   this.getParsedInfoHandler()) ;
		restListenerGeneratorTest.generate() ;
		
		// Rest Handler test Generator
		final IGenerator restHandlerGeneratorTest = new RestHandlerTestExampleImplGeneratorT(destDir,
				                                                                                 this.getGenerationParams(),
				                                                                                 this.getParsedInfoHandler()) ;
		restHandlerGeneratorTest.generate() ;
		
		TranslatorGeneratorTestExampleImplRest.LOGGER.info("Generated Spring (server) Rest handler test classes") ;
	}
	
	@Override
	public void generatePOM(final File destDir) throws APIRestGeneratorException
	{
		TranslatorGeneratorTestExampleImplRest.LOGGER.info("Generating Spring (server) pom files for the rest implementation") ;

		// Generate the pom.xml file
		final IGenerator normalPom = new RestPOMTestExampleImplGenerator(destDir,
				                                                          this.getGenerationParams(),
				                                                          this.getParsedInfoHandler(),
				                                                          ConstantsOutput.EXTENSION_POM) ;
		normalPom.generate();

		// Generate the pom file again but using the name that correspond to the pom stored in nexus / artifactory
		final IGenerator artifactoryPom = new RestPOMTestExampleImplGenerator(destDir,
				                                                               this.getGenerationParams(),
				                                                               this.getParsedInfoHandler(),
				                                                               this.getArtifactoryPomName()) ;
		artifactoryPom.generate();
		
		TranslatorGeneratorTestExampleImplRest.LOGGER.info("Generated Spring (server) pom files for the rest implementation") ;
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
