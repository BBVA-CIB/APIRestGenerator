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

package com.bbva.kltt.generator.clientTypescript.ant;

import com.bbva.kltt.core.ant.AbstractAntExecutor;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsAnt;
import com.bbva.kltt.generator.clientTypescript.launcher.Launcher;
import com.bbva.kltt.generator.clientTypescript.util.ConstantsOutputTypescript;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class AntExecutor extends AbstractAntExecutor
{
    /**
     * Public constructor
     *
     * @param generatorBuilderPath with the generator builder path
     * @param parserType           with the parser type
     */
    public AntExecutor(final String generatorBuilderPath, final String parserType)
    {
        super(generatorBuilderPath,
                ConstantsAnt.ANT_FILE_CLI_TYPE_SCRIPT,
                ConstantsOutputTypescript.MODULE_NAME,
                Launcher.class.getCanonicalName(),
                parserType);
    }

    /**
     * Example main
     *
     * @param args with the arguments
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static void main(final String[] args) throws APIRestGeneratorException
    {
        final String generatorBuilderPath = "generatorBuilder";
        final String filePath             = "apiSpec.yaml";
        final String parserType           = "swagger";

        final AntExecutor myAntExecutor = new AntExecutor(generatorBuilderPath, parserType);

        myAntExecutor.executeAntTask(filePath, System.err, System.out, ConstantsAnt.TEMP_DIRECTORY_PREFIX + "1");
    }
}
