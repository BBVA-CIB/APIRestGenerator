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

package com.bbva.kltt.core.generator.output.language;

import com.bbva.kltt.core.parsed_info.Scheme;

import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IOutputLanguageRootValues
{
	/**
	 * @return the host
	 */
	public String getHost() ;
	
	/**
	 * @return the only the port
	 */
	public int getOnlyPort() ;
	
	/**
	 * @return the base path
	 */
	public String getBasePath() ;
	
	/**
	 * @return the client base path
	 */
	public String getClientBasePath() ;
	
	/**
	 * @param pathValue     with the path value
	 * @param pathOperation with the path operation
	 * @return the schemes
	 */
	public Set<Scheme> getSchemes(final String pathValue, final String pathOperation) ;
	
	/**
	 * @param pathValue     with the path value
	 * @param pathOperation with the path operation
	 * @return one scheme for examples
	 */
	Scheme getOneSchemeExample(final String pathValue, final String pathOperation) ;
}