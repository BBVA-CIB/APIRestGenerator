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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Utility class to invoke classes using reflection
 * ------------------------------------------------
 *
 * @author Jorge Sánchez
 * ------------------------------------------------
 */
public class InvokeModule {

    /** Attribute - classInvoke*/
    private Class classInvoke;

    /**
     * Private constructor
     *
     * @param classObj with Class object
     */
    private InvokeModule(Class classObj) {
        this.classInvoke = classObj;
    }


    /**
     * @param packModule   with the name of the package
     * @param className    with the name of the class to invoke
     * @return the class that has been found
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static InvokeModule initInvokeModule(String packModule, String className) throws APIRestGeneratorException {

        InvokeModule invokeObj = null ;

        try {

            if (!className.equals(""))
            {
                className = "." + className ;
            }

            // Full path to find the class
            String fClassName = new StringBuilder(packModule).append(className).toString();

            Class classInvoke = Class.forName(fClassName);

            invokeObj = new InvokeModule(classInvoke);
        } catch (ClassNotFoundException e) {
            throw new APIRestGeneratorException(e.getMessage(), e.getCause());
        }

        return invokeObj;
    }

    /**
     * @param parameters   with the constructor's parameters
     * @param typeParams   with the constructor's parameters type
     * @return the created instance
     * @throws APIRestGeneratorException with an occurred exception
     */
    public Object createInstance(Object[] parameters, Class<?>[] typeParams) throws APIRestGeneratorException {

        Object newInst = null ;

        try {

            Constructor constObj = this.classInvoke.getConstructor(typeParams);
            newInst = constObj.newInstance(parameters);

        } catch (InstantiationException e) {
            throw new APIRestGeneratorException(e.getMessage(), e.getCause());
        } catch (IllegalAccessException e) {
            throw new APIRestGeneratorException(e.getMessage(), e.getCause());
        } catch (InvocationTargetException e) {
            throw new APIRestGeneratorException(e.getMessage(), e.getCause());
        } catch (NoSuchMethodException e) {
            throw new APIRestGeneratorException(e.getMessage(), e.getCause());
        }

        return newInst;
    }

    /**
     * @param _instance    with the instance of the class
     * @param methodName   with the method to invoke
     * @param parameters   with the method's parameters
     * @param typeParams   with the method's parameters type
     * @return the result of the method
     * @throws APIRestGeneratorException with an occurred exception
     */
    public Object invokeMethod(Object _instance, String methodName, Object[] parameters, Class<?>[] typeParams)
            throws APIRestGeneratorException {

        Object returnMethod = null;

        try {

            Method  myMethod = this.classInvoke.getMethod(methodName, typeParams);
            returnMethod = myMethod.invoke(_instance, parameters);

        } catch (NoSuchMethodException e) {
            throw new APIRestGeneratorException(e.getMessage(), e.getCause());
        } catch (InvocationTargetException e) {
            throw new APIRestGeneratorException(e.getMessage(), e.getCause());
        } catch (IllegalAccessException e) {
            throw new APIRestGeneratorException(e.getMessage(), e.getCause());
        }

        return returnMethod;
    }
}
