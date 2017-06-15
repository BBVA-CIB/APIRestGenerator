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

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.parsed_info.Responses;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.bbva.kltt.apirest.core.util.parser.ParserUtil;
import com.bbva.kltt.apirest.parser.swagger.util.ConstantsInputParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ResponsesParserOperation extends ResponsesParser
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsesParserOperation.class) ;
    
    /** Constant - Path String Logger */
    private static final String PATH_STRING_LOGGER = "The path '" ;
	
    /** Attribute - Path value */
    private final String pathValue ;
    
    /** Attribute - Path operation */
    private final String pathOp ;
    
    /** Attribute - Global Responses */
    private final Responses globalResponses ;
    
    /** Attribute - Local Responses */
    private final Responses localResponses ;
    
	/**
	 * Public constructor
	 * @param pathValue 	  with the path value
	 * @param pathOp    	  with the path operation
	 * @param globalResponses with the global responses
	 * @param localResponses  with the local response
	 */
	public ResponsesParserOperation(final String pathValue, final String pathOp, final Responses globalResponses, final Responses localResponses)
	{
		super(localResponses) ;
		
		this.pathValue 		 = pathValue ;
		this.pathOp 		 = pathOp ;
		this.globalResponses = globalResponses ;
		this.localResponses  = localResponses ;
	}
	
	/**
	 * Parse the responses
	 * @param inspectedNode with the inspected node
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void parseResponses(final JsonNode inspectedNode) throws APIRestGeneratorException
	{
		// Response node
		final JsonNode responsesNode = inspectedNode.get(ConstantsInputParser.SW_COMMON_RESPONSES) ;
		
		// Validate Response Array Node
		this.validateResponseArrayNode(responsesNode) ;
		
		// Parse the full responses
		this.parseResponsesInternal((ArrayNode)responsesNode) ;
	}
	
	/**
	 * Validate the response array node
	 * @param responsesNode with the response node
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void validateResponseArrayNode(final JsonNode responsesNode) throws APIRestGeneratorException
	{
		final boolean isResponseArrayNode = responsesNode != null && responsesNode.isArray() ;
		if (!isResponseArrayNode)
		{
			final String errorString = PATH_STRING_LOGGER + this.pathValue + "' (operation '" + this.pathOp + "') needs a responses array defined" ;
			
			ResponsesParserOperation.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
	}

	/**
	 * Parse the full parameters
	 * @param inspectedNode with the inspected node
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	protected void parseResponsesInternal(final ArrayNode inspectedNode) throws APIRestGeneratorException
	{
		final Iterator<JsonNode> iterator = ((ArrayNode)inspectedNode).elements() ;
		while (iterator.hasNext())
		{
			// New Entry
			final JsonNode responseNode = iterator.next() ;
			
			// This is the identifier for exception keys
			final String idName 		= this.pathValue + ConstantsCommon.STRING_COLON + ConstantsInputParser.SW_COMMON_RESPONSES ;
			
			// Entry with the key and the value
			final AbstractMap.SimpleEntry<String, Response> responseEntry = this.generateResponseEntry(idName, responseNode) ;
			
			// Validate current entry key if exist
			this.validateIfExistResponseCode(responseEntry.getKey()) ;
			
			// Put the new response in the map
			this.localResponses.getResponsesMap().put(responseEntry.getKey(), responseEntry.getValue()) ;
		}
		
		// Post validations
		final ResponsesPostValidator responsesPostValidator = new ResponsesPostValidator(this.pathValue, this.pathOp, this.localResponses) ;
		responsesPostValidator.postValidations() ;
	}
	
	/**
	 * @param responseCode with the response code
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void validateIfExistResponseCode(final String responseCode) throws APIRestGeneratorException
	{
		if (this.localResponses.getResponsesMap().containsKey(responseCode))
		{
			final String errorString = PATH_STRING_LOGGER + this.pathValue + "' ('" + this.pathOp + "' operation) contains a duplicated response code: " + responseCode ;
			
			ResponsesParserOperation.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;			
		}
	}

	/**
	 * @param idName	   with the identifier for exceptions
	 * @param responseNode with the response node which it is an array
	 * @return a new entry with the response key and value
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private AbstractMap.SimpleEntry<String, Response> generateResponseEntry(final String idName, final JsonNode responseNode) throws APIRestGeneratorException
	{
		// Firstly, inspect if there is a reference object
		AbstractMap.SimpleEntry<String, Response> responseEntry = this.inspectRefResponse(responseNode) ;
		
		if (responseEntry == null)
		{
			// Inspect a defined response
			final Iterator<String> iteratorSingleField = responseNode.fieldNames() ;
			if (!iteratorSingleField.hasNext())
			{
				final String errorString = PATH_STRING_LOGGER + this.pathValue + "' (operation '" + this.pathOp + "') needs a key response defined" ;
				
				ResponsesParserOperation.LOGGER.error(errorString) ;
		    	throw new APIRestGeneratorException(errorString) ;
			}
			
			final String responseSingleKey 	   = iteratorSingleField.next() ;
			final JsonNode responseSingleNode  = ParserUtil.getNodeValueJson(idName, responseNode, responseSingleKey, true) ;
			final Response responseSingleValue = this.parseResponseObject(responseSingleKey, responseSingleNode) ;
			
			responseEntry = new AbstractMap.SimpleEntry<String, Response>(responseSingleKey, responseSingleValue) ;
		}
		
		return responseEntry ;
	}
	
	/**
	 * Inspect a possible $ref response
	 * @param nodeToInspect with the node to inspect
	 * @return a new entry with the reference response key and value
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private AbstractMap.SimpleEntry<String, Response> inspectRefResponse(final JsonNode nodeToInspect) throws APIRestGeneratorException
	{
		// Inspect if there is a $ref value
		final String reference = ParserUtil.getNodeValueField(ConstantsInputParser.SW_COMMON_RESPONSES, nodeToInspect, ConstantsInputParser.SW_COMMON_REF, false) ;

		AbstractMap.SimpleEntry<String, Response> responseEntry = null ;
		
		if (reference != null && !reference.isEmpty())
		{
			responseEntry = this.getExistingResponseRef(reference) ;
		}
		
		return responseEntry ;
	}
	
    /**
     * @param reference with the reference
     * @return a new entry with the reference response key and value
     * @throws APIRestGeneratorException with an occurred exception
     */
	protected AbstractMap.SimpleEntry<String, Response> getExistingResponseRef(final String reference) throws APIRestGeneratorException
	{
		AbstractMap.SimpleEntry<String, Response> responseEntry = null ;
    	
    	final Pattern pattern = Pattern.compile(ConstantsInput.PATT_RESPONSES_REF) ;
		final Matcher matcher = pattern.matcher(reference) ;
		if (matcher.matches())
		{
			final String responseRefKey     = matcher.group(1) ;
			final Response responseRefValue = this.globalResponses.getResponsesMap().get(responseRefKey) ;
			
			if (responseRefValue == null)
			{
				final String errorString = "The following reference was not found in the responses map: " + responseRefKey ;
				
				ResponsesParserOperation.LOGGER.error(errorString) ;
		    	throw new APIRestGeneratorException(errorString) ;
			}
			
			responseEntry = new AbstractMap.SimpleEntry<String, Response>(responseRefKey, responseRefValue) ;
		}
		else
		{
			final String errorString = "The following reference was not defined properly to be search in the responses map: " + reference ;
			
			ResponsesParserOperation.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
    	
    	return responseEntry ;
	}
}
