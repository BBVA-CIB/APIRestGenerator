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

package com.bbva.kltt.core.generator.output.language;

import com.bbva.kltt.core.generator.output.parameters.OutputParameter;
import com.bbva.kltt.core.generator.output.parameters.OutputParameters;
import com.bbva.kltt.core.parsed_info.parameters.Parameter;

import java.util.List;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IOutputLanguageParameters
{
	/**
	 * @param pathValue     with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with all the parameters
	 */
	public List<Parameter> getParameterListBody(final String pathValue, final String pathOperation) ;
	
	/**
	 * @param pathValue     with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with all the parameters
	 */
	public List<Parameter> getParametersListFormData(final String pathValue, final String pathOperation) ;
	
	/**
	 * @param pathValue     with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with all the parameters
	 */
	public List<Parameter> getParametersListHeader(final String pathValue, final String pathOperation) ;
	
	/**
	 * @param pathValue     with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with all the parameters
	 */
	public List<Parameter> getParametersListPath(final String pathValue, final String pathOperation) ;
	
	/**
	 * @param pathValue     with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with all the parameters
	 */
	public List<Parameter> getParametersListQuery(final String pathValue, final String pathOperation) ;
	
	/**
	 * Generate all the output parameters
	 * @param pathValue	    with the path value
	 * @param pathOperation with the path operation
	 * @return the output parameters
	 */
	public OutputParameters generateAllOutputParameters(final String pathValue, final String pathOperation) ;
	
	/**
	 * Generate the output body parameter
	 * @param pathValue	    with the path value
	 * @param pathOperation with the path operation
	 * @return the output parameters
	 */
	public OutputParameters generateOutputParameterBody(final String pathValue, final String pathOperation) ;
	
	/**
	 * Generate all the output form data parameters
	 * @param pathValue	    with the path value
	 * @param pathOperation with the path operation
	 * @return the output parameters
	 */
	public OutputParameters generateOutputParametersFormData(final String pathValue, final String pathOperation) ;
	
	/**
	 * Generate all the output header parameters
	 * @param pathValue	    with the path value
	 * @param pathOperation with the path operation
	 * @return the output parameters
	 */
	public OutputParameters generateOutputParametersHeader(final String pathValue, final String pathOperation) ;
	
	/**
	 * Generate all the output path parameters
	 * @param pathValue	    with the path value
	 * @param pathOperation with the path operation
	 * @return the output parameters
	 */
	public OutputParameters generateOutputParametersPath(final String pathValue, final String pathOperation) ;
	
	/**
	 * Generate all the output query parameters
	 * @param pathValue	    with the path value
	 * @param pathOperation with the path operation
	 * @return the output parameters
	 */
	public OutputParameters generateOutputParametersQuery(final String pathValue, final String pathOperation) ;
	
	/**
	 * @param outputParamList with the output parameters list
	 * @return a new instance of output parameters
	 */
	public OutputParameters createNewOutputParameters(final List<OutputParameter> outputParamList) ;
	
	/**
	 * @param parameter with the parameter
	 * @return a new instance of output parameter
	 */
	public OutputParameter createNewOutputParameter(Parameter parameter) ;
}
