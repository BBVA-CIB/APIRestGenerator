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

package com.bbva.kltt.core.generator.velocity;

import org.apache.velocity.app.VelocityEngine;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class VelocityManager
{
    /** The velocity engine */
	private static final VelocityEngine VELOCITY_ENGINE ;

    // Static initializer
	static
	{
		VELOCITY_ENGINE = new VelocityEngine() ;
		VELOCITY_ENGINE.setProperty("resource.loader", "class") ;
		VELOCITY_ENGINE.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader") ;
		VELOCITY_ENGINE.setProperty("directive.set.null.allowed", true) ;
		VELOCITY_ENGINE.init() ;
  	}

    /**
     * @return the velocity engine to generate the code 
     */
	public static VelocityEngine getEngine()
	{
		return VELOCITY_ENGINE ;
	}
}
