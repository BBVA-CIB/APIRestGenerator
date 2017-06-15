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

package com.bbva.kltt.apirest.core.generator.output.language;

import java.util.List;
import java.util.Map;

import com.bbva.kltt.apirest.core.generator.output.exceptions.OutputCommonException;
import com.bbva.kltt.apirest.core.generator.output.exceptions.OutputCustomException;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IOutputLanguageExceptions
{
	/**
	 * @return the class name for the common exception
	 */
	String getCommonExceptionClassName() ;
	
	/**
	 * @return the output common exception
	 */
	OutputCommonException getCommonException() ;
	
	/**
	 * @return a new map with all the response exceptions
	 */
	Map<String, List<Response>> getOutboundServerExceptionsMap() ;
	
	/**
	 * @param operationId with the operation identifier
	 * @param response 	  with the response
	 * @return the class name for the custom exception
	 */
	String getCustomExceptionAsClassName(final String operationId, final Response response) ;
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a list with all the custom exceptions for the path and operation
	 */
	List<OutputCustomException> getCustomExceptionsList(final String pathValue, final String pathOperation) ;
}
