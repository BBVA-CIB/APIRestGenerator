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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonView;


/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class ParametersPathBase extends ParametersBase
{
	/** Attribute - FormData Parameters List */
	@JsonView(GeneratorView.class)
	private final List<ParameterFormData> parametersFormDataList ;

	/** Attribute - Header Parameters List */
	@JsonView(GeneratorView.class)
	private final List<ParameterHeader> parametersHeaderList ;

	/** Attribute - Path Parameters List */
	@JsonView(GeneratorView.class)
	private final List<ParameterPath> parametersPathList ;

	/** Attribute - Query Parameters List */
	@JsonView(GeneratorView.class)
	private final List<ParameterQuery> parametersQueryList ;

	/**
	 * Protected constructor
	 */
	protected ParametersPathBase()
	{
		super() ;
		
		this.parametersFormDataList = new ArrayList<ParameterFormData>() ;
		this.parametersHeaderList   = new ArrayList<ParameterHeader>() ;
		this.parametersPathList     = new ArrayList<ParameterPath>() ;
		this.parametersQueryList    = new ArrayList<ParameterQuery>() ;
	}

	/**
	 * Add a new parameter
	 * @param itemParameter with the item parameter
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void addParameter(final Item itemParameter) throws APIRestGeneratorException
	{
		if (itemParameter instanceof ParameterFormData)
		{
			this.parametersFormDataList.add((ParameterFormData) itemParameter) ;
		}
		else if (itemParameter instanceof ParameterHeader)
		{
			this.parametersHeaderList.add((ParameterHeader) itemParameter) ;
		}
		else if (itemParameter instanceof ParameterPath)
		{
			this.parametersPathList.add((ParameterPath) itemParameter) ;
		}
		else if (itemParameter instanceof ParameterQuery)
		{
			this.parametersQueryList.add((ParameterQuery) itemParameter) ;
		}
		else if (itemParameter instanceof ParameterBody)
		{
			this.addParameterBody((ParameterBody) itemParameter) ;
		}
		else
		{
			// This is a reference parameter
			final Parameter refParameter = this.getReferenceParameter((ItemRef) itemParameter) ;
			this.addParameter(refParameter) ;
		}
	}

	@Override
	public Parameter getParameterFormData(final String parameterName)
	{
		Parameter outcome = null ;

		final Iterator<ParameterFormData> iterator = this.parametersFormDataList.iterator() ;
		while (iterator.hasNext() && outcome == null)
		{
			final ParameterFormData temp = iterator.next() ;
			if (parameterName.equals(temp.getName()))
			{
				outcome = temp ;
			}
		}

		return outcome ;
	}

	@Override
	public Parameter getParameterHeader(final String parameterName)
	{
		Parameter outcome = null ;

		final Iterator<ParameterHeader> iterator = this.parametersHeaderList.iterator() ;
		while (iterator.hasNext() && outcome == null)
		{
			final ParameterHeader temp = iterator.next() ;
			if (parameterName.equals(temp.getName()))
			{
				outcome = temp ;
			}
		}

		return outcome ;
	}

	@Override
	public Parameter getParameterPath(final String parameterName)
	{
		Parameter outcome = null ;

		final Iterator<ParameterPath> iterator = this.parametersPathList.iterator() ;
		while (iterator.hasNext() && outcome == null)
		{
			final ParameterPath temp = iterator.next() ;
			if (parameterName.equals(temp.getName()))
			{
				outcome = temp ;
			}
		}

		return outcome ;
	}

	@Override
	public Parameter getParameterQuery(final String parameterName)
	{
		Parameter outcome = null ;

		final Iterator<ParameterQuery> iterator = this.parametersQueryList.iterator() ;
		while (iterator.hasNext() && outcome == null)
		{
			final ParameterQuery temp = iterator.next() ;
			if (parameterName.equals(temp.getName()))
			{
				outcome = temp ;
			}
		}

		return outcome ;
	}

	@Override
	public boolean hasAnyParameterFormData()
	{
		return !this.parametersFormDataList.isEmpty() ;
	}

	@Override
	public boolean hasAnyParameterHeader()
	{
		return !this.parametersHeaderList.isEmpty() ;
	}

	@Override
	public boolean hasAnyParameterPath()
	{
		return !this.parametersPathList.isEmpty() ;
	}

	@Override
	public boolean hasAnyParameterQuery()
	{
		return !this.parametersQueryList.isEmpty() ;
	}

	/**
	 * @return a new map of form data parameters
	 */
	public Map<String, Parameter> generateParametersFormDataMap()
	{
		final Map<String, Parameter> outcome = new HashMap<String, Parameter>() ;

		for (final Parameter parameter : this.parametersFormDataList)
		{
			outcome.put(parameter.getName(), parameter) ;
		}

		return outcome;
	}

	/**
	 * @return a new map of header parameters
	 */
	public Map<String, Parameter> generateParametersHeaderMap()
	{
		final Map<String, Parameter> outcome = new HashMap<String, Parameter>() ;

		for (final ParameterHeader parameter : this.parametersHeaderList)
		{
			outcome.put(parameter.getName(), parameter) ;
		}

		return outcome;
	}

	/**
	 * @return a new map of path parameters
	 */
	public Map<String, Parameter> generateParametersPathMap()
	{
		final Map<String, Parameter> outcome = new HashMap<String, Parameter>() ;

		for (final ParameterPath parameter : this.parametersPathList)
		{
			outcome.put(parameter.getName(), parameter) ;
		}

		return outcome;
	}

	/**
	 * @return a new map of query parameters
	 */
	public Map<String, Parameter> generateParametersQueryMap()
	{
		final Map<String, Parameter> outcome = new HashMap<String, Parameter>() ;
		
		for (final Parameter parameter : this.parametersQueryList)
		{
			outcome.put(parameter.getName(), parameter) ;
		}
		
		return outcome;
	}
}
