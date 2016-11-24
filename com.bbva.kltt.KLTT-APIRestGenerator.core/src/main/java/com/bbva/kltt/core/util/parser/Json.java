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

package com.bbva.kltt.core.util.parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Json
{
	/** Attribute - mapper */
	private static ObjectMapper mapper ;

	/**
	 * @return a new instance of ObjectMapper
	 */
	public static ObjectMapper mapper()
	{
		if (Json.mapper == null)
		{
			Json.mapper = Json.createJson() ;
		}
		
		return Json.mapper ;
	}
	
	private static ObjectMapper createJson()
	{
		return Json.createJson(true, true) ;
	}

	protected static ObjectMapper createJson(final boolean includePathDeserializer, final boolean includeResponseDeserializer)
	{
		return Json.create(null, includePathDeserializer, includeResponseDeserializer) ;
	}

	private static ObjectMapper create(final JsonFactory jsonFactory, final boolean includePathDeserializer, final boolean includeResponseDeserializer)
	{
		ObjectMapper mapper = jsonFactory == null ? new ObjectMapper() : new ObjectMapper(jsonFactory);

		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return mapper;
	}
}
