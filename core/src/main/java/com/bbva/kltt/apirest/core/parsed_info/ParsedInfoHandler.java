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

package com.bbva.kltt.apirest.core.parsed_info;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterBody;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParsedInfoHandler
{
	/** Attribute - Parsed information */
	private final ParsedInfo parsedInfo ;

	/**
	 * Public constructor
	 * @param parsedInfo with the parsed information
	 */
	public ParsedInfoHandler(final ParsedInfo parsedInfo)
	{
		this.parsedInfo = parsedInfo ;
	}
	
	/**
	 * @return the information values
	 */
	public InfoValues getInfoValues()
	{
		return this.parsedInfo.getInfoValues() ;
	}
	
	/**
	 * @return true if there is info to generate
	 */
	public boolean hasInfoToGenerate()
	{
		return this.parsedInfo.getPaths() != null && !this.parsedInfo.getPaths().getPathsMap().isEmpty() ;
	}
	
	/**
	 * @return the host
	 */
	public String getHost()
	{
		return this.parsedInfo.getRootValues().getHost() ;
	}
	
	/**
	 * @return the business unit
	 */
	public String getBUnit()
	{
		return this.parsedInfo.getGeneratorParams().getBUnit() ;
	}
	
	/**
	 * @return the base path
	 */
	public String getBasePath()
	{
		return this.parsedInfo.getRootValues().getBasePath() ;
	}
	
	/**
	 * @return the path values
	 */
	public Set<String> getPathValues()
	{
		Set<String> outcome = null ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().getPathsMap().keySet() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @return a new map with the full definitions (complex and references)
	 */
	public Map<String, Item> generateDefinitionsMap()
	{
		Map<String, Item> outcome = new HashMap<String, Item>() ;
		
		if (this.parsedInfo.getDefinitions() != null)
		{
			outcome = this.parsedInfo.getDefinitions().generateDefinitionsMap() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @return the definitions names (complex and references)
	 */
	public Set<String> getDefinitionsListNames()
	{
		final Set<String> outcome = new HashSet<String>() ;
		
		for (final Item complexDefinition : this.generateDefinitionsMap().values())
		{
			outcome.add(complexDefinition.getName()) ;
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
		Set<Scheme> outcome = this.parsedInfo.getRootValues().getSchemes() ;
		
		if (this.hasAnyScheme(pathValue, pathOperation))
		{
			outcome = this.parsedInfo.getPaths().getSchemes(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any scheme in the operation
	 */
	private boolean hasAnyScheme(String pathValue, String pathOperation)
	{
		boolean outcome = false ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().hasAnyScheme(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}

	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new body parameter
	 */
	public Parameter getParameterBody(final String pathValue, final String pathOperation)
	{
		ParameterBody outcome = null ;
		
		if (this.hasAnyParameterBody(pathValue, pathOperation))
		{
			outcome = this.parsedInfo.getPaths().generateParameterBody(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any body parameter in the operation
	 */
	private boolean hasAnyParameterBody(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().hasAnyParameterBody(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return an entry with the parameter and its description
	 */
	public Entry<String, String> getParametersDescriptionBody(final String pathValue, final String pathOperation)
	{
		Entry<String, String> entry = null ;
		
		final Parameter parameter = this.getParameterBody(pathValue, pathOperation) ;
		if (parameter != null)
		{
			entry = this.generateNewParamDescriptionEntry(parameter) ;
		}
		
		return entry ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any formData parameter in the operation
	 */
	private boolean hasAnyParameterFormData(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().hasAnyParameterFormData(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new map with the form data parameters
	 */
	public Map<String, Parameter> getParametersFormDataMap(final String pathValue, final String pathOperation)
	{
		Map<String, Parameter> outcome = null ;
		
		if (this.hasAnyParameterFormData(pathValue, pathOperation))
		{
			outcome = this.parsedInfo.getPaths().generateParametersFormDataMap(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with the entries (parameter and its description)
	 */
	public List<Entry<String, String>> getParametersDescriptionFormData(final String pathValue, final String pathOperation)
	{
		List<Entry<String, String>> entriesList = null ;
		
		final Map<String, Parameter> parameterMap = this.getParametersFormDataMap(pathValue, pathOperation) ;
		if (parameterMap != null && !parameterMap.isEmpty())
		{
			entriesList = this.generateNewParamDescriptionListEntry(parameterMap) ;
		}
		
		return entriesList ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any header parameter in the operation
	 */
	private boolean hasAnyParameterHeader(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().hasAnyParameterHeader(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new map with the header parameters
	 */
	public Map<String, Parameter> getParametersHeaderMap(final String pathValue, final String pathOperation)
	{
		Map<String, Parameter> outcome = null ;
		
		if (this.hasAnyParameterHeader(pathValue, pathOperation))
		{
			outcome = this.parsedInfo.getPaths().generateParametersHeaderMap(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with the entries (parameter and its description)
	 */
	public List<Entry<String, String>> getParametersDescriptionHeader(final String pathValue, final String pathOperation)
	{
		List<Entry<String, String>> entriesList = null ;
		
		final Map<String, Parameter> parameterMap = this.getParametersHeaderMap(pathValue, pathOperation) ;
		if (parameterMap != null && !parameterMap.isEmpty())
		{
			entriesList = this.generateNewParamDescriptionListEntry(parameterMap) ;
		}
		
		return entriesList ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any path parameter in the operation
	 */
	private boolean hasAnyParameterPath(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().hasAnyParameterPath(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new map with the path parameters
	 */
	public Map<String, Parameter> getParametersPathMap(final String pathValue, final String pathOperation)
	{
		Map<String, Parameter> outcome = null ;
		
		if (this.hasAnyParameterPath(pathValue, pathOperation))
		{
			outcome = this.parsedInfo.getPaths().generateParametersPathMap(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with the entries (parameter and its description)
	 */
	public List<Entry<String, String>> getParametersDescriptionPath(final String pathValue, final String pathOperation)
	{
		List<Entry<String, String>> entriesList = null ;
		
		final Map<String, Parameter> parameterMap = this.getParametersPathMap(pathValue, pathOperation) ;
		if (parameterMap != null && !parameterMap.isEmpty())
		{
			entriesList = this.generateNewParamDescriptionListEntry(parameterMap) ;
		}
		
		return entriesList ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is any query parameter in the operation
	 */
	private boolean hasAnyParameterQuery(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().hasAnyParameterQuery(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new map with the query parameters
	 */
	public Map<String, Parameter> getParametersQueryMap(final String pathValue, final String pathOperation)
	{
		Map<String, Parameter> outcome = null ;
		
		if (this.hasAnyParameterQuery(pathValue, pathOperation))
		{
			outcome = this.parsedInfo.getPaths().generateParametersQueryMap(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new list with the entries (parameter and its description)
	 */
	public List<Entry<String, String>> getParametersDescriptionQuery(final String pathValue, final String pathOperation)
	{
		List<Entry<String, String>> entriesList = null ;
		
		final Map<String, Parameter> parameterMap = this.getParametersQueryMap(pathValue, pathOperation) ;
		if (parameterMap != null && !parameterMap.isEmpty())
		{
			entriesList = this.generateNewParamDescriptionListEntry(parameterMap) ;
		}
		
		return entriesList ;
	}
	
	/**
	 * @param parameterMap with the parameters map
	 * @return a new list with the entries (parameter and its description)
	 */
	private List<Entry<String, String>> generateNewParamDescriptionListEntry(final Map<String, Parameter> parameterMap)
	{
		final List<Entry<String, String>> entriesList = new ArrayList<Entry<String, String>>() ;
		
		for (final Parameter parameter : parameterMap.values())
		{
			entriesList.add(this.generateNewParamDescriptionEntry(parameter)) ;
		}
		
		return entriesList ;
	}
	
	/**
	 * @param parameter with the parameter
	 * @return a new entry with the parameter and its description
	 */
	private Entry<String, String> generateNewParamDescriptionEntry(final Parameter parameter)
	{
		return new AbstractMap.SimpleEntry<String, String>(parameter.getName(), parameter.getDescription()) ;
	}
	
	/**
	 * @return true if there is operation identifier for whatever path
	 */
	public boolean hasAnyOperationId()
	{
		return this.hasInfoToGenerate() ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if there is operation identifier for this path operation
	 */
	public boolean hasAnyOperationId(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().hasAnyOperationId(pathValue, pathOperation) ;
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
		String outcome = null ;
		
		if (this.hasAnyOperationId(pathValue, pathOperation))
		{
			outcome = this.parsedInfo.getPaths().getOperationId(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @return all the operation identifiers
	 */
	public List<String> getAllOperationIds()
	{
		List<String> outcome = null ;
		
		if (this.hasAnyOperationId())
		{
			outcome = this.parsedInfo.getPaths().getAllOperationIds() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return the return item type
	 */
	public Item getOutboundServerItemType(final String pathValue, final String pathOperation)
	{
		Item outcome = null ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().getOutboundServerItemType(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}

	/**
	 * @return a new map with all the response exceptions
	 */
	public Map<String, List<Response>> getOutboundServerExceptionsMap()
	{
		final Map<String, List<Response>> outcome = new HashMap<String, List<Response>>() ;
		
		if (this.hasInfoToGenerate())
		{
			this.parsedInfo.getPaths().getOutboundServerExceptionsMap(outcome) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a list with all the custom response exceptions for the path and operation
	 */
	public List<Response> getOutboundServerExceptionsList(final String pathValue, final String pathOperation)
	{
		List<Response> customExceptionsList = new ArrayList<Response>() ;
		
		if (this.hasInfoToGenerate())
		{
			customExceptionsList = this.parsedInfo.getPaths().getOutboundServerExceptionsList(pathValue, pathOperation) ;
		}
		
		return customExceptionsList ;
	}
	
	/**
	 * Global consumes
	 * @return a new String with the global consumes
	 */
	public Set<String> getConsumes()
	{
		return this.parsedInfo.getRootValues().getConsumes() ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new set with the consumes for the pathValue-pathOperation
	 */
	public Set<String> getConsumes(final String pathValue, final String pathOperation)
	{
		Set<String> outcome = null ;
		
		if (this.hasAnyOperationConsumes(pathValue, pathOperation))
		{
			outcome = this.parsedInfo.getPaths().getConsumes(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if exists any consumes for the pathValue-pathOperation
	 */
	private boolean hasAnyOperationConsumes(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().hasAnyOperationConsumes(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * Global produces
	 * @return a new String with the global produces
	 */
	public Set<String> getProduces()
	{
		return this.parsedInfo.getRootValues().getProduces() ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new set with the produces for the pathValue-pathOperation
	 */
	public Set<String> getProduces(final String pathValue, final String pathOperation)
	{
		Set<String> outcome = null ;
		
		if (this.hasAnyOperationProduces(pathValue, pathOperation))
		{
			outcome = this.parsedInfo.getPaths().getProduces(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return true if exists any produces for the pathValue-pathOperation
	 */
	private boolean hasAnyOperationProduces(final String pathValue, final String pathOperation)
	{
		boolean outcome = false ;
		
		if (this.hasInfoToGenerate())
		{
			outcome = this.parsedInfo.getPaths().hasAnyOperationProduces(pathValue, pathOperation) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @return the Architecture type
	 */
	public String getOsgiArchitectureType()
	{
		return this.parsedInfo.getGeneratorParams().getOsgiParams().getArchitectureType() ;
	}
	
	/**
	 * @return the CXF Address
	 */
	public String getOsgiCxfAddress()
	{
		return this.parsedInfo.getGeneratorParams().getOsgiParams().getCxfAddress() ;
	}
	
	/**
	 * @return the CXF Context
	 */
	public String getOsgiCxfContext()
	{
		return this.parsedInfo.getGeneratorParams().getOsgiParams().getCxfContext() ;
	}
	
	@Override
	public String toString()
	{
		return this.parsedInfo.toString() ;
	}
}
