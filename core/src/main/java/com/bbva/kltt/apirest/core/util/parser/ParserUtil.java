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

package com.bbva.kltt.apirest.core.util.parser;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parser.IParser;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsInput;
import com.bbva.kltt.apirest.core.util.ConstantsLauncher;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.core.util.InvokeModule;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class ParserUtil
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParserUtil.class);

    /**
     * @param filePath with the file path
     * @return the extension of the file
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static String validateExtension(final String filePath) throws APIRestGeneratorException
    {
        String outcome = null ;

        final String filePathLowerCase = filePath.toLowerCase() ;
        if (filePathLowerCase.endsWith(ConstantsCommon.STRING_DOT + ConstantsCommon.EXTENSION_YAML))
        {
            outcome = ConstantsCommon.EXTENSION_YAML ;
        }
        else if (filePathLowerCase.endsWith(ConstantsCommon.STRING_DOT + ConstantsCommon.EXTENSION_YAML_OTHER))
        {
            outcome = ConstantsCommon.EXTENSION_YAML_OTHER ;
        }
        else if (filePathLowerCase.endsWith(ConstantsCommon.STRING_DOT + ConstantsCommon.EXTENSION_JSON))
        {
            outcome = ConstantsCommon.EXTENSION_JSON ;
        }
        else
        {
            final String errorString = "The current file has not a valid extension to be parsed: " + filePath ;

            ParserUtil.LOGGER.error(errorString) ;
            throw new APIRestGeneratorException(errorString) ;
        }

        return outcome ;
    }

    /**
     * Get a common string as JSON to be parsed
     *
     * @param filePath with the file path
     * @return a common string as JSON to be parsed
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static JsonNode deserializeIntoJson(final String filePath) throws APIRestGeneratorException
    {
        JsonNode jsonNode = null;

        final String fileExtension = ParserUtil.validateExtension(filePath);
        final String fileContent = ParserUtil.readFileContent(filePath);

        try
        {
            if (fileExtension.equals(ConstantsCommon.EXTENSION_YAML) || fileExtension.equals(ConstantsCommon.EXTENSION_YAML_OTHER))
            {
                final Yaml yaml = new Yaml();
                jsonNode = (JsonNode) Json.mapper().convertValue(yaml.load(fileContent), JsonNode.class);
            }
            else
            {
                jsonNode = Json.mapper().readTree(fileContent);
            }
        }
        catch (IOException ioException)
        {
            final String errorString = "IOException when reading the file: " + ioException;

            ParserUtil.LOGGER.error(errorString, ioException);
            throw new APIRestGeneratorException(errorString, ioException);
        }

        return jsonNode;
    }

    /**
     * Read the file content
     *
     * @param filePath with the file path
     * @return the file content
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static String readFileContent(final String filePath) throws APIRestGeneratorException
    {
        Path path = null;

        if (filePath.toLowerCase().startsWith(ConstantsInput.SO_PATH_STRING_PREFIX))
        {
            path = Paths.get(URI.create(filePath));
        }
        else
        {
            path = Paths.get(filePath, new String[0]);
        }

        String fileContent = null;

        if (Files.exists(path, new LinkOption[0]))
        {
            try
            {
                fileContent = FileUtils.readFileToString(path.toFile(), "UTF-8");
            }
            catch (IOException ioException)
            {
                final String errorString = "IOException when reading the file: " + ioException;

                ParserUtil.LOGGER.error(errorString, ioException);
                throw new APIRestGeneratorException(errorString, ioException);
            }
        }
        else
        {
            fileContent = FilesUtility.loadFileContentFromClasspath(filePath);
        }

        return fileContent;
    }

    /**
     * Generate a Set of String from String
     *
     * @param arrayNode with the JsonNode as Array
     * @return a set of strings
     */
    public static Set<String> generateSetStringFromString(final ArrayNode arrayNode)
    {
        final Set<String> outcome = new HashSet<String>();

        final Iterator<JsonNode> iterator = arrayNode.elements();
        while (iterator.hasNext())
        {
            final JsonNode jsonNode = iterator.next();
            outcome.add(jsonNode.asText());
        }

        return outcome;
    }

    /**
     * @param nodeName   with the node name
     * @param node       with the node to search the field
     * @param fieldName  with the field name to be searched
     * @param isRequired true if the field is required
     * @return the json node of the child
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static JsonNode getNodeValueJson(final String nodeName, final JsonNode node, final String fieldName, final boolean isRequired) throws APIRestGeneratorException
    {
        JsonNode outcome = null;

        final boolean hasField = node.has(fieldName);
        if (hasField)
        {
            outcome = node.get(fieldName);
            if (outcome.isNull() && isRequired)
            {
                ParserUtil.generateExceptionRequiredNodeContent(nodeName, fieldName);
            }
        }
        else if (!hasField && isRequired)
        {
            ParserUtil.generateExceptionRequiredField(nodeName, fieldName);
        }

        return outcome;
    }

    /**
     * @param nodeName   with the node name
     * @param node       with the node to search the field
     * @param fieldName  with the field name to be searched
     * @param isRequired true if the field is required
     * @return the field value
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static String getNodeValueField(final String nodeName, final JsonNode node, final String fieldName, final boolean isRequired) throws APIRestGeneratorException
    {
        String outcome = null;

        final boolean hasField = node.has(fieldName);
        if (hasField)
        {
            final JsonNode attributeNode = node.get(fieldName);
            if (attributeNode.isNull() && isRequired)
            {
                ParserUtil.generateExceptionRequiredNodeContent(nodeName, fieldName);
            }

            outcome = attributeNode.asText();
        }
        else if (!hasField && isRequired)
        {
            ParserUtil.generateExceptionRequiredField(nodeName, fieldName);
        }

        return outcome;
    }

    /**
     * @param nodeName   with the node name
     * @param node       with the node to search the field
     * @param fieldName  with the field name to be searched
     * @param isRequired true if the field is required
     * @return the field value as set
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static Set<String> getNodeValueSetField(final String nodeName,
                                                   final JsonNode node,
                                                   final String fieldName,
                                                   final boolean isRequired) throws APIRestGeneratorException
    {
        Set<String> outcome = null;

        final boolean hasField = node.has(fieldName);
        if (hasField)
        {
            JsonNode jsonNode = node.get(fieldName);
            if (jsonNode instanceof ArrayNode)
            {
                outcome = ParserUtil.generateSetStringFromString((ArrayNode) jsonNode);
            }
            else
            {
                outcome = new HashSet<String>();

                if (!jsonNode.isNull())
                {
                    outcome.add(jsonNode.asText());
                }
            }
        }
        else if (!hasField && isRequired)
        {
            ParserUtil.generateExceptionRequiredField(nodeName, fieldName);
        }

        return outcome;
    }

    /**
     * @param nodeName  with the node name
     * @param fieldName with the field name
     * @throws APIRestGeneratorException with an occurred exception
     */
    public static void generateExceptionRequiredField(final String nodeName, final String fieldName) throws APIRestGeneratorException
    {
        final String errorString = "The field name '" + fieldName + "' is required in the node: " + nodeName;

        ParserUtil.LOGGER.error(errorString);
        throw new APIRestGeneratorException(errorString);
    }

    /**
     * @param nodeName  with the node name
     * @param fieldName with the field name
     * @throws APIRestGeneratorException with an occurred exception
     */
    private static void generateExceptionRequiredNodeContent(final String nodeName, final String fieldName) throws APIRestGeneratorException
    {
        final String errorString = "The content of the field '" + fieldName + "' is required in the node: " + nodeName;

        ParserUtil.LOGGER.error(errorString);
        throw new APIRestGeneratorException(errorString);
    }

    /**
     * @param listString with the list string
     * @return with a string separated by comma
     */
    public static String generateToStringFromSetString(final Set<String> listString)
    {
        String outcome = ConstantsCommon.STRING_EMPTY ;
        for (final String string : listString)
        {
            if (outcome.isEmpty())
            {
                outcome = string ;
            }
            else
            {
            	final StringBuilder stringBuilder = new StringBuilder() ;
            	
            	stringBuilder.append(outcome) ;
            	stringBuilder.append(ConstantsCommon.STRING_COMMA) ;
            	stringBuilder.append(string) ;
            	
                outcome = stringBuilder.toString() ;
            }
        }

        return outcome ;
    }
    
    /**
     * Get the schemas parser.
     * Important some generation parameters may be assigned during parsing time.
     *
     * @param generationParameters with the generation parameters
     * @return the parsedInfo
     * @throws APIRestGeneratorException exception thrown if there is any problem during the process
     */
    public static IParser getSchemasParser(final GenerationParameters generationParameters) throws APIRestGeneratorException
    {
        IParser launchParser      = null;

        String packageName 		  = new StringBuilder(ConstantsLauncher.PARSER_PACKAGE).append(ConstantsCommon.STRING_DOT)
        																			   .append(generationParameters.getParserType()).toString() ;
        
        InvokeModule invokeModule = InvokeModule.initInvokeModule(packageName, ConstantsLauncher.MAIN_LAUNCH_PARSER) ;

        if (invokeModule != null)
        {
            Class<?>[] typeParams = {String.class} ;
            Object[] params = {generationParameters.getSpecificationFilePath()} ;

            launchParser = (IParser) invokeModule.createInstance(params, typeParams) ;
        }

        return launchParser ;
    }

    /**
     * Private constructor
     */
    private ParserUtil()
    {
        // Private constructor
    }
}
