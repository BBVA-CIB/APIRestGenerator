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

package com.bbva.kltt.apirest.core.generator.output.exceptions;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageNaming;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputCustomException extends AbstractOutputException
{
	/** Attribute - operation identifier */
	private final String operationId ;
	
	/** Attribute - response */
	private final Response response ;
	
	/**
	 * @param outputLanguageNaming with the Output Language Item
	 * @param operationId		   with the operation identifier
	 * @param response 			   with the response
	 */
	public OutputCustomException(final IOutputLanguageNaming outputLanguageNaming, final String operationId, final Response response)
	{
		super(outputLanguageNaming) ;
		
		this.operationId = operationId ;
		this.response    = response ;
	}

	/**
	 * @return the custom exception as class name format
	 */
	public String getAsClassName()
	{
		return this.getOutputLanguageNaming().prefixClassName(this.getNoFormatedName()) ;
	}
	
	/**
	 * @return the custom exception as method name format
	 */
	public String getAsMethodName()
	{
		return this.getOutputLanguageNaming().prefixAttributeName(this.getNoFormatedName()) ;
	}
	
	/**
	 * @return the custom exception as no formatted name
	 */
	private String getNoFormatedName()
	{
		return this.operationId + ConstantsOutput.EXC_PREFIX_CUSTOM_CLASS_NAME + this.response.getName() ;
	}
	
	/**
	 * @return the status code of the response
	 */
	public int getStatusCode()
	{
		return Integer.valueOf(this.response.getName()) ;
	}
	
	/**
	 * @return the response item with the type of the outcome
	 */
	public Item getResponseItem()
	{
		return this.response.getItem() ;
	}
}
