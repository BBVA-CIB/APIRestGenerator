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

package com.bbva.kltt.apirest.core.generator;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parser.IParser;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.core.util.parser.ParserUtil;

/**
 * Base class that gives the common methods for every Translator generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class TranslatorGeneratorBase implements ITranslatorGenerator
{
	/** Logger of the class */
	private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorBase.class) ;
	
	/** Translator type */
	private final String translatorType ;

	/** Global generation parameters */
	private final GenerationParameters generationParams ;

	/** Full input parsed information for generator handler */
	private final ParsedInfoHandler parsedInfoHandler ;

	/** Path to top generation directory **/
	private final String generationPackage;

	/**
	 * Constructor of the class
	 * @param translatorType    with the translator type
	 * @param generationParams  with the generation parameters
	 * @param parsedInfoHandler with the parsed information handler from the schemes that will be used to generate
	 * @param generationPackage with the generation package
	 */
	public TranslatorGeneratorBase(final String translatorType,
								   final GenerationParameters generationParams,
								   final ParsedInfoHandler parsedInfoHandler,
								   final String generationPackage)
	{
		this.translatorType	   = translatorType ;
		this.generationParams  = generationParams ;
		this.parsedInfoHandler = parsedInfoHandler ;
		this.generationPackage = generationPackage ;
	}

	/**
	 * Entry point for the generation, it will generate all the translators, tests and auxiliary classes
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	@Override
	public void generate() throws APIRestGeneratorException
	{
		// Initialize all directories and paths
		this.initializeFileDirectories() ;

		// Generate the specific implementation (models or rest)
		this.generateSpecific() ;

		// Add the source specification file
		this.addSourceSpecificationFile(this.getResourceDir()) ;

		// Add the parsed information file
		this.addParsedInfoFile(this.getResourceDir()) ;

		// Add the generator information (project version)
		this.addGeneratorInfo(this.getResourceDir()) ;
	}

	@Override
	public String getTranslatorType()
	{
		return this.translatorType ;
	}

	@Override
	public String getGenerationPackage()
	{
		return this.generationPackage ;
	}

	@Override
	public GenerationParameters getGenerationParams()
	{
		return this.generationParams ;
	}

	@Override
	public void addSourceSpecificationFile(final File destDir) throws APIRestGeneratorException
	{
		final File sourceSpecFile = new File(this.getGenerationParams().getSpecificationFilePath()) ;
		final File destSpecFile   = new File(destDir, FilesUtility.getFileNameOfPath(this.getGenerationParams().getSpecificationFilePath())) ;

		FilesUtility.copyFileUsingStream(sourceSpecFile, destSpecFile) ;
	}

	@Override
	public void addParsedInfoFile(final File destDir) throws APIRestGeneratorException
	{
		final InputStream parsedInfoIS = new ByteArrayInputStream(this.getParsedInfoHandler().toString().getBytes()) ;
		final File destSpecFile   	   = new File(destDir, FilesUtility.getFileNameForJson(this.getGenerationParams().getSpecificationFilePath())) ;

		FilesUtility.copyFileUsingStream(parsedInfoIS, destSpecFile) ;
	}

	@Override
	public void addGeneratorInfo(final File destDir) throws APIRestGeneratorException
	{
		final InputStream generatorInfoIS = new ByteArrayInputStream(this.getGeneratorInfoProjects().getBytes()) ;
		final File destGeneratorInfo	  = new File(destDir, ConstantsOutput.GENERATOR_INFO_FILE_NAME) ;

		FilesUtility.copyFileUsingStream(generatorInfoIS, destGeneratorInfo) ;
	}
	
	/**
	 * @return the generator info projects
	 */
	private String getGeneratorInfoProjects()
	{
		final StringBuilder outcome = new StringBuilder() ;
		
		final String coreInfo      = FilesUtility.loadFileContentFromClasspath(ConstantsOutput.PROJECT_NAME + 
																		  	   ConstantsCommon.STRING_DOT   + ConstantsOutput.EXTENSION_PROPERTIES) ;
		outcome.append(coreInfo) ;
		outcome.append(ConstantsCommon.STRING_CARRIAGE_RETURN) ;
		
		String parserInfo 	       = null ;
		
		try
		{
			IParser parser = ParserUtil.getSchemasParser(this.generationParams) ;
			parserInfo 	   = parser.getParserProjectInfo() ;
		}
		catch (APIRestGeneratorException apiRestGeneratorExc)
		{
			parserInfo = "Unable to get the parser project info due to " + apiRestGeneratorExc.getMessage() ;
			
			TranslatorGeneratorBase.LOGGER.warn(parserInfo, apiRestGeneratorExc) ;
		}
		
		outcome.append(parserInfo) ;
		outcome.append(ConstantsCommon.STRING_CARRIAGE_RETURN) ;
		
		final String childrenProjectsInfo = this.getGeneratorInfoProjectsChildren() ;
		outcome.append(childrenProjectsInfo) ;
		
		return outcome.toString() ;
	}

	/**
	 * @return the generator info projects children
	 */
	public abstract String getGeneratorInfoProjectsChildren() ;

	/**
	 * @return the parsedInfoHandler
	 */
	public ParsedInfoHandler getParsedInfoHandler()
	{
		return this.parsedInfoHandler ;
	}

	/**
	 * Initialize the file references and directory references for the rest of the generation.
	 * It will also create the required directory paths for the generation.
	 * @throws APIRestGeneratorException exception thrown if there is any problem.
	 */
	public abstract void initializeFileDirectories() throws APIRestGeneratorException ;

	/**
	 * Generate the specific code depending on the type (models or rest)
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public abstract void generateSpecific() throws APIRestGeneratorException ;
}
