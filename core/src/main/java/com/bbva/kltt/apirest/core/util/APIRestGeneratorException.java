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

/**
 * Internal exception for problems that occur inside the code generator
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class APIRestGeneratorException extends Exception
{
	/** Serial version of the exception class */
	private static final long serialVersionUID = 4416842934138913642L;

	/**
	 * Constructor of the class
	 *
	 * @param message message for the exception
	 */
	public APIRestGeneratorException(final String message)
	{
		super(message);
	}

	/**
	 * Constructor of the class
	 *
	 * @param cause throwable exception cause
	 */
	public APIRestGeneratorException(final Throwable cause)
	{
		super(cause);
	}

	/**
	 * Construct an exception given the exception message and the cause of the exception
	 *
	 * @param message exception message
	 * @param cause throwable cause of the exception
	 */
	public APIRestGeneratorException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
