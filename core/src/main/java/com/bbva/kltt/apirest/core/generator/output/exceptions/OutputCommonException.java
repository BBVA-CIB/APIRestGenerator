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
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputCommonException extends AbstractOutputException
{
	/**
	 * @param outputLanguageNaming with the Output Language Item
	 */
	public OutputCommonException(final IOutputLanguageNaming outputLanguageNaming)
	{
		super(outputLanguageNaming) ;
	}
	
	/**
	 * @return the custom exception as class name format
	 */
	public String getAsClassName()
	{
		return this.getName() ;
	}
	
	/**
	 * @param operationId with the operation identifier
	 * @return the custom exception as method name format
	 */
	public String getAsMethodName(final String operationId)
	{
		return this.getOutputLanguageNaming().prefixAttributeName(operationId + this.getName()) ;
	}
	
	/**
	 * @return the custom exception as no formatted name
	 */
	private String getName()
	{
		return ConstantsOutput.CLASSNAME_COMMON_EXCEPTION ;
	}
}
