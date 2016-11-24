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

package com.bbva.kltt.parser.swagger.parameters;

import com.bbva.kltt.core.parsed_info.parameters.ParametersGlobal;
import com.bbva.kltt.core.parsed_info.parameters.ParametersOperation;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParametersOperationParser extends ParametersPathParser
{
    /**
     * Public constructor
     *
     * @param parametersGlobal    with the global parameters
     * @param parametersOperation with the operation parameters
     * @param pathKey             with the path key
     */
    public ParametersOperationParser(final ParametersGlobal parametersGlobal,
                                     final ParametersOperation parametersOperation,
                                     final String pathKey)
    {
        super(parametersGlobal, parametersOperation, pathKey);
    }
}
