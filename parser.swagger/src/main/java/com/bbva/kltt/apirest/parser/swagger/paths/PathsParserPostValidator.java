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

package com.bbva.kltt.apirest.parser.swagger.paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterPath;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersPath;
import com.bbva.kltt.apirest.core.parsed_info.paths.Path;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOp;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpDelete;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpGet;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpHead;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpOptions;
import com.bbva.kltt.apirest.core.parsed_info.paths.Paths;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PathsParserPostValidator
{
	/** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(PathsParserPostValidator.class) ;
    
    /** Constant - Path String Logger */
    private static final String PATH_STRING_LOGGER = "The path '" ;
	
    /** Attribute - Paths */
    private final Paths paths ;
    
    /**
     * Protected constructor
     * @param paths with the parsed paths
     */
    protected PathsParserPostValidator(final Paths paths)
    {
    	this.paths = paths ;
    }
    
	/**
	 * Post validations over the different parameters
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	protected void postValidations() throws APIRestGeneratorException
	{
		// Post validate the operation identifiers
		this.postValidationOperationIdentifiers() ;
		
		// Post validate the path parameters
		this.postValidationPathParameters() ;
		
		// Post validate the payload types (body & formData parameters)
		this.postValidationPayloadTypes() ;
		
		// Parameters name validation (to avoid to different parameters with the same name)
		this.postValidationParametersName() ;
		
		// HTTP Specification validation
		this.postValidationHttpSpecification() ;
	}
	
	/**
	 * Post validations the operation identifiers
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationOperationIdentifiers() throws APIRestGeneratorException
	{
		final List<String> allOperationIds = this.paths.getAllOperationIds() ;
		
		String operationIdDuplicated = null ;
		boolean	foundConflictName    = false ;
		int indexList			     = 0 ;
		final int lengthList	     = allOperationIds.size() ;
		while (indexList < lengthList && !foundConflictName)
		{
			operationIdDuplicated	   = allOperationIds.get(indexList) ;
			final List<String> sublist = allOperationIds.subList(indexList + 1, lengthList) ;
			foundConflictName 		   = sublist.contains(operationIdDuplicated) ;
			
			indexList ++ ;
		}
		
		if (foundConflictName)
		{
			final String errorString = "The operationId '" + operationIdDuplicated + "' cannot be duplicated." ;
			
			PathsParserPostValidator.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
	}
	
	/**
	 * Post validation the path parameters
	 * @throws APIRestGeneratorException with an occurred exception 
	 */
	private void postValidationPathParameters() throws APIRestGeneratorException
	{
		for (final Path path : this.paths.getPathsMap().values())
		{
			final List<String> pendingPathValues = path.generatePathValuesList() ;
			
			// Purge the path values using the global parameters map
			this.purgePathValuesGlobalParameters(pendingPathValues) ;
			
			// Purge the path values using the path parameters list
			this.purgePathValuesPathParameters(pendingPathValues, path.getParametersPath()) ;
			
			// Purge the path values using the operation parameters list
			this.purgePathValuesOperationParameters(path.getPathValue(), pendingPathValues, path) ;
		}
	}

	/**
	 * @param pendingPathValues with the list of pending path values
	 */
	private void purgePathValuesGlobalParameters(final List<String> pendingPathValues)
	{
		final Iterator<String> itPendingPathValues = pendingPathValues.iterator() ;
		boolean found							   = false ;
		
		while (itPendingPathValues.hasNext() && !found)
		{
			final String pendingPathValue = itPendingPathValues.next() ;
			Parameter parameter 		  = (Parameter) this.paths.getParametersGlobal().getParameterPath(pendingPathValue) ;
			found						  = parameter != null && parameter instanceof ParameterPath ; 
			if (found)
			{
				itPendingPathValues.remove() ;
			}
		}
	}
	
	/**
	 * @param pendingPathValues with the list of pending path values
	 * @param parametersPath    with the parameters path
	 */
	private void purgePathValuesPathParameters(final List<String> pendingPathValues, final ParametersPath parametersPath)
	{
		if (parametersPath != null)
		{
			final Iterator<String> itPendingPathValues = pendingPathValues.iterator() ;
			while (itPendingPathValues.hasNext())
			{
				final String pendingPathValue 		   = itPendingPathValues.next() ;

				final Parameter parameter			   = parametersPath.getParameterPath(pendingPathValue) ;
				if (parameter != null)
				{
					itPendingPathValues.remove() ;
				}
			}
		}
	}
	
	/**
	 * @param pathValue 				 with the path value
	 * @param pendingPathValues 		 with the list of pending path values
	 * @param path 						 with the path
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void purgePathValuesOperationParameters(final String pathValue, final List<String> pendingPathValues, final Path path) throws APIRestGeneratorException
	{
		this.purgePathValuesOperationParameters(pathValue, new ArrayList<String>(pendingPathValues), path.getPathOpDelete()) ;
		this.purgePathValuesOperationParameters(pathValue, new ArrayList<String>(pendingPathValues), path.getPathOpGet()) ;
		this.purgePathValuesOperationParameters(pathValue, new ArrayList<String>(pendingPathValues), path.getPathOpHead()) ;
		this.purgePathValuesOperationParameters(pathValue, new ArrayList<String>(pendingPathValues), path.getPathOpOptions()) ;
		this.purgePathValuesOperationParameters(pathValue, new ArrayList<String>(pendingPathValues), path.getPathOpPatch()) ;
		this.purgePathValuesOperationParameters(pathValue, new ArrayList<String>(pendingPathValues), path.getPathOpPost()) ;
		this.purgePathValuesOperationParameters(pathValue, new ArrayList<String>(pendingPathValues), path.getPathOpPut()) ;
	}

	/**
	 * @param pathValue 				 with the path value
	 * @param pendingPathValues 		 with the list of pending path values
	 * @param pathOp					 with the path op
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void purgePathValuesOperationParameters(final String pathValue, ArrayList<String> pendingPathValues, PathOp pathOp) throws APIRestGeneratorException
	{
		final boolean containAnyData 		  = !pathOp.isEmpty() && pathOp.getOperation().getParametersOperation() != null ;
		if (containAnyData)
		{
			final Iterator<String> iterator   = pendingPathValues.iterator() ;
			while (iterator.hasNext())
			{
				final String pendingPathValue = iterator.next() ;
				
				final Parameter parameter 	  = pathOp.getOperation().getParametersOperation().getParameterPath(pendingPathValue) ;
				if (parameter != null)
				{
					iterator.remove() ;
				}
			}
			
			if (!pendingPathValues.isEmpty())
			{
				this.loggingAndThrowPathValuesException(pathValue, pendingPathValues, pathOp.getPathOpType()) ;
			}
		}
	}

	/**
	 * @param pathValue 				 with the path value
	 * @param pendingPathValues	 		 with the pending path values
	 * @param pathOpType 				 with the path op type
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void loggingAndThrowPathValuesException(final String pathValue,
													final ArrayList<String> pendingPathValues,
													final String pathOpType) throws APIRestGeneratorException
	{
		String pendingPathValString = ConstantsCommon.STRING_EMPTY ;
		
		for (final String pendingPathValue : pendingPathValues)
		{
			if (pendingPathValString.isEmpty())
			{
				pendingPathValString = pendingPathValue ;
			}
			else
			{
				final StringBuilder stringBuilder = new StringBuilder() ;
				
				stringBuilder.append(pendingPathValue) ;
				stringBuilder.append(ConstantsCommon.STRING_COMMA) ;
				stringBuilder.append(ConstantsCommon.STRING_BLANK) ;
				stringBuilder.append(pendingPathValString) ;
				
				pendingPathValString = stringBuilder.toString() ;
			}
		}
		
		final String errorString = PATH_STRING_LOGGER + pathValue + 
								   "' has the following missed path values in the '" + pathOpType + "' operation: " + pendingPathValString ;
		
		PathsParserPostValidator.LOGGER.error(errorString) ;
    	throw new APIRestGeneratorException(errorString) ;
	}
	
	/**
	 * Post validation the body parameters
	 * @throws APIRestGeneratorException with an occurred exception 
	 */
	private void postValidationPayloadTypes() throws APIRestGeneratorException
	{
		for (final Path path : this.paths.getPathsMap().values())
		{
			if (path.hasParametersPath())
			{
				final boolean hasAnyParameterBody     = path.getParametersPath().hasAnyParameterBody() ;
				final boolean hasAnyParameterFormData = path.getParametersPath().hasAnyParameterFormData() ;
				
				if (hasAnyParameterBody && hasAnyParameterFormData)
				{
					this.loggingAndThrowBodyFormDataTogetherException(path.getPathValue());
				}
				else
				{
					this.postValidationPayloadTypes(path.getPathValue(), path.getPathOpDelete(),  hasAnyParameterBody, hasAnyParameterFormData) ;
					this.postValidationPayloadTypes(path.getPathValue(), path.getPathOpGet(), 	  hasAnyParameterBody, hasAnyParameterFormData) ;
					this.postValidationPayloadTypes(path.getPathValue(), path.getPathOpHead(), 	  hasAnyParameterBody, hasAnyParameterFormData) ;
					this.postValidationPayloadTypes(path.getPathValue(), path.getPathOpOptions(), hasAnyParameterBody, hasAnyParameterFormData) ;
					this.postValidationPayloadTypes(path.getPathValue(), path.getPathOpPatch(),   hasAnyParameterBody, hasAnyParameterFormData) ;
					this.postValidationPayloadTypes(path.getPathValue(), path.getPathOpPost(), 	  hasAnyParameterBody, hasAnyParameterFormData) ;
					this.postValidationPayloadTypes(path.getPathValue(), path.getPathOpPut(), 	  hasAnyParameterBody, hasAnyParameterFormData) ;
				}
			}
		}
	}

	/**
	 * Post validation the body parameters
	 * @param pathValue 			  	  with the path value
	 * @param pathOp   				  	  with the path operation
	 * @param hasAnyParameterPathFormData true if it has any form data parameter in the common path
	 * @param hasAnyParameterPathBody 	  true if it has any body parameter in the common path
	 * @throws APIRestGeneratorException  with an occurred exception 
	 */
	private void postValidationPayloadTypes(final String pathValue,
											final PathOp pathOp,
											final boolean hasAnyParameterPathBody,
											final boolean hasAnyParameterPathFormData) throws APIRestGeneratorException
	{
		final boolean hasAnyParameterOpBody 	= pathOp.hasAnyParameterBody() ;
		final boolean hasAnyParameterOpFormData = pathOp.hasAnyParameterFormData() ;
		
		final boolean isThrowBodyFormDataExcG1  = hasAnyParameterOpBody     && hasAnyParameterOpFormData ;
		final boolean isThrowBodyFormDataExcG2  = hasAnyParameterOpBody     && hasAnyParameterPathFormData ;
		final boolean isThrowBodyFormDataExcG3  = hasAnyParameterOpFormData && hasAnyParameterPathBody ;
		
		if (isThrowBodyFormDataExcG1 || isThrowBodyFormDataExcG2 || isThrowBodyFormDataExcG3)
		{
			this.loggingAndThrowBodyFormDataTogetherException(pathValue, pathOp) ;
		}
		else if (hasAnyParameterPathBody && hasAnyParameterOpBody)
		{
			this.loggingAndThrowMultipleBodyParametersException(pathValue, pathOp) ;
		}
	}
	
	/**
	 * @param pathValue with the path value
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void loggingAndThrowBodyFormDataTogetherException(final String pathValue) throws APIRestGeneratorException
	{
		final String errorString = PATH_STRING_LOGGER + pathValue + "' cannot have body and formData parameters together in the common path parameters" ;
		
		PathsParserPostValidator.LOGGER.error(errorString) ;
    	throw new APIRestGeneratorException(errorString) ;
	}
	
	/**
	 * @param pathValue with the path value
	 * @param pathOp    with the path operation
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void loggingAndThrowBodyFormDataTogetherException(final String pathValue, final PathOp pathOp) throws APIRestGeneratorException
	{
		final String errorString = PATH_STRING_LOGGER + pathValue + 
								   "' cannot have body and formData parameters together in the '" + pathOp.getPathOpType() + "' operation" ;
		
		PathsParserPostValidator.LOGGER.error(errorString) ;
    	throw new APIRestGeneratorException(errorString) ;
	}
	
	/**
	 * @param pathValue with the path value
	 * @param pathOp    with the path operation
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void loggingAndThrowMultipleBodyParametersException(final String pathValue, final PathOp pathOp) throws APIRestGeneratorException
	{
		final String errorString = PATH_STRING_LOGGER + pathValue + "' cannot multiple body parameters in the '" + pathOp.getPathOpType() + "' operation" ;
		
		PathsParserPostValidator.LOGGER.error(errorString) ;
    	throw new APIRestGeneratorException(errorString) ;
	}
	
	/**
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationParametersName() throws APIRestGeneratorException
	{
		for (final Path path : this.paths.getPathsMap().values())
		{
			Map<String, Parameter> fullPathParametersMap = new HashMap<String, Parameter>() ;
			if (path.hasParametersPath())
			{
				fullPathParametersMap = this.postValidationParametersName(path) ;
			}
			
			this.postValidationParametersName(path.getPathValue(), path.getPathOpDelete(), new HashMap<String, Parameter>(fullPathParametersMap)) ;
			this.postValidationParametersName(path.getPathValue(), path.getPathOpGet(), new HashMap<String, Parameter>(fullPathParametersMap)) ;
			this.postValidationParametersName(path.getPathValue(), path.getPathOpHead(), new HashMap<String, Parameter>(fullPathParametersMap)) ;
			this.postValidationParametersName(path.getPathValue(), path.getPathOpOptions(), new HashMap<String, Parameter>(fullPathParametersMap)) ;
			this.postValidationParametersName(path.getPathValue(), path.getPathOpPatch(), new HashMap<String, Parameter>(fullPathParametersMap)) ;
			this.postValidationParametersName(path.getPathValue(), path.getPathOpPost(), new HashMap<String, Parameter>(fullPathParametersMap)) ;
			this.postValidationParametersName(path.getPathValue(), path.getPathOpPut(), new HashMap<String, Parameter>(fullPathParametersMap)) ;
		}
	}

	/**
	 * @param path with the path
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private Map<String, Parameter> postValidationParametersName(final Path path) throws APIRestGeneratorException
	{
		Map<String, Parameter> fullPathParametersMap = new HashMap<String, Parameter>() ;
		
		if (path.getParametersPath().hasAnyParameterBody())
		{
			Parameter parameter = path.getParametersPath().getParameterBody() ;
			fullPathParametersMap.put(parameter.getName(), parameter) ;
		}
		
		if (path.getParametersPath().hasAnyParameterFormData())
		{
			final Map<String, Parameter> formDataMap = path.getParametersPath().generateParametersFormDataMap() ;
			this.postValidationParametersName(path.getPathValue(), formDataMap, fullPathParametersMap) ;
		}
		
		if (path.getParametersPath().hasAnyParameterHeader())
		{
			final Map<String, Parameter> headerMap = path.getParametersPath().generateParametersHeaderMap() ;
			this.postValidationParametersName(path.getPathValue(), headerMap, fullPathParametersMap) ;
		}
		
		if (path.getParametersPath().hasAnyParameterPath())
		{
			final Map<String, Parameter> pathMap = path.getParametersPath().generateParametersPathMap() ;
			this.postValidationParametersName(path.getPathValue(), pathMap, fullPathParametersMap) ;
		}
		
		if (path.getParametersPath().hasAnyParameterQuery())
		{
			final Map<String, Parameter> queryMap = path.getParametersPath().generateParametersQueryMap() ;
			this.postValidationParametersName(path.getPathValue(), queryMap, fullPathParametersMap) ;
		}
			
		return fullPathParametersMap ;
	}
	
	/**
	 * @param pathValue		    with the path value
	 * @param pathOp			with the path operation
	 * @param fullParametersMap with the full parameters map
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationParametersName(final String pathValue,
											  final PathOp pathOp,
											  final Map<String, Parameter> fullParametersMap) throws APIRestGeneratorException
	{
		if (pathOp.hasAnyParameterBody())
		{
			final Map<String, Parameter> bodyMap = new HashMap<String, Parameter>() ;
			Parameter parameter 				 = pathOp.getOperation().getParametersOperation().getParameterBody() ;
			bodyMap.put(parameter.getName(), parameter) ;
			
			this.postValidationParametersName(pathValue, bodyMap, fullParametersMap) ;
		}
		
		if (pathOp.hasAnyParameterFormData())
		{
			final Map<String, Parameter> formDataMap = pathOp.getOperation().getParametersOperation().generateParametersFormDataMap() ;
			this.postValidationParametersName(pathValue, formDataMap, fullParametersMap) ;
		}
		
		if (pathOp.hasAnyParameterHeader())
		{
			final Map<String, Parameter> headerMap = pathOp.getOperation().getParametersOperation().generateParametersHeaderMap() ;
			this.postValidationParametersName(pathValue, headerMap, fullParametersMap) ;
		}
		
		if (pathOp.hasAnyParameterPath())
		{
			final Map<String, Parameter> pathMap = pathOp.getOperation().getParametersOperation().generateParametersPathMap() ;
			this.postValidationParametersName(pathValue, pathMap, fullParametersMap) ;
		}
		
		if (pathOp.hasAnyParameterQuery())
		{
			final Map<String, Parameter> queryMap = pathOp.getOperation().getParametersOperation().generateParametersQueryMap() ;
			this.postValidationParametersName(pathValue, queryMap, fullParametersMap) ;
		}
	}

	/**
	 * @param pathValue with the path value
	 * @param map 		with the map with the specific parameters from a type 
	 * @param fullMap 	with the map with all the current parameters
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationParametersName(final String pathValue,
											  final Map<String, Parameter> map,
											  final Map<String, Parameter> fullMap) throws APIRestGeneratorException
	{
		for (final String mapKey : map.keySet())
		{
			final Parameter fullMapParam 	= fullMap.get(mapKey) ;
			
			if (fullMapParam != null)
			{
				final Parameter mapParam 	= map.get(mapKey) ;
				if (!fullMapParam.getClass().equals(mapParam.getClass())) // If both instances belong to the same class there is not any problem
				{
					
					final String errorString    = "There are two parameters in the path '" + pathValue + "' with the same name '" + mapKey + "' and differents types: " +
												  mapParam.getClassName() + " and " + fullMapParam.getClassName() ;
					
					PathsParserPostValidator.LOGGER.error(errorString) ;
			    	throw new APIRestGeneratorException(errorString) ;
				}
			}
		}
		
		// If there is not problem, then we could put all the parameters into map
		fullMap.putAll(map) ;
	}
	
	/**
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationHttpSpecification() throws APIRestGeneratorException
	{
		for (final Path path : this.paths.getPathsMap().values())
		{
			this.postValidationHttpSpecificationHead(path.getPathValue(), path.getPathOpHead()) ;
			this.postValidationHttpSpecificationGet(path.getPathValue(), path.getPathOpGet()) ;
			this.postValidationHttpSpecificationDelete(path.getPathValue(), path.getPathOpDelete()) ;
			this.postValidationHttpSpecificationOptions(path.getPathValue(), path.getPathOpOptions()) ;
		}
	}

	/**
	 * @param pathValue					 with the path value
	 * @param pathOpHead				 with the path operation head
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationHttpSpecificationHead(final String pathValue, final PathOpHead pathOpHead) throws APIRestGeneratorException
	{
		String errorString = null ;
		
		// 'Head' operation must not contain any form data and body parameters 
		this.postValidationHttpSpecficationNoFormDataNoBody(pathValue, pathOpHead) ;
		
		final boolean isResponseWithType = pathOpHead.getOperation() != null 																	&&
										   pathOpHead.getOperation().getResponses().getOutboundServerItemType() != null 						&&
										   ((Response)pathOpHead.getOperation().getResponses().getOutboundServerItemType()).getItem() != null ;
		if (isResponseWithType)
		{
			errorString = "Head operation in the path '" + pathValue + "' must not have any response due to W3 Specification" ;
		}
		
		if (errorString != null)
		{
			PathsParserPostValidator.LOGGER.error(errorString) ;
	    	throw new APIRestGeneratorException(errorString) ;
		}
	}
	
	/**
	 * @param pathValue					 with the path value
	 * @param pathOpGet					 with the path operation head
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationHttpSpecificationGet(final String pathValue, final PathOpGet pathOpGet) throws APIRestGeneratorException
	{
		// 'Get' operation must not contain any form data and body parameters 
		this.postValidationHttpSpecficationNoFormDataNoBody(pathValue, pathOpGet) ;
	}
	
	/**
	 * @param pathValue					 with the path value
	 * @param pathOpDelete			 	 with the path operation head
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationHttpSpecificationDelete(final String pathValue, final PathOpDelete pathOpDelete) throws APIRestGeneratorException
	{
		// 'Delete' operation must not contain any form data and body parameters 
		this.postValidationHttpSpecficationNoFormDataNoBody(pathValue, pathOpDelete) ;
	}
	
	/**
	 * @param pathValue					 with the path value
	 * @param pathOpOptions				 with the path operation head
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationHttpSpecificationOptions(final String pathValue, final PathOpOptions pathOpOptions) throws APIRestGeneratorException
	{
		// 'Options' operation must not contain any form data and body parameters 
		this.postValidationHttpSpecficationNoFormDataNoBody(pathValue, pathOpOptions) ;
	}
	
	/**
	 * @param pathValue					 with the path value
	 * @param pathOp					 with the path operation
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	private void postValidationHttpSpecficationNoFormDataNoBody(final String pathValue, final PathOp pathOp) throws APIRestGeneratorException
	{
		final StringBuilder errorString = new StringBuilder() ;
		
		if (pathOp.hasAnyParameterBody() || pathOp.hasAnyParameterFormData())
		{
			errorString.append(pathOp.getPathOpType()) ;
			errorString.append(" operation in the path '") ;
			errorString.append(pathValue) ;
			errorString.append("' must not have any ") ;
			
			if (pathOp.hasAnyParameterBody())
			{
				errorString.append("body ") ; 
			}
			else
			{
				errorString.append("form data ") ; 
			}
			
			errorString.append("parameter due to W3 Specification") ;
		}
		
		if (errorString.length() > 0)
		{
			PathsParserPostValidator.LOGGER.error(errorString.toString()) ;
	    	throw new APIRestGeneratorException(errorString.toString()) ;
		}
	}
}
