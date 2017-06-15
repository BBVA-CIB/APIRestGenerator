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

package com.bbva.kltt.apirest.core.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;

/**
 * Utility class to get the right package names for the classes
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class PackageUtils
{
	/** Attribute - Business Unit */
	private final String businessUnit ;
	
	/** Attribute - Base Package Root */
	private final String basePackageRoot ;
	
	/** Attribute - Project title */
	private final String projectTitle ;
	
	/**
	 * Public constructor
	 * @param businessUnit with the business unit
	 * @param projectTitle with the project title
	 */
	public PackageUtils(final String businessUnit, final String projectTitle)
	{
		this.businessUnit	 = businessUnit.toLowerCase() ;
		this.basePackageRoot = ConstantsOutput.PACKAGE_ROOT_PREFIX + this.businessUnit + ConstantsOutput.PACKAGE_ROOT_SUFFIX ;
		this.projectTitle	 = projectTitle.toLowerCase() ;
	}
	
    /**
     * Return the package where the models should be generated
     * @return the models package
     */
    public String getModelsPackage()
    {
        return this.joinPackages(ConstantsOutput.FOLDER_MODEL) ;
    }
    
    /**
     * Return the package where the model exceptions should be generated
     * @return the models package
     */
    public String getModelsExceptionPackage()
    {
        return this.joinPackages(ConstantsOutput.FOLDER_MODEL_EXCEPTION) ;
    }
    
    /**
     * Return the package where the handlers should be generated
     * @param translatorType with the translator type
     * @return the rest handler package
     */
    public String getHandlersPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutput.FOLDER_REST) ;
    }
    
    /**
     * Return the package where the examples should be generated
     * @param translatorType with the translator type
     * @return the models package
     */
    public String getExamplesPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutput.FOLDER_EXAMPLE) ;
    }
    
    /**
     * Join the given packages into a single String
     * @param packageNames with a list of package names
     * @return the joined packages in one string
     */
    protected String joinPackages(String... packageNames)
    {
        StringBuilder result = new StringBuilder() ;
        
        // Add the base package root
        result.append(this.normalizeString(this.basePackageRoot)) ;
        result.append(ConstantsCommon.STRING_DOT) ;
        
        // Add the project title and convert to lower case
        result.append(this.normalizeString(this.projectTitle)) ;
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
    
    /**
     * @param unnormalizedString with an unnormalized string
     * @return normalized string
     */
    protected String normalizeString(final String unnormalizedString)
    {
    	return unnormalizedString.replace(ConstantsCommon.STRING_HYPHEN, ConstantsCommon.STRING_UNDERSCORE) ;
    }

	/**
	 * @return the businessUnit
	 */
	public String getBusinessUnit()
	{
		return this.businessUnit ;
	}
	
	/**
	 * @return the basePackageRoot
	 */
	public String getBasePackageRoot()
	{
		return this.basePackageRoot ;
	}

	/**
	 * @return the projectTitle
	 */
	public String getProjectTitle()
	{
		return this.projectTitle ;
	}
}
