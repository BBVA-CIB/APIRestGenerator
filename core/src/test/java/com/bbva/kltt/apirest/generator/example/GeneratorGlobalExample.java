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

package com.bbva.kltt.apirest.generator.example;

import com.bbva.kltt.apirest.core.generator.GeneratorGlobalLanguage;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorGlobalExample extends GeneratorGlobalLanguage
{
    /**
     * Constructs a new global generator for Example
     *
     * @param genParams         with the parameters for the generation for the Example
     * @param parsedInfoHandler with the parsed information handler to generate from
     */
    public GeneratorGlobalExample(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(genParams, parsedInfoHandler, "example") ;
    }

    /**
     * @param translatorType with the translator type
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void generate(final String translatorType) throws APIRestGeneratorException
    {
        // Nothing to do
    }
}
