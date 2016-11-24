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

package com.bbva.kltt.parser.swagger.definitions;

import com.bbva.kltt.core.parsed_info.Definitions;
import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.parser.swagger.util.ItemParser;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsInput;
import com.bbva.kltt.core.util.parser.ParserUtil;
import com.bbva.kltt.parser.swagger.util.ConstantsInputParser;
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
public class DefinitionsParser extends ItemParser
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefinitionsParser.class) ;
    
    /** Attribute - Definitions */
    private final Definitions definitions ;
    
	/**
	 * Public constructor
	 * @param definitions with the definitions
	 */
	public DefinitionsParser(final Definitions definitions)
	{
		super(definitions.getItemFactory()) ;
		
		this.definitions = definitions ;
	}
	
	/**
	 * Parse the definitions
	 * @param fileContent with the file content
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void parseDefinitions(final JsonNode fileContent) throws APIRestGeneratorException
	{
		final JsonNode definitionsNode = fileContent.get(ConstantsInputParser.SW_MAIN_SCH_DEFINIT) ;
		if (definitionsNode != null && definitionsNode.isObject())
		{
			// Init the main definitions map
			this.initMainDefinitions(definitionsNode, this.definitions.getItemsMap()) ;
			
			// Parse the full item objects
			this.parseItemObjects(definitionsNode, this.definitions.getItemsMap()) ;
		}
	}
	
	/**
	 * Initialize the main items map to avoid "ref" problems
	 * @param definitionsNode with the root definitions node
	 * @param mainItemsMap 	  with the main items map
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void initMainDefinitions(final JsonNode definitionsNode, final Map<String, Item> mainItemsMap) throws APIRestGeneratorException
	{
		final Iterator<String> keyDefinitions = definitionsNode.fieldNames() ;
		while (keyDefinitions.hasNext())
		{
			// Get the key definition (future class name)
			final String keyDefinition = keyDefinitions.next() ;
			
			// Validate the type of the keyDefinition content
			this.validateKeyDefinition(definitionsNode, keyDefinition) ;
			
			// Put the key definition into the map (it is useful for definitions which have "$ref" attribute) 
			mainItemsMap.put(keyDefinition, null) ;
		}
	}
	
	/**
	 * Validate the key definition of the first level only contains "object" type
	 * @param definitionsNode with the root definitions node
	 * @param nodeName  	  with the node name
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void validateKeyDefinition(final JsonNode definitionsNode, final String nodeName) throws APIRestGeneratorException
	{
		// Get the attribute node to verify the content (if it has a $ref or type attribute)
		final JsonNode attributeNode = ParserUtil.getNodeValueJson(ConstantsInputParser.SW_MAIN_SCH_DEFINIT, definitionsNode, nodeName, true) ;
		
		// Get the values of $ref or type
		final String ref  = ParserUtil.getNodeValueField(nodeName, attributeNode, ConstantsInputParser.SW_COMMON_REF, false)  ;
		final String type = ParserUtil.getNodeValueField(nodeName, attributeNode, ConstantsInputParser.SW_COMMON_TYPE, false)  ;

		String errorString = null ;
		if ((ref == null || ref.isEmpty()) && (type == null || type.isEmpty()))
		{
			errorString = "The node '" + nodeName  + "' must contain any of the following attributes to be defined: " +  
						  ConstantsInputParser.SW_COMMON_TYPE + " or " + ConstantsInputParser.SW_COMMON_REF ;
		}
		else if (type != null && !ConstantsInput.JSON_TYPE_OBJECT.equalsIgnoreCase(type))
		{
			errorString = "The node '" + nodeName + "' is using the 'type' attribute, but it should contain the following the value: " + 
						  ConstantsInput.JSON_TYPE_OBJECT ;
		}
		
		if (errorString != null)
		{
			DefinitionsParser.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
	}

	/**
	 * @return the definitions
	 */
	public Definitions getDefinitions()
	{
		return this.definitions ;
	}
}
