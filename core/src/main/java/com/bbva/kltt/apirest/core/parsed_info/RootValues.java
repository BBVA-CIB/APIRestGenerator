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

import java.util.Set;

import com.bbva.kltt.apirest.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RootValues
{
	/** Host */
	@JsonView(GeneratorView.class)
	private final String host ;
	
	/** Base path */
	@JsonView(GeneratorView.class)
	private final String basePath ;
	
	/** Schemes list */
	@JsonView(GeneratorView.class)
	private final Set<Scheme> schemes ;
	
	/** Consumes list */
	@JsonView(GeneratorView.class)
	private final Set<String> consumes ;
	
	/** Produces list */
	@JsonView(GeneratorView.class)
	private final Set<String> produces ;
	
	/**
	 * Public constructor
	 * @param host     with the host
	 * @param basePath with the basePath
	 * @param schemes  with the schemes
	 * @param consumes with the consumes
	 * @param produces with the produces
	 */
	public RootValues(final String host,
					  final String basePath,
					  final Set<Scheme> schemes,
					  final Set<String> consumes,
					  final Set<String> produces)
	{
		this.host  	  = host     ;
		this.basePath = basePath ;
		this.schemes  = schemes  ;
		this.consumes = consumes ;
		this.produces = produces ;
	}

	/**
	 * @return the host
	 */
	public String getHost()
	{
		return this.host ;
	}
	
	/**
	 * @return the basePath
	 */
	public String getBasePath()
	{
		return this.basePath ;
	}

	/**
	 * @return the schemes
	 */
	public Set<Scheme> getSchemes()
	{
		return this.schemes ;
	}
	
	/**
	 * @return the consumes
	 */
	public Set<String> getConsumes()
	{
		return this.consumes ;
	}

	/**
	 * @return the produces
	 */
	public Set<String> getProduces()
	{
		return this.produces ;
	}
}
