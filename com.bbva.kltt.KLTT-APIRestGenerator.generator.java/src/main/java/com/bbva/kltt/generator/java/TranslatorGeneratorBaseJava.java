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

package com.bbva.kltt.generator.java;

import com.bbva.kltt.core.generator.TranslatorGeneratorBase;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.generator.util.ConstantsOutputJava;
import com.bbva.kltt.core.util.FilesUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Base class that gives the common methods for every Translator java generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorBaseJava extends TranslatorGeneratorBase
{
	/** Logger of the class */
	private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorBaseJava.class) ;
	
    /** Base directory for source files */
	private File sourceDir ;
	/** Base directory for resource files */
	private File resourceDir ;
    /** Base directory for test files */
	private File testDir ;
    /** Base directory for test resource files */
	private File testResourceDir ;
    /** Directory for the pom file */
	private File pomDir ;

	/**
	 * Constructor of the class
	 * @param translatorType	with the translator type
	 * @param generationParams  with the generation parameters information for java
	 * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
	 * @param generationPackage with the package of the generator module
	 */
    public TranslatorGeneratorBaseJava(final String translatorType,
									   final GenerationParameters generationParams,
									   final ParsedInfoHandler parsedInfoHandler,
									   final String generationPackage)
    {
		super(translatorType, generationParams, parsedInfoHandler, generationPackage) ;
	}
	
	/**
	 * Initialize the file references and directory references for the rest of the generation.
	 * It will also create the required directory paths for the generation.
	 * @throws APIRestGeneratorException exception thrown if there is any problem.
	 */
	public void initializeFileDirectories() throws APIRestGeneratorException
	{
		// Base Path
		final String basePath		  = this.getBasePath() ;
		
		// Logging the creation of files and directories
		LOGGER.info("Creating directories with base [{}]", basePath) ;
		
		// Build the paths
		final String sourcePath 	  = basePath + File.separator + ConstantsOutputJava.GEN_CLASSES_DIR_JAVA ;
		// Resource path
		final String resourcePath 	  = basePath + File.separator + ConstantsOutputJava.GEN_RESOURCES_DIR_JAVA ;
		// Test path
		final String testPath 	 	  = basePath + File.separator + ConstantsOutputJava.GEN_TEST_DIR_JAVA ;
		// Test resources path
		final String testResourcePath = basePath + File.separator + ConstantsOutputJava.GEN_TEST_RESOURCES_DIR_JAVA ;
		// Pom path
		final String pomPath 		  = basePath ;
		
		// Initialize the files
		this.sourceDir 				  = new File(sourcePath) ;
		this.resourceDir 			  = new File(resourcePath) ;
		this.testDir 				  = new File(testPath) ;
		this.testResourceDir 		  = new File(testResourcePath) ;
		this.pomDir 				  = new File(pomPath) ;

		// Make the directories
		FilesUtility.createFullDirectoryTree(this.sourceDir) ;
		FilesUtility.createFullDirectoryTree(this.resourceDir) ;
		FilesUtility.createFullDirectoryTree(this.testDir) ;
		FilesUtility.createFullDirectoryTree(this.testResourceDir) ;
		FilesUtility.createFullDirectoryTree(this.pomDir) ;
        
		// Logging the creation of files and directories
        LOGGER.info("Created directories with base [{}]", basePath) ;
	}
	
	/**
	 * @return the base path of the generated code type
	 */
	public String getBasePath()
	{
		final String codeGenOutputDir = this.getGenerationParams().getCodeGenOutputDirectory() ;
		final String translatorName   = this.getTranslatorType() ;
		final String projectGenName   = this.getParsedInfoHandler().getInfoValues().getTitle() ;
		
		return codeGenOutputDir + File.separator 				   +
			   translatorName   + File.separator 				   +
			   this.getParsedInfoHandler().getBUnit().toUpperCase()+ ConstantsCommon.STRING_HYPHEN +
			   projectGenName 									   + ConstantsCommon.STRING_HYPHEN +
			   this.getTranslatorTypeForProjectName() 			   + 
			   this.getOutputCodeType() ;
	}
	
	/**
	 * @return the translator type for the project name
	 */
	public abstract String getTranslatorTypeForProjectName() ;
	
	/**
	 * @return the code type to be generated (model or rest)
	 */
	public abstract String getOutputCodeType() ;
	
	/**
	 * @return the artifactory POM file name
	 */
	protected String getArtifactoryPomName()
	{
		final String bUnit			= this.getParsedInfoHandler().getBUnit().toUpperCase() ;
		final String projectGenName = this.getParsedInfoHandler().getInfoValues().getTitle() ;
		final String versionNumber	= this.getParsedInfoHandler().getInfoValues().getVersion() ;
		
		return bUnit 		  						  + ConstantsCommon.STRING_HYPHEN +
			   projectGenName 						  + ConstantsCommon.STRING_HYPHEN + 
			   this.getTranslatorTypeForProjectName() +
			   this.getOutputCodeType()				  + ConstantsCommon.STRING_HYPHEN +
			   versionNumber ;
	}

	@Override
	public File getSourceDir()
	{
		return this.sourceDir ;
	}
	
	@Override
	public File getResourceDir()
	{
		return this.resourceDir ;
	}
	
	/**
	 * @return the test directory for generated code
	 */
	public File getTestDir()
	{
		return this.testDir ;
	}
	
	/**
	 * @return the test resource directory for generated code
	 */
	public File getTestResourceDir()
	{
		return this.testResourceDir ;
	}
	
	/**
	 * @return the pom directory for generated code
	 */
	public File getPomDir()
	{
		return this.pomDir ;
	}
}
