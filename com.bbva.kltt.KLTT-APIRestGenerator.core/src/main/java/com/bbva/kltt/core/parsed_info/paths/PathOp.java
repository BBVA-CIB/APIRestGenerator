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

package com.bbva.kltt.core.parsed_info.paths;

import com.bbva.kltt.core.parsed_info.Scheme;
import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.parsed_info.operations.Operation;
import com.bbva.kltt.core.parsed_info.parameters.Parameter;
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
public abstract class PathOp
{
	/** Attribute - Operation */
	@JsonView(GeneratorView.class)
	private Operation operation ;

	/**
	 * @return the operation
	 */
	public Operation getOperation()
	{
		return this.operation ;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(final Operation operation)
	{
		this.operation = operation ;
	}
	
	/**
	 * @return true if the path operation does not contain any data
	 */
	@JsonIgnore
	public boolean isEmpty()
	{
		return this.operation == null ;
	}
	
	/**
	 * @return true if there is any scheme in the operation
	 */
	@JsonIgnore
	protected boolean hasAnyScheme()
	{
		return this.operation != null && this.operation.hasAnyScheme() ;
	}
	
	/**
	 * @return true if there is any body parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterBody()
	{
		return this.operation != null && this.operation.hasAnyParameterBody() ;
	}
	
	/**
	 * @return true if there is any form data parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterFormData()
	{
		return this.operation != null && this.operation.hasAnyParameterFormData() ;
	}
	
	/**
	 * @return true if there is any header parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterHeader()
	{
		return this.operation != null && this.operation.hasAnyParameterHeader() ;
	}
	
	/**
	 * @return true if there is any path parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterPath()
	{
		return this.operation != null && this.operation.hasAnyParameterPath() ;
	}
	
	/**
	 * @return true if there is any query parameter in the operation
	 */
	@JsonIgnore
	public boolean hasAnyParameterQuery()
	{
		return this.operation != null && this.operation.hasAnyParameterQuery() ;
	}

	/**
	 * @return the path operation type
	 */
	public abstract String getPathOpType() ;
	
	/**
	 * @return the schemes
	 */
	@JsonIgnore
	public Set<Scheme> getSchemes()
	{
		return this.operation.getSchemes() ;
	}

	/**
	 * @param parametersMap with the map of parameters
	 */
	protected void generateParametersFormDataMap(final Map<String, Parameter> parametersMap)
	{
		if (this.hasAnyParameterFormData())
		{
			this.operation.generateParametersFormDataMap(parametersMap) ;
		}
	}

	/**
	 * @param parametersMap with the map of parameters
	 */
	protected void generateParametersHeaderMap(final Map<String, Parameter> parametersMap)
	{
		if (this.hasAnyParameterHeader())
		{
			this.operation.generateParametersHeaderMap(parametersMap) ;
		}		
	}

	/**
	 * @param parametersMap with the map of parameters
	 */
	protected void generateParametersPathMap(final Map<String, Parameter> parametersMap)
	{
		if (this.hasAnyParameterPath())
		{
			this.operation.generateParametersPathMap(parametersMap) ;
		}		
	}

	/**
	 * @param parametersMap with the map of parameters
	 */
	protected void generateParametersQueryMap(final Map<String, Parameter> parametersMap)
	{
		if (this.hasAnyParameterQuery())
		{
			this.operation.generateParametersQueryMap(parametersMap) ;
		}		
	}

	/**
	 * @return the operation identifier
	 */
	@JsonIgnore
	protected String getOperationId()
	{
		String outcome = null ;
		
		if (!this.isEmpty())
		{
			outcome = this.operation.getOperationId() ;
		}

		return outcome;
	}

	/**
	 * @return the return item type
	 */
	@JsonIgnore
	public Item getOutboundServerItemType()
	{
		Item outcome = null ;
		
		if (!this.isEmpty())
		{
			outcome = this.operation.getOutboundServerItemType() ;
		}

		return outcome;
	}

	/**
	 * @return true if exists any consumes for the pathValue-pathOperation
	 */
	@JsonIgnore
	public boolean hasAnyOperationConsumes()
	{
		return this.operation.getConsumes() != null ;
	}
	
	/**
	 * @return a new set with the consumes for this pathValue-pathOperation
	 */
	@JsonIgnore
	public Set<String> getConsumes()
	{
		return this.operation.getConsumes() ;
	}
	
	/**
	 * @return true if exists any produces for the pathValue-pathOperation
	 */
	@JsonIgnore
	public boolean hasAnyOperationProduces()
	{
		return this.operation.getProduces() != null ;
	}
	
	/**
	 * @return a new set with the produces for this pathValue-pathOperation
	 */
	@JsonIgnore
	public Set<String> getProduces()
	{
		return this.operation.getProduces() ;
	}
}
