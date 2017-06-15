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

package com.bbva.kltt.apirest.core.generator.output.language;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.Scheme;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageRootValues extends OutputLanguageBase implements IOutputLanguageRootValues
{
	/** Attribute - Output Language Naming */
	private final IOutputLanguageNaming outputLanguageNaming ;
	/** Attribute - Output Language Generator properties parameters */
	private final IOutputLanguageGeneratorParams outputLanguageGeneratorParams ;
	
	/**
	 * Public constructor
	 * @param parsedInfoHandler 	        with the parsed information handler
	 * @param outputLanguageNaming	        with the Output Language Naming
	 * @param outputLanguageGeneratorParams with the Output Language Generator properties Parameters
	 */
	public OutputLanguageRootValues(final ParsedInfoHandler parsedInfoHandler,
									final IOutputLanguageNaming outputLanguageNaming,
									final IOutputLanguageGeneratorParams outputLanguageGeneratorParams)
	{
		super(parsedInfoHandler) ;
		
		this.outputLanguageNaming	       = outputLanguageNaming ;
		this.outputLanguageGeneratorParams = outputLanguageGeneratorParams ;
	}
	
	@Override
	public String getHost()
	{
		return this.getParsedInfoHandler().getHost() ;
	}
	
	@Override
	public int getOnlyPort()
	{
		int outcome = ConstantsOutput.DEFAULT_PORT_NUMBER ;
		
		final Pattern pattern = Pattern.compile(ConstantsOutput.PATT_HOST_AND_PORT) ;
		final Matcher matcher = pattern.matcher(this.getHost()) ;
		if (matcher.matches())
		{
			outcome = Integer.valueOf(matcher.group(1)) ;
		}
		
		return outcome ;
	}
	
	@Override
	public String getBasePath()
	{
		return this.getParsedInfoHandler().getBasePath() ;
	}
	
	@Override
	public String getClientBasePath()
	{
		final String architectureT = this.outputLanguageGeneratorParams.getOsgiArchitectureType() ;
		final String cxfContext    = this.outputLanguageGeneratorParams.getOsgiCxfContext() ;
		final String cxfAddress    = this.outputLanguageGeneratorParams.getOsgiCxfAddress() ;
		
		return this.outputLanguageNaming.getUrlWithSlashes(architectureT, cxfContext, cxfAddress, this.getBasePath()) ;
	}
	
	@Override
	public Set<Scheme> getSchemes(final String pathValue, final String pathOperation)
	{
		return this.getParsedInfoHandler().getSchemes(pathValue, pathOperation) ;
	}
	
	@Override
	public Scheme getOneSchemeExample(final String pathValue, final String pathOperation)
	{
		final Set<Scheme> schemes = this.getSchemes(pathValue, pathOperation) ;
		
		return schemes.iterator().next() ;
	}
}
