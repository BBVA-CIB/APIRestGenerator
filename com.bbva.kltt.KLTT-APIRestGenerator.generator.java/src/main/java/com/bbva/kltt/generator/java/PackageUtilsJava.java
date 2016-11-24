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

package com.bbva.kltt.generator.java;

import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.util.ConstantsOutputJava;

/**
 * Utility class to get the right package names for the classes
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PackageUtilsJava
{
	/** Attribute - Base Package Root */
	private final String basePackageRoot ;
	
	/** Attribute - Project title */
	private final String projectTitle ;
	
	/**
	 * Public constructor
	 * @param bUnit		   with the business unit
	 * @param projectTitle with the project title
	 */
	public PackageUtilsJava(final String bUnit, final String projectTitle)
	{
		this.basePackageRoot = ConstantsOutput.PACKAGE_ROOT_PREFIX + bUnit.toLowerCase() + ConstantsOutput.PACKAGE_ROOT_SUFFIX ;
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
     * Return the package where the utility classes should be generated
     * @return the models utility package
     */
    public String getModelsUtilsPackage()
    {
        return this.joinPackages(ConstantsOutput.FOLDER_MODEL_UTILS) ;
    }
    
    /**
     * Return the package where the utility classes should be generated
     * @param className with the class name to concatenate
     * @return the models utility package
     */
    public String getModelsUtilsPackage(final String className)
    {
        return this.joinPackages(ConstantsOutput.FOLDER_MODEL_UTILS + ConstantsCommon.STRING_DOT + className) ;
    }

    /**
     * Package where the model tests are generated
     * @return the models test package
     */
    public String getModelsTestPackage()
    {
        return this.joinPackages(ConstantsOutput.FOLDER_MODEL_TEST) ;
    }
    
    /**
     * Return the package where the rest handler interfaces should be generated
     * @param translatorType with the translator type
     * @return the rest handler package
     */
    public String getRestHandlerInterfacesPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutput.FOLDER_REST_INTERFACES) ;
    }
    
    /**
     * Return the package where the rest handler implementations should be generated
     * @param translatorType with the translator type
     * @return the rest handler package
     */
    public String getRestHandlerImplPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutput.FOLDER_REST_IMPL) ;
    }
    
    /**
     * Package where the rest handler tests are generated
     * @param translatorType with the translator type
     * @return the rest handler test package
     */
    public String getRestHandlerTestPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutput.FOLDER_REST_IMPL) ;
    }

    /**
     * Package where the rest handler util is generated
     * @param translatorType with the translator type
     * @return the rest handler test package
     */
    public String getRestHandlerCorsUtilPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutput.FOLDER_REST_IMPL, ConstantsOutput.FOLDER_UTILS) ;
    }
    
    /**
     * Return the package where the rest utility classes should be generated
     * @param translatorType with the translator type
     * @return the rest handler package
     */
    public String getRestHandlerUtilsPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutput.FOLDER_REST_UTILS) ;
    }
    
    /**
     * Return the package where the rest utility test classes should be generated
     * @param translatorType with the translator type
     * @return the rest handler test package
     */
    public String getRestHandlerUtilsTestPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutput.FOLDER_REST_UTILS_TEST) ;
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
    private String joinPackages(String... packageNames)
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
    private String normalizeString(final String unnormalizedString)
    {
    	return unnormalizedString.replace(ConstantsCommon.STRING_HYPHEN, ConstantsCommon.STRING_UNDERSCORE) ;
    }
}
