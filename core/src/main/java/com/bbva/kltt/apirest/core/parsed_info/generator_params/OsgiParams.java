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

package com.bbva.kltt.apirest.core.parsed_info.generator_params;

import com.bbva.kltt.apirest.core.util.mapper.JacksonViews.GeneratorView;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OsgiParams
{
	/** Attribute - architecture type */
	@JsonView(GeneratorView.class)
	private String architectureType ;
	
	/** Attribute - CXF Address */
	@JsonView(GeneratorView.class)
	private String cxfAddress ;
	
	/** Attribute - CXF Context */
	@JsonView(GeneratorView.class)
	private String cxfContext ;
	
	/**
	 * Protected constructor
	 */
	protected OsgiParams()
	{
		// Empty constructor
	}
	
	/**
	 * @return the architectureType
	 */
	public String getArchitectureType()
	{
		return this.architectureType ;
	}

	/**
	 * @param architectureType the architectureType to set
	 */
	public void setArchitectureType(final String architectureType)
	{
		this.architectureType = architectureType ;
	}

	/**
	 * @return the cxfAddress
	 */
	public String getCxfAddress()
	{
		return this.cxfAddress ;
	}

	/**
	 * @param cxfAddress the cxfAddress to set
	 */
	public void setCxfAddress(final String cxfAddress)
	{
		this.cxfAddress = cxfAddress ;
	}

	/**
	 * @return the cxfContext
	 */
	public String getCxfContext()
	{
		return this.cxfContext ;
	}

	/**
	 * @param cxfContext the cxfContext to set
	 */
	public void setCxfContext(final String cxfContext)
	{
		this.cxfContext = cxfContext ;
	}
}
