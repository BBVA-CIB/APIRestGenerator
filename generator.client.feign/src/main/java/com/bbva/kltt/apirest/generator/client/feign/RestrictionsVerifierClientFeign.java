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

package com.bbva.kltt.apirest.generator.client.feign;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemFile;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsInput;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RestrictionsVerifierClientFeign
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestrictionsVerifierClientFeign.class);

    /**
     * parsed information handler to generate from
     */
    private final ParsedInfoHandler parsedInfoHandler ;

    /**
     * @param parsedInfoHandler with the parsed information handler to generate from
     */
    public RestrictionsVerifierClientFeign(final ParsedInfoHandler parsedInfoHandler)
    {
        this.parsedInfoHandler = parsedInfoHandler ;
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
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_OPTIONS) ;
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_HEAD) ;
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_DELETE) ;
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_PUT) ;
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_GET) ;
            this.verifyPath(pathValue, ConstantsInput.HTTP_METHOD_POST) ;
        }
    }

    /**
     * @param pathValue     with the path value
     * @param pathOperation with the HTTP method
     * @throws APIRestGeneratorException with an occurred exception
     */
    private void verifyPath(final String pathValue, final String pathOperation) throws APIRestGeneratorException
    {
        // Receive a file type is forbidden
        this.verifyPathFileTypeAsParameter(pathValue, pathOperation);
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
                    final String errorString = "Feign generator has not implemented the feature 'upload file as Multipart' yet. " +
                    						   "A different alternative is creating a string parameter in Base64 with the file content.\n" +
                    						   "Info about where is the error: [pathValue: " + pathValue + ", pathOperation: " + pathOperation + "]" ;

                    RestrictionsVerifierClientFeign.LOGGER.error(errorString);
                    throw new APIRestGeneratorException(errorString);
                }
            }
        }
    }
}
