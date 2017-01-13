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

package com.bbva.kltt.controller.serverSpring;

import com.bbva.kltt.core.ant.GlobalAntExecutor;
import com.bbva.kltt.controller.serverSpring.util.ConstantsLauncherWeb;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.util.AbstractDependencyUtility;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.core.util.FilesUtility;
import com.bbva.kltt.core.web.IAPIRestGeneratorService;
import com.bbva.kltt.core.web.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintStream;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public final class SpecificationsController
{
    /**
     * Logger of the class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecificationsController.class);

    /**
     * API Rest Generator Configuration
     */
    @Autowired
    public IAPIRestGeneratorService apiRestGenService;

    /**
     * Simple Messaging Template
     */
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Public constructor
     */
    public SpecificationsController()
    {
        // Empty Constructor
    }

    /**
     * @return a response entity with the generator modules that are installed
     */
    @RequestMapping(method 	= RequestMethod.GET, value 	= "/getGenerateModules")
    public ResponseEntity<String> getGenerateModules()
    {
        String outcome;

        try
        {
            outcome = AbstractDependencyUtility.getDependencies(ConstantsOutput.COMMON_GEN_TYPE_PACKAGE) ;
        }
        catch (APIRestGeneratorException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body(outcome) ;
    }

    /**
     * @param session   with the http session
     * @param ivUser    with the ivUser
     * @return a response entity with the ID user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserId")
    public ResponseEntity<String> getUserId(final HttpSession session,
                                            @RequestHeader(required = false,
                                                           value = ConstantsLauncherWeb.HEADER_REQ_IV_USER) final String ivUser)
    {
        String outcome = ivUser;
        if (outcome == null || ivUser.isEmpty())
        {
            outcome = session.getId();

            SpecificationsController.LOGGER.warn("The value {} was not send in the header, then system will use the ID '{}' from HttpSession",
                                                  ConstantsLauncherWeb.HEADER_REQ_IV_USER,
                                                  outcome);
        }

        return ResponseEntity.ok().body(outcome);
    }

    /**
     * @param fileName             with the file name
     * @param specificationContent with the specification content
     * @param username             with the user name
     * @param parserType           with the parser type
     * @param translatorTypeString with the translator type as string
     * @return a response entity
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST,
                    value = "/generate/{username}/{parserType}/{translatorTypeString}",
                    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
                    produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity generate(@RequestParam final String fileName,
                                   @RequestParam final String specificationContent,
                                   @PathVariable final String username,
                                   @PathVariable final String parserType,
                                   @PathVariable final String translatorTypeString)
    {
        ResponseEntity responseEntity = null;
        GlobalAntExecutor globalAntExecutor = null;
        String fullTemporaryDir = null;

        try
        {
            globalAntExecutor = new GlobalAntExecutor(parserType, translatorTypeString);

            String temporaryDir = globalAntExecutor.generateTemporaryOutputDirectory(this.apiRestGenService.getConfig().getGeneratorBuilderPath());

            // Generate and get the full temporary directory
            fullTemporaryDir = globalAntExecutor.generateFullTemporaryDir(this.apiRestGenService.getConfig().getGeneratorBuilderPath(),
                                                                          temporaryDir);

            // Validate the file extension
            globalAntExecutor.validateFileExtension(fileName);

            // Store the income multipart in temporary folder
            final File fullTemporaryFile = this.storeIncomeMultipartInTemporaryFolder(fileName, specificationContent, fullTemporaryDir);

            // PrintStreams
            final PrintStream errorStreamWrapper  = new WrapperPrintStream(System.err, this, username);
            final PrintStream outputStreamWrapper = new WrapperPrintStream(System.out, this, username);

            // Execute the task
            globalAntExecutor.launchAntExecutor(this.apiRestGenService.getConfig().getGeneratorBuilderPath(),
                                                fullTemporaryFile.getAbsolutePath(),
                                                temporaryDir,
                                                errorStreamWrapper,
                                                outputStreamWrapper);

            // Internal method to generate the response
            responseEntity = this.generateResponse(fullTemporaryDir);
        }
        catch (APIRestGeneratorException apiRestGeneratorExc)
        {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiRestGeneratorExc.getMessage());
        }
        finally
        {
            if (this.apiRestGenService.getConfig().isRemoveAfterSendZip())
            {
                try
                {
                    // Remove the full temporary directory
                    globalAntExecutor.removeFullTemporaryDirectory(fullTemporaryDir);
                }
                catch (APIRestGeneratorException apiRestGeneratorExc)
                {
                    responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiRestGeneratorExc.getMessage());
                }
            }
        }

        return responseEntity;
    }

    /**
     * @param fullTemporaryDir with the full temporary directory path
     * @throws APIRestGeneratorException with an occurred exception
     */
    private ResponseEntity<InputStreamResource> generateResponse(final String fullTemporaryDir) throws APIRestGeneratorException
    {
        // Get the deliver file as String
        final byte[] deliverable = Utilities.readFileContent(fullTemporaryDir + File.separator + ConstantsLauncherWeb.DELIVER_COMPRESS_FILE_NAME);

        // Generate the response
        return ResponseEntity.ok(new InputStreamResource(new ByteArrayInputStream(deliverable)));
    }

    /**
     * @param fileName             with the file name
     * @param specificationContent with the specification content
     * @param fullTemporaryDir     with the full temporary directory
     * @return a new file (full temporary path)
     * @throws APIRestGeneratorException with an occurred exception
     */
    private File storeIncomeMultipartInTemporaryFolder(final String fileName,
                                                       final String specificationContent,
                                                       final String fullTemporaryDir) throws APIRestGeneratorException
    {
        final File fullTemporaryFile = FilesUtility.createOutputFile(fullTemporaryDir, fileName);
        FilesUtility.copyFileUsingStream(new ByteArrayInputStream(specificationContent.getBytes()), fullTemporaryFile);

        return fullTemporaryFile;
    }

    /**
     * @param username with the user name
     * @param data     with the data to be sent
     */
    public void sendDataToClient(final String username, final String data)
    {
        this.simpMessagingTemplate.convertAndSend("/topic/" + username + "/job", data);
    }
}

