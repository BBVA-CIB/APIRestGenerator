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

package com.bbva.kltt.apirest.parser.swagger.parameters;

import com.bbva.kltt.apirest.parser.swagger.util.ConstantsInputParser;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersGlobal;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersPath;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.parser.ParserUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Iterator;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParametersPathParser extends ParametersBaseParser
{
    /** Attribute - Path key */
    private final String pathKey ;
	
    /** Attribute - Global Parameters */
    private final ParametersGlobal parametersGlobal ;
    
    /** Attribute - Path Parameter */
    private final ParametersPath parametersPath ;
    
	/**
	 * Public constructor
	 * @param parametersGlobal with the global parameters
	 * @param parametersPath   with the path parameters
	 * @param pathKey		   with the path key
	 */
	public ParametersPathParser(final ParametersGlobal parametersGlobal, final ParametersPath parametersPath, final String pathKey)
	{
		super(parametersGlobal.getItemFactory(), parametersPath.getConsumes()) ;
		
		this.pathKey		  = pathKey ;
    	this.parametersGlobal = parametersGlobal ;
    	this.parametersPath   = parametersPath ;
    }
	
	/**
	 * Parse the parameters
	 * @param jsonNode with the JSON content
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void parseParameters(final JsonNode jsonNode) throws APIRestGeneratorException
	{
		final Iterator<JsonNode> iterator = ((ArrayNode)jsonNode).elements() ;
		while (iterator.hasNext())
		{
			// Get the next item from the Array
			final JsonNode arrayNodeItem  = iterator.next() ;

			// This is the identifier for exception keys
			final String idName 		  = this.pathKey + ConstantsCommon.STRING_COLON + ConstantsInputParser.SW_COMMON_PARAMS ;
			
			// Inspect a possible $ref parameter
			Parameter parameter 		  = this.inspectRefParameter(idName, arrayNodeItem) ;
			if (parameter == null)
			{
				// Parse and generate a new Parameter with all the children from this keyDefinition
				parameter		  	      = this.parseParameterObject(idName, arrayNodeItem) ;		
			}
			
			// Put the new parameter value in the specific type (body, request, path, etc.)
			this.parametersPath.addParameter(parameter) ;
		}
	}
	
	/**
	 * Parse a possible $ref parameter
	 * @param anyIdentifierName with the key name
	 * @param arrayNodeItem     with the array node item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Parameter inspectRefParameter(final String anyIdentifierName, final JsonNode arrayNodeItem) throws APIRestGeneratorException
	{
		// Inspect if there is values of $ref (invalid way to define parameters)
		final String reference = ParserUtil.getNodeValueField(anyIdentifierName, arrayNodeItem, ConstantsInputParser.SW_COMMON_REF, false) ;

		Parameter parameter = null ;
		
		if (reference != null && !reference.isEmpty())
		{
			parameter = this.parametersGlobal.getReferenceParameter(reference) ;
		}
		
		return parameter ;
	}
}
