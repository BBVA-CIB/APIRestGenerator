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

package com.bbva.kltt.apirest.generator.client.feign.velocity;

import java.io.File;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.generator.client.feign.output.language.OutputLanguageConsumesProducesClientFeign;
import com.bbva.kltt.apirest.generator.client.feign.output.language.OutputLanguageItemsClientFeign;
import com.bbva.kltt.apirest.generator.client.feign.output.language.OutputLanguageParametersClientFeign;
import com.bbva.kltt.apirest.generator.client.feign.util.ConstantsOutputClientFeign;
import com.bbva.kltt.apirest.generator.java.velocity.fwk.GeneratorBaseFrameworkJava;

/**
 * Base methods for Java generation
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBaseClientFeign extends GeneratorBaseFrameworkJava
{
	/** Attribute - Parsed information handler */
	private final IOutputLanguageItems outputLanguageItems ;
	
	/** Attribute - Output Language Parameters */
	private final IOutputLanguageParameters outputLanguageParameters ;

	/** Attribute - Output Language Consumes Produces */
	private final IOutputLanguageConsumesProduces outputLanguageConsProd ;
	
    /**
     * Build the generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for spring
     * @param parsedInfoHandler with the parsed information handler
     */
    public GeneratorBaseClientFeign(final File baseDestDir, final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
        
        this.outputLanguageItems 	  = new OutputLanguageItemsClientFeign(parsedInfoHandler) ;
        this.outputLanguageParameters = new OutputLanguageParametersClientFeign(parsedInfoHandler, this.outputLanguageItems) ;
        this.outputLanguageConsProd   = new OutputLanguageConsumesProducesClientFeign(parsedInfoHandler) ;
    }
    
	@Override
	public String getTranslatorType()
	{
		return ConstantsOutputClientFeign.MODULE_NAME ;
	}
    
	@Override
	public IOutputLanguageItems getOutputLanguageItems()
	{
		return this.outputLanguageItems ;
	}
    
	@Override
	public IOutputLanguageParameters getOutputLanguageParameters()
	{
		return this.outputLanguageParameters ;
	}

	@Override
	public IOutputLanguageConsumesProduces getOutputLanguageConsProd()
	{
		return this.outputLanguageConsProd ;
	}
	
    /**
     * @param translatorType with the translator type
     * @return the additional import - handler implementation
     */
    protected String generateImportHystrixInterface(final String translatorType)
    {
        return this.getPackageUtilsJava().getRestHandlerInterfacesPackage(translatorType) +
               ConstantsCommon.STRING_DOT                                           	  +
               ConstantsOutputClientFeign.INTERF_NAME_HYSTRIX_FALLBACK 					  +
               this.getTitleCamelCase() ;
    }
}
