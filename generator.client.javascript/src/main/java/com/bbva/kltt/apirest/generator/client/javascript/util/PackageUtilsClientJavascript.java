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

package com.bbva.kltt.apirest.generator.client.javascript.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.generator.client.web.util.PackageUtilsClientWeb;

/**
 * Utility class to get the right module project name and version
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PackageUtilsClientJavascript extends PackageUtilsClientWeb
{
	/**
	 * Public constructor
	 * @param businessUnit   with the business unit
	 * @param projectTitle   with the project title
	 * @param projectVersion with the project version
	 */
	public PackageUtilsClientJavascript(final String businessUnit, final String projectTitle, final String projectVersion)
	{
		super(businessUnit, projectTitle, projectVersion) ;
	}
	
    /**
     * @return the module project name
     */
    public String getModuleProjectName()
    {
    	final StringBuilder result = new StringBuilder() ;
        
        // Add the business unit
        result.append(this.getBusinessUnit().toLowerCase()) ;
        
        result.append(ConstantsCommon.STRING_UNDERSCORE) ;
        
        // Add the project title and convert to lower case
        result.append(this.normalizeString(this.getProjectTitle())) ;

        return result.toString() ;
    }
    
    /**
     * @param unnormalizedString with an unnormalized string
     * @return normalized string
     */
    protected String normalizeString(final String unnormalizedString)
    {
    	return unnormalizedString.replace(ConstantsCommon.STRING_HYPHEN, ConstantsCommon.STRING_UNDERSCORE)
    						     .replace(ConstantsCommon.STRING_DOT, ConstantsCommon.STRING_UNDERSCORE) ;
    }
}
