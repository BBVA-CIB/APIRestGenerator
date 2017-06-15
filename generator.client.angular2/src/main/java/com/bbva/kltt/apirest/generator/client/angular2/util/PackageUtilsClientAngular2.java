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

package com.bbva.kltt.apirest.generator.client.angular2.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.web.util.PackageUtilsClientWeb;

/**
 * Utility class to get the right module project name and version
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PackageUtilsClientAngular2 extends PackageUtilsClientWeb
{
	/**
	 * Public constructor
	 * @param businessUnit   with the business unit
	 * @param projectTitle   with the project title
	 * @param projectVersion with the project version
	 */
	public PackageUtilsClientAngular2(final String businessUnit, final String projectTitle, final String projectVersion)
	{
		super(businessUnit, projectTitle, projectVersion) ;
	}
	
    /**
     * Return the package where the models should be generated
     * @param translatorType with the translator type
     * @return the models package
     */
    public String getModelsPackage(final String translatorType)
    {
        return this.addParentFolder() + this.joinPackages(translatorType, ConstantsOutput.FOLDER_MODEL) ;
    }
    
    /**
     * Return the package where the handlers should be generated
     * @param translatorType with the translator type
     * @return the rest handler package
     */
	@Override
    public String getHandlersPackage(final String translatorType)
    {
    	return this.addParentFolder() + super.getHandlersPackage(translatorType) ;
    }
    
    /**
     * Return the package where the examples should be generated
     * @param translatorType with the translator type
     * @return the models package
     */
	@Override
    public String getExamplesPackage(final String translatorType)
    {
    	return this.addParentFolder() + super.getExamplesPackage(translatorType) ;
    }
    
    /**
     * Return the package where the common utilities should be generated
     * @return the models package
     */
    public String getUtilsPackage()
    {
        return this.addParentFolder() + this.joinPackagesCommon(ConstantsOutput.FOLDER_UTILS) ;
    }
    
    /**
     * @return the parent folder for all the generated files 
     */
    private String addParentFolder()
    {
    	return ConstantsOutputClientAngular2.ANGULAR2_MAIN_FOLDER + ConstantsCommon.STRING_DOT ;
    }
    
    /**
     * Join the given packages into a single String for the common utility classes
     * @param packageNames with a list of package names
     * @return the joined packages in one string
     */
    public String joinPackagesCommon(String... packageNames)
    {
        StringBuilder result = new StringBuilder() ;
        
        // Add the base package root
        result.append(this.normalizeString(this.getBasePackageRoot())) ;
        result.append(ConstantsCommon.STRING_DOT) ;
        
        // Add the subset of packages
        for (int i = 0; i < packageNames.length; i++)
        {
            result.append(this.normalizeString(packageNames[i])) ;

            if (i != packageNames.length - 1)
            {
                result.append(ConstantsCommon.STRING_DOT) ;
            }
        }

        return result.toString() ;
    }
}
