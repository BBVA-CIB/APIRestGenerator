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

package com.bbva.kltt.apirest.core.generator.output.language;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IOutputLanguageNaming
{
	/**
	 * Returns the same string with the first char upper case for class names
	 * 
	 * @param complexTypeName with the complex type name
	 * @return the class name but with the first char upper case
	 */
	String prefixClassName(final String complexTypeName) ;
	
	/**
	 * Returns the same string with the first char upper case for methods names
	 * 
	 * @param attributeName with the attribute name
	 * @return the attribute name but with the first char upper case
	 */
	String suffixMethodName(final String attributeName) ;
	
	/**
	 * Returns the same string with the first char lower case for attribute names
	 * 
	 * @param attributeName with the attribute name
	 * @return the attribute name but with the first char lower case
	 */
	String prefixAttributeName(final String attributeName) ;
	
	/**
	 * @param httpTokens with the HTTP tokens
	 * @return HTTP tokens with slashes
	 */
	String getUrlWithSlashes(final String... httpTokens) ;

    /**
     * Return the same string in CamelCase format
     *
     * @param name with the String to convert
     * @return the name in CamelCase format
     */
    String getCamelCaseName(final String name) ;
}
