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

package com.bbva.kltt.apirest.controller.server.spring;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintStream;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.bbva.kltt.apirest.controller.server.spring.util.ConstantsLauncherWeb;
import com.bbva.kltt.apirest.core.ant.GlobalAntExecutor;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.AbstractDependencyUtility;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.core.util.FilesUtility;
import com.bbva.kltt.apirest.core.web.Utilities;

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
     * Simple Messaging Template
     */
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    /**
	 * Environment variables
	 */
    @Autowired
    private Environment environment ;

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
        String outcome = "" ;

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
     * @return a response entity with the ID user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getUserId")
    public ResponseEntity<String> getUserId(final HttpSession session)
    {
        return ResponseEntity.ok().body(session.getId()) ;
    }

    /**
     * @param username             with the user name
     * @param parserType           with the parser type
     * @param fileName             with the file name
     * @param specificationContent with the specification content
     * @param translatorType 	   with the translator type as string
     * @return a response entity
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST,
                    value = "/generate/{username}/{parserType}",
                    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
                    produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity generate(@PathVariable final String username,
            					   @PathVariable final String parserType,
    							   @RequestParam final String fileName,
                                   @RequestParam final String specificationContent,
                                   @RequestParam final String translatorType)
    {
        ResponseEntity responseEntity = null;
        GlobalAntExecutor globalAntExecutor = null;
        String fullTemporaryDir = null;

        boolean removeAfterSend = true ;
        try
        {
            globalAntExecutor = new GlobalAntExecutor(parserType, translatorType);
            
            String temporaryDir = globalAntExecutor.generateTemporaryOutputDirectory(this.getFlagGenBuilderPath()) ;

            // Generate and get the full temporary directory
            fullTemporaryDir = globalAntExecutor.generateFullTemporaryDir(this.getFlagGenBuilderPath(), temporaryDir) ;
            // Validate the file extension
            globalAntExecutor.validateFileExtension(fileName);
            
            // Get the deliverable file name
            final String deliverableFileName = globalAntExecutor.getDeliverableFileName(fileName, translatorType) ;

            // Store the income multipart in temporary folder
            final File fullTemporaryFile = this.storeIncomeMultipartInTemporaryFolder(fileName,
                                                                                      specificationContent,
                                                                                      fullTemporaryDir);

            // PrintStreams
            final PrintStream errorStreamWrapper = new WrapperPrintStream(System.err, this, username);
            final PrintStream outputStreamWrapper = new WrapperPrintStream(System.out, this, username);

            // Execute the task
            globalAntExecutor.launchAntExecutor(this.getFlagGenBuilderPath(),
                                                fullTemporaryFile.getAbsolutePath(),
                                                deliverableFileName,
                                                temporaryDir,
                                                errorStreamWrapper,
                                                outputStreamWrapper);

            // Internal method to generate the response
            responseEntity = this.generateResponse(fullTemporaryDir, deliverableFileName);
            
            removeAfterSend = this.getFlagRemoveAfterSend() ;
        }
        catch (APIRestGeneratorException apiRestGeneratorExc)
        {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiRestGeneratorExc.getMessage());
        }
        finally
        {
            if (removeAfterSend)
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
     * @return the flag removeAfterSend
     * @throws APIRestGeneratorException with an occurred exception
     */
    private boolean getFlagRemoveAfterSend() throws APIRestGeneratorException
    {
    	final String removeAfterSendZip = this.environment.getProperty(ConstantsLauncherWeb.REMOVE_AFTER_SEND_ZIP) ;
        if (removeAfterSendZip == null || removeAfterSendZip.isEmpty())
        {
            final String errorString = ConstantsLauncherWeb.REMOVE_AFTER_SEND_ZIP + " was not configured" ;

            SpecificationsController.LOGGER.error(errorString) ;
            throw new APIRestGeneratorException(errorString) ;

        }
        
        return Boolean.valueOf(removeAfterSendZip) ;
    }
    
    /**
     * @return the flag genBuilderPath
     * @throws APIRestGeneratorException with an occurred exception
     */
    private String getFlagGenBuilderPath() throws APIRestGeneratorException
    {
        final String genBuilderPath = this.environment.getProperty(ConstantsLauncherWeb.GEN_BUILDER_PATH) ;
        if (genBuilderPath == null || genBuilderPath.isEmpty())
        {
            final String errorString = ConstantsLauncherWeb.GEN_BUILDER_PATH + " was not configured" ;

            SpecificationsController.LOGGER.error(errorString) ;
            throw new APIRestGeneratorException(errorString) ;
        }

        return genBuilderPath ;
    }

	/**
     * @param fullTemporaryDir 	  with the full temporary directory path
     * @param deliverableFileName with the deliverable file name
     * @throws APIRestGeneratorException with an occurred exception
     */
    private ResponseEntity<InputStreamResource> generateResponse(final String fullTemporaryDir, final String deliverableFileName) throws APIRestGeneratorException
    {
        // Get the deliver file as String
        final byte[] deliverable = Utilities.readFileContent(fullTemporaryDir + File.separator + deliverableFileName);

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

