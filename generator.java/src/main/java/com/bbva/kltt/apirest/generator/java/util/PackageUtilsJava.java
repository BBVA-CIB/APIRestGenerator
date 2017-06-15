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

package com.bbva.kltt.apirest.generator.java.util;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.PackageUtils;

/**
 * Utility class to get the right package names for the classes
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class PackageUtilsJava extends PackageUtils
{
	/**
	 * Public constructor
	 * @param businessUnit with the business unit
	 * @param projectTitle with the project title
	 */
	public PackageUtilsJava(final String businessUnit, final String projectTitle)
	{
		super(businessUnit, projectTitle) ;
	}
	
    /**
     * Return the package where the utility classes should be generated
     * @return the models utility package
     */
    public String getModelsUtilsPackage()
    {
        return this.joinPackages(ConstantsOutputJava.FOLDER_MODEL_UTILS) ;
    }
    
    /**
     * Return the package where the utility classes should be generated
     * @param className with the class name to concatenate
     * @return the models utility package
     */
    public String getModelsUtilsPackage(final String className)
    {
        return this.joinPackages(ConstantsOutputJava.FOLDER_MODEL_UTILS + ConstantsCommon.STRING_DOT + className) ;
    }

    /**
     * Package where the model tests are generated
     * @return the models test package
     */
    public String getModelsTestPackage()
    {
        return this.joinPackages(ConstantsOutputJava.FOLDER_MODEL_TEST) ;
    }
    
    /**
     * Return the package where the rest handler interfaces should be generated
     * @param translatorType with the translator type
     * @return the rest handler package
     */
    public String getRestHandlerInterfacesPackage(final String translatorType)
    {
        return this.getHandlersPackage(translatorType) ;
    }
    
    /**
     * Return the package where the rest handler implementations should be generated
     * @param translatorType with the translator type
     * @return the rest handler package
     */
    public String getRestHandlerImplPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutputJava.FOLDER_REST_IMPL) ;
    }
    
    /**
     * Package where the rest handler tests are generated
     * @param translatorType with the translator type
     * @return the rest handler test package
     */
    public String getRestHandlerTestPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutputJava.FOLDER_REST_IMPL) ;
    }

    /**
     * Package where the rest handler util is generated
     * @param translatorType with the translator type
     * @return the rest handler test package
     */
    public String getRestHandlerCorsUtilPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutputJava.FOLDER_REST_IMPL, ConstantsOutput.FOLDER_UTILS) ;
    }
    
    /**
     * Return the package where the rest utility classes should be generated
     * @param translatorType with the translator type
     * @return the rest handler package
     */
    public String getRestHandlerUtilsPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutputJava.FOLDER_REST_UTILS) ;
    }
    
    /**
     * Return the package where the rest utility test classes should be generated
     * @param translatorType with the translator type
     * @return the rest handler test package
     */
    public String getRestHandlerUtilsTestPackage(final String translatorType)
    {
        return this.joinPackages(translatorType, ConstantsOutputJava.FOLDER_REST_UTILS_TEST) ;
    }
}
