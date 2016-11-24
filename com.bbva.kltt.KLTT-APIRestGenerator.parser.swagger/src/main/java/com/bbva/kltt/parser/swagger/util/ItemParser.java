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

package com.bbva.kltt.parser.swagger.util;

import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.parsed_info.common.ItemArray;
import com.bbva.kltt.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.core.parsed_info.common.ItemRef;
import com.bbva.kltt.core.parser.IItemFactory;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.parser.ParserUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemParser
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemParser.class) ;

    /** Attribute - ItemFactory */
    protected final IItemFactory itemFactory ;

	/**
	 * Protected constructor
	 * @param itemFactory with the item factory
	 */
    protected ItemParser(final IItemFactory itemFactory)
    {
    	this.itemFactory = itemFactory ;
    }

	/**
	 * Parse the full item objects
	 * @param nodeToInspect   with the node to inspect
	 * @param currentItemsMap with the current items objects map
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	protected void parseItemObjects(final JsonNode nodeToInspect, final Map<String, Item> currentItemsMap) throws APIRestGeneratorException
	{
		final Iterator<String> keyDefinitions = nodeToInspect.fieldNames() ;
		while (keyDefinitions.hasNext())
		{
			// Get the key definition (future class name)
			final String keyDefinition 		  = keyDefinitions.next() ;

			// Definition node
			final JsonNode definitionNode 	  = nodeToInspect.get(keyDefinition) ;

			// Parse and generate a new Item with all the children from this keyDefinition
			final Item item					  = this.parseItemObject(keyDefinition, definitionNode) ;

			currentItemsMap.put(keyDefinition, item) ;
		}
	}

	/**
	 * @param keyName with the key name
	 * @param keyNode with the key node
	 * @return a new item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	protected Item parseItemObject(final String keyName, final JsonNode keyNode) throws APIRestGeneratorException
	{
		// Get the description, and the type and required (if both exist)
		final String description = ParserUtil.getNodeValueField(keyName, keyNode, ConstantsInputParser.SW_COMMON_DESCR, false) ;

		return this.parseItemObject(keyName, keyNode, description) ;
	}

	/**
	 * @param keyName 	  with the key name
	 * @param keyNode 	  with the key node
	 * @param description with the description
	 * @return a new item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	protected Item parseItemObject(final String keyName, final JsonNode keyNode, final String description) throws APIRestGeneratorException
	{
		final String alias     = ParserUtil.getNodeValueField(keyName, keyNode,  ConstantsInputParser.SW_COMMON_ALIAS,    false) ;
		final String type      = ParserUtil.getNodeValueField(keyName, keyNode,  ConstantsInputParser.SW_COMMON_TYPE,     false) ;
		final String format    = ParserUtil.getNodeValueField(keyName, keyNode,  ConstantsInputParser.SW_COMMON_FORMAT,   false) ;
		final String required  = ParserUtil.getNodeValueField(keyName, keyNode,  ConstantsInputParser.SW_COMMON_REQUIRED, false) ;
		final String reference = ParserUtil.getNodeValueField(keyName, keyNode,  ConstantsInputParser.SW_COMMON_REF,      false) ;

		Item item = null ;
		if (reference != null)
		{
			item = this.parseItemRef(keyName, alias, description, reference) ;
		}
		else if (this.itemFactory.isArrayItem(type))
		{
			item = this.parseItemArray(keyName, keyNode, alias, description, required) ;
		}
		else if (this.itemFactory.isComplexItem(type))
		{
			item = this.parseItemComplex(keyName, keyNode, alias, description, required) ;
		}
		else if (this.itemFactory.isFileItem(type))
		{
			item = this.parseItemFile(keyName, alias, description, required) ;
		}
		else // It should be SimpleType
		{
			item = this.parseItemSimple(keyName, alias, description, required, type, format) ;
		}

		return item ;
	}

	/**
	 * Parse the item reference
	 * @param name 	  	  with the item name
	 * @param alias   	  with the alias name
	 * @param description with the description
	 * @param reference   with the reference
	 * @return a new Item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	protected ItemRef parseItemRef(final String name, final String alias, final String description, final String reference) throws APIRestGeneratorException
	{
		// A reference object is always required, then "true" value
		return this.itemFactory.createNewItemRef(name, alias, description, "true", reference) ;
	}

	/**
	 * Parse the item array
	 * @param nodeName 	   with the node name
	 * @param nodeJson	   with the definition node
	 * @param alias   	   with the alias name
	 * @param description  with the description
	 * @param required	   true if the array is required
	 * @return a new Item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Item parseItemArray(final String nodeName,
								final JsonNode nodeJson,
								final String alias,
								final String description,
								final String required) throws APIRestGeneratorException
	{
		Item item			 	 = null ;

		final JsonNode itemsNode = ParserUtil.getNodeValueJson(nodeName,  nodeJson,  ConstantsInputParser.SW_COMMON_ARR_ITEM, true) ;
		final String arrayType   = ParserUtil.getNodeValueField(nodeName, itemsNode, ConstantsInputParser.SW_COMMON_TYPE,     false) ;
		final String arrayFormat = ParserUtil.getNodeValueField(nodeName, itemsNode, ConstantsInputParser.SW_COMMON_FORMAT,   false) ;
		final String arrayRef    = ParserUtil.getNodeValueField(nodeName, itemsNode, ConstantsInputParser.SW_COMMON_REF,      false) ;

		if ((arrayType == null || arrayType.isEmpty()) && (arrayRef  == null || arrayRef.isEmpty()))
		{
			final String errorString = "The node '" + nodeName + "' must contain any of these attributes: " +
									   ConstantsInputParser.SW_COMMON_TYPE + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK + ConstantsInputParser.SW_COMMON_REF  ;

			ItemParser.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
		else
		{
			item = this.parseItemArray(nodeName, alias, description, required, arrayType, arrayFormat, arrayRef, itemsNode) ;
		}

		return item ;
	}

	/**
	 * Parse the item array
	 * @param nodeName 	   with the node name
	 * @param alias   	   with the alias name
	 * @param description  with the description
	 * @param required	   true if the array is required
	 * @param arrayType    with the array type (if exists)
	 * @param arrayFormat  with the array format (if exists)
	 * @param arrayRef     with the array ref (if exists)
	 * @param itemsNode    with the json with the items node
	 * @return a new Item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Item parseItemArray(final String nodeName,
								final String alias,
								final String description,
								final String required,
								final String arrayType,
								final String arrayFormat,
								final String arrayRef,
								final JsonNode itemsNode) throws APIRestGeneratorException
	{
		final Item item = this.itemFactory.createNewArray(nodeName, alias, description, required) ;
		Item itemArray = null ;

		if (arrayType != null && !arrayType.isEmpty())
		{
			if (this.itemFactory.isSimpleItem(arrayType))
			{
				itemArray = this.itemFactory.createNewSimpleItem(nodeName, alias, description, required, arrayType, arrayFormat) ;
			}
			else if (this.itemFactory.isArrayItem(arrayType))
			{
				itemArray = this.parseItemArray(nodeName, itemsNode, alias, description, required) ;
			}
			else if (this.itemFactory.isFileItem(arrayType))
			{
				final String errorString = "Cannot upload/download multiple files in one parameter: " + nodeName  ;

				ItemParser.LOGGER.error(errorString) ;
				throw new APIRestGeneratorException(errorString) ;
			}
		}
		else
		{
			itemArray = this.parseItemRef(nodeName, alias, description, arrayRef) ;
		}

		// Set the item to the item array
		((ItemArray) item).setItemArray(itemArray) ;

		return item ;
	}

	/**
	 * @param nodeName    		with the key definition
	 * @param nodeJson	   		with the complex node
	 * @param alias				with the alias name
	 * @param description 		with the description
	 * @param required 			true if it has some internal properties
	 * @return a new instance of Item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Item parseItemComplex(final String nodeName,
								  final JsonNode nodeJson,
								  final String alias,
								  final String description,
								  final String required) throws APIRestGeneratorException
	{
		final JsonNode propertiesNode = ParserUtil.getNodeValueJson(nodeName, nodeJson, ConstantsInputParser.SW_DEFIN_SUBSC_PROPE, true) ;
		final ItemComplex itemComplex = this.itemFactory.createNewItemComplex(nodeName, alias, description, required) ;

		// Recursive call for internal properties
		this.parseItemObjects(propertiesNode, itemComplex.getItemsMap()) ;

		return itemComplex ;
	}

	/**
	 * Parse the property of the definition object file
	 * @param name 		  with the field name
	 * @param alias		  with the alias name
	 * @param description with the description
	 * @param required	  true if the array is required
	 * @return a new instance of Item File
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Item parseItemFile(final String name,
                               final String alias,
                               final String description,
                               final String required) throws APIRestGeneratorException
	{
		return this.itemFactory.createNewItemFile(name, alias, description, required) ;
	}

	/**
	 * Parse the property of the definition object
	 * @param name 		  with the field name
	 * @param alias		  with the alias name
	 * @param description with the description
	 * @param required	  true if the array is required
	 * @param type		  with the type
	 * @param format	  with the format
	 * @return a new instance of Item
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Item parseItemSimple(final String name,
								 final String alias,
								 final String description,
								 final String required,
								 final String type,
								 final String format) throws APIRestGeneratorException
	{
		return this.itemFactory.createNewSimpleItem(name, alias, description, required, type, format) ;
	}
}
