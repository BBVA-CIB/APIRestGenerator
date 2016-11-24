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

import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.core.parsed_info.common.ItemRef;

import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IOutputLanguageItems
{
	/**
	 * @param outputItem with the output item
	 * @return true if the item is a simple type
	 */
	boolean isSimpleType(final Item outputItem) ;
	
	/**
	 * Returns the full type for the output
	 * 
	 * @param outputItem with the output item
	 * @return the type for the output
	 */
	String getFullTypeOutput(final Item outputItem) ;
	
	/**
	 * Returns the base type for the array
	 * 
	 * @param outputItem with the output item
	 * @return the base type for the array
	 */
	String getTypeBaseArray(final Item outputItem) ;
	
	/**
	 * @return the definitions names
	 */
	Set<String> getDefinitionsListNames() ;
	
	/**
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return the return item type
	 */
	Item getOutboundServerItemType(final String pathValue, final String pathOperation) ;
	
	/**
	 * @return the type output for array
	 */
	String typeOutputArray() ;
	
	/**
	 * @param isArrayBaseType true if it is a base type for the array
	 * @return the type output for simple boolean
	 */
	String getTypeOutputSimpleBoolean(final boolean isArrayBaseType) ;
	
	/**
	 * @param isArrayBaseType true if it is a base type for the array
	 * @return the type output for simple integer
	 */
	String getTypeOutputSimpleInteger(final boolean isArrayBaseType) ;
	
	/**
	 * @param isArrayBaseType true if it is a base type for the array
	 * @return the type output for simple long
	 */
	String getTypeOutputSimpleLong(final boolean isArrayBaseType) ;
	
	/**
	 * @param isArrayBaseType true if it is a base type for the array
	 * @return the type output for simple float
	 */
	String getTypeOutputSimpleFloat(final boolean isArrayBaseType) ;
	
	/**
	 * @param isArrayBaseType true if it is a base type for the array
	 * @return the type output for simple double
	 */
	String getTypeOutputSimpleDouble(final boolean isArrayBaseType) ;
	
	/**
	 * @param isArrayBaseType true if it is a base type for the array
	 * @return the type output for simple string
	 */
	String getTypeOutputSimpleString(final boolean isArrayBaseType) ;
	
	/**
	 * @param itemRef with the item reference
	 * @return the type output for item reference
	 */
	String getTypeOutputItemRef(final ItemRef itemRef) ;

	/**
	 * @param itemComplex with the item complex
	 * @return the type output for item complex
	 */
	String getTypeOutputItemComplex(final ItemComplex itemComplex) ;
	
	/**
	 * @return the type output for item file
	 */
	String getTypeOutputItemFile() ;
	
	/**
	 * @return the type output for default
	 */
	String getTypeOutputDefault() ;
	
	/**
	 * @param item with the item
	 * @return the depth
	 */
	int calculateDepth(final Item item) ;
	
	/**
	 * @param item with the item
	 * @return the base type of this multiple array
	 */
	Item calculateBaseType(final Item item) ;	
}
