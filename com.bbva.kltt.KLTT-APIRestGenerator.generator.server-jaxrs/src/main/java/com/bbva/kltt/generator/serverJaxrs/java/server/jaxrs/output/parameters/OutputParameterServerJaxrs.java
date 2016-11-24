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

package com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs.output.parameters;

import com.bbva.kltt.core.generator.output.parameters.OutputParameter;
import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.generator.util.ConstantsOutputJava;
import com.bbva.kltt.generator.serverJaxrs.util.ConstantsOutputServerJaxrs;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputParameterServerJaxrs extends OutputParameter
{
	/** Attribute - Annotation */
	private String annotation ;
	
	/** Attribute - Prefix Token */
	private String prefixToken ;
	
	/**
	 * Public constructor
	 * @param item		  with the item parameter information
	 * @param description with the description
	 * @param type		  with the type
	 * @param name		  with the name
	 * @param annotation  with the annotation
	 */
	public OutputParameterServerJaxrs(final Item item, final String description, final String type, final String name, final String annotation)
	{
		super(item, description, type, name) ;
		
		this.annotation  = annotation ;
		this.prefixToken = ConstantsOutputJava.JAVA_TOKEN_FINAL ;
	}

	@Override
	public String getOutputTypeName()
	{
		return this.getType() + ConstantsCommon.STRING_BLANK + this.getName() ;
	}
	
	@Override
	public String getParameterHeader()
	{
		return this.prefixToken + ConstantsCommon.STRING_BLANK + this.getOutputTypeName() ;
	}

	@Override
	public String getParameterCall()
	{
		return this.getName() ;
	}

	@Override
	public String getParameterHeaderRest()
	{
		return this.annotation + ConstantsCommon.STRING_BLANK + this.prefixToken + ConstantsCommon.STRING_BLANK + this.getOutputTypeName() ;
	}
	
	@Override
	public String getParameterTest()
	{
		return ConstantsOutputJava.DEFAULT_VAL_TEST_JAVA_NULL ;
	}
	
	public String handlePossibleFormDataAnnotationHeader()
	{
		String outcome = null ;
		
		if (this.isFormDataType())
		{
			outcome = this.prefixToken 								   	   + ConstantsCommon.STRING_BLANK + 
					ConstantsOutputServerJaxrs.JAXRS_FORM_DATA_DETAILS_TYP + ConstantsCommon.STRING_BLANK + 
					  this.getName() 								       + ConstantsOutputServerJaxrs.JAXRS_FORM_DATA_DETAILS_SUF ;
		}
		
		return outcome ;
	}
	
	public String handlePossibleFormDataAnnotationCall()
	{
		String outcome = null ;
		
		if (this.isFormDataType())
		{
			outcome = this.getName() + ConstantsOutputServerJaxrs.JAXRS_FORM_DATA_DETAILS_SUF ;			
		}
		
		return outcome ;
	}

	public String handlePossibleFormDataAnnotationDescription()
	{
		String outcome = null ;
		
		if (this.isFormDataType())
		{
			outcome = this.generateParameterDescription() ;
		}
		
		return outcome ;
	}
	
	public String handlePossibleFormDataAnnotationHeaderRest()
	{
		String outcome = null ;
		
		if (this.isFormDataType())
		{
			outcome = this.annotation  								   		 + ConstantsCommon.STRING_BLANK + 
					  this.prefixToken 								   		 + ConstantsCommon.STRING_BLANK + 
					  ConstantsOutputServerJaxrs.JAXRS_FORM_DATA_DETAILS_TYP + ConstantsCommon.STRING_BLANK + 
					  this.getName() 								   		 + ConstantsOutputServerJaxrs.JAXRS_FORM_DATA_DETAILS_SUF ;
			
		}

		return outcome ;
	}

	public String handlePossibleFormDataAnnotationTest()
	{
		String outcome = null ;
		
		if (this.isFormDataType())
		{
			outcome = ConstantsOutputJava.DEFAULT_VAL_TEST_JAVA_NULL ;
			
		}

		return outcome ;
	}
	
	/**
	 * @return true if it is a form data 
	 */
	private boolean isFormDataType()
	{
		return ConstantsOutputServerJaxrs.JAXRS_FORM_DATA_INPUTSTREAM.equals(this.getType()) ;
	}
}
