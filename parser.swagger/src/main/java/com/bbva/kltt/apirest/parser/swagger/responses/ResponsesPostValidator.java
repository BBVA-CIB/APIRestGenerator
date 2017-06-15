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

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.parsed_info.Responses;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemArray;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemFile;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsMiddle;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ResponsesPostValidator
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsesPostValidator.class) ;
	
    /** Attribute - Path value */
    private final String pathValue ;
    
    /** Attribute - Path operation */
    private final String pathOp ;
    
    /** Attribute - Local Responses */
    private final Responses localResponses ;
    
    /**
     * Protected constructor
     * @param pathValue 	 with the path value
	 * @param pathOp    	 with the path operation
     * @param localResponses with the local response
     */
    protected ResponsesPostValidator(final String pathValue, final String pathOp, final Responses localResponses)
    {
		this.pathValue 		= pathValue ;
		this.pathOp 		= pathOp ;
    	this.localResponses = localResponses ;
    }
    
    /**
     * Post validations
     * @throws APIRestGeneratorException with an occurred exception
     */
	protected void postValidations() throws APIRestGeneratorException
	{
		this.postValidationResponseCodeSuccess() ;
		
		this.postValidationNoArrayOfFiles() ;
	}
    
	/**
	 * Post validation - Response code success exist for all the requests/responses
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationResponseCodeSuccess() throws APIRestGeneratorException
	{
		final Iterator<String> iterator = this.localResponses.getResponsesMap().keySet().iterator() ;
		boolean found 				    = false ;
		
		while (iterator.hasNext() && !found)
		{
			final String codeKey = iterator.next() ;
			found 				 = ConstantsMiddle.RESP_CODE_SUCCESS.equals(codeKey) ;
		}
		
		if (!found)
		{
			final String errorString = "The path '" + this.pathValue + "' ('" + this.pathOp + "' operation) "   +
									   "needs a defined response with code " + ConstantsMiddle.RESP_CODE_SUCCESS ;
			
			ResponsesPostValidator.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
	}
	
	/**
	 * Post validation - Forbidden to use array of files in the response
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationNoArrayOfFiles() throws APIRestGeneratorException
	{
		final Iterator<Response> iterator = this.localResponses.getResponsesMap().values().iterator() ;
		boolean found 				      = false ;
		
		while (iterator.hasNext() && !found)
		{
			final Response response = iterator.next() ;
			final Item responseItem = response.getItem() ;
			if (responseItem instanceof ItemArray)
			{
				final Item itemBaseArray = ((ItemArray) responseItem).calculateBaseType() ;
				found =  itemBaseArray instanceof ItemFile ;
			}
		}
		
		if (found)
		{
			final String errorString = "The path '" + this.pathValue + "' ('" + this.pathOp + "' operation) cannot contain a response with an array of files" ;
			
			ResponsesPostValidator.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
	}
}
