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

package com.bbva.kltt.generator.clientTypescript.typescript.output.language;

import com.bbva.kltt.core.generator.output.language.OutputLanguageConsumesProduces;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageConsumesProducesTypescript extends OutputLanguageConsumesProduces
{
	/**
	 * Public constructor
	 * @param parsedInfoHandler with the parsed information handler
	 */
	public OutputLanguageConsumesProducesTypescript(final ParsedInfoHandler parsedInfoHandler)
	{
		super(parsedInfoHandler, new OutputLanguageSeparatorsTypescript()) ;
	}
	
	@Override
	public String generateInitialConsumesOrProducesString()
	{
		// There is only one consumes in TypeScript, then this delimiter is empty
		// (This restriction was explained in the method "getConsumesOrProducesString" - class OutputLanguageConsumesProduces)
		return ConstantsCommon.STRING_EMPTY ;
	}

	@Override
	public String generateFinalConsumesOrProducesString()
	{
		// There is only one consumes in TypeScript, then this delimiter is empty
		// (This restriction was explained in the method "getConsumesOrProducesString" - class OutputLanguageConsumesProduces)
		return ConstantsCommon.STRING_EMPTY ;
	}
	
	/**
	 * Redefine the parent method to calculate what contentType should be used (global or specific)
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new String with the consumes for the pathValue-pathOperation
	 */
	@Override
	public String generateInboundServerContentType(final String pathValue, final String pathOperation)
	{
		String outcome = super.generateInboundServerContentType(pathValue, pathOperation) ;
		
		if (outcome == null || outcome.isEmpty())
		{
			outcome = super.generateInboundServerContentType() ;
		}
		
		return outcome ;
	}
}
