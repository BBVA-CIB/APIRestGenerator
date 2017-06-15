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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to invoke classes using reflection
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class InvokeModule
{
	/** Logger of the class */
	private static final Logger LOGGER = LoggerFactory.getLogger(InvokeModule.class) ;
	
    /** Attribute - classInvoke*/
    @SuppressWarnings("rawtypes")
	private final Class classInvoke ;

    /**
     * Private constructor
     *
     * @param classInvoke with Class object to be invoked
     */
    @SuppressWarnings("rawtypes")
	private InvokeModule(final Class classInvoke)
    {
        this.classInvoke = classInvoke ;
    }


    /**
     * @param packModule   with the name of the package
     * @param className    with the name of the class to invoke
     * @return the class that has been found
     * @throws APIRestGeneratorException with an occurred exception
     */
    @SuppressWarnings("rawtypes")
	public static InvokeModule initInvokeModule(final String packModule, final String className) throws APIRestGeneratorException
    {
        InvokeModule invokeModule = null ;

        try
        {
            // Full path to find the class
            final StringBuilder fClassName = new StringBuilder(packModule) ;

            if (!className.equals(ConstantsCommon.STRING_EMPTY))
            {
                fClassName.append(ConstantsCommon.STRING_DOT).append(className) ;
            }

            final Class classInvoke = Class.forName(fClassName.toString()) ;

            invokeModule = new InvokeModule(classInvoke) ;
        }
        catch (ClassNotFoundException classNotFoundExc)
        {
        	final String errorString = "Exception while class loading (packModule: " + packModule + ", classModule: " + className + "): " + 
        							   classNotFoundExc.getMessage() ;
        	
        	InvokeModule.LOGGER.error(errorString) ;
            throw new APIRestGeneratorException(errorString, classNotFoundExc.getCause()) ;
        }

        return invokeModule ;
    }

    /**
     * @param parameters   with the constructor's parameters
     * @param typeParams   with the constructor's parameters type
     * @return the created instance
     * @throws APIRestGeneratorException with an occurred exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object createInstance(final Object[] parameters, final Class<?>[] typeParams) throws APIRestGeneratorException
    {
        Object newInstance = null ;

        try
        {
            final Constructor constObj = this.classInvoke.getConstructor(typeParams) ;
            newInstance 			   = constObj.newInstance(parameters) ;
        }
        catch (InstantiationException instantiationException)
        {
        	final String errorString = "InstantiationException while creating instance: " 	 + instantiationException.getMessage() ;
        	
        	InvokeModule.LOGGER.error(errorString, instantiationException) ;
            throw new APIRestGeneratorException(errorString, instantiationException) ;
        }
        catch (IllegalAccessException illegalAccessException)
        {
        	final String errorString = "IllegalAccessException while creating instance: " 	 + illegalAccessException.getMessage() ;
        	
        	InvokeModule.LOGGER.error(errorString, illegalAccessException) ;
            throw new APIRestGeneratorException(errorString, illegalAccessException) ;
        }
        catch (InvocationTargetException invocationTargetException)
        {
        	final String errorString = "InvocationTargetException while creating instance: " + invocationTargetException.getMessage() ;
        	
        	InvokeModule.LOGGER.error(errorString, invocationTargetException) ;
            throw new APIRestGeneratorException(errorString, invocationTargetException) ;
        }
        catch (NoSuchMethodException noSuchMethodException)
        {
        	final String errorString = "NoSuchMethodException while creating instance: " 	 + noSuchMethodException.getMessage() ;
        	
        	InvokeModule.LOGGER.error(errorString, noSuchMethodException) ;
            throw new APIRestGeneratorException(errorString, noSuchMethodException) ;
        }

        return newInstance ;
    }

    /**
     * @param instance     with the instance of the class
     * @param methodName   with the method to invoke
     * @param parameters   with the method's parameters
     * @param typeParams   with the method's parameters type
     * @return the result of the method
     * @throws APIRestGeneratorException with an occurred exception
     */
    @SuppressWarnings("unchecked")
	public Object invokeMethod(final Object instance, final String methodName, final Object[] parameters, final Class<?>[] typeParams)
            	  throws APIRestGeneratorException
    {
        Object returnMethod = null ;

        try
        {
            final Method  myMethod = this.classInvoke.getMethod(methodName, typeParams) ;
            returnMethod 		   = myMethod.invoke(instance, parameters) ;

        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException invokeMethodExc)
        {
        	final String errorString = "Exception while invoking method (" + methodName + "): " + invokeMethodExc.getMessage() ;
        	
        	InvokeModule.LOGGER.error(errorString) ;
            throw new APIRestGeneratorException(errorString, invokeMethodExc.getCause()) ;
        }

        return returnMethod ;
    }
}
