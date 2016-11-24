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

import com.bbva.kltt.core.parsed_info.common.Item;
import com.bbva.kltt.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.core.parsed_info.parameters.ParameterFactory;
import com.bbva.kltt.core.parser.IItemFactory;
import com.bbva.kltt.core.parser.IParameterFactory;
import com.bbva.kltt.parser.swagger.util.ConstantsInputParser;
import com.bbva.kltt.parser.swagger.util.ItemParser;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsMiddle;
import com.bbva.kltt.core.util.parser.ParserUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class ParametersBaseParser extends ItemParser
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParametersBaseParser.class);

    /**
     * Attribute - Parameter factory
     */
    private final IParameterFactory parameterFactory;

    /**
     * Protected constructor
     *
     * @param itemFactory with the item factory
     * @param consumes    with the consumes
     */
    protected ParametersBaseParser(final IItemFactory itemFactory, final Set<String> consumes)
    {
        super(itemFactory);

        this.parameterFactory = new ParameterFactory(consumes);
    }

    /**
     * @param keyName with the key name
     * @param keyNode with the key node
     * @return a new parameter
     * @throws APIRestGeneratorException with an occurred exception
     */
    protected Parameter parseParameterObject(final String keyName, final JsonNode keyNode) throws APIRestGeneratorException
    {
        // Get the optional field 'description'
        final String description = ParserUtil.getNodeValueField(keyName, keyNode, ConstantsInputParser.SW_COMMON_DESCR, false);

        final String in = ParserUtil.getNodeValueField(keyName, keyNode, ConstantsInputParser.SW_PARAM_SUBSC_IN, true);
        final String name = ParserUtil.getNodeValueField(keyName, keyNode, ConstantsInputParser.SW_PARAM_SUBSC_NAME, true);
        final String alias = ParserUtil.getNodeValueField(keyName, keyNode, ConstantsInputParser.SW_COMMON_ALIAS, false);
        final String required = ParserUtil.getNodeValueField(keyName, keyNode, ConstantsInputParser.SW_PARAM_SUBSC_REQ, false);
        final String inValue = this.getParamType(in);

        Parameter parameter = null;
        Item item = null;

        if (this.parameterFactory.isBodyParameter(in, ConstantsInputParser.SW_PARAM_IN_BODY))
        {
            final JsonNode schemaNode = ParserUtil.getNodeValueJson(name, keyNode, ConstantsInputParser.SW_COMMON_SUBSC_SCHE, true);
            final String type = ParserUtil.getNodeValueField(keyName, schemaNode, ConstantsInputParser.SW_COMMON_TYPE, false);
            parameter = this.parameterFactory.createNewParameter(inValue, name, alias, description, required, type);
            item = this.parseItemObject(name, schemaNode);
        }
        else
        {
            final String type = ParserUtil.getNodeValueField(keyName, keyNode, ConstantsInputParser.SW_COMMON_TYPE, false);
            parameter = this.parameterFactory.createNewParameter(inValue, name, alias, description, required, type);
            item = this.parseItemObject(name, keyNode);
        }

        this.parameterFactory.addItemIntoParameter(parameter, item);

        return parameter;
    }

    /**
     * @param in with the key type param
     * @return type of the param
     * @throws APIRestGeneratorException with an occurred exception
     */
    protected String getParamType(final String in) throws APIRestGeneratorException
    {
        String inValue = null;

        if (ConstantsInputParser.SW_PARAM_IN_BODY.equalsIgnoreCase(in))
        {
            inValue = ConstantsMiddle.PARAM_IN_BODY;
        }
        else if (ConstantsInputParser.SW_PARAM_IN_QUERY.equalsIgnoreCase(in))
        {
            inValue = ConstantsMiddle.PARAM_IN_QUERY;
        }
        else if (ConstantsInputParser.SW_PARAM_IN_HEADER.equalsIgnoreCase(in))
        {
            inValue = ConstantsMiddle.PARAM_IN_HEADER;
        }
        else if (ConstantsInputParser.SW_PARAM_IN_PATH.equalsIgnoreCase(in))
        {
            inValue = ConstantsMiddle.PARAM_IN_PATH;
        }
        else if (ConstantsInputParser.SW_PARAM_IN_FORMDATA.equalsIgnoreCase(in))
        {
            inValue = ConstantsMiddle.PARAM_IN_FORMDATA;
        }
        else
        {
            final String errorString = "The parameter 'in' is invalid. The expected values are one of the following: " +
                    ConstantsInputParser.SW_PARAM_IN_QUERY + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK +
                    ConstantsInputParser.SW_PARAM_IN_HEADER + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK +
                    ConstantsInputParser.SW_PARAM_IN_PATH + ConstantsCommon.STRING_COMMA + ConstantsCommon.STRING_BLANK +
                    ConstantsInputParser.SW_PARAM_IN_FORMDATA;

            ParametersBaseParser.LOGGER.error(errorString);
            throw new APIRestGeneratorException(errorString);
        }

        return inValue;
    }
}
