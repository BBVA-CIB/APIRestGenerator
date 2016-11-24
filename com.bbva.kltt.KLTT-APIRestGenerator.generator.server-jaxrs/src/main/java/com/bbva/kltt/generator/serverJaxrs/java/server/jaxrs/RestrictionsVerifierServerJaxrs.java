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

package com.bbva.kltt.generator.serverJaxrs.java.server.jaxrs;

import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.parsed_info.common.ItemArray;
import com.bbva.kltt.core.parsed_info.common.ItemFile;
import com.bbva.kltt.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.core.parsed_info.responses.Response;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.ConstantsInput;
import com.bbva.kltt.generator.serverJaxrs.util.ConstantsOutputServerJaxrs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RestrictionsVerifierServerJaxrs
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestrictionsVerifierServerJaxrs.class);

    /**
     * parsed information handler to generate from
     */
    private final ParsedInfoHandler parsedInfoHandler;

    /**
     * @param parsedInfoHandler with the parsed information handler to generate from
     */
    public RestrictionsVerifierServerJaxrs(final ParsedInfoHandler parsedInfoHandler)
    {
        this.parsedInfoHandler = parsedInfoHandler;
    }

    /**
     * Verify the input values for JAX-RS
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void verify() throws APIRestGeneratorException
    {
        for (final String pathValue : this.parsedInfoHandler.getPathValues())
        {
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_OPTIONS);
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_HEAD);
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_DELETE);
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_PUT);
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_GET);
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_POST);
        }
    }

    /**
     * @param pathValue     with the path value
     * @param pathOperation with the HTTP method
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void verifyPath(final String pathValue, final String pathOperation) throws APIRestGeneratorException
    {
        // Path value must match with a regular expression
        this.verifyPathName(pathValue);

        // Return a file type is forbidden
        this.verifyPathFileTypeAsReturn(pathValue, pathOperation);

        // Receive a file type is forbidden
        this.verifyPathFileTypeAsParameter(pathValue, pathOperation);

        // Receive any array as query parameter is forbidden
        this.verifyPathArrayInQueryParameters(pathValue, pathOperation);
    }

    /**
     * @param pathValue with the path value
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void verifyPathName(final String pathValue) throws APIRestGeneratorException
    {
        final Pattern pattern = Pattern.compile(ConstantsOutputServerJaxrs.PATT_PATHS);
        final Matcher matcher = pattern.matcher(pathValue);
        if (!matcher.matches())
        {
            final String errorString = "The pathValue '" + pathValue + "' must match with the following pattern: " +
                                        ConstantsOutputServerJaxrs.PATT_PATHS;

            RestrictionsVerifierServerJaxrs.LOGGER.error(errorString);
            throw new APIRestGeneratorException(errorString);
        }

    }

    /**
     * @param pathValue     with the path value
     * @param pathOperation with the HTTP method
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void verifyPathFileTypeAsReturn(final String pathValue, final String pathOperation) throws APIRestGeneratorException
    {
        final Response response = (Response) this.parsedInfoHandler.getOutboundServerItemType(pathValue, pathOperation);
        if (response != null && response.getItem() != null && response.getItem() instanceof ItemFile)
        {
            final String errorString = "JAXRS cannot contains any return value as 'file' type";

            RestrictionsVerifierServerJaxrs.LOGGER.error(errorString);
            throw new APIRestGeneratorException(errorString);
        }
    }

    /**
     * @param pathValue     with the path value
     * @param pathOperation with the HTTP method
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void verifyPathFileTypeAsParameter(final String pathValue, final String pathOperation) throws APIRestGeneratorException
    {
        final Map<String, Parameter> formDataParams = this.parsedInfoHandler.getParametersFormDataMap(pathValue, pathOperation);
        if (formDataParams != null)
        {
            for (final Parameter parameter : formDataParams.values())
            {
                if (parameter.getItem() != null && parameter.getItem() instanceof ItemFile)
                {
                    final String errorString = "JAXRS cannot contains any input parameter as 'file' type";

                    RestrictionsVerifierServerJaxrs.LOGGER.error(errorString);
                    throw new APIRestGeneratorException(errorString);
                }
            }
        }
    }

    /**
     * @param pathValue     with the path value
     * @param pathOperation with the HTTP method
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void verifyPathArrayInQueryParameters(final String pathValue, final String pathOperation) throws APIRestGeneratorException
    {
        final Map<String, Parameter> queryParams = this.parsedInfoHandler.getParametersQueryMap(pathValue, pathOperation);
        if (queryParams != null)
        {
            for (final Parameter parameter : queryParams.values())
            {
                if (parameter.getItem() != null && parameter.getItem() instanceof ItemArray)
                {
                    final String errorString = "JAXRS cannot contains any input query parameter as 'array' type";

                    RestrictionsVerifierServerJaxrs.LOGGER.error(errorString);
                    throw new APIRestGeneratorException(errorString);
                }
            }
        }
    }
}
