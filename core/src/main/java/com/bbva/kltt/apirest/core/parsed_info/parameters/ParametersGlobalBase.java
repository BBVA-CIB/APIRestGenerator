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

import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.mapper.JacksonViews;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class ParametersGlobalBase extends ParametersBase
{
	/** Attribute - FormData Parameters Map */
	@JsonView(JacksonViews.GeneratorView.class)
	private final Map<String, ParameterFormData> parametersFormDataMap ;

	/** Attribute - Header Parameters Map */
	@JsonView(JacksonViews.GeneratorView.class)
	private final Map<String, ParameterHeader> parametersHeaderMap ;

	/** Attribute - Path Parameters Map */
	@JsonView(JacksonViews.GeneratorView.class)
	private final Map<String, ParameterPath> parametersPathMap ;

	/** Attribute - Query Parameters Map */
	@JsonView(JacksonViews.GeneratorView.class)
	private final Map<String, ParameterQuery> parametersQueryMap ;

	/** Attribute - Body Parameter */
	@JsonView(JacksonViews.GeneratorView.class)
	private ParameterBody parameterBody ;

	/**
	 * Protected constructor
	 */
	protected ParametersGlobalBase()
	{
		super() ;
		
		this.parametersFormDataMap = new HashMap<String, ParameterFormData>() ;
		this.parametersHeaderMap   = new HashMap<String, ParameterHeader>() ;
		this.parametersPathMap     = new HashMap<String, ParameterPath>() ;
		this.parametersQueryMap    = new HashMap<String, ParameterQuery>() ;
	}

	/**
	 * Add a new parameter
	 * @param parameterName with the parameter name
	 * @param itemParameter with the item parameter
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void addParameter(final String parameterName, final Item itemParameter) throws APIRestGeneratorException
	{
		if (itemParameter instanceof ParameterFormData)
		{
			this.parametersFormDataMap.put(parameterName, (ParameterFormData) itemParameter) ;
		}
		else if (itemParameter instanceof ParameterHeader)
		{
			this.parametersHeaderMap.put(parameterName, (ParameterHeader) itemParameter) ;
		}
		else if (itemParameter instanceof ParameterPath)
		{
			this.parametersPathMap.put(parameterName, (ParameterPath) itemParameter) ;
		}
		else if (itemParameter instanceof ParameterQuery)
		{
			this.parametersQueryMap.put(parameterName, (ParameterQuery) itemParameter) ;
		}
		else if (itemParameter instanceof ParameterBody)
		{
			this.addParameterBody((ParameterBody) itemParameter) ;
		}
		else
		{
			// This is a reference parameter
			final Parameter refParameter = this.getReferenceParameter((ItemRef) itemParameter) ;
			this.addParameter(parameterName, refParameter) ;
		}
	}

	@Override
	public Parameter getParameterFormData(final String parameterName)
	{
		return this.parametersFormDataMap.get(parameterName) ;
	}

	@Override
	public Parameter getParameterHeader(final String parameterName)
	{
		return this.parametersHeaderMap.get(parameterName) ;
	}

	@Override
	public Parameter getParameterPath(final String parameterName)
	{
		return this.parametersPathMap.get(parameterName) ;
	}
	
	@Override
	public Parameter getParameterQuery(final String parameterName)
	{
		return this.parametersQueryMap.get(parameterName) ;
	}
	
	@Override
	public boolean hasAnyParameterFormData()
	{
		return !this.parametersFormDataMap.isEmpty() ;
	}

	@Override
	public boolean hasAnyParameterHeader()
	{
		return !this.parametersHeaderMap.isEmpty() ;
	}

	@Override
	public boolean hasAnyParameterPath()
	{
		return !this.parametersPathMap.isEmpty() ;
	}

	@Override
	public boolean hasAnyParameterQuery()
	{
		return !this.parametersQueryMap.isEmpty() ;
	}
}
