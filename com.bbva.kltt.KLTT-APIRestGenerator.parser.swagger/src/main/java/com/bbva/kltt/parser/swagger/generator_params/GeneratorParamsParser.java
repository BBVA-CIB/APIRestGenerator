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

package com.bbva.kltt.parser.swagger.generator_params;

import com.bbva.kltt.core.parsed_info.generator_params.GeneratorParams;
import com.bbva.kltt.parser.swagger.util.ConstantsInputParser;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsInput;
import com.bbva.kltt.core.util.parser.ParserUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorParamsParser
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorParamsParser.class);

    /**
     * Attribute - Generator properties Parameters
     */
    private final GeneratorParams generatorParams;

    /**
     * @param generatorParams with the Generator properties parameters
     */
    public GeneratorParamsParser(final GeneratorParams generatorParams)
    {
        this.generatorParams = generatorParams;
    }

    /**
     * @param fileContent with the file content
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void parseGeneratorParams(final JsonNode fileContent) throws APIRestGeneratorException
    {
        // Get the Generator properties Node
        final JsonNode generatorNode = ParserUtil.getNodeValueJson(ConstantsInput.ROOT_JSON_NODE_NAME,
                                                                   fileContent,
                                                                   ConstantsInputParser.SW_MAIN_SCH_GENERA,
                                                                   true);

        // Parse Business Unit
        this.parseBUnit(generatorNode);

        // Parse OSGi parameters
        this.parseOSGiParams(generatorNode);
    }

    /**
     * @param generatorNode with the Generator properties node content
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void parseBUnit(final JsonNode generatorNode) throws APIRestGeneratorException
    {
        // Get the Business Unit from the specification
        final String bUnit = ParserUtil.getNodeValueField(ConstantsInputParser.SW_MAIN_SCH_GENERA,
                                                          generatorNode,
                                                          ConstantsInputParser.SW_GEN_SUBSC_BUNIT,
                                                          true);

        // Validate Business Unit
        this.validateBUnit(bUnit);

        // Set Business Unit
        this.generatorParams.setBUnit(bUnit);
    }

    /**
     * @param bUnit with the business unit
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void validateBUnit(final String bUnit) throws APIRestGeneratorException
    {
        final Pattern pattern = Pattern.compile(ConstantsInput.PATT_BUNIT_VALID);
        final Matcher matcher = pattern.matcher(bUnit);
        if (!matcher.matches())
        {
            final String errorString = "The Business Unit is invalid, it must match with the following Regular Expression: " +
                                       ConstantsInput.PATT_BUNIT_VALID;

            GeneratorParamsParser.LOGGER.error(errorString);
            throw new APIRestGeneratorException(errorString);
        }
    }

    /**
     * @param generatorNode with the Generator properties node content
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void parseOSGiParams(final JsonNode generatorNode) throws APIRestGeneratorException
    {
        // Get the OSGi node
        final JsonNode osgiNode = ParserUtil.getNodeValueJson(ConstantsInputParser.SW_MAIN_SCH_GENERA,
                                                              generatorNode,
                                                              ConstantsInputParser.SW_GEN_SUBSC_OSGI,
                                                              false);

        // Warn this situation if the user want to generate any flavor that uses OSGi
        if (osgiNode == null)
        {
            GeneratorParamsParser.LOGGER.warn("'{}' node is null. If you want to generate an OSGi flavour, then you must fill these fields",
                                              ConstantsInputParser.SW_GEN_SUBSC_OSGI);
        }
        else
        {
            // Get the architecture type
            final String architectureType = ParserUtil.getNodeValueField(ConstantsInputParser.SW_GEN_SUBSC_OSGI,
                                                                         osgiNode,
                                                                         ConstantsInputParser.SW_GEN_SUBSC_O_AR_T,
                                                                         true);

            // Get the CXF Address value
            final String cxfAddress = ParserUtil.getNodeValueField(ConstantsInputParser.SW_GEN_SUBSC_OSGI,
                                                                   osgiNode,
                                                                   ConstantsInputParser.SW_GEN_SUBSC_O_ADDR,
                                                                   true);

            // Get the CXF Context value
            final String cxfContext = ParserUtil.getNodeValueField(ConstantsInputParser.SW_GEN_SUBSC_OSGI,
                                                                   osgiNode,
                                                                   ConstantsInputParser.SW_GEN_SUBSC_O_CONT,
                                                                   true);

            // Set the values
            this.generatorParams.addOsgiParams(architectureType, cxfAddress, cxfContext);
        }
    }
}
