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

package com.bbva.kltt.apirest.core.parsed_info.paths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bbva.kltt.apirest.core.parsed_info.Scheme;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterBody;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersPath;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.bbva.kltt.apirest.core.util.ConstantsMiddle;
import com.bbva.kltt.apirest.core.util.mapper.JacksonViews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Path
{
	/** Attribute - Path value */
	@JsonView(JacksonViews.GeneratorView.class)
	private final String pathValue ;
	
	/** Attribute - Path - delete */
	@JsonView(JacksonViews.GeneratorView.class)
	private final PathOpDelete pathOpDelete ;
	/** Attribute - Path - get */
	@JsonView(JacksonViews.GeneratorView.class)
	private final PathOpGet pathOpGet ;
	/** Attribute - Path - head */
	@JsonView(JacksonViews.GeneratorView.class)
	private final PathOpHead pathOpHead ;
	/** Attribute - Path - options */
	@JsonView(JacksonViews.GeneratorView.class)
	private final PathOpOptions pathOpOptions ;
	/** Attribute - Path - patch */
	@JsonView(JacksonViews.GeneratorView.class)
	private final PathOpPatch pathOpPatch ;
	/** Attribute - Path - post */
	@JsonView(JacksonViews.GeneratorView.class)
	private final PathOpPost pathOpPost ;
	/** Attribute - Path - put */
	@JsonView(JacksonViews.GeneratorView.class)
	private final PathOpPut pathOpPut ;

	/** Attribute - Path Parameters */
	@JsonView(JacksonViews.GeneratorView.class)
	private ParametersPath parametersPath ;

	/**
	 * Public constructor
	 * @param pathValue with the path value
	 */
	public Path(final String pathValue)
	{
		this.pathValue 	   = pathValue ;

		this.pathOpDelete  =  new PathOpDelete() ;
		this.pathOpGet 	   =  new PathOpGet() ;
		this.pathOpHead    =  new PathOpHead() ;
		this.pathOpOptions =  new PathOpOptions() ;
		this.pathOpPatch   =  new PathOpPatch() ;
		this.pathOpPost    =  new PathOpPost() ;
		this.pathOpPut     =  new PathOpPut() ;
	}
	
	/**
	 * @return the pathValue
	 */
	public String getPathValue()
	{
		return this.pathValue ;
	}

	/**
	 * @return the pathOpDelete
	 */
	public PathOpDelete getPathOpDelete()
	{
		return this.pathOpDelete ;
	}

	/**
	 * @return the pathOpGet
	 */
	public PathOpGet getPathOpGet()
	{
		return this.pathOpGet ;
	}

	/**
	 * @return the pathOpHead
	 */
	public PathOpHead getPathOpHead()
	{
		return this.pathOpHead ;
	}

	/**
	 * @return the pathOpOptions
	 */
	public PathOpOptions getPathOpOptions()
	{
		return this.pathOpOptions ;
	}

	/**
	 * @return the pathOpPatch
	 */
	public PathOpPatch getPathOpPatch()
	{
		return this.pathOpPatch ;
	}

	/**
	 * @return the pathOpPost
	 */
	public PathOpPost getPathOpPost()
	{
		return this.pathOpPost ;
	}

	/**
	 * @return the pathOpPut
	 */
	public PathOpPut getPathOpPut()
	{
		return this.pathOpPut ;
	}

	/**
	 * @return the path parameters
	 */
	public ParametersPath getParametersPath()
	{
		return this.parametersPath ;
	}
	
	/**
	 * @return true if there are parameters path
	 */
	public boolean hasParametersPath()
	{
		return this.parametersPath != null ;
	}

	/**
	 * @param parametersPath the parametersPath to set
	 */
	public void setParametersPath(final ParametersPath parametersPath)
	{
		this.parametersPath = parametersPath ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if there is any scheme in the operation
	 */
	protected boolean hasAnyScheme(final String pathOperation)
	{
		boolean outcome = false ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.hasAnyScheme() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return the schemes
	 */
	protected Set<Scheme> getSchemes(final String pathOperation)
	{
		Set<Scheme> outcome = null ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.getSchemes() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if there is any body parameter in the operation
	 */
	protected boolean hasAnyParameterBody(String pathOperation)
	{
		boolean outcome = this.hasParametersPath() && this.parametersPath.getParameterBody() != null ;
		
		if (!outcome)
		{
			final PathOp pathOp = this.getPathOp(pathOperation) ;
			if (pathOp != null)
			{
				outcome = pathOp.hasAnyParameterBody() ;
			}
		}
		
		return outcome ;
	}

	/**
	 * @param pathOperation	with the path operation
	 * @return the body parameter
	 */
	protected ParameterBody generateParameterBody(final String pathOperation)
	{
		ParameterBody outcome = null ;
		
		// Firstly, inspect if the common parameters in the path contains a body parameter
		if (this.hasParametersPath() && this.parametersPath.hasAnyParameterBody())
		{
			outcome = this.parametersPath.getParameterBody() ;
		}
		
		// Secondly, if there is not any body parameter in the common parameters in the path, then have a look the specific operations
		if (outcome == null)
		{
			final PathOp pathOp = this.getPathOp(pathOperation) ;
			if (pathOp != null && pathOp.hasAnyParameterBody())
			{
				outcome = pathOp.getOperation().getParametersOperation().getParameterBody() ;
			}
		}
		
		return outcome ;
	}
	
	/**
	 * @return a list with the path values
	 */
	public List<String> generatePathValuesList()
	{
		final List<String> pathValuesList = new ArrayList<String>() ;
		final String[] keyArray 		  = this.pathValue.split(ConstantsCommon.STRING_SLASH) ;
		
		for (final String key : keyArray)
		{
			final Pattern pattern = Pattern.compile(ConstantsInput.PATT_PATH_KEY_VALUES) ;
			final Matcher matcher = pattern.matcher(key) ;
			
			if (matcher.matches())
			{
				pathValuesList.add(matcher.group(1)) ;
			}	
		}
		
		return pathValuesList ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if there is any formData parameter in the operation
	 */
	protected boolean hasAnyParameterFormData(final String pathOperation)
	{
		boolean outcome = this.hasParametersPath() && this.parametersPath.hasAnyParameterFormData() ;
		
		if (!outcome)
		{
			final PathOp pathOp = this.getPathOp(pathOperation) ;
			if (pathOp != null)
			{
				outcome = pathOp.hasAnyParameterFormData() ;
			}
		}
		
		return outcome ;
	}

	/**
	 * @param pathOperation with the path operation
	 * @return a new map with the form data parameters
	 */
	protected Map<String, Parameter> generateParametersFormDataMap(final String pathOperation)
	{
		final Map<String, Parameter> outcome = new HashMap<String, Parameter>() ;
		
		// Firstly, inspect if the common parameters in the path contains the form data parameters
		if (this.hasParametersPath())
		{
			outcome.putAll(this.parametersPath.generateParametersFormDataMap()) ;
		}
		
		// Secondly, if there are more form data parameters in the internal operations, add them
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			pathOp.generateParametersFormDataMap(outcome) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if there is any header parameter in the operation
	 */
	protected boolean hasAnyParameterHeader(final String pathOperation)
	{
		boolean outcome = this.hasParametersPath() && this.parametersPath.hasAnyParameterHeader() ;
		
		if (!outcome)
		{
			final PathOp pathOp = this.getPathOp(pathOperation) ;
			if (pathOp != null)
			{
				outcome = pathOp.hasAnyParameterHeader() ;
			}
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return a new map with the header parameters
	 */
	protected Map<String, Parameter> generateParametersHeaderMap(final String pathOperation)
	{
		final Map<String, Parameter> outcome = new HashMap<String, Parameter>() ;
		
		// Firstly, inspect if the common parameters in the path contains the header parameters
		if (this.hasParametersPath())
		{
			outcome.putAll(this.parametersPath.generateParametersHeaderMap()) ;
		}
		
		// Secondly, if there are more header parameters in the internal operations, add them
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			pathOp.generateParametersHeaderMap(outcome) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if there is any path parameter in the operation
	 */
	protected boolean hasAnyParameterPath(final String pathOperation)
	{
		boolean outcome = this.hasParametersPath() && this.parametersPath.hasAnyParameterPath() ;
		
		if (!outcome)
		{
			final PathOp pathOp = this.getPathOp(pathOperation) ;
			if (pathOp != null)
			{
				outcome = pathOp.hasAnyParameterPath() ;
			}
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return a new map with the path parameters
	 */
	protected Map<String, Parameter> generateParametersPathMap(final String pathOperation)
	{
		final Map<String, Parameter> outcome = new HashMap<String, Parameter>() ;
		
		// Firstly, inspect if the common parameters in the path contains the path parameters
		if (this.hasParametersPath())
		{
			outcome.putAll(this.parametersPath.generateParametersPathMap()) ;
		}
		
		// Secondly, if there are more path parameters in the internal operations, add them
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			pathOp.generateParametersPathMap(outcome) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if there is any query parameter in the operation
	 */
	protected boolean hasAnyParameterQuery(final String pathOperation)
	{
		boolean outcome = this.hasParametersPath() && this.parametersPath.hasAnyParameterQuery() ;
		
		if (!outcome)
		{
			final PathOp pathOp = this.getPathOp(pathOperation) ;
			if (pathOp != null)
			{
				outcome = pathOp.hasAnyParameterQuery() ;
			}
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return a new map with the query parameters
	 */
	protected Map<String, Parameter> generateParametersQueryMap(final String pathOperation)
	{
		final Map<String, Parameter> outcome = new HashMap<String, Parameter>() ;
		
		// Firstly, inspect if the common parameters in the path contains the query parameters
		if (this.hasParametersPath())
		{
			outcome.putAll(this.parametersPath.generateParametersQueryMap()) ;
		}
		
		// Secondly, if there are more query parameters in the internal operations, add them
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			pathOp.generateParametersQueryMap(outcome) ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if there is operation identifier for this path operation
	 */
	public boolean hasAnyOperationId(final String pathOperation)
	{
		boolean outcome = false ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.getOperationId() != null ;
		}
		
		return outcome ;
	}
	
	/**
	 * @return all the operation identifiers as list
	 */
	@JsonIgnore
	protected List<String> getAllOperationIds()
	{
		final List<String> outcome = new ArrayList<String>() ;
		
		this.addOperationIdToAll(outcome, this.pathOpDelete) ;
		this.addOperationIdToAll(outcome, this.pathOpGet) ;
		this.addOperationIdToAll(outcome, this.pathOpHead) ;
		this.addOperationIdToAll(outcome, this.pathOpOptions) ;
		this.addOperationIdToAll(outcome, this.pathOpPatch) ;
		this.addOperationIdToAll(outcome, this.pathOpPost) ;
		this.addOperationIdToAll(outcome, this.pathOpPut) ;
		
		return outcome ;
	}
	
	/**
	 * Add the operation id to the list if exists
	 * @param operationIdList with the operation id list
	 * @param pathOp with the path operation
	 */
	private void addOperationIdToAll(final List<String> operationIdList, final PathOp pathOp)
	{
		String operationId = pathOp.getOperationId() ;
		if (operationId != null && !operationId.isEmpty())
		{
			operationIdList.add(operationId) ;
		}
	}

	/**
	 * @param pathOperation with the path operation
	 * @return the operation identifier
	 */
	protected String getOperationId(final String pathOperation)
	{
		String outcome = null ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.getOperationId() ;
		}
		return outcome ;
	}

	/**
	 * @param pathOperation with the path operation
	 * @return the return item type
	 */
	protected Item getOutboundServerItemType(final String pathOperation)
	{
		Item outcome = null ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.getOutboundServerItemType() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if exists any consumes for the pathValue-pathOperation
	 */
	public boolean hasAnyOperationConsumes(final String pathOperation)
	{
		boolean outcome = false ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.hasAnyOperationConsumes() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return a new set with the consumes for this pathValue-pathOperation
	 */
	public Set<String> getConsumes(final String pathOperation)
	{
		Set<String> outcome = null ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.getConsumes() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if exists any produces for the pathValue-pathOperation
	 */
	public boolean hasAnyOperationProduces(final String pathOperation)
	{
		boolean outcome = false ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.hasAnyOperationProduces() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return a new set with the produces for this pathValue-pathOperation
	 */
	public Set<String> getProduces(final String pathOperation)
	{
		Set<String> outcome = null ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.getProduces() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param outcome with the outcome map
	 */
	public void getOutboundServerExceptionsMap(final Map<String, List<Response>> outcome)
	{
		this.getOutboundServerExceptionsMap(outcome, ConstantsMiddle.PATH_OP_DELETE) ;
		this.getOutboundServerExceptionsMap(outcome, ConstantsMiddle.PATH_OP_GET) ;
		this.getOutboundServerExceptionsMap(outcome, ConstantsMiddle.PATH_OP_HEAD) ;
		this.getOutboundServerExceptionsMap(outcome, ConstantsMiddle.PATH_OP_OPTIONS) ;
		this.getOutboundServerExceptionsMap(outcome, ConstantsMiddle.PATH_OP_PATCH) ;
		this.getOutboundServerExceptionsMap(outcome, ConstantsMiddle.PATH_OP_POST) ;
		this.getOutboundServerExceptionsMap(outcome, ConstantsMiddle.PATH_OP_PUT) ;
	}
	
	
	/**
	 * @param outcome		with the outcome map
	 * @param pathOperation with the path operation
	 */
	private void getOutboundServerExceptionsMap(final Map<String, List<Response>> outcome, final String pathOperation)
	{
		if (this.hasAnyResponseExceptions(pathOperation))
		{
			final String operationId 			    = this.getOperationId(pathOperation) ;
			final List<Response> responseExceptions = this.getResponseExceptions(pathOperation) ;
			outcome.put(operationId, responseExceptions) ;
		}
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return true if exists any response exceptions for the pathValue-pathOperation
	 */
	private boolean hasAnyResponseExceptions(final String pathOperation)
	{
		boolean outcome = false ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.hasAnyResponseExceptions() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return a list with all the return response exceptions types
	 */
	private List<Response> getResponseExceptions(final String pathOperation)
	{
		List<Response> outcome = null ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.getResponseExceptions() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the path operation
	 * @return a list with all the custom response exceptions for the path and operation
	 */
	public List<Response> getOutboundServerExceptionsList(final String pathOperation)
	{
		List<Response> outcome = null ;
		
		final PathOp pathOp = this.getPathOp(pathOperation) ;
		if (pathOp != null)
		{
			outcome = pathOp.getOutboundServerExceptionsList() ;
		}
		
		return outcome ;
	}
	
	/**
	 * @param pathOperation with the string that contains the path operation
	 * @return the instance of PathOp
	 */
	private PathOp getPathOp(final String pathOperation)
	{
		PathOp outcome = null ;
		
		if (ConstantsMiddle.PATH_OP_DELETE.equalsIgnoreCase(pathOperation))
		{
			outcome = this.getPathOpDelete() ;
		}
		else if (ConstantsMiddle.PATH_OP_GET.equalsIgnoreCase(pathOperation))
		{
			outcome = this.getPathOpGet() ;
		}
		else if (ConstantsMiddle.PATH_OP_HEAD.equalsIgnoreCase(pathOperation))
		{
			outcome = this.getPathOpHead() ;
		}
		else if (ConstantsMiddle.PATH_OP_OPTIONS.equalsIgnoreCase(pathOperation))
		{
			outcome = this.getPathOpOptions() ;
		}
		else if (ConstantsMiddle.PATH_OP_PATCH.equalsIgnoreCase(pathOperation))
		{
			outcome = this.getPathOpPatch() ;
		}
		else if (ConstantsMiddle.PATH_OP_POST.equalsIgnoreCase(pathOperation))
		{
			outcome = this.getPathOpPost() ;
		}
		else if (ConstantsMiddle.PATH_OP_PUT.equalsIgnoreCase(pathOperation))
		{
			outcome = this.getPathOpPut() ;
		}
		
		return outcome ;
	}
}
