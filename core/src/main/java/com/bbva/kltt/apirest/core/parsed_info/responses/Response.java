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

package com.bbva.kltt.apirest.core.parsed_info.responses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsMiddle;
import com.bbva.kltt.apirest.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Response extends Item
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(Response.class) ;
	
	/** Attribute - Type which represents the type of the response */
	private final String type ;
    
	/** Attribute - Item which represents the type and format */
	@JsonView(GeneratorView.class)
	private Item item ;

	/**
	 * Public constructor
	 * @param name 		  with the name
	 * @param alias		  with the alias
	 * @param description with the description
	 * @param type		  with the type
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public Response(final String name, final String alias, final String description, final String type) throws APIRestGeneratorException
	{
		super(name, alias, description, "true") ;

		this.type = type;
	}
	
	/**
	 * @return the item
	 */
	public Item getItem()
	{
		return this.item ;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(final Item item)
	{
		this.item = item ;
	}

	@Override
	public String getType()
	{
		return this.type ;
	}

	@Override
	public String getClassName()
	{
		return this.getClass().getSimpleName() ;
	}
	
	
	/**
	 * Validate response attributes
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void validate() throws APIRestGeneratorException
	{
		boolean validResponseCode = ConstantsMiddle.RESP_CODE_SUCCESS.equals(this.getName()) ;
		
		if (!validResponseCode)
		{
			validResponseCode = this.validResponseException(this.getName(), ConstantsMiddle.RESP_CODE_CLIENT_ERROR_4XX) ||
				   	 			this.validResponseException(this.getName(), ConstantsMiddle.RESP_CODE_SERVER_ERROR_5XX) ;
		}
		
		if (!validResponseCode)
		{
			final String errorString = "The following response code is invalid '" + this.getName() + "'." 						   + 
									   "The expected responses are " + ConstantsMiddle.RESP_CODE_SUCCESS  						   + 
									   								   ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK + 
									   								   ConstantsMiddle.RESP_CODE_CLIENT_ERROR_4XX_L 			   + 
									   								   ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK +
									   								   ConstantsMiddle.RESP_CODE_SERVER_ERROR_5XX_L 	  		   ;
									   								   
			Response.LOGGER.error(errorString) ;
			throw new APIRestGeneratorException(errorString) ;
		}
	}

	/**
	 * @param code 				with the response code error
	 * @param responseCodeValid with the response codes valid
	 * @return
	 */
	private boolean validResponseException(final String code, final String[] responseCodeValid)
	{
		boolean found 		  = false ;
		
		int index 	  		  = 0 ;
		final int sizeOfArray = responseCodeValid.length ;
		while (index < sizeOfArray && !found)
		{
			found = responseCodeValid[index].equals(code) ;
			index ++ ;
		}
		
		return found;
	}
}
