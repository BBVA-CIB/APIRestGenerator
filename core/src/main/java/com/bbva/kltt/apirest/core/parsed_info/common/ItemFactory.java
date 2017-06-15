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

package com.bbva.kltt.apirest.core.parsed_info.common;

import com.bbva.kltt.apirest.core.parser.IItemFactory;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsInput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemFactory implements IItemFactory
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemFactory.class) ;
    
    /** Attribute - Main items map */
    private final Map<String, Item> mainItemsMap ;
    
    /**
     * Public constructor
     * @param mainItemsMap with the main items map
     */
    public ItemFactory(final Map<String, Item> mainItemsMap)
    {
    	this.mainItemsMap = mainItemsMap ;
    }
    
	/**
	 * @param type with the type
	 * @return true if the type is simple
	 */
    @Override
	public boolean isSimpleItem(final String type)
	{
    	boolean isSimpleItem = type == null ;
    	if (!isSimpleItem)
    	{
    		final boolean isSimpleItemG1 = type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_STRING)  || type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_BOOLEAN) ;
    		final boolean isSimpleItemG2 = type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_INTEGER) || type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_NUMBER) ;
    		
    		isSimpleItem = isSimpleItemG1 || isSimpleItemG2 ;
    	}
    	
		return isSimpleItem ;
			   
			   
	}

    /**
     * @param name        with the item name
     * @param alias		  with the alias name
     * @param description with the description
	 * @param required	  true if the item is required
     * @param type   	  with the type
     * @param format 	  with the format
     * @return a new item
     * @throws APIRestGeneratorException with an occurred exception
     */
	@Override
	public Item createNewSimpleItem(final String name,
									final String alias,
						   	  		final String description,
						   	  		final String required,
						   	  		final String type,
						   	  		final String format) throws APIRestGeneratorException
	{
		Item item = this.createNewSimpleItemG1(name, alias, description, required, type, format) ;
		
		if (item == null)
		{
			item = this.createNewSimpleItemG2(name, alias, description, required, type, format) ;
		}
		
		return item ;
	}
	
    /**
     * @param name        with the item name
     * @param alias		  with the alias name
     * @param description with the description
	 * @param required	  true if the item is required
     * @param type   	  with the type
     * @param format 	  with the format
     * @return a new item
     * @throws APIRestGeneratorException with an occurred exception
     */
	private Item createNewSimpleItemG1(final String name,
									   final String alias,
						   	  		   final String description,
						   	  		   final String required,
						   	  		   final String type,
						   	  		   final String format) throws APIRestGeneratorException
	{
		Item item = null ;
		
		if (type == null || type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_STRING))
		{
			item = new ItemSimpleString(name, alias, description, required) ;
		}
		else if (type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_BOOLEAN))
		{
			item = new ItemSimpleBoolean(name, alias, description, required) ;
		}
		else if (type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_INTEGER) && (format == null || format.equalsIgnoreCase(ConstantsInput.JSON_FORMAT_INT32)))
		{
			item = new ItemSimpleInteger(name, alias, description, required) ;
	    }
		else if (type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_INTEGER) && (format != null && format.equalsIgnoreCase(ConstantsInput.JSON_FORMAT_INT64)))
		{
			item = new ItemSimpleLong(name, alias, description, required) ;
		}
		
		return item ;
	}
	
    /**
     * @param name        with the item name
     * @param alias		  with the alias name
     * @param description with the description
	 * @param required	  true if the item is required
     * @param type   	  with the type
     * @param format 	  with the format
     * @return a new item
     * @throws APIRestGeneratorException with an occurred exception
     */
	private Item createNewSimpleItemG2(final String name,
									   final String alias,
							  		   final String description,
							  		   final String required,
							  		   final String type,
							  		   final String format) throws APIRestGeneratorException
	{
		Item item = null ;
		
		if (type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_NUMBER) && (format == null || format.equalsIgnoreCase(ConstantsInput.JSON_FORMAT_FLOAT)))
		{
			item = new ItemSimpleFloat(name, alias, description, required) ;
		}
		else if (type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_NUMBER) && (format != null && format.equalsIgnoreCase(ConstantsInput.JSON_FORMAT_DOUBLE)))
		{
			item = new ItemSimpleDouble(name, alias, description, required) ;
		}
		else
		{
			String errorString = "'type' or 'format' values are invalid for the item values: " +
								 "[name: " + name + ", description: " + description + ", type: " + type + ", format: " + format + "]" ;
			
			ItemFactory.LOGGER.error(errorString) ;
			throw new APIRestGeneratorException(errorString) ;
		}
		
		return item ;
	}
	
	/**
	 * @param type with the type
	 * @return true if the type is array type
	 */
	@Override
	public boolean isArrayItem(final String type)
	{
		return type != null && type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_ARRAY) ;
	}
	
    /**
     * @param name        with the item name
     * @param alias	      with the alias name
     * @param description with the description
	 * @param required	  true if the item is required
     * @return a new item array
     * @throws APIRestGeneratorException with an occurred exception
     */
	@Override
	public ItemArray createNewArray(final String name, final String alias, final String description, final String required) throws APIRestGeneratorException
	{
		return new ItemArray(name, alias, description, required) ;
	}

	
    /**
     * @param name        with the item name
     * @param alias	      with the alias name
     * @param description with the description
	 * @param required	  true if the item is required
     * @param reference	  with the reference
     * @return a new instance of ItemRef
     * @throws APIRestGeneratorException with an occurred exception
     */
	@Override
    public ItemRef createNewItemRef(final String name,
    								final String alias,
    								final String description,
    								final String required,
    								final String reference) throws APIRestGeneratorException
    {
    	ItemRef itemRef = null ;
    	
    	final Pattern pattern = Pattern.compile(ConstantsInput.PATT_DEFINITION_REF) ;
		final Matcher matcher = pattern.matcher(reference) ;
		if (matcher.matches())
		{
			final String itemRefString = matcher.group(1) ;
			if (!this.mainItemsMap.containsKey(itemRefString))
			{
				final String errorString = "The following reference was not found in the items map: " + itemRefString ;
				
				ItemFactory.LOGGER.error(errorString) ;
		    	throw new APIRestGeneratorException(errorString) ;
			}
			
			itemRef = new ItemRef(name, alias, description, required, itemRefString) ;
		}
		else
		{
			final String errorString = "The following reference was not defined properly to be search in the items map: " + reference ;
			
			ItemFactory.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
    	
    	return itemRef ;
    }
	
	/**
	 * @param type with the type
	 * @return true if the type is complex type
	 */
    @Override
	public boolean isComplexItem(final String type)
	{
		return type != null && type.equalsIgnoreCase(ConstantsInput.JSON_TYPE_OBJECT) ;
	}
	
	/**
	 * @param name 		  with the name
	 * @param alias	      with the alias name
	 * @param description with the description
	 * @param required	  true if the item is required
	 * @return a new item complex
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	@Override
	public ItemComplex createNewItemComplex(final String name, final String alias, final String description, final String required) throws APIRestGeneratorException
	{
		return new ItemComplex(name, alias, description, required) ;
	}

	/**
	 * @param type with the type
	 * @return true if the type is file type
	 */
	@Override
	public boolean isFileItem(final String type)
	{
		return type != null && type.equalsIgnoreCase(ConstantsCommon.TYPE_FILE) ;
	}
	
	/**
	 * @param name 		  with the name
	 * @param description with the description
	 * @param required	  true if the item is required
	 * @return a new item file
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	@Override
	public ItemFile createNewItemFile(final String name, final String alias, final String description, final String required) throws APIRestGeneratorException
	{
		return new ItemFile(name, alias, description, required) ;
	}
}
