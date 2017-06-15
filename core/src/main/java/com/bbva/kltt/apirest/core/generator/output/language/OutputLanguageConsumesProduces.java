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

import java.util.Set;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class OutputLanguageConsumesProduces extends OutputLanguageBase implements IOutputLanguageConsumesProduces
{
	/** Output language separators */
	private final IOutputLanguageSeparators outputLangSeparators ;
	
	/**
	 * Public constructor
	 * @param parsedInfoHandler    with the parsed information handler
	 * @param outputLangSeparators with the output language separators
	 */
	public OutputLanguageConsumesProduces(final ParsedInfoHandler parsedInfoHandler, final IOutputLanguageSeparators outputLangSeparators)
	{
		super(parsedInfoHandler) ;
		
		this.outputLangSeparators = outputLangSeparators ;
	}
	
	@Override
	public String generateInboundServerContentType()
	{
		String outcome = null ;
		
		final Set<String> consumes = this.getParsedInfoHandler().getConsumes() ;
		if (consumes != null && !consumes.isEmpty())
		{
			outcome = this.getConsumesOrProducesString(consumes) ;
		}
		
		return outcome ;
	}

	@Override
	public String generateInboundServerContentType(final String pathValue, final String pathOperation)
	{
		String outcome = null ;
		
		final Set<String> consumes = this.getParsedInfoHandler().getConsumes(pathValue, pathOperation) ;
		if (consumes != null && !consumes.isEmpty())
		{
			outcome = this.getConsumesOrProducesString(consumes) ;
		}
		
		return outcome ;
	}
	
	@Override
	public String generateOutboundServerContentType()
	{
		String outcome = null ;
		
		final Set<String> produces = this.getParsedInfoHandler().getProduces() ;
		if (produces != null && !produces.isEmpty())
		{
			outcome = this.getConsumesOrProducesString(produces) ;
		}
		
		return outcome ;
	}
	
	@Override
	public String generateOutboundServerContentType(final String pathValue, final String pathOperation)
	{
		String outcome = null ;
		
		final Set<String> produces = this.getParsedInfoHandler().getProduces(pathValue, pathOperation) ;
		if (produces != null && !produces.isEmpty())
		{
			outcome = this.getConsumesOrProducesString(produces) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param sources with the sources
	 * @return the string with the consumes or produces
	 */
	private String getConsumesOrProducesString(final Set<String> sources)
	{
		final StringBuilder stringBuilder = new StringBuilder(this.generateInitialConsumesOrProducesString()) ;
		
		// This is a restriction: we only get the first consumes or produces defined
		// It is impossible all of them in Web languages like TypeScript or JavaScript
		// because the expected Content-Type is only one.
		
		stringBuilder.append(this.outputLangSeparators.generateStringDelimiter()) ;
		stringBuilder.append(sources.iterator().next()) ;
		stringBuilder.append(this.outputLangSeparators.generateStringDelimiter()) ;
		
//		for (final String source : sources)
//		{
//			if (!this.generateInitialConsumesOrProducesString().equals(stringBuilder.toString()))
//			{
//				stringBuilder.append(this.outputLangSeparators.generateSeparatorValues()) ;
//			}
//			
//			stringBuilder.append(this.outputLangSeparators.generateStringDelimiter()) ;
//			stringBuilder.append(source) ;
//			stringBuilder.append(this.outputLangSeparators.generateStringDelimiter()) ;
//		}
		
		// Add the final string
		stringBuilder.append(this.generateFinalConsumesOrProducesString()) ;
		
		return stringBuilder.toString() ;
	}
}
