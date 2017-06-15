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
public class GeneratorParams
{
	/** Attribute - Business Unit */
	@JsonView(GeneratorView.class)
	private String bUnit ;
	
	/** Attribute - OSGi Parameters */
	@JsonView(GeneratorView.class)
	private final OsgiParams osgiParams ;

	/**
	 * Public constructor
	 */
	public GeneratorParams()
	{
		this.osgiParams = new OsgiParams() ;
	}

	/**
	 * @return the business unit
	 */
	public String getBUnit()
	{
		return this.bUnit ;
	}

	/**
	 * @param bUnit the business unit to set
	 */
	public void setBUnit(final String bUnit)
	{
		this.bUnit = bUnit ;
	}

	/**
	 * @param architectureType with the architecture type
	 * @param cxfAddress 	   with the CXF Address
	 * @param cxfContext 	   with the CXF Context
	 */
	public void addOsgiParams(final String architectureType, final String cxfAddress, final String cxfContext)
	{
		this.osgiParams.setArchitectureType(architectureType) ;
		this.osgiParams.setCxfAddress(cxfAddress) ;
		this.osgiParams.setCxfContext(cxfContext) ;
	}

	/**
	 * @return the osgiParams
	 */
	public OsgiParams getOsgiParams()
	{
		return this.osgiParams ;
	}
}
