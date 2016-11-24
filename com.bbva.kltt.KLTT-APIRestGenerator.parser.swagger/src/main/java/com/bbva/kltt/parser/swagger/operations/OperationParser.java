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

package com.bbva.kltt.parser.swagger.operations;

import com.bbva.kltt.core.parsed_info.Definitions;
import com.bbva.kltt.core.parsed_info.Responses;
import com.bbva.kltt.core.parsed_info.Scheme;
import com.bbva.kltt.core.parsed_info.operations.Operation;
import com.bbva.kltt.core.parsed_info.parameters.ParametersGlobal;
import com.bbva.kltt.core.parsed_info.parameters.ParametersOperation;
import com.bbva.kltt.parser.swagger.responses.ResponsesParser;
import com.bbva.kltt.parser.swagger.responses.ResponsesParserOperation;
import com.bbva.kltt.parser.swagger.parameters.ParametersOperationParser;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.parser.swagger.util.ConstantsInputParser;
import com.bbva.kltt.core.util.parser.ParserUtil;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashSet;
import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 *  ------------------------------------------------
 */
public class OperationParser
{
    /**
     * Attribute - global definitions
     */
    private final Definitions globalDefinitions;

    /**
     * Attribute - global parameters
     */
    private final ParametersGlobal parametersGlobal;

    /**
     * Attribute - global responses
     */
    private final Responses globalResponses;

    /**
     * Attribute - global consumes
     */
    private final Set<String> globalConsumes;

    /**
     * Public constructor
     *
     * @param globalDefinitions with the global definitions
     * @param parametersGlobal  with the global parameters
     * @param globalResponses   with the global responses
     * @param globalConsumes    with the global consumes
     */
    public OperationParser(final Definitions globalDefinitions,
                           final ParametersGlobal parametersGlobal,
                           final Responses globalResponses,
                           final Set<String> globalConsumes)
    {
        this.globalDefinitions = globalDefinitions;
        this.parametersGlobal = parametersGlobal;
        this.globalResponses = globalResponses;
        this.globalConsumes = globalConsumes;
    }

    /**
     * Parse the operation
     *
     * @param pathValue        with the path value
     * @param operationContent with the operation content
     * @param pathOpKey        with the path operation key
     * @return a new operation parsed
     * @throws APIRestGeneratorException with an occurred exception
     */
    public Operation parseOperation(final String pathValue,
                                    final JsonNode operationContent,
                                    final String pathOpKey) throws APIRestGeneratorException
    {
        // Get the compulsory attribute 'responses'
        final Responses responses = this.getResponses(pathValue, operationContent, pathOpKey);

        // Generate a new instance of Operation
        final Operation operation = new Operation(responses);

        // Get the optional parameter 'description' and set to operation attribute
        final String description = ParserUtil.getNodeValueField(pathOpKey, operationContent, ConstantsInputParser.SW_COMMON_DESCR, false);
        operation.setDescription(description);

        // Get the optional parameter 'operationId' and set to operation attribute
        final String operationId = ParserUtil.getNodeValueField(pathOpKey, operationContent, ConstantsInputParser.SW_OP_OPERATION_ID, true);
        operation.setOperationId(operationId);

        // Generate a set with the schemes and set to operation attribute
        final Set<Scheme> schemes = this.generateSchemes(pathOpKey, operationContent);
        operation.setSchemes(schemes);

        // Generate the set of string with the consumes and set to operation attribute
        final Set<String> consumes = ParserUtil.getNodeValueSetField(pathOpKey, operationContent, ConstantsInputParser.SW_COMMON_CONSUMES, false);
        operation.setConsumes(consumes);

        // Generate a temporary set of consumes (if not assigned) with the global consumes
        final Set<String> tempConsumes = this.generateTemporarySetOfConsumes(consumes);

        // Generate the set of string with the produces and set to operation attribute
        final Set<String> producesS = ParserUtil.getNodeValueSetField(pathOpKey, operationContent, ConstantsInputParser.SW_COMMON_PRODUCES, false);
        operation.setProduces(producesS);

        // Get the optional parameter 'description' and set to operation attribute
        final String deprecated = ParserUtil.getNodeValueField(pathOpKey, operationContent, ConstantsInputParser.SW_OP_DEPRECATED, false);
        operation.setDeprecated(Boolean.getBoolean(deprecated));

        // Get the optional parameter 'parameters' and set to operation attribute
        final ParametersOperation parametersOperation = this.getParametersOperation(operationContent, pathOpKey, tempConsumes);
        operation.setParametersOperation(parametersOperation);

        return operation;
    }

    /**
     * Parse the internal Responses
     *
     * @param pathValue        with the path value
     * @param operationContent with the operation content
     * @param pathOpKey        with the path operation key
     * @return a new instance of responses
     * @throws APIRestGeneratorException with an occurred exception
     */
    private Responses getResponses(final String pathValue, final JsonNode operationContent, final String pathOpKey) throws APIRestGeneratorException
    {
        // Compulsory parameter - validate if exists the 'responses' node
        ParserUtil.getNodeValueJson(pathOpKey, operationContent, ConstantsInputParser.SW_COMMON_RESPONSES, true);
        // Generate a new instance of Responses
        final Responses responses = new Responses(this.globalDefinitions);
        // Generate a new instance of ResponsesParser
        final ResponsesParser responsesParser = new ResponsesParserOperation(pathValue, pathOpKey, this.globalResponses, responses);
        // Parse the responses
        responsesParser.parseResponses(operationContent);

        return responses;
    }

    /**
     * @param pathOpKey        with the path operation key
     * @param operationContent with the operation content
     * @return the sheme
     * @throws APIRestGeneratorException with an occurred exception
     */
    private Set<Scheme> generateSchemes(final String pathOpKey, final JsonNode operationContent) throws APIRestGeneratorException
    {
        final Set<Scheme> outcome = new HashSet<Scheme>();
        final Set<String> schemesStringSet = ParserUtil.getNodeValueSetField(pathOpKey, operationContent, ConstantsInputParser.SW_COMMON_SCHEMES, false);

        if (schemesStringSet != null && !schemesStringSet.isEmpty())
        {
            for (final String schemeString : schemesStringSet)
            {
                outcome.add(Scheme.fromStringName(schemeString));
            }
        }

        return outcome;
    }

    /**
     * @param operationConsumes with the operation consumes
     * @return with the operation consumes or global consumes (if operation consumes was not assigned)
     */
    private Set<String> generateTemporarySetOfConsumes(final Set<String> operationConsumes)
    {
        Set<String> outcome = operationConsumes;

        if (outcome == null)
        {
            outcome = this.globalConsumes;
        }

        return outcome;
    }

    /**
     * Parse the internal Parameters
     *
     * @param operationContent  with the operation content
     * @param keyName           with the key name
     * @param operationConsumes with the local consumes
     * @return a new instance of ParametersOperation
     * @throws APIRestGeneratorException with an occurred exception
     */
    private ParametersOperation getParametersOperation(final JsonNode operationContent,
                                                       final String keyName,
                                                       final Set<String> operationConsumes) throws APIRestGeneratorException
    {
        ParametersOperation parametersOperation = null;

        // Optional parameter - parameters
        final JsonNode parametersNode = ParserUtil.getNodeValueJson(keyName, operationContent, ConstantsInputParser.SW_COMMON_PARAMS, false);

        if (parametersNode != null && parametersNode.isArray())
        {
            // Generate a new instance of Parameters
            parametersOperation = new ParametersOperation(this.globalDefinitions, operationConsumes);
            // Generate a new instance of ParametersParser
            final ParametersOperationParser parametersOperationParser = new ParametersOperationParser(this.parametersGlobal, parametersOperation, keyName);
            // Parse the parameters
            parametersOperationParser.parseParameters(parametersNode);
        }

        return parametersOperation;
    }
}
