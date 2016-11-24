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

package com.bbva.kltt.core.util.mapper;

import com.bbva.kltt.core.parsed_info.operations.Operation;
import com.bbva.kltt.core.parsed_info.responses.Response;
import com.bbva.kltt.core.parsed_info.*;
import com.bbva.kltt.core.parsed_info.common.*;
import com.bbva.kltt.core.parsed_info.parameters.*;
import com.bbva.kltt.core.parsed_info.paths.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class JacksonMapper extends ObjectMapper 
{
	/** Serial Version UID */
	private static final long serialVersionUID = 3753496109727081104L ;
	
	/** Private static instance */
	private static ObjectMapper INSTANCE = new JacksonMapper();

	public static ObjectMapper getInstance()
	{
		return INSTANCE ;
	}

	/**
	 * Private constructor
	 */
	private JacksonMapper()
	{
		this.setDefaultTyping(new FilteredTypeResolverBuilder(DefaultTyping.NON_FINAL).init(JsonTypeInfo.Id.MINIMAL_CLASS, null)
																							  	   .inclusion(JsonTypeInfo.As.WRAPPER_OBJECT)) ;
		this.setSerializationInclusion(JsonInclude.Include.NON_NULL) ;
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) ;

		this.setConfig(getSerializationConfig().withView(JacksonViews.GeneratorView.class)) ;
	}

	protected static class FilteredTypeResolverBuilder extends DefaultTypeResolverBuilder
	{
		/** Serial Version UID */
		private static final long serialVersionUID = -8145899696439248804L;

		/**
		 * @param defaultTyping with the default typing
		 */
		public FilteredTypeResolverBuilder(final DefaultTyping defaultTyping)
		{
			super(defaultTyping) ;
		}

		/**
		 * @param javaTypeSource with the java type
		 * @return true if it is an expected java type
		 */
		public boolean useForType(final JavaType javaTypeSource)
		{
			boolean use = super.useForType(javaTypeSource) ;
			if (use)
			{
				final boolean isParsedInfo  = javaTypeSource.getRawClass().equals(ParsedInfo.class) ;
				
				final boolean isItem	    = this.isItem(javaTypeSource) ;
				
				final boolean isEntities    = this.isEntities(javaTypeSource) ;
				
				final boolean isOperation   = javaTypeSource.getRawClass().equals(Operation.class) ;
				
				final boolean isParameters  = this.isParameters(javaTypeSource) ;
				
				final boolean isPaths	    = this.isPaths(javaTypeSource) ;
				
				final boolean isJavaComplex = this.isJavaComplex(javaTypeSource) ;
				
				use = !(isParsedInfo || isItem || isEntities || isOperation || isParameters || isPaths || isJavaComplex) ;
			}
			
			return use;
		}
		
		/**
		 * @param javaTypeSource with the java type source
		 * @return true if the javaType is an Item
		 */
		private boolean isItem(final JavaType javaTypeSource)
		{
			final boolean isItem	    = javaTypeSource.getRawClass().equals(Item.class) ;
			
			final boolean isItemArray   = javaTypeSource.getRawClass().equals(ItemArray.class) ;
			
			final boolean isItmeComplex = javaTypeSource.getRawClass().equals(ItemComplex.class) ;
			
			final boolean isItemFile	= javaTypeSource.getRawClass().equals(ItemFile.class) ;
			
			final boolean isItemNumber  = javaTypeSource.getRawClass().equals(ItemNumber.class) ;
			
			final boolean isItemRef	    = javaTypeSource.getRawClass().equals(ItemRef.class) ;
			
			final boolean isItemSimple  = javaTypeSource.getRawClass().equals(ItemSimpleBoolean.class) || 
					 					  javaTypeSource.getRawClass().equals(ItemSimpleDouble.class)  ||
					 					  javaTypeSource.getRawClass().equals(ItemSimpleFloat.class)   ||
					 					  javaTypeSource.getRawClass().equals(ItemSimpleInteger.class) ||
					 					  javaTypeSource.getRawClass().equals(ItemSimpleLong.class)    ||
					 					  javaTypeSource.getRawClass().equals(ItemSimpleString.class)  ;
			
			final boolean isItemString  = javaTypeSource.getRawClass().equals(ItemString.class)  ;
			
			return isItem || isItemArray || isItmeComplex || isItemFile || isItemNumber || isItemRef || isItemSimple || isItemString ;
		}
		
		/**
		 * @param javaTypeSource with the java type source
		 * @return true if the javaType is an Entity
		 */
		private boolean isEntities(final JavaType javaTypeSource)
		{
			final boolean isContactValues = javaTypeSource.getRawClass().equals(ContactValues.class) ;
			final boolean isDefinitions   = javaTypeSource.getRawClass().equals(Definitions.class) ;
			final boolean isInfoValues 	  = javaTypeSource.getRawClass().equals(InfoValues.class) ;
			final boolean isParametersGlo = javaTypeSource.getRawClass().equals(ParametersGlobal.class) ;
			final boolean isParametersPat = javaTypeSource.getRawClass().equals(ParametersPath.class) ;
			final boolean isParametersOpe = javaTypeSource.getRawClass().equals(ParametersOperation.class) ;
			final boolean isPaths 	   	  = javaTypeSource.getRawClass().equals(Paths.class) ;
			final boolean isResponses  	  = javaTypeSource.getRawClass().equals(Responses.class) ;
			final boolean isRootValues 	  = javaTypeSource.getRawClass().equals(RootValues.class) ;
			final boolean isResponse 	  = javaTypeSource.getRawClass().equals(Response.class) ;

			return isContactValues ||
				   isDefinitions   ||
				   isInfoValues    ||
				   isParametersGlo ||
				   isParametersPat ||
				   isParametersOpe ||
				   isPaths 		   ||
				   isResponses 	   ||
				   isRootValues    ||
				   isResponse      ;
		}
		
		/**
		 * @param javaTypeSource with the java type source
		 * @return true if the javaType is an Parameter
		 */
		private boolean isParameters(final JavaType javaTypeSource)
		{
			final boolean isParameter 		  = javaTypeSource.getRawClass().equals(Parameter.class) ;
			final boolean isParameterBody     = javaTypeSource.getRawClass().equals(ParameterBody.class) ;
			final boolean isParameterFormData = javaTypeSource.getRawClass().equals(ParameterFormData.class) ;
			final boolean isParameterHeader   = javaTypeSource.getRawClass().equals(ParameterHeader.class) ;
			final boolean isParameterNoBody   = javaTypeSource.getRawClass().equals(ParameterNoBody.class) ;
			final boolean isParameterPath 	  = javaTypeSource.getRawClass().equals(ParameterPath.class) ;
			final boolean isParameterQuery    = javaTypeSource.getRawClass().equals(ParameterQuery.class) ;
			
			return isParameter || isParameterBody || isParameterFormData || isParameterHeader || isParameterNoBody || isParameterPath || isParameterQuery ;
		}
		
		/**
		 * @param javaTypeSource with the java type source
		 * @return true if the javaType is a Path
		 */
		private boolean isPaths(final JavaType javaTypeSource)
		{
			final boolean isPath		  = javaTypeSource.getRawClass().equals(Path.class) ;
			final boolean isPathOp 		  = javaTypeSource.getRawClass().equals(PathOp.class) ;
			final boolean isPathDelete	  = javaTypeSource.getRawClass().equals(PathOpDelete.class) ;
			final boolean isPathOpGet 	  = javaTypeSource.getRawClass().equals(PathOpGet.class) ;
			final boolean isPathOpHead 	  = javaTypeSource.getRawClass().equals(PathOpHead.class) ;
			final boolean isPathOpOptions = javaTypeSource.getRawClass().equals(PathOpOptions.class) ;
			final boolean isPathOpPatch   = javaTypeSource.getRawClass().equals(PathOpPatch.class) ;
			final boolean isPathOpPost    = javaTypeSource.getRawClass().equals(PathOpPost.class) ;
			final boolean isPathOpPut     = javaTypeSource.getRawClass().equals(PathOpPut.class) ;
			
			return isPath || isPathOp || isPathDelete || isPathOpGet || isPathOpHead || isPathOpOptions || isPathOpPatch || isPathOpPost || isPathOpPut ;
		}
		
		/**
		 * @param javaTypeSource with the java type source
		 * @return true if the javaType is an complex java type
		 */
		private boolean isJavaComplex(final JavaType javaTypeSource)
		{
			final boolean isSet 	  = javaTypeSource.getRawClass().equals(Set.class) ;
			final boolean isHashSet   = javaTypeSource.getRawClass().equals(java.util.HashSet.class) ;
			final boolean isMap 	  = javaTypeSource.getRawClass().equals(Map.class) ;
			final boolean isHashMap   = javaTypeSource.getRawClass().equals(HashMap.class) ;
			final boolean isList 	  = javaTypeSource.getRawClass().equals(List.class) ;
			final boolean isArrayList = javaTypeSource.getRawClass().equals(ArrayList.class) ;
			
			return isSet || isHashSet || isMap || isHashMap || isList || isArrayList ;
		}
	}
}
