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

package com.bbva.kltt.generator.serverJaxrs.launcher;

import com.bbva.kltt.core.launcher.GlobalLauncher;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.generator.serverJaxrs.util.ConstantsOutputServerJaxrs;

/**
 * Launcher for code generations (JAX-RS)
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
        // Split the args to get parserType
        String[] newArgs = new String[args.length - 1];
        String parserType = args[2];

        for (int i = 0; i < newArgs.length; i++)
        {
            newArgs[i] = args[i];
        }

        final Launcher launcher = new Launcher();
        launcher.launch(newArgs,
                        ConstantsOutputServerJaxrs.MODULE_NAME,
                        ConstantsOutputServerJaxrs.GENERATOR_INIT_CLASS,
                        parserType);
    }
}