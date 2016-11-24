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

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class VelocityTemplateLocator
{
	private static char PACKAGE_SEP = '.' ;
	private static char RESOURCE_SEP = '/' ;

	/**
	 * Get the template resource name
	 * @param resourcePackage with the resource package
	 * @param resourceName    with the resource name
	 * @return the template resource name
	 */
	public static String getTemplateResourceName(final String resourcePackage, final String resourceName)
	{
		return resourcePackage.replace(VelocityTemplateLocator.PACKAGE_SEP, VelocityTemplateLocator.RESOURCE_SEP) + VelocityTemplateLocator.RESOURCE_SEP + resourceName;
	}
}
