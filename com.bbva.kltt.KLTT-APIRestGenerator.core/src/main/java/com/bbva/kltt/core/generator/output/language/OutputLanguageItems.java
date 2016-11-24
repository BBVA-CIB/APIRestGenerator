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

import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.parsed_info.common.*;

import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class OutputLanguageItems extends OutputLanguageBase implements IOutputLanguageItems
{
	/**
	 * Public constructor
	 * @param parsedInfoHandler with the parsed information handler
	 */
	public OutputLanguageItems(final ParsedInfoHandler parsedInfoHandler)
	{
		super(parsedInfoHandler) ;
	}
	
	@Override
	public boolean isSimpleType(final Item outputItem)
	{
		boolean isSimpleTypeG1 = outputItem instanceof ItemSimpleBoolean || outputItem instanceof ItemSimpleInteger || outputItem instanceof ItemSimpleLong   ;
		
		boolean isSimpleTypeG2 = outputItem instanceof ItemSimpleFloat   || outputItem instanceof ItemSimpleDouble  || outputItem instanceof ItemSimpleString ;
		
		return isSimpleTypeG1 || isSimpleTypeG2 ;
	}

	@Override
	public String getFullTypeOutput(final Item outputItem)
	{
		// Initially, we do not know if the type is array then 'false'
		return this.getFullTypeOutputInternal(outputItem, false) ;
	}
	
	/**
	 * @param outputItem with the output item
	 * @param isArrayBaseType true if it is a base type for the array
	 * @return a base type
	 */
	private String getFullTypeOutputInternal(final Item outputItem, final boolean isArrayBaseType)
	{
		String outcome = this.getFullTypeOutputSimple(outputItem, isArrayBaseType) ;
		
		if (outcome == null)
		{
			outcome = this.getFullTypeOutputArray(outputItem) ;
		}
		
		if (outcome == null)
		{
			outcome = this.getFullTypeOutputOthers(outputItem) ;
		}
		
		if (outcome == null)
		{
			outcome = this.getTypeOutputDefault() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param outputItem with the output item
	 * @param isArrayBaseType true if it is a base type for the array
	 * @return a simple type
	 */
	private String getFullTypeOutputSimple(final Item outputItem, final boolean isArrayBaseType)
	{
		String outcome = null ;
		
		if (outputItem instanceof ItemSimpleBoolean)
		{
			outcome = this.getTypeOutputSimpleBoolean(isArrayBaseType) ;
		}
		else if (outputItem instanceof ItemSimpleInteger)
		{
			outcome = this.getTypeOutputSimpleInteger(isArrayBaseType) ;
		}
		else if (outputItem instanceof ItemSimpleLong)
		{
			outcome = this.getTypeOutputSimpleLong(isArrayBaseType) ;
		}
		else if (outputItem instanceof ItemSimpleFloat)
		{
			outcome = this.getTypeOutputSimpleFloat(isArrayBaseType) ;
		}
		else if (outputItem instanceof ItemSimpleDouble)
		{
			outcome = this.getTypeOutputSimpleDouble(isArrayBaseType) ;
		}
		else if (outputItem instanceof ItemSimpleString)
		{
			outcome = this.getTypeOutputSimpleString(isArrayBaseType) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param outputItem with the output item
	 * @return an array type
	 */
	private String getFullTypeOutputArray(final Item outputItem)
	{
		String outcome = null ;
		
		if (outputItem instanceof ItemArray)
		{
			final String simpleType   		= this.getArraySimpleType(outputItem) ;
			final int depth			  		= ((ItemArray)outputItem).calculateDepth() ;
			
			final StringBuffer stringBuffer = new StringBuffer(simpleType) ;
			for (int i=0 ; i < depth ; i++)
			{
				stringBuffer.append(this.typeOutputArray()) ;
			}
			
			outcome = stringBuffer.toString() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param outputItem with the output item
	 * @return the simple base type
	 */
	private String getArraySimpleType(final Item outputItem)
	{
		final Item itemSimpleType = ((ItemArray)outputItem).calculateBaseType() ;
		return this.getFullTypeOutputInternal(itemSimpleType, true) ;
	}
	
	/**
	 * This is a common method for the previous methods
	 * @param outputItem with the output item
	 * @return the type output
	 */
	private String getFullTypeOutputOthers(final Item outputItem)
	{
		String outcome = null ;
		
		if (outputItem instanceof ItemRef)
		{
			outcome = this.getTypeOutputItemRef((ItemRef)outputItem) ;
		}
		else if (outputItem instanceof ItemComplex)
		{
			outcome = this.getTypeOutputItemComplex((ItemComplex)outputItem) ;
		}
		else if (outputItem instanceof ItemFile)
		{
			outcome = this.getTypeOutputItemFile() ;
		}
		
		return outcome ;
	}
	
	@Override 
	public String getTypeBaseArray(final Item outputItem)
	{
		return this.getArraySimpleType(outputItem) ;
	}
	
	@Override
	public Set<String> getDefinitionsListNames()
	{
		return this.getParsedInfoHandler().getDefinitionsListNames() ;
	}
	
	@Override
	public Item getOutboundServerItemType(final String pathValue, final String pathOperation)
	{
		return this.getParsedInfoHandler().getOutboundServerItemType(pathValue, pathOperation) ;
	}
	
	@Override
	public int calculateDepth(final Item item)
	{
		return ((ItemArray) item).calculateDepth() ;
	}
	
	@Override
	public Item calculateBaseType(final Item item)
	{
		return ((ItemArray) item).calculateBaseType() ;
	}
}
