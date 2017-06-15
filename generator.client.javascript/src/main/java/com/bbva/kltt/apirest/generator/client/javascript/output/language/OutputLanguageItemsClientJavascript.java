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

package com.bbva.kltt.apirest.generator.client.javascript.output.language;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageNaming;
import com.bbva.kltt.apirest.core.generator.output.language.OutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.OutputLanguageNaming;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageItemsClientJavascript extends OutputLanguageItems
{
	/** Attribute - Parsed information handler */
	private final IOutputLanguageNaming outputLangNaming ;
	
	/**
	 * Public constructor
	 * @param parsedInfoHandler with the parsed information handler
	 */
	public OutputLanguageItemsClientJavascript(final ParsedInfoHandler parsedInfoHandler)
	{
		super(parsedInfoHandler) ;
		
		this.outputLangNaming = new OutputLanguageNaming() ;
	}
	
	@Override
	public String typeOutputArray()
	{
		return ConstantsOutput.COMMON_TYPE_ARRAY ;
	}

	@Override
	public String getTypeOutputSimpleBoolean(final boolean isArrayBaseType)
	{
		return ConstantsOutputClientWeb.WEB_TYPE_BOOLEAN ;
	}

	@Override
	public String getTypeOutputSimpleInteger(final boolean isArrayBaseType)
	{
		return ConstantsOutputClientWeb.WEB_TYPE_NUMBER ;
	}
	
	@Override
	public String getTypeOutputSimpleLong(final boolean isArrayBaseType)
	{
		return ConstantsOutputClientWeb.WEB_TYPE_NUMBER ;
	}
	
	@Override
	public String getTypeOutputSimpleFloat(final boolean isArrayBaseType)
	{
		return ConstantsOutputClientWeb.WEB_TYPE_NUMBER ;
	}
	
	@Override
	public String getTypeOutputSimpleDouble(final boolean isArrayBaseType)
	{
		return ConstantsOutputClientWeb.WEB_TYPE_NUMBER ;
	}
	
	@Override
	public String getTypeOutputSimpleString(final boolean isArrayBaseType)
	{
		return ConstantsOutputClientWeb.WEB_TYPE_STRING ;
	}
	
	@Override
	public String getTypeOutputItemRef(final ItemRef itemRef)
	{
		return this.outputLangNaming.prefixClassName(itemRef.getItemRef()) ;
	}
	
	@Override
	public String getTypeOutputItemComplex(final ItemComplex itemComplex)
	{
		return this.outputLangNaming.prefixClassName(itemComplex.getName()) ;
	}
	
	@Override
	public String getTypeOutputItemFile()
	{
		return ConstantsOutputClientWeb.WEB_TYPE_FILE ;
	}
	
	@Override
	public String getTypeOutputDefault()
	{
		return "void" ;
	}
}
