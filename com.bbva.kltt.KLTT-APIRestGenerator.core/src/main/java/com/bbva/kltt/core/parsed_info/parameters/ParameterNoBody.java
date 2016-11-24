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

package com.bbva.kltt.core.parsed_info.parameters;

import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class ParameterNoBody extends Parameter
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterNoBody.class) ;
	
	/**
	 * Public constructor
	 * @param name 		  with the name
	 * @param alias		  with the alias name
	 * @param description with the description
	 * @param required	  true if the item is required
	 * @param type		  with the type
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public ParameterNoBody(final String name, final String alias, final String description, final String required, final String type) throws APIRestGeneratorException
	{
		super(name, alias, description, required, type) ;
	}
	
	/**
	 * Validate instance attributes
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	@Override
	public void validate() throws APIRestGeneratorException
	{
		String errorString = null ;
    	
		if (this.getType() == null)
		{
			errorString = "The 'name' field '" + this.getName() + "' must not contain a null 'type'" ;
		}
		else if (!ConstantsInput.JSON_TYPE_ARRAY.equals(this.getType())   &&
				 !ConstantsInput.JSON_TYPE_BOOLEAN.equals(this.getType()) && 
				 !ConstantsInput.JSON_TYPE_INTEGER.equals(this.getType()) && 
				 !ConstantsInput.JSON_TYPE_NUMBER.equals(this.getType())  && 
				 !ConstantsInput.JSON_TYPE_STRING.equals(this.getType()))
		{
			errorString = "The 'name' field '" + this.getName() + "' must contain any 'type' as following: "  + 
							  ConstantsInput.JSON_TYPE_ARRAY   + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK +
							  ConstantsInput.JSON_TYPE_BOOLEAN + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK +
							  ConstantsInput.JSON_TYPE_INTEGER + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK +
							  ConstantsInput.JSON_TYPE_NUMBER  + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK +
							  ConstantsInput.JSON_TYPE_STRING  + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK ;
		}
		
		if (errorString != null)
		{
			ParameterNoBody.LOGGER.error(errorString) ;
			throw new APIRestGeneratorException(errorString) ;
		}
	}
}
