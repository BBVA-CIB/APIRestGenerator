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

package com.bbva.kltt.apirest.core.parsed_info.paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bbva.kltt.apirest.core.parsed_info.Definitions;
import com.bbva.kltt.apirest.core.parsed_info.Responses;
import com.bbva.kltt.apirest.core.parsed_info.RootValues;
import com.bbva.kltt.apirest.core.parsed_info.Scheme;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterBody;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersGlobal;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Paths 
{
	/** Attribute - root values */
	@JsonIgnore
	private final RootValues rootValues ;
	
	/** Attribute - global definitions */
	@JsonIgnore
	private final Definitions globalDefinitions ;
	
	/** Attribute - Parameters global */
	@JsonIgnore
	private final ParametersGlobal parametersGlobal ;
	
	/** Attribute - global responses */
	@JsonIgnore
	private final Responses globalResponses ;
	
	/** Paths Map */
	@JsonView(GeneratorView.class)
	private final Map<String, Path> pathsMap ;
	
	/**
	 * Public constructor
	 * @param rootValues		with the rootValues 
     * @param globalDefinitions with the global definitions
	 * @param parametersGlobal  with the global parameters
	 * @param globalResponses   with the global responses
	 */
	public Paths(final RootValues rootValues, final Definitions globalDefinitions, final ParametersGlobal parametersGlobal, final Responses globalResponses)
	{
		this.rootValues		   = rootValues ;
		this.globalDefinitions = globalDefinitions ; 
		this.parametersGlobal  = parametersGlobal ; 
		this.globalResponses   = globalResponses ;
		
		this.pathsMap 	 	   = new HashMap<String, Path>() ;
	}
	
	/**
	 * @return the rootValues
	 */
	public RootValues getRootValues()
	{
		return this.rootValues ;
	}

	/**
	 * @return the global definitions
	 */
	public Definitions getGlobalDefinitions()
	{
		return this.globalDefinitions ;
	}

	/**
	 * @return the global parameters
	 */
	public ParametersGlobal getParametersGlobal()
	{
		return this.parametersGlobal ;
	}

	/**
	 * @return the global responses
	 */
	public Responses getGlobalResponses()
	{
		return this.globalResponses ;
	}

	/**
	 * @return the pathsMap
	 */
	public Map<String, Path> getPathsMap()
	{
		return this.pathsMap ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any scheme in the operation
	 */
	public boolean hasAnyScheme(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;

		final Path path = this.pathsMap.get(pathValue) ;
		if (path != null)
		{
			outcome = path.hasAnyScheme(pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue     with the path value
	 * @param pathOperation with the path operation
	 * @return the schemes
	 */
	public Set<Scheme> getSchemes(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).getSchemes(pathOperation) ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any body parameter in the operation
	 */
	public boolean hasAnyParameterBody(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;

		final Path path = this.pathsMap.get(pathValue) ;
		if (path != null)
		{
			outcome = path.hasAnyParameterBody(pathOperation) ;
		}
		
		return outcome ;
	}

	/**
	 * @param pathValue        with the path value
	 * @param pathOperation    with the path operation
	 * @return a new body parameter
	 */
	public ParameterBody generateParameterBody(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).generateParameterBody(pathOperation) ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any formData parameter in the operation
	 */
	public boolean hasAnyParameterFormData(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;

		final Path path = this.pathsMap.get(pathValue) ;
		if (path != null)
		{
			outcome = path.hasAnyParameterFormData(pathOperation) ;
		}
		
		return outcome ;
	}

	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new map with the form data parameters
	 */
	public Map<String, Parameter> generateParametersFormDataMap(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).generateParametersFormDataMap(pathOperation) ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any header parameter in the operation
	 */
	public boolean hasAnyParameterHeader(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;

		final Path path = this.pathsMap.get(pathValue) ;
		if (path != null)
		{
			outcome = path.hasAnyParameterHeader(pathOperation) ;
		}
		
		return outcome ;
	}

	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new map with the form data parameters
	 */
	public Map<String, Parameter> generateParametersHeaderMap(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).generateParametersHeaderMap(pathOperation) ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any path parameter in the operation
	 */
	public boolean hasAnyParameterPath(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;

		final Path path = this.pathsMap.get(pathValue) ;
		if (path != null)
		{
			outcome = path.hasAnyParameterPath(pathOperation) ;
		}
		
		return outcome ;
	}

	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new map with the path parameters
	 */
	public Map<String, Parameter> generateParametersPathMap(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).generateParametersPathMap(pathOperation) ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any query parameter in the operation
	 */
	public boolean hasAnyParameterQuery(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;

		final Path path = this.pathsMap.get(pathValue) ;
		if (path != null)
		{
			outcome = path.hasAnyParameterQuery(pathOperation) ;
		}
		
		return outcome ;
	}

	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new map with the query parameters
	 */
	public Map<String, Parameter> generateParametersQueryMap(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).generateParametersQueryMap(pathOperation) ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is operation identifier for this path operation
	 */
	public boolean hasAnyOperationId(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;

		final Path path = this.pathsMap.get(pathValue) ;
		if (path != null)
		{
			outcome = path.hasAnyOperationId(pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @return all the operation identifiers
	 */
	public List<String> getAllOperationIds()
	{
		final List<String> outcome = new ArrayList<String>() ;
		
		for (final Path path : this.pathsMap.values())
		{
			outcome.addAll(path.getAllOperationIds()) ;
		}
		
		return outcome ;
	}

	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return the operation identifier
	 */
	public String getOperationId(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).getOperationId(pathOperation) ;
	}

	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return the return item type
	 */
	public Item getOutboundServerItemType(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).getOutboundServerItemType(pathOperation) ;
	}

	/**
	 * @param outcome with the outcome map
	 */
	public void getOutboundServerExceptionsMap(final Map<String, List<Response>> outcome)
	{
		for (final Path path : this.pathsMap.values())
		{
			path.getOutboundServerExceptionsMap(outcome) ;
		}
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a list with all the custom response exceptions for the path and operation
	 */
	public List<Response> getOutboundServerExceptionsList(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).getOutboundServerExceptionsList(pathOperation) ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new set with the consumes for this pathValue-pathOperation
	 */
	public Set<String> getConsumes(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).getConsumes(pathOperation) ;
	}

	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if exists any consumes for the pathValue-pathOperation
	 */
	public boolean hasAnyOperationConsumes(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;

		final Path path = this.pathsMap.get(pathValue) ;
		if (path != null)
		{
			outcome = path.hasAnyOperationConsumes(pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new set with the produces for this pathValue-pathOperation
	 */
	public Set<String> getProduces(final String pathValue, final String pathOperation)
	{
		return this.pathsMap.get(pathValue).getProduces(pathOperation) ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if exists any produces for the pathValue-pathOperation
	 */
	public boolean hasAnyOperationProduces(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;

		final Path path = this.pathsMap.get(pathValue) ;
		if (path != null)
		{
			outcome = path.hasAnyOperationProduces(pathOperation) ;
		}
		
		return outcome ;
	}
}
