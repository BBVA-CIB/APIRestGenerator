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

package com.bbva.kltt.apirest.generator.java.velocity.models;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.apirest.core.generator.IGenerator;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemComplex;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRef;
import com.bbva.kltt.apirest.core.parsed_info.responses.Response;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.velocity.TranslatorGeneratorJavaModelsBase;
import com.bbva.kltt.apirest.generator.java.velocity.models.exceptions.CommonExceptionJavaGenerator;
import com.bbva.kltt.apirest.generator.java.velocity.models.exceptions.CustomExceptionJavaGenerator;
import com.bbva.kltt.apirest.generator.java.velocity.models.exceptions.GeneratedInterfaceExceptionJavaGenerator;
import com.bbva.kltt.apirest.generator.java.velocity.models.test.ModelsJavaGeneratorTest;
import com.bbva.kltt.apirest.generator.java.velocity.models.utils.APIRestGeneratorRandomUtils;
import com.bbva.kltt.apirest.generator.java.velocity.models.utils.JacksonMapperJavaGenerator;
import com.bbva.kltt.apirest.generator.java.velocity.models.utils.JacksonViewsJavaGenerator;

/**
 * Main class and entry point to generate all the classes related to specification
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TranslatorGeneratorJavaModels extends TranslatorGeneratorJavaModelsBase
{
    /** Logger of the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorGeneratorJavaModels.class) ;

    /** Attribute - Map of complex objects (complex and references) */
    private final Map<String, Item> definitionsMap ;
    
    /** Attribute - Map of server response exceptions and them operationIds */
    private final Map<String, List<Response>> serverResponseExceptionsMap ;

    /**
     * Creates the Java Models Translator
     *
     * @param generationParams  with the generator parameters for java
     * @param parsedInfoHandler with the parsed information from the input definitions and schemas
     * @param generationPackage with the package of the generator module
     */
    public TranslatorGeneratorJavaModels(final GenerationParameters generationParams,
                                         final ParsedInfoHandler parsedInfoHandler,
                                         final String generationPackage)
    {
        super(ConstantsCommon.STRING_EMPTY, generationParams, parsedInfoHandler, generationPackage);

        this.definitionsMap 	   		 = this.getParsedInfoHandler().generateDefinitionsMap() ;
        this.serverResponseExceptionsMap = this.getParsedInfoHandler().getOutboundServerExceptionsMap() ;
    }

    @Override
    public void generateBaseInterfaceModels(final File destDir) throws APIRestGeneratorException
    {
        LOGGER.info("Generating the Java common base interface for all the models");

        final IGenerator baseInterfaceModelsGenerator = new BaseInterfaceModelsJavaGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        baseInterfaceModelsGenerator.generate();

        LOGGER.info("Generated the Java common base interface for all the models");
    }

    @Override
    public void generateModels(final File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        LOGGER.info("Generating the Java common definition classes and subclasses") ;

        for (final Entry<String, Item> entry : this.definitionsMap.entrySet())
        {
            IGenerator definitionsGenerator = null;

            final String className = entry.getKey() ;
            final Item item = entry.getValue() ;
            if (item instanceof ItemComplex)
            {
                definitionsGenerator = new ModelsComplexJavaGenerator(destDir,
                                                                      this.getGenerationParams(),
                                                                      this.getParsedInfoHandler(),
                                                                      className,
                                                                      (ItemComplex) item) ;
            }
            else if (item instanceof ItemRef)
            {
                definitionsGenerator = new ModelsRefJavaGenerator(destDir,
                                                                  this.getGenerationParams(),
                                                                  this.getParsedInfoHandler(),
                                                                  className,
                                                                  (ItemRef) item) ;
            }

            if (definitionsGenerator != null)
            {
            	definitionsGenerator.generate() ;
            }
        }

        LOGGER.info("Generated the Java common definition classes and subclasses") ;
    }
    
	@Override
	public void generateModelExceptions(final File destDir) throws APIRestGeneratorException
	{
        // Generate the exception object classes
        LOGGER.info("Generating the Java common exception classes") ;

        // Firstly, generate the common interface exception class
        final IGenerator generatedInterfaceExceptionGen = new GeneratedInterfaceExceptionJavaGenerator(destDir,
																					 				   this.getGenerationParams(),
																					 				   this.getParsedInfoHandler()) ;
        generatedInterfaceExceptionGen.generate() ;
        
        // Secondly, generate the common exception class
        final IGenerator commonExceptionGenerator 	    = new CommonExceptionJavaGenerator(destDir,
																					 	   this.getGenerationParams(),
																					 	   this.getParsedInfoHandler()) ;
        commonExceptionGenerator.generate() ;
        
        // Finally, generate all the exceptions response classes
        for (final Entry<String, List<Response>> entry : this.serverResponseExceptionsMap.entrySet())
        {
            final String operationId 		            = entry.getKey() ;
            final List<Response> exceptionResponsesList = entry.getValue() ;
            
            for (final Response exceptionResponse : exceptionResponsesList)
            {
            	final IGenerator customExceptionGenerator = new CustomExceptionJavaGenerator(destDir,
	                        															 	 this.getGenerationParams(),
	                        															 	 this.getParsedInfoHandler(),
	                        															 	 operationId,
	                        															 	 exceptionResponse) ;
            	customExceptionGenerator.generate() ;
            }
        }

        LOGGER.info("Generated the Java common exception classes") ;
	}

    @Override
    public void generateModelUtilities(File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        LOGGER.info("Generating the Java utility classes for definition classes");

        final IGenerator jacksonViewsGenerator = new JacksonViewsJavaGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        jacksonViewsGenerator.generate();

        final IGenerator jacksonMapperGenerator = new JacksonMapperJavaGenerator(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        jacksonMapperGenerator.generate();

        final IGenerator randomUtilsGenerator = new APIRestGeneratorRandomUtils(destDir, this.getGenerationParams(), this.getParsedInfoHandler()) ;
        randomUtilsGenerator.generate();

        LOGGER.info("Generated the Java common utility classes for definition classes");
    }

    @Override
    public void generateModelTests(final File destDir) throws APIRestGeneratorException
    {
        // Generate the definition object classes
        LOGGER.info("Generating the Java common definition test-classes and subclasses");

        for (final String className : this.definitionsMap.keySet())
        {
            final IGenerator definitionsGenerator = new ModelsJavaGeneratorTest(destDir, this.getGenerationParams(), this.getParsedInfoHandler(), className) ;
            definitionsGenerator.generate();
        }

        LOGGER.info("Generated the Java common definition test-classes and subclasses");
    }

    @Override
    public void generatePOM(final File destDir) throws APIRestGeneratorException
    {
        LOGGER.info("Generating the Java common pom files for the models...");

        // Generate the pom.xml file
        final IGenerator normalPom = new ModelsPOMJavaGenerator(destDir,
                                                                this.getGenerationParams(),
                                                                this.getParsedInfoHandler(),
                                                                ConstantsOutput.EXTENSION_POM) ;
        normalPom.generate();

        // Generate the pom file again but using the name that correspond to the pom stored in nexus / artifactory
        final IGenerator artifactoryPom = new ModelsPOMJavaGenerator(destDir,
                                                                     this.getGenerationParams(),
                                                                     this.getParsedInfoHandler(),
                                                                     this.getArtifactoryPomName());
        artifactoryPom.generate();

        LOGGER.info("Generated the Java common pom files for the models...");
    }

    @Override
    public String getOutputCodeType()
    {
        return ConstantsOutput.OUTPUT_CODE_TYPE_MODELS;
    }
    
	/**
	 * @return the generator info projects
	 */
    @Override
	public String getGeneratorInfoProjectsChildrenJava()
	{
    	// For this translator is not necessary to get the project info because it belongs to the same project than 'generator.java'
		return ConstantsCommon.STRING_EMPTY ;
	}
}
