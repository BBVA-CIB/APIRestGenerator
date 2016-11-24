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

package com.bbva.kltt.generator.clientTypescript.typescript.output.parameters;

import com.bbva.kltt.core.generator.output.parameters.OutputParameter;
import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.generator.clientTypescript.util.ConstantsOutputTypescript;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputParameterTypescript extends OutputParameter
{
	/**
	 * Public constructor
	 * @param item		  with the item parameter information
	 * @param description with the description
	 * @param type		  with the type
	 * @param name		  with the name
	 */
	public OutputParameterTypescript(final Item item, final String description, final String type, final String name)
	{
		super(item, description, type, name) ;
	}
	
	@Override
	public String getOutputTypeName()
	{
		return this.getName() + ConstantsCommon.STRING_COLON + ConstantsCommon.STRING_BLANK + this.getType() ;
	}

	@Override
	public String getParameterHeader()
	{
		return this.getOutputTypeName() ;
	}

	@Override
	public String getParameterCall()
	{
		return this.getName() ;
	}

	@Override
	public String getParameterHeaderRest()
	{
		return this.getOutputTypeName() ;
	}

	@Override
	public String getParameterTest()
	{
		return ConstantsOutputTypescript.DEFAULT_VAL_TEST_TS_NULL ;
	}
}
