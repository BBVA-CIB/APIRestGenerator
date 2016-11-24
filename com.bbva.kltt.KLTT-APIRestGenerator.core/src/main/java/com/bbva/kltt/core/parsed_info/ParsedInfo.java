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

package com.bbva.kltt.core.parsed_info;

import com.bbva.kltt.core.parsed_info.generator_params.GeneratorParams;
import com.bbva.kltt.core.parsed_info.parameters.ParametersGlobal;
import com.bbva.kltt.core.parsed_info.paths.Paths;
import com.bbva.kltt.core.util.mapper.JacksonMapper;
import com.bbva.kltt.core.util.mapper.JacksonViews;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParsedInfo
{
	/** Root values */
	@JsonView(JacksonViews.GeneratorView.class)
	private RootValues rootValues ;
	
	/** Info values */
	@JsonView(JacksonViews.GeneratorView.class)
	private InfoValues infoValues ;
	
	/** Definitions */
	@JsonView(JacksonViews.GeneratorView.class)
	private Definitions definitions ;
	
	/** Global Parameters */
	@JsonView(JacksonViews.GeneratorView.class)
	private ParametersGlobal parametersGlobal ;
	
	/** Responses */
	@JsonView(JacksonViews.GeneratorView.class)
	private Responses responses ;
	
	/** Paths */
	@JsonView(JacksonViews.GeneratorView.class)
	private Paths paths ;
	
	/** Generator properties Params */
	@JsonView(JacksonViews.GeneratorView.class)
	private GeneratorParams generatorParams ;
	
	/**
	 * @return the rootValues
	 */
	public RootValues getRootValues()
	{
		return this.rootValues ;
	}
	
	/**
	 * @param rootValues the rootValues to set
	 */
	public void setRootValues(final RootValues rootValues)
	{
		this.rootValues = rootValues ;
	}
	
	/**
	 * @return the infoValues
	 */
	public InfoValues getInfoValues()
	{
		return this.infoValues ;
	}

	/**
	 * @param infoValues the infoValues to set
	 */
	public void setInfoValues(final InfoValues infoValues)
	{
		this.infoValues = infoValues ;
	}
	
	/**
	 * @return the definitions
	 */
	public Definitions getDefinitions()
	{
		return this.definitions ;
	}

	/**
	 * @param definitions the definitions to set
	 */
	public void setDefinitions(final Definitions definitions)
	{
		this.definitions = definitions ;
	}
	
	/**
	 * @return the global parameters
	 */
	public ParametersGlobal getParametersGlobal()
	{
		return this.parametersGlobal ;
	}

	/**
	 * @param parametersGlobal the global parameters to set
	 */
	public void setParametersGlobal(final ParametersGlobal parametersGlobal)
	{
		this.parametersGlobal = parametersGlobal ;
	}
	
	/**
	 * @return the responses
	 */
	public Responses getResponses()
	{
		return this.responses ;
	}

	/**
	 * @param responses with the responses
	 */
	public void setResponses(final Responses responses)
	{
		this.responses = responses ;
	}
	
	/**
	 * @return the paths
	 */
	public Paths getPaths()
	{
		return this.paths ;
	}

	/**
	 * @param paths with the paths
	 */
	public void setPaths(Paths paths)
	{
		this.paths = paths ;
	}
	
	/**
	 * @return the generatorParams
	 */
	public GeneratorParams getGeneratorParams()
	{
		return this.generatorParams ;
	}

	/**
	 * @param generatorParams with the Generator properties parameters
	 */
	public void setGeneratorParams(final GeneratorParams generatorParams)
	{
		this.generatorParams = generatorParams ;
	}
	
	@Override
	public String toString()
	{
		try
	    {
	      return JacksonMapper.getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(this);
	    }
	    catch (JsonProcessingException jsonProcessingExc)
	    {
	      return "Exception converting to Json string: " + jsonProcessingExc.getMessage() ;
	    }
	}
}
