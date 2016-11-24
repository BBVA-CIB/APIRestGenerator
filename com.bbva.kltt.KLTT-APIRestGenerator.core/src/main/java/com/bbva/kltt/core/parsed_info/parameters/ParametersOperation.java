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

package com.bbva.kltt.core.parsed_info.parameters;

import com.bbva.kltt.core.parsed_info.Definitions;

import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParametersOperation extends ParametersPath
{
	/**
	 * Public constructor
     * @param globalDefinitions with the definitions
     * @param consumes    		with the consumes 
	 */
	public ParametersOperation(final Definitions globalDefinitions, final Set<String> consumes)
	{
		super(globalDefinitions, consumes) ;
	}
}
