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

import com.bbva.kltt.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageNaming implements IOutputLanguageNaming
{
	@Override
	public String prefixClassName(final String complexTypeName)
	{
		return this.suffixMethodName(complexTypeName) ;
	}
	
	@Override
	public String suffixMethodName(final String attributeName)
	{
		if (attributeName.length() == 1)
		{
			return attributeName.toUpperCase() ;
		}

		return Character.toUpperCase(attributeName.charAt(0)) + attributeName.substring(1) ;
	}
	
	@Override
	public String prefixAttributeName(final String attributeName)
	{
		if (attributeName.length() == 1)
		{
			return attributeName.toLowerCase() ;
		}

		return Character.toLowerCase(attributeName.charAt(0)) + attributeName.substring(1) ;
	}
	
	@Override
	public String getUrlWithSlashes(final String...httpTokens)
	{
		final StringBuffer outcome = new StringBuffer() ;
		
		if (httpTokens != null && httpTokens.length > 0)
		{
			final int tokensLength = httpTokens.length ;
			for (int i=0 ; i < tokensLength ; i++)
			{
				String httpToken = httpTokens[i] ;
				if (httpToken != null && !httpToken.isEmpty())
				{
					// If it starts with "/" then remove it 
					if (httpToken.startsWith(ConstantsCommon.STRING_SLASH))
					{
						httpToken = httpToken.substring(1) ;
					}
					
					// If it does not finish with "/" then add it
					if (!httpToken.endsWith(ConstantsCommon.STRING_SLASH))
					{
						httpToken = httpToken + ConstantsCommon.STRING_SLASH ;
					}
					
					outcome.append(httpToken) ;
				}
			}
		}
		
		return outcome.toString() ;
	}
}
