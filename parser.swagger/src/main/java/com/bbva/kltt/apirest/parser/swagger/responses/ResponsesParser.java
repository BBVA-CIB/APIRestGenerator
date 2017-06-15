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

package com.bbva.kltt.apirest.parser.swagger.responses;

import com.bbva.kltt.apirest.parser.swagger.util.ConstantsInputParser;
import com.bbva.kltt.apirest.parser.swagger.util.ItemParser;
import com.bbva.kltt.apirest.core.parsed_info.Responses;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.parser.IResponseFactory;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.parser.ParserUtil;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ResponsesParser extends ItemParser
{
    /** Responses */
    private final Responses responses ;
    
	/** Attribute - Response factory */
	private final IResponseFactory responseFactory ;
    
	/**
	 * Public constructor
	 * @param responses with the response instance
	 */
	public ResponsesParser(final Responses responses)
	{
		super(responses.getItemFactory()) ;
		
		this.responses = responses ;
		this.responseFactory = responses.getResponseFactory() ;
	}

	/**
	 * Parse the responses
	 * @param fileContent with the file content
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void parseResponses(final JsonNode fileContent) throws APIRestGeneratorException
	{
		final JsonNode responsesNode = fileContent.get(ConstantsInputParser.SW_COMMON_RESPONSES) ;
		if (responsesNode != null && responsesNode.isObject())
		{
			// Parse the full responses
			this.parseResponsesInternal(responsesNode) ;
		}
	}
	
	/**
	 * Parse the full parameters
	 * @param nodeToInspect        with the node to inspect
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	protected void parseResponsesInternal(final JsonNode nodeToInspect) throws APIRestGeneratorException
	{
		final Iterator<String> keyResponses = nodeToInspect.fieldNames() ;
		while (keyResponses.hasNext())
		{
			// Get the key response (future class name)
			final String keyResponse 		= keyResponses.next() ;
			
			// Response node
			final JsonNode responseNode 	= nodeToInspect.get(keyResponse) ;
			
			// Parse and generate a new Response with all the children from this keyDefinition
			final Response response		    = this.parseResponseObject(keyResponse, responseNode) ;
			
			this.responses.getResponsesMap().put(keyResponse, response) ;
		}
	}

	/**
	 * @param keyName with the key name
	 * @param keyNode with the key node
	 * @return a new response
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	protected Response parseResponseObject(final String keyName, final JsonNode keyNode) throws APIRestGeneratorException
	{
		// Compulsory 'description' field and optional 'schema' field
		final String alias    		= this.getAlias(keyName, keyNode) ;
		final String description    = ParserUtil.getNodeValueField(keyName, keyNode, ConstantsInputParser.SW_COMMON_DESCR, true) ;
		final JsonNode schemaObject = ParserUtil.getNodeValueJson(keyName, keyNode, ConstantsInputParser.SW_COMMON_SUBSC_SCHE, false) ;
		
		Response response 			= this.responseFactory.createNewResponse(keyName, alias, description, ConstantsInputParser.SW_COMMON_RESPONSES) ;
		Item item 		  			= null ;
		
		if (schemaObject != null && schemaObject.isObject())
		{
			item = this.parseItemObject(keyName, schemaObject) ;
		}
		
		this.responseFactory.addItemIntoResponse(response, item) ;
		
		return response ;
	}
}
