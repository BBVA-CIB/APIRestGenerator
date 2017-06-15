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

package com.bbva.kltt.apirest.core.launcher;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * Interface that any launcher should implement
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface ILauncher
{
    /**
     * Launch the code generation, this is the entry point of any generation
     *
     * @param cmdArgs    with the command line arguments
     * @param moduleName with the module name
     * @param className  with the name of the init generator class
     * @throws APIRestGeneratorException exception thrown if there is any problem
     */
    void launch(final String[] cmdArgs, final String moduleName, final String className) throws APIRestGeneratorException ;
}
