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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bbva.kltt.apirest.core.generator.output.exceptions.OutputCommonException;
import com.bbva.kltt.apirest.core.generator.output.exceptions.OutputCustomException;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageExceptions extends OutputLanguageBase implements IOutputLanguageExceptions
{
	/** Attribute - Output Language Naming */
	private final IOutputLanguageNaming outputLanguageNaming ;
	
	/** Attribute - Output Language Operations */
	private final IOutputLanguageOperations outputLanguageOperat ;
	
	/**
	 * Public constructor
	 * @param parsedInfoHandler    with the parsed information handler
	 * @param outputLanguageNaming with the Output Language Naming
	 * @param outputLanguageOperat with the Output Language Operations
	 */
	public OutputLanguageExceptions(final ParsedInfoHandler parsedInfoHandler,
									final IOutputLanguageNaming outputLanguageNaming,
									final IOutputLanguageOperations outputLanguageOperat)
	{
		super(parsedInfoHandler) ;
		
		this.outputLanguageNaming = outputLanguageNaming ;
		this.outputLanguageOperat = outputLanguageOperat ;
	}
	
	@Override
	public String getCommonExceptionClassName()
	{
		final OutputCommonException outputCommonException = this.getCommonException() ;
		return outputCommonException.getAsClassName() ;
	}
	
	@Override
	public OutputCommonException getCommonException()
	{
		return new OutputCommonException(this.outputLanguageNaming) ;
	}
	
	@Override
	public Map<String, List<Response>> getOutboundServerExceptionsMap()
	{
		return this.getParsedInfoHandler().getOutboundServerExceptionsMap() ;
	}
	
	@Override
	public String getCustomExceptionAsClassName(final String operationId, final Response response)
	{
		final OutputCustomException outputCustomException = new OutputCustomException(this.outputLanguageNaming, operationId, response) ;
		return outputCustomException.getAsClassName() ;
	}
	
	@Override
	public List<OutputCustomException> getCustomExceptionsList(final String pathValue, final String pathOperation)
	{
		final List<OutputCustomException> outcome 	  = new ArrayList<OutputCustomException>() ;

		final String operationId 					  = this.outputLanguageOperat.getOperationId(pathValue, pathOperation) ;
		final List<Response> customExceptionResponses = this.getParsedInfoHandler().getOutboundServerExceptionsList(pathValue, pathOperation) ;
		
		for (final Response customExceptionResponse : customExceptionResponses)
		{
			outcome.add(new OutputCustomException(this.outputLanguageNaming, operationId, customExceptionResponse)) ;
		}
		
		return outcome ;
	}
}
