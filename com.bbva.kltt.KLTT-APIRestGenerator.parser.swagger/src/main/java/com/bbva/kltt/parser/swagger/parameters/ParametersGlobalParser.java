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

package com.bbva.kltt.parser.swagger.parameters;

import com.bbva.kltt.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.core.parsed_info.parameters.ParametersGlobal;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.parser.ParserUtil;
import com.bbva.kltt.parser.swagger.util.ConstantsInputParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParametersGlobalParser extends ParametersBaseParser
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParametersGlobalParser.class) ;
	
    /** Attribute - Parameters Global */
    private final ParametersGlobal parametersGlobal ;
    
	/**
	 * Public constructor
	 * @param parametersGlobal with the global parameters
	 */
	public ParametersGlobalParser(final ParametersGlobal parametersGlobal)
	{
		super(parametersGlobal.getItemFactory(), parametersGlobal.getConsumes()) ;
		
    	this.parametersGlobal = parametersGlobal ;
    }
	
	/**
	 * Parse the parameters
	 * @param fileContent with the file content
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void parseParameters(final JsonNode fileContent) throws APIRestGeneratorException
	{
		final JsonNode parametersNode = fileContent.get(ConstantsInputParser.SW_COMMON_PARAMS) ;
		if (parametersNode != null && parametersNode.isObject())
		{
			// Validate the key definition parameters objects map
			this.validateKeyDefinition(parametersNode) ;
			
			// Parse the full parameters
			this.parseParametersInternal(parametersNode) ;
		}
	}
	
	/**
	 * Validate the main parameters map to avoid "ref" problems
	 * @param parametersNode    with the root parameters node
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void validateKeyDefinition(final JsonNode parametersNode) throws APIRestGeneratorException
	{
		final Iterator<String> keyParameters = parametersNode.fieldNames() ;
		while (keyParameters.hasNext())
		{
			// Get the key parameter
			final String keyDefinition = keyParameters.next() ;
			
			// Get the attribute node to verify the content (if it has a $ref)
			final JsonNode attributeNode = ParserUtil.getNodeValueJson(ConstantsInputParser.SW_COMMON_PARAMS, parametersNode, keyDefinition, true) ;
			
			// Inspect if there is values of $ref (invalid way to define parameters)
			final String ref = ParserUtil.getNodeValueField(keyDefinition, attributeNode, ConstantsInputParser.SW_COMMON_REF, false) ;

			String errorString = null ;
			if (ref != null && !ref.isEmpty())
			{
				errorString = "The node '" + keyDefinition  + "' contains an invalid '$ref' at the first level" ;
				
				ParametersGlobalParser.LOGGER.error(errorString) ;
		    	throw new APIRestGeneratorException(errorString) ;
			}
		}
	}
	
	/**
	 * Parse the full parameters
	 * @param nodeToInspect with the node to inspect
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void parseParametersInternal(final JsonNode nodeToInspect) throws APIRestGeneratorException
	{
		final Iterator<String> keyDefinitions = nodeToInspect.fieldNames() ;
		while (keyDefinitions.hasNext())
		{
			// Get the key definition (future class name)
			final String keyDefinition 		  = keyDefinitions.next() ;
			
			// Definition node
			final JsonNode definitionNode 	  = nodeToInspect.get(keyDefinition) ;
			
			// Parse and generate a new Parameter with all the children from this keyDefinition
			final Parameter parameter		  = this.parseParameterObject(keyDefinition, definitionNode) ;
			
			// Put the new parameter value
			this.parametersGlobal.addParameter(keyDefinition, parameter) ;
		}
	}
}
