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

package com.bbva.kltt.apirest.core.launcher;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.generator.GeneratorGlobal;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfo;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsLauncher;
import com.bbva.kltt.apirest.core.util.parser.ParserUtil;


/**
 * Parent launcher for all launcher classes, it contains the common constants and command line argument and helper
 * methods.
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GlobalLauncher implements ILauncher
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalLauncher.class);

    // -----------------------------------------------------------------------------------------------------------------
    // --------------------------------- COMMAND LINE OPTIONS ----------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The file path where is the specification file content
     */
    private final Option specificationFilePathOption = new Option(ConstantsLauncher.FILE_PATH_FLAG,
                                                                  ConstantsLauncher.FILE_PATH_EXTE_FLAG,
                                                                  true,
                                                                  ConstantsLauncher.FILE_PATH_DESCRIP);

    /**
     * The directory where code will be generated
     */
    private final Option codeGenOutputDirectoryOption = new Option(ConstantsLauncher.CODE_GEN_OUT_DIR_FLAG,
                                                                   ConstantsLauncher.CODE_GEN_OUT_DIR_EXTE_FLAG,
                                                                   true,
                                                                   ConstantsLauncher.CODE_GEN_OUT_DIR_DESCRIP);
    
    /**
     * The directory where code will be generated
     */
    private final Option parserTypeOption 			  = new Option(ConstantsLauncher.PARSER_TYPE_FLAG,
                                                                   ConstantsLauncher.PARSER_TYPE_EXTE_FLAG,
                                                                   true,
                                                                   ConstantsLauncher.PARSER_TYPE_DESCRIP);
    /**
     * The command line with all the values parsed
     */
    private CommandLine commandLine;

    /**
     * Launch the code generation, this is the entry point of any generation
     *
     * @param cmdArgs    with the command line arguments
     * @param moduleName with the module name
     * @param className  with the name of the init generator class
     * @throws APIRestGeneratorException exception thrown if there is any problem
     */
    public void launch(final String[] cmdArgs, final String moduleName, final String className) throws APIRestGeneratorException
    {
        final GenerationParameters genParams      = new GenerationParameters();

        // Parse the command line arguments
        this.parseAndValidateCommandLine(cmdArgs);

        // Apply the common parameters to the parsed information
        this.applyCommonParameters(genParams);

        // Get the schemas parser and parse the information
        final ParsedInfo parsedInfo               = ParserUtil.getSchemasParser(genParams).parse();

        // Create an instance of ParsedInfoHandler to control the calls
        final ParsedInfoHandler parsedInfoHandler = new ParsedInfoHandler(parsedInfo);

        // Create and run the global generator
        final GeneratorGlobal generator           = new GeneratorGlobal(genParams, parsedInfoHandler);

        // Run the generation
        generator.start(moduleName, className);

    }

    /**
     * Add the command line options including the specific parser ones, parse the command line and validate the found options
     *
     * @param args command line arguments
     * @throws APIRestGeneratorException exception thrown if there is a problem
     */
    private void parseAndValidateCommandLine(final String[] args) throws APIRestGeneratorException
    {
        GlobalLauncher.LOGGER.info("Parsing command line arguments: {}", (Object) args);

        // Create the options
        final Options commandLineOptions = new Options();

        // Add the common command line options
        this.addCommonCommandLineOptions(commandLineOptions);

        // Parse the command line
        final CommandLineParser commandLineParser = new PosixParser();

        try
        {
            this.commandLine = commandLineParser.parse(commandLineOptions, args);
        }
        catch (ParseException parseException)
        {
            String errorString = "Error parsing command line arguments";

            GlobalLauncher.LOGGER.error(errorString, parseException);
            throw new APIRestGeneratorException(errorString, parseException);
        }

        // Verify the command line arguments, both the common and the additional ones
        this.validateCommandLineArguments();
    }

    /**
     * Add the common command line options to the options list, it will be used to parse the command line later
     *
     * @param options with the options object to add the common options to
     */
    private void addCommonCommandLineOptions(final Options options)
    {
        options.addOption(this.specificationFilePathOption);
        options.addOption(this.codeGenOutputDirectoryOption);
        options.addOption(this.parserTypeOption);
    }

    /**
     * Validate the input command line arguments parsed from the command line
     *
     * @throws APIRestGeneratorException exception thrown if the validation is not correct
     */
    private void validateCommandLineArguments() throws APIRestGeneratorException
    {
        final String specificationFilePath  = this.getCmdStringOption(this.specificationFilePathOption);
        final String codeGenOutputDirectory = this.getCmdStringOption(this.codeGenOutputDirectoryOption);
        final String parserType 			= this.getCmdStringOption(this.parserTypeOption);

        String errorString = null;
        if (specificationFilePath == null || specificationFilePath.isEmpty())
        {
            errorString = "Invalid file path of the specification content: " + specificationFilePath;
        }
        else if (codeGenOutputDirectory == null || codeGenOutputDirectory.isEmpty())
        {
            errorString = "CodeGen output directory is null or empty: " + codeGenOutputDirectory;
        }
        else if (parserType == null || parserType.isEmpty())
        {
        	errorString = "Parser type is null or empty: " + parserType;
        }

        if (errorString != null)
        {
            GlobalLauncher.LOGGER.error(errorString);
            throw new APIRestGeneratorException(errorString);
        }
    }

    /**
     * Applies the common parameters of the command line to the generation information to give access to them later to the parsers
     * and generators in the next steps
     *
     * @param genParams with the generation parameters object that is going to be filled with the parameters values
     * @throws APIRestGeneratorException exception thrown if there is any problem
     */
    private void applyCommonParameters(final GenerationParameters genParams) throws APIRestGeneratorException
    {
        final String specificationFilePath  = this.getCmdStringOption(this.specificationFilePathOption);
        final String codeGenOutputDirectory = this.getCmdStringOption(this.codeGenOutputDirectoryOption);
        final String parserType 			= this.getCmdStringOption(this.parserTypeOption);

        // Set the rest of the parameters
        genParams.setSpecificationFilePath(specificationFilePath);
        genParams.setCodeGenOutputDirectory(codeGenOutputDirectory);
        genParams.setParserType(parserType);
    }

    /**
     * Return the String option value from the command line given the representing option
     *
     * @param option with the representing option of the command line
     * @return the value of the option, null if unsettled
     */
    protected String getCmdStringOption(final Option option)
    {
        if (this.commandLine.hasOption(option.getOpt()))
        {
            return this.commandLine.getOptionValue(option.getOpt()).trim();
        }
        else if (this.commandLine.hasOption(option.getLongOpt()))
        {
            return this.commandLine.getOptionValue(option.getLongOpt()).trim();
        }

        return null;
    }
}
