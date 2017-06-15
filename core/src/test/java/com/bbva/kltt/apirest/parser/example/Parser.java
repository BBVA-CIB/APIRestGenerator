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

package com.bbva.kltt.apirest.parser.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfo;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathsTest;
import com.bbva.kltt.apirest.core.parser.IParser;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Parser implements IParser
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class) ;
	
	/** File path */
	private final String filePath ;
	
	/**
	 * Public constructor
	 * @param filePath with the file path
	 */
	public Parser(final String filePath)
	{
		this.filePath = filePath ;
	}
    
	/**
	 * Parse the input schemas and return the parsed information
	 * @return the parsed info
	 * @throws APIRestGeneratorException exception thrown if there is a problem
	 */
	@Override
	public ParsedInfo parse() throws APIRestGeneratorException
	{
		// Generate a new instance of the parsed info
		final ParsedInfo parsedInfo 			= new ParsedInfo() ;
		
		parsedInfo.setPaths(PathsTest.generateDummyPaths()) ;
		
		// Logging the parsed result
		if (Parser.LOGGER.isDebugEnabled())
		{
			Parser.LOGGER.debug(parsedInfo.toString()) ;
		}
		
		return parsedInfo ;
	}
	
	@Override
	public String getParserProjectInfo()
	{
		return this.filePath ;
	}
}
