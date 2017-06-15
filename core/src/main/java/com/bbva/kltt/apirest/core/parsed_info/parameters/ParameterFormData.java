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

package com.bbva.kltt.apirest.core.parsed_info.parameters;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParameterFormData extends ParameterNoBody
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterFormData.class) ;
    
    /** Attribute - consumes */
    @JsonIgnore
    private final Set<String> consumes ;
    
	/**
	 * Public constructor
	 * @param name 		   with the name
	 * @param alias		   with the alias name
	 * @param description  with the description
	 * @param required	   true if the item is required
	 * @param type		   with the type
	 * @param consumes     with the consumes
	 * @param autoInjected true if the item is auto injected
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public ParameterFormData(final String name,
							 final String alias,
							 final String description,
							 final String required,
							 final String type,
							 final Set<String> consumes,
							 final String autoInjected) throws APIRestGeneratorException
	{
		super(name, alias, description, required, type, autoInjected) ;
		
		this.consumes = consumes ;
	}
	
	/**
	 * Validate instance attributes
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	@Override
	public void validate() throws APIRestGeneratorException
	{
    	String errorString = null ;
    	
    	if (ConstantsCommon.TYPE_FILE.equals(this.getType()) 		  	  &&
    	   !this.consumes.contains(ConstantsInput.CON_MULTIPART_FORM_DAT) && 
    	   !this.consumes.contains(ConstantsInput.CON_APP_X_WWW_FORM_URL))
		{
			errorString = "The 'name' field (with the value '" + this.getName() + "') needs some (or both) of the following types in the consumes definition: "  + 
						  ConstantsInput.CON_MULTIPART_FORM_DAT + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK + ConstantsInput.CON_APP_X_WWW_FORM_URL ; 
		}
    	else if (this.getType() == null)
		{
			errorString = "The 'name' field (with the value '" + this.getName() + "') needs a 'type'" ;
		}
    	else if (this.isAutoInjected())
		{
			errorString = "Auto Injection is not implemented for form-data parameters" ;
		}
		
		if (errorString != null)
		{
			ParameterFormData.LOGGER.error(errorString) ;
			throw new APIRestGeneratorException(errorString) ;
		}
    }

	@Override
	public String getClassName()
	{
		return this.getClass().getSimpleName() ;
	}
}
