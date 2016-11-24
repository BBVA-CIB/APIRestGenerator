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

package com.bbva.kltt.core.web;

import com.bbva.kltt.core.util.APIRestGeneratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
* ------------------------------------------------
* @author Francisco Manuel Benitez Chico
* ------------------------------------------------
*/
public class APIRestGeneratorConfig
{
	/** Logger of the class */
    protected static final Logger LOGGER = LoggerFactory.getLogger(APIRestGeneratorConfig.class) ;
	
    /** Attribute - Remove After Send ZIP */
	private boolean removeAfterSendZip ;
	
	/** Attribute - Generator Builder Path */
	private String generatorBuilderPath ;
	
	public APIRestGeneratorConfig()
	{
		this.removeAfterSendZip = false ;
	}
	
	/**
	 * @return the removeAfterSendZip
	 */
	public boolean isRemoveAfterSendZip()
	{
		return this.removeAfterSendZip ;
	}

	/**
	 * @param removeAfterSendZip the removeAfterSendZip to set
	 */
	public void setRemoveAfterSendZip(final boolean removeAfterSendZip)
	{
		this.removeAfterSendZip = removeAfterSendZip ;
	}

	/**
	 * @return the removeAfterSendZip
	 */
	public boolean getRemoveAfterSendZip()
	{
		return this.removeAfterSendZip ;
	}
	
	/**
	 * @return the generatorBuilderPath
	 */
	public String getGeneratorBuilderPath()
	{
		return this.generatorBuilderPath ;
	}

	/**
	 * @param generatorBuilderPath the generatorBuilderPath to set
	 */
	public void setGeneratorBuilderPath(final String generatorBuilderPath)
	{
		this.generatorBuilderPath = generatorBuilderPath ;
	}
	
	/**
	 * @param properties with the properties
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void processProperties(final Properties properties) throws APIRestGeneratorException
	{
		// Override to set up the properties
	}
}
