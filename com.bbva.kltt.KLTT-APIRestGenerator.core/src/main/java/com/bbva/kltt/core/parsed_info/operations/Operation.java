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

package com.bbva.kltt.core.parsed_info.operations;

import com.bbva.kltt.core.parsed_info.Responses;
import com.bbva.kltt.core.parsed_info.Scheme;
import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.core.parsed_info.parameters.ParametersOperation;
import com.bbva.kltt.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Map;
import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Operation
{
	/** Attribute - List of responses */
	@JsonView(GeneratorView.class)
	private final Responses responses ;
	
	/** Attribute - description */
	@JsonView(GeneratorView.class)
	private String description ;
	
	/** Attribute - operation id */
	@JsonView(GeneratorView.class)
	private String operationId ;
	
	/** Attribute - consumes */
	@JsonView(GeneratorView.class)
	private Set<String> consumes ;
	
	/** Attribute - produces */
	@JsonView(GeneratorView.class)
	private Set<String> produces ;
	
	/** Attribute - parameters */
	@JsonView(GeneratorView.class)
	private ParametersOperation parametersOperation ;
	
	/** Attribute - Scheme */
	@JsonView(GeneratorView.class)
	private Set<Scheme> schemes ;
	
	/** Attribute - Deprecated */
	@JsonView(GeneratorView.class)
	private boolean deprecated ;
	
	/**
	 * Public constructor
	 * @param responses with the responses
	 */
	public Operation(final Responses responses)
	{
		this.responses = responses ;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return this.description ;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description)
	{
		this.description = description ;
	}

	/**
	 * @return the operation parameters
	 */
	public ParametersOperation getParametersOperation()
	{
		return this.parametersOperation ;
	}

	/**
	 * @param parametersOperation the operation parameters to set
	 */
	public void setParametersOperation(final ParametersOperation parametersOperation)
	{
		this.parametersOperation = parametersOperation ;
	}
	
	/**
	 * @return the operationId
	 */
	public String getOperationId()
	{
		return this.operationId ;
	}

	/**
	 * @param operationId the operationId to set
	 */
	public void setOperationId(final String operationId)
	{
		this.operationId = operationId ;
	}

	/**
	 * @return the consumes
	 */
	public Set<String> getConsumes()
	{
		return this.consumes ;
	}

	/**
	 * @param consumes the consumes to set
	 */
	public void setConsumes(final Set<String> consumes)
	{
		this.consumes = consumes ;
	}

	/**
	 * @return the produces
	 */
	public Set<String> getProduces()
	{
		return this.produces ;
	}

	/**
	 * @param produces the produces to set
	 */
	public void setProduces(final Set<String> produces)
	{
		this.produces = produces ;
	}

	/**s
	 * @return the responses
	 */
	public Responses getResponses()
	{
		return this.responses ;
	}

	/**
	 * @return the schemes
	 */
	public Set<Scheme> getSchemes()
	{
		return this.schemes ;
	}

	/**
	 * @param schemes the schemes to set
	 */
	public void setSchemes(final Set<Scheme> schemes)
	{
		this.schemes = schemes ;
	}
	
	/**
	 * @return the deprecated
	 */
	public boolean isDeprecated()
	{
		return this.deprecated ;
	}

	/**
	 * @param deprecated the deprecated to set
	 */
	public void setDeprecated(final boolean deprecated)
	{
		this.deprecated = deprecated ;
	}

	/**
	 * @return true if there is any scheme in the operation
	 */
	@JsonIgnore
	public boolean hasAnyScheme()
	{
		return this.schemes != null && this.schemes.size() > 0 ;
	}
	
	/**
	 * @return true if there is any body parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterBody()
	{
		return this.parametersOperation != null && this.parametersOperation.hasAnyParameterBody() ;
	}

	/**
	 * @return true if there is any form data parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterFormData()
	{
		return this.parametersOperation != null && this.parametersOperation.hasAnyParameterFormData() ;
	}

	/**
	 * @return true if there is any header parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterHeader()
	{
		return this.parametersOperation != null && this.parametersOperation.hasAnyParameterHeader() ;
	}

	/**
	 * @return true if there is any path parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterPath()
	{
		return this.parametersOperation != null && this.parametersOperation.hasAnyParameterPath() ;
	}

	/**
	 * @return true if there is any query parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterQuery()
	{
		return this.parametersOperation != null && this.parametersOperation.hasAnyParameterQuery() ;
	}

	/**
	 * @param parametersMap with the map of parameters
	 */
	public void generateParametersFormDataMap(final Map<String, Parameter> parametersMap)
	{
		parametersMap.putAll(this.parametersOperation.generateParametersFormDataMap()) ;
	}

	/**
	 * @param parametersMap with the map of parameters
	 */
	public void generateParametersHeaderMap(final Map<String, Parameter> parametersMap)
	{
		parametersMap.putAll(this.parametersOperation.generateParametersHeaderMap()) ;
	}

	/**
	 * @param parametersMap with the map of parameters
	 */
	public void generateParametersPathMap(final Map<String, Parameter> parametersMap)
	{
		parametersMap.putAll(this.parametersOperation.generateParametersPathMap()) ;
	}

	/**
	 * @param parametersMap with the map of parameters
	 */
	public void generateParametersQueryMap(final Map<String, Parameter> parametersMap)
	{
		parametersMap.putAll(this.parametersOperation.generateParametersQueryMap()) ;
	}

	/**
	 * @return the return item type
	 */
	@JsonIgnore
	public Item getOutboundServerItemType()
	{
		return this.responses.getOutboundServerItemType() ;
	}
}
