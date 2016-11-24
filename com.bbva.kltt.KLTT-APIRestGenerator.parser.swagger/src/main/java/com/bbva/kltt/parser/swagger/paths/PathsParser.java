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

package com.bbva.kltt.parser.swagger.paths;

import com.bbva.kltt.core.parsed_info.operations.Operation;
import com.bbva.kltt.core.parsed_info.parameters.ParametersPath;
import com.bbva.kltt.core.parsed_info.paths.Path;
import com.bbva.kltt.core.parsed_info.paths.Paths;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.parser.ParserUtil;
import com.bbva.kltt.parser.swagger.operations.OperationParser;
import com.bbva.kltt.parser.swagger.parameters.ParametersPathParser;
import com.bbva.kltt.parser.swagger.util.ConstantsInputParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathsParser
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(PathsParser.class) ;
	
    /** Attribute - Paths */
    private final Paths paths ;
    
	/**
	 * Public constructor
	 * @param paths with the paths
	 */
	public PathsParser(final Paths paths)
	{
    	this.paths = paths ;
    }
	
	/**
	 * Parse the paths
	 * @param fileContent with the file content
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void parsePaths(final JsonNode fileContent) throws APIRestGeneratorException
	{
		final JsonNode pathsNode = fileContent.get(ConstantsInputParser.SW_MAIN_SCH_PATHS) ;
		if (pathsNode != null && pathsNode.isObject())
		{
			// Parse the full paths
			this.parsePathsInternal(pathsNode) ;
		}
	}
	
	/**
	 * Parse the full parameters
	 * @param nodeToInspect with the node to inspect
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void parsePathsInternal(final JsonNode nodeToInspect) throws APIRestGeneratorException
	{
		final Iterator<String> keyPaths = nodeToInspect.fieldNames() ;
		while (keyPaths.hasNext())
		{
			// Get the key path (future class name)
			final String keyPath 		= keyPaths.next() ;
			
			if (keyPath == null || keyPath.isEmpty() || keyPath.charAt(0) != ConstantsCommon.STRING_SLASH.charAt(0))
			{
				final String errorString = "The node '" + keyPath  + "' must start with a slash (/) character" ;
				
				PathsParser.LOGGER.error(errorString) ;
		    	throw new APIRestGeneratorException(errorString) ;
			}
			
			// Get the attribute node to verify that exists content
			final JsonNode pathNode 	= ParserUtil.getNodeValueJson(ConstantsInputParser.SW_MAIN_SCH_PATHS, nodeToInspect, keyPath, true) ;
			
			// Generate a new instance of Path
			final Path path	  			= new Path(keyPath) ;
					
			// Parse and generate Operation instances per path operation			
			this.parsePathOpObject(keyPath, path, pathNode) ;
			
			// Put the new path value
			this.paths.getPathsMap().put(keyPath, path) ;
		}
		
		// Post validations
		final PathsParserPostValidator pathsParserPostValidator = new PathsParserPostValidator(this.paths) ;
		pathsParserPostValidator.postValidations() ;
	}
	
	/**
	 * @param keyPath  with the key path
	 * @param path 	   with an instance of path
	 * @param pathNode with the path node
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void parsePathOpObject(final String keyPath, final Path path, final JsonNode pathNode) throws APIRestGeneratorException
	{
		final Iterator<String> keyPaths = pathNode.fieldNames() ;
		while (keyPaths.hasNext())
		{
			// Get the key path attribute
			final String pathOpKey = keyPaths.next() ;
			
			// Get the common parameters for all the paths
			final JsonNode commonParamsNode = ParserUtil.getNodeValueJson(keyPath, pathNode, ConstantsInputParser.SW_COMMON_PARAMS, false) ;
			if (commonParamsNode != null && commonParamsNode.isArray())
			{
				this.parseCommonParameters(path, commonParamsNode) ;
			}
			
			final boolean isPathOpSetOne  = ConstantsInputParser.SW_PATH_OP_DELETE.equalsIgnoreCase(pathOpKey)  ||
										    ConstantsInputParser.SW_PATH_OP_GET.equalsIgnoreCase(pathOpKey)     ||
										    ConstantsInputParser.SW_PATH_OP_HEAD.equalsIgnoreCase(pathOpKey)    ||
										    ConstantsInputParser.SW_PATH_OP_OPTIONS.equalsIgnoreCase(pathOpKey) ;
			
			final boolean isPathOpSetTwo  = ConstantsInputParser.SW_PATH_OP_PATCH.equalsIgnoreCase(pathOpKey) ||
										    ConstantsInputParser.SW_PATH_OP_POST.equalsIgnoreCase(pathOpKey)  ||
										    ConstantsInputParser.SW_PATH_OP_PUT.equalsIgnoreCase(pathOpKey)   ;
			
			if (isPathOpSetOne || isPathOpSetTwo)
			{
				final JsonNode keyPathAttrNode = ParserUtil.getNodeValueJson(keyPath, pathNode, pathOpKey, true) ;
				
				this.parsePathOpObject(path, pathOpKey, keyPathAttrNode) ;
			}
		}
	}
	
	/**
	 * @param path 	           with an instance of path
	 * @param commonParamsNode with the common parameters node
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void parseCommonParameters(final Path path, final JsonNode commonParamsNode) throws APIRestGeneratorException
	{
		// Generate a new instance of Parameters and parse the path parameters
		final ParametersPath parametersPath				= new ParametersPath(this.paths.getGlobalDefinitions(), this.paths.getRootValues().getConsumes()) ;
		final ParametersPathParser parametersPathParser = new ParametersPathParser(this.paths.getParametersGlobal(), parametersPath, path.getPathValue()) ;
		parametersPathParser.parseParameters(commonParamsNode) ;
		
		// Set the path parameters to the Path
		path.setParametersPath(parametersPath) ;
	}
	
	/**
	 * @param path 	          with an instance of path
	 * @param pathOpKey       with the path operation key
	 * @param keyPathAttrNode with the path attribute node
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void parsePathOpObject(final Path path, final String pathOpKey, final JsonNode keyPathAttrNode) throws APIRestGeneratorException
	{
		// Generate an instance of OperationParser
		final OperationParser operationParser = new OperationParser(this.paths.getGlobalDefinitions(),
																	this.paths.getParametersGlobal(),
																	this.paths.getGlobalResponses(),
																	this.paths.getRootValues().getConsumes()) ;
		// Parse the operation
		final Operation operation 		  	  = operationParser.parseOperation(path.getPathValue(), keyPathAttrNode, pathOpKey) ;
		
		if (ConstantsInputParser.SW_PATH_OP_DELETE.equalsIgnoreCase(pathOpKey))
		{
			path.getPathOpDelete().setOperation(operation) ;
		}
		else if (ConstantsInputParser.SW_PATH_OP_GET.equalsIgnoreCase(pathOpKey))
		{
			path.getPathOpGet().setOperation(operation) ;
		}
		else if (ConstantsInputParser.SW_PATH_OP_HEAD.equalsIgnoreCase(pathOpKey))
		{
			path.getPathOpHead().setOperation(operation) ;
		}
		else if (ConstantsInputParser.SW_PATH_OP_OPTIONS.equalsIgnoreCase(pathOpKey))
		{
			path.getPathOpOptions().setOperation(operation) ;
		}
		else if (ConstantsInputParser.SW_PATH_OP_PATCH.equalsIgnoreCase(pathOpKey))
		{
			path.getPathOpPatch().setOperation(operation) ;
		}
		else if (ConstantsInputParser.SW_PATH_OP_POST.equalsIgnoreCase(pathOpKey))
		{
			path.getPathOpPost().setOperation(operation) ;
		}
		else if (ConstantsInputParser.SW_PATH_OP_PUT.equalsIgnoreCase(pathOpKey))
		{
			path.getPathOpPut().setOperation(operation) ;
		}
	}
}
