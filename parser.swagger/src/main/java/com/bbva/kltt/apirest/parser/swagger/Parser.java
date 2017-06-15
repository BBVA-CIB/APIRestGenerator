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

package com.bbva.kltt.apirest.parser.swagger;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.parsed_info.Definitions;
import com.bbva.kltt.apirest.core.parsed_info.InfoValues;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfo;
import com.bbva.kltt.apirest.core.parsed_info.Responses;
import com.bbva.kltt.apirest.core.parsed_info.RootValues;
import com.bbva.kltt.apirest.core.parsed_info.Scheme;
import com.bbva.kltt.apirest.core.parsed_info.generator_params.GeneratorParams;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersGlobal;
import com.bbva.kltt.apirest.core.parsed_info.paths.Paths;
import com.bbva.kltt.apirest.core.parser.IParser;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.core.util.parser.ParserUtil;
import com.bbva.kltt.apirest.parser.swagger.definitions.DefinitionsParser;
import com.bbva.kltt.apirest.parser.swagger.generator_params.GeneratorParamsParser;
import com.bbva.kltt.apirest.parser.swagger.parameters.ParametersGlobalParser;
import com.bbva.kltt.apirest.parser.swagger.paths.PathsParser;
import com.bbva.kltt.apirest.parser.swagger.responses.ResponsesParser;
import com.bbva.kltt.apirest.parser.swagger.util.ConstantsInputParser;
import com.bbva.kltt.apirest.parser.swagger.util.ConstantsOutputParser;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Parser implements IParser
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class) ;
	
	/** File path */
	private final String filePath ;
	
	/**
	 * Public constructor
	 * @param filePath with the file path
	 */
	public Parser(final String filePath)
	{
		this.filePath = filePath ;
	}
	
	/**
	 * Parse the input schemas and return the parsed information
	 * @return the parsed info
	 * @throws APIRestGeneratorException exception thrown if there is a problem
	 */
	@Override
	public ParsedInfo parse() throws APIRestGeneratorException
	{
		// Generate a new instance of the parsed info
		final ParsedInfo parsedInfo 			= new ParsedInfo() ;
		
		// Deserialize it into JSON
		final JsonNode fileContent 				= ParserUtil.deserializeIntoJson(this.filePath) ;
		
		// Parse the root values
		final RootValues rootValues 			= this.parseRootValues(fileContent) ;
		parsedInfo.setRootValues(rootValues) ;
		
		// Parse the info values
		final InfoValues infoValues 		  	= this.parseInfoValues(fileContent) ;
		parsedInfo.setInfoValues(infoValues) ;
		
		// Parse the definitions
		final Definitions definitions 		    = this.parseDefinitions(fileContent) ;
		parsedInfo.setDefinitions(definitions) ;
		
		// Parse the common parameters
		final ParametersGlobal parametersGlobal = this.parseParameters(fileContent, rootValues, definitions) ;
		parsedInfo.setParametersGlobal(parametersGlobal) ;
		
		// Parse the common responses
		final Responses responses	  			= this.parseResponses(fileContent, definitions) ;
		parsedInfo.setResponses(responses) ;
		
		// Parse the paths
		final Paths paths			  			= this.parsePaths(fileContent, rootValues, definitions, parametersGlobal, responses) ;
		parsedInfo.setPaths(paths) ;
		
		// Parse Generator properties parameters
		final GeneratorParams generatorParams	= this.parseGeneratorParams(fileContent) ;
		parsedInfo.setGeneratorParams(generatorParams) ;
		
		// Logging the parsed result
		if (Parser.LOGGER.isDebugEnabled())
		{
			Parser.LOGGER.debug(parsedInfo.toString()) ;
		}
		
		return parsedInfo ;
	}
	
	/**
	 * Parse the root values
	 * @param fileContent with the file content
	 * @return the root values
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private RootValues parseRootValues(final JsonNode fileContent) throws APIRestGeneratorException
	{
		ParserUtil.getNodeValueField(ConstantsInput.ROOT_JSON_NODE_NAME, fileContent, ConstantsInputParser.SW_MAIN_SCH_SWAGGER, true) ;

		final String host     		= ParserUtil.getNodeValueField(ConstantsInput.ROOT_JSON_NODE_NAME, fileContent, ConstantsInputParser.SW_MAIN_SCH_HOST, true) ;
		final String basePath 		= ParserUtil.getNodeValueField(ConstantsInput.ROOT_JSON_NODE_NAME, fileContent, ConstantsInputParser.SW_MAIN_SCH_BASEPATH, true) ;
		
		// Generate the string with the schemes
		final Set<Scheme> schemeSet = this.getSetOfSchemes(fileContent) ;
		// Generate the set of string with the consumes
		final Set<String> consumesL = ParserUtil.getNodeValueSetField(ConstantsInput.ROOT_JSON_NODE_NAME, fileContent, ConstantsInputParser.SW_COMMON_CONSUMES, true) ;
		// Generate the set of string with the produces
		final Set<String> producesL = ParserUtil.getNodeValueSetField(ConstantsInput.ROOT_JSON_NODE_NAME, fileContent, ConstantsInputParser.SW_COMMON_PRODUCES, true) ;
		
		return new RootValues(host, basePath, schemeSet, consumesL, producesL) ;
	}
	
	/**
	 * @param fileContent with the file content
	 * @return the set of schemes
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Set<Scheme> getSetOfSchemes(final JsonNode fileContent) throws APIRestGeneratorException
	{
		final Set<Scheme> schemesSet	   = new HashSet<Scheme>() ;
		final Set<String> schemesStringSet = ParserUtil.getNodeValueSetField(ConstantsInput.ROOT_JSON_NODE_NAME, fileContent, ConstantsInputParser.SW_COMMON_SCHEMES, true) ;
		
		for (final String schemeString : schemesStringSet)
		{
			schemesSet.add(Scheme.fromStringName(schemeString)) ;
		}
		
		return schemesSet ;
	}
	
	/**
	 * Parse the info values
	 * @param fileContent with the file content
	 * @return the info values
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private InfoValues parseInfoValues(final JsonNode fileContent) throws APIRestGeneratorException
	{
		InfoValues infoValues = null ;
		
		final JsonNode infoNode    = fileContent.get(ConstantsInputParser.SW_MAIN_SCH_INFO) ;
		if (infoNode == null || infoNode.isMissingNode())
		{
			ParserUtil.generateExceptionRequiredField(ConstantsInput.ROOT_JSON_NODE_NAME, ConstantsInputParser.SW_MAIN_SCH_INFO) ;
		}
		
		final String title 		 = ParserUtil.getNodeValueField(ConstantsInputParser.SW_MAIN_SCH_INFO, infoNode, ConstantsInputParser.SW_INFO_SUBSC_TITLE, true) ;
		final String description = ParserUtil.getNodeValueField(ConstantsInputParser.SW_MAIN_SCH_INFO, infoNode, ConstantsInputParser.SW_INFO_SUBSC_DESC, true) ;
		final String version 	 = ParserUtil.getNodeValueField(ConstantsInputParser.SW_MAIN_SCH_INFO, infoNode, ConstantsInputParser.SW_INFO_SUBSC_VERSI, true) ;
		
		// Validate the title (it will be the project name)
		this.validateTitle(title) ;
		
		// New instance of InfoValues
		infoValues = new InfoValues(title, description, version) ;
		
		// Parse the Contact Objects
		this.parseInfoContactObjects(infoNode, infoValues) ;
		
		return infoValues ;
	}
	
	/**
	 * @param title with the title
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void validateTitle(final String title) throws APIRestGeneratorException
	{
		final Pattern pattern = Pattern.compile(ConstantsInput.PATT_TITLE_VALID) ;
		final Matcher matcher = pattern.matcher(title) ;
		if (!matcher.matches())
		{
			final String errorString = "The title is invalid, it must match with the following Regular Expression: " + ConstantsInput.PATT_TITLE_VALID ;
			
			Parser.LOGGER.error(errorString) ;
			throw new APIRestGeneratorException(errorString) ;
		}
	}
	
	/**
	 * Parse the contact values
	 * @param infoNode   with the contact node
	 * @param infoValues with the info values
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void parseInfoContactObjects(final JsonNode infoNode, final InfoValues infoValues) throws APIRestGeneratorException
	{
		final JsonNode contactNode = ParserUtil.getNodeValueJson(ConstantsInputParser.SW_MAIN_SCH_INFO, infoNode, ConstantsInputParser.SW_INFO_SUBSC_CONTAC, true) ;
		
		final String name  		   = ParserUtil.getNodeValueField(ConstantsInputParser.SW_INFO_SUBSC_CONTAC, contactNode, ConstantsInputParser.SW_INFO_CONT_SUBSC_N, true) ;
		final String url   		   = ParserUtil.getNodeValueField(ConstantsInputParser.SW_INFO_SUBSC_CONTAC, contactNode, ConstantsInputParser.SW_INFO_CONT_SUBSC_U, true) ;
		final String email 		   = ParserUtil.getNodeValueField(ConstantsInputParser.SW_INFO_SUBSC_CONTAC, contactNode, ConstantsInputParser.SW_INFO_CONT_SUBSC_E, true) ;
		
		infoValues.addContactValues(name, url, email) ;
	}
	
	/**
	 * Parse the definition
	 * @param fileContent with the file content
	 * @return the definitions
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Definitions parseDefinitions(final JsonNode fileContent) throws APIRestGeneratorException
	{
		// Definitions
		final Definitions definitions     = new Definitions() ;
		
		// Parse the definitions
		final DefinitionsParser defParser = new DefinitionsParser(definitions) ;
		defParser.parseDefinitions(fileContent) ;
		
		return definitions ;
	}

	/**
	 * Parse the parameters
	 * @param fileContent       with the file content
	 * @param rootValues        with the root values
	 * @param globalDefinitions with the global definitions
	 * @return the global parameters
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private ParametersGlobal parseParameters(JsonNode fileContent, final RootValues rootValues, final Definitions globalDefinitions) throws APIRestGeneratorException
	{
		// Parameters
		final ParametersGlobal parameters = new ParametersGlobal(globalDefinitions, rootValues.getConsumes()) ;
		
		// Parse the parameters
		final ParametersGlobalParser parametersParser = new ParametersGlobalParser(parameters) ;
		parametersParser.parseParameters(fileContent) ;
		
		return parameters ;
	}

	/**
	 * Parse the responses
	 * @param fileContent 		with the file content
	 * @param globalDefinitions with the global definitions
	 * @return the responses
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Responses parseResponses(JsonNode fileContent, final Definitions globalDefinitions) throws APIRestGeneratorException
	{
		// Responses
		final Responses responses = new Responses(globalDefinitions) ;
		
		// Parse the responses
		final ResponsesParser responsesParser = new ResponsesParser(responses) ;
		responsesParser.parseResponses(fileContent) ;
		
		return responses ;
	}

	/**
	 * Parse the responses
	 * @param fileContent 	   with the file content
	 * @param definitions 	   with the definitions
	 * @param parametersGlobal with the global parameters
	 * @param responses   	   with the responses
	 * @return the paths
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Paths parsePaths(final JsonNode fileContent,
							 final RootValues rootValues,
							 final Definitions definitions,
							 final ParametersGlobal parametersGlobal,
							 final Responses responses) throws APIRestGeneratorException
	{
		// Paths
		final Paths paths = new Paths(rootValues, definitions, parametersGlobal, responses) ;
		
		// Parse the paths
		final PathsParser pathsParser = new PathsParser(paths) ;
		pathsParser.parsePaths(fileContent) ;
		
		return paths ;
	}
	
	/**
	 * Parse the Generator properties values
	 * @param fileContent with the file content
	 * @return the Generator properties parameters
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private GeneratorParams parseGeneratorParams(final JsonNode fileContent) throws APIRestGeneratorException
	{
		// Generator properties Params
		final GeneratorParams generatorParams = new GeneratorParams() ;
		
		// Parse the Generator properties params
		final GeneratorParamsParser generatorParamsParser = new GeneratorParamsParser(generatorParams) ;
		generatorParamsParser.parseGeneratorParams(fileContent) ;
		
		return generatorParams ;
	}
	
	@Override
	public String getParserProjectInfo()
	{
		return FilesUtility.loadFileContentFromClasspath(ConstantsOutputParser.PROJECT_NAME + 
				  										 ConstantsCommon.STRING_DOT   		+ ConstantsOutput.EXTENSION_PROPERTIES) ;
	}
}
