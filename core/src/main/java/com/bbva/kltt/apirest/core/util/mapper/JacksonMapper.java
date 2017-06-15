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

package com.bbva.kltt.apirest.core.util.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bbva.kltt.apirest.core.parsed_info.ContactValues;
import com.bbva.kltt.apirest.core.parsed_info.Definitions;
import com.bbva.kltt.apirest.core.parsed_info.InfoValues;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfo;
import com.bbva.kltt.apirest.core.parsed_info.Responses;
import com.bbva.kltt.apirest.core.parsed_info.RootValues;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemArray;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemFile;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemNumber;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemSimpleBoolean;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemSimpleDouble;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemSimpleFloat;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemSimpleInteger;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemSimpleLong;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemSimpleString;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemString;
import com.bbva.kltt.apirest.core.parsed_info.operations.Operation;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterBody;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterFormData;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterHeader;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterNoBody;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterPath;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterQuery;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersGlobal;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersOperation;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParametersPath;
import com.bbva.kltt.apirest.core.parsed_info.paths.Path;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOp;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpDelete;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpGet;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpHead;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpOptions;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpPatch;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpPost;
import com.bbva.kltt.apirest.core.parsed_info.paths.PathOpPut;
import com.bbva.kltt.apirest.core.parsed_info.paths.Paths;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

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
				
				final boolean useForTypeG1  = isParsedInfo || isItem 	   || isEntities ;
				final boolean useForTypeG2  = isOperation  || isParameters || isPaths ;
				final boolean useForTypeG3  = isJavaComplex ;
				
				use = !(useForTypeG1 || useForTypeG2 || useForTypeG3) ;
			}
			
			return use;
		}
		
		/**
		 * @param javaTypeSource with the java type source
		 * @return true if the javaType is an Item
		 */
		private boolean isItem(final JavaType javaTypeSource)
		{
			final boolean isItem	     = javaTypeSource.getRawClass().equals(Item.class) ;
			
			final boolean isItemArray    = javaTypeSource.getRawClass().equals(ItemArray.class) ;
			
			final boolean isItemComplex  = javaTypeSource.getRawClass().equals(ItemComplex.class) ;
			
			final boolean isItemFile	 = javaTypeSource.getRawClass().equals(ItemFile.class) ;
			
			final boolean isItemNumber   = javaTypeSource.getRawClass().equals(ItemNumber.class) ;
			
			final boolean isItemRef	     = javaTypeSource.getRawClass().equals(ItemRef.class) ;
			
			final boolean isItemSimpleG1 = javaTypeSource.getRawClass().equals(ItemSimpleBoolean.class) || 
										   javaTypeSource.getRawClass().equals(ItemSimpleDouble.class)  ||
										   javaTypeSource.getRawClass().equals(ItemSimpleFloat.class)   ;
			
			final boolean isItemSimpleG2 = javaTypeSource.getRawClass().equals(ItemSimpleInteger.class) ||
										   javaTypeSource.getRawClass().equals(ItemSimpleLong.class)    ||
										   javaTypeSource.getRawClass().equals(ItemSimpleString.class)  ;
			
			final boolean isItemSimple   = isItemSimpleG1 || isItemSimpleG2 ;
					 					  
			
			final boolean isItemString   = javaTypeSource.getRawClass().equals(ItemString.class)  ;
			
			final boolean isItemG1		 = isItem       || isItemArray  || isItemComplex ;
			final boolean isItemG2		 = isItemFile   || isItemNumber || isItemRef ;
			final boolean isItemG3		 = isItemSimple || isItemString ;
			
			return isItemG1 || isItemG2 || isItemG3 ;
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

			final boolean isEntityG11	  = isContactValues || isDefinitions   || isInfoValues ;
			final boolean isEntityG12	  = isParametersGlo || isParametersPat || isParametersOpe ;
			final boolean isEntityG13	  = isPaths 		|| isResponses 	   || isRootValues ;
			final boolean isEntityG14	  = isResponse ;

			final boolean isEntityG1	  = isEntityG11 || isEntityG12 || isEntityG13 ;
			final boolean isEntityG2	  = isEntityG14 ;
			
			return isEntityG1 || isEntityG2 ;
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
			
			final boolean isParameterG1		  = isParameter 	  || isParameterBody   || isParameterFormData ;
			final boolean isParameterG2		  = isParameterHeader || isParameterNoBody || isParameterPath ;
			final boolean isParameterG3		  = isParameterQuery  ;
			
			return isParameterG1 || isParameterG2 || isParameterG3 ;
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
			
			final boolean isPathsG1		  = isPath 		  || isPathOp 	  || isPathDelete ;
			final boolean isPathsG2 	  = isPathOpGet   || isPathOpHead || isPathOpOptions ;
			final boolean isPathsG3 	  = isPathOpPatch || isPathOpPost || isPathOpPut ;
			
			return isPathsG1 || isPathsG2 || isPathsG3 ;
		}
		
		/**
		 * @param javaTypeSource with the java type source
		 * @return true if the javaType is an complex java type
		 */
		private boolean isJavaComplex(final JavaType javaTypeSource)
		{
			final boolean isSet 	  	  = javaTypeSource.getRawClass().equals(Set.class) ;
			final boolean isHashSet   	  = javaTypeSource.getRawClass().equals(java.util.HashSet.class) ;
			final boolean isMap 	  	  = javaTypeSource.getRawClass().equals(Map.class) ;
			final boolean isHashMap  	  = javaTypeSource.getRawClass().equals(HashMap.class) ;
			final boolean isList 	  	  = javaTypeSource.getRawClass().equals(List.class) ;
			final boolean isArrayList 	  = javaTypeSource.getRawClass().equals(ArrayList.class) ;
			
			final boolean isJavaComplexG1 = isSet 	  || isHashSet || isMap ;
			final boolean isJavaComplexG2 = isHashMap || isList    || isArrayList ;
			
			return isJavaComplexG1 || isJavaComplexG2 ;
		}
	}
	
	/**
	 * Private constructor
	 */
	private JacksonMapper()
	{
		super() ;
		
		this.setDefaultTyping(new FilteredTypeResolverBuilder(DefaultTyping.NON_FINAL).init(JsonTypeInfo.Id.MINIMAL_CLASS, null)
																							  	   .inclusion(JsonTypeInfo.As.WRAPPER_OBJECT)) ;
		this.setSerializationInclusion(JsonInclude.Include.NON_NULL) ;
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) ;

		this.setConfig(getSerializationConfig().withView(JacksonViews.GeneratorView.class)) ;
	}
}
