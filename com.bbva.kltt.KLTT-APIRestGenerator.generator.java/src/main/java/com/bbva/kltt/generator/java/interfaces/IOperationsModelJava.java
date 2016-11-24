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

package com.bbva.kltt.generator.java.interfaces;

import com.bbva.kltt.core.generator.interfaces.IOperationsModel;
import com.bbva.kltt.core.util.APIRestGeneratorException;

import java.io.File;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IOperationsModelJava extends IOperationsModel
{
	/**
	 * Generate the base interface for all the models classes
	 * 
	 * @param destDir a {@link File} instance with the information of the destination directory
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void generateBaseInterfaceModels(final File destDir) throws APIRestGeneratorException ;
	
	/**
	 * Generate the utility classes for the model classes
	 * 
	 * @param destDir a {@link File} instance with the information of the destination directory
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void generateModelUtilities(final File destDir) throws APIRestGeneratorException ;
	
	/**
	 * Generate the test classes for the model classes
	 *
	 * @param destDir a {@link File} instance with the information of the destination directory
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void generateModelTests(final File destDir) throws APIRestGeneratorException ;
	
	/**
	 * Generate the POM for classes
	 *
	 * @param destDir a {@link File} instance with the information of the destination directory
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public void generatePOM(final File destDir) throws APIRestGeneratorException ;
}
