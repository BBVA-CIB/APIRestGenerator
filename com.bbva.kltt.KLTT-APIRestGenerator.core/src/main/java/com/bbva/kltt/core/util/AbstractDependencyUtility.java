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

package com.bbva.kltt.core.util;

import org.reflections.Reflections;

import java.util.*;

/**
 * This class helps the controllers to find what generator modules are installed.
 * To know if a generator module (i.e. Server Spring) is installed, the module must implement this class
 * and create a class constructor using the module type, the name to display and the module name.
 * <p>
 * ------------------------------------------------
 *
 * @author Jorge Sánchez
 * ------------------------------------------------
 */
public abstract class AbstractDependencyUtility
{

    /**
     * Attribute - Type of the generator module (client or server)
     */
    private String moduleType;

    /**
     * Attribute - Name of the module to display
     */
    private String displayName;

    /**
     * Attribute - Real name of the module
     */
    private String moduleName;

    /**
     * Constructor for generator modules
     *
     * @param moduleType  with the type of the module
     * @param displayName with the name to display
     * @param moduleName  with the real name of the module
     */
    public AbstractDependencyUtility(final String moduleType, final String displayName, final String moduleName)
    {
        this.moduleType  = moduleType;
        this.displayName = displayName;
        this.moduleName  = moduleName;
    }

    /**
     * @return the module type
     */
    public String getTypeModule()
    {
        return this.moduleType;
    }

    /**
     * @return the module name
     */
    public String getModuleName()
    {
        return this.moduleName;
    }

    /**
     * @return the name to display
     */
    public String getDisplayName()
    {
        return this.displayName;
    }

    /**
     * Method to get what generator modules (client and server) are installed.
     *
     * @param packageName with the name of the root package
     * @return JSON String with the installed dependencies
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static String getDependencies(final String packageName) throws APIRestGeneratorException
    {

        StringBuilder moduleString = new StringBuilder("[");

        // Get modules with the same root package name
        Reflections reflections    = new Reflections(packageName);

        // Get classes what implement AbstractDependencyUtility class
        Set<Class<? extends AbstractDependencyUtility>> classes = reflections.getSubTypesOf(AbstractDependencyUtility.class);

        Iterator<Class<? extends AbstractDependencyUtility>> iterator = classes.iterator();
        while (iterator.hasNext())
        {

            InvokeModule invModule = InvokeModule.initInvokeModule(iterator.next().getCanonicalName(), "");
            AbstractDependencyUtility classInstance = (AbstractDependencyUtility) invModule.createInstance(null, null);

            if (!moduleString.toString().equals("["))
            {
                moduleString.append(", ");
            }

            moduleString.append(classInstance.toString());
        }

        moduleString.append("]");

        return moduleString.toString();
    }

    /**
     * @return JSON String of the instance
     */
    @Override
    public String toString()
    {
        return String.format("{\"typeModule\": \"%s\", \"moduleName\": \"%s\", \"displayName\": \"%s\"}",
                             this.getTypeModule(),
                             this.getModuleName(),
                             this.getDisplayName());
    }

}
