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
public interface IOutputLanguageConsumesProduces
{
	/**
	 * Global inbound server - content type
	 * @return a new String with the global consumes
	 */
	String generateInboundServerContentType() ;
	
	/**
	 * Inbound server - content type - for the specific pathValue and pathOperation
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new String with the consumes for the pathValue-pathOperation
	 */
	String generateInboundServerContentType(final String pathValue, final String pathOperation) ;
	
	/**
	 * Global outbound server - content type
	 * @return a new String with the global produces
	 */
	String generateOutboundServerContentType() ;
	
	/**
	 * Outbound server - content type - for the specific pathValue and pathOperation
	 * @param pathValue 	with the path value
	 * @param pathOperation with the path operation
	 * @return a new String with the produces for the pathValue-pathOperation
	 */
	String generateOutboundServerContentType(final String pathValue, final String pathOperation) ;
	
	/**
	 * @return the initial consumes or produces string when there is more than one element (array)
	 */
	String generateInitialConsumesOrProducesString() ;
	
	/**
	 * @return the final consumes or produces string when there is more than one element (array)
	 */
	String generateFinalConsumesOrProducesString() ;
}
