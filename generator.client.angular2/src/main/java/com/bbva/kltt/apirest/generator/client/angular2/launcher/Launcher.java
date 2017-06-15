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

package com.bbva.kltt.apirest.generator.client.angular2.launcher;

import com.bbva.kltt.apirest.core.launcher.GlobalLauncher;
import com.bbva.kltt.apirest.core.launcher.ILauncher;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;

/**
 * Launcher for code generations (Angular2)
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class Launcher extends GlobalLauncher
{
    /**
     * Main for the Launcher, the required parameters are the ones defined in the AbstractLauncher plus the ones in this class
     *
     * @param args with the input arguments
     * @throws APIRestGeneratorException exception thrown if there is any problem in the code generation
     */
    public static void main(final String[] args) throws APIRestGeneratorException
    {
    	final ILauncher launcher = new Launcher();
        launcher.launch(args, ConstantsOutputClientAngular2.MODULE_NAME, ConstantsOutputClientAngular2.GENERATOR_INIT_CLASS);
    }
}
