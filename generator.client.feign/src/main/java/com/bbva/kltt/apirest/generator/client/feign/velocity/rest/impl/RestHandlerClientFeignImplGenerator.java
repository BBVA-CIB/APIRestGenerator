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

package com.bbva.kltt.apirest.generator.client.feign.velocity.rest.impl;

import java.io.File;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.feign.util.ConstantsOutputClientFeign;
import com.bbva.kltt.apirest.generator.client.feign.velocity.GeneratorBaseClientFeign;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;

/**
 * Generator to create the class that represents the rest handler
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RestHandlerClientFeignImplGenerator extends GeneratorBaseClientFeign
{
    /**
     * Build the rest handler generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for Feign
     * @param parsedInfoHandler with the parsed information handler
     */
    public RestHandlerClientFeignImplGenerator(final File baseDestDir,
                                                final GenerationParameters generationParams,
                                                final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
    }
    
    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext() ;
        
        // Parameters
        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage()) ;
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports()) ;
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName()) ;
        context.put(ConstantsOutput.VP_R_HANDLER_INTERFACE_NAME, ConstantsOutputJava.INTERFACE_NAME_REST_HANDLER +
                                                                 this.getTitleCamelCase()) ;
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutputJava.INTERFACE_NAME_REST_LISTENER +
                                                               this.getTitleCamelCase()) ;
        
        // Java Templates
        context.put(ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS,
        			this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_TEMPLATES, ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS)) ;
        // Feign Macros
        context.put(ConstantsOutputClientFeign.VP_FEI_COMMON_MACROS,
    				this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_TWO + ConstantsOutputClientFeign.COMMON_JAVA_DIR_MACROS,
    											 ConstantsOutputClientFeign.VP_FEI_COMMON_MACROS)) ;
        
        // Feign Templates
        context.put(ConstantsOutputClientFeign.VP_FEI_TEMPL_REST_MET_HEA_IM,
        			this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputClientFeign.VP_FEI_TEMPL_REST_MET_HEA_IM)) ;
        
        return context ;
    }

	@Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getRestHandlerImplPackage(this.getTranslatorType()) ;
    }
	
	/**
	 * @return the additional imports
	 */
	private List<String> generateAdditionalImports()
	{
		// Model imports
		final List<String> additionalImports = this.generateImportsModel() ;
		
		// Add the common exception class
		additionalImports.add(this.generateImportExceptionCommonException()) ;

		// Rest handler interface - import
		additionalImports.add(this.generateImportRestHandlerInterface(this.getTranslatorType())) ;
		
		// Listener interface - import
		additionalImports.add(this.generateImportListenerInterface(this.getTranslatorType())) ;
		
		return additionalImports ;
	}

    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutput.CLASSNAME_REST_HANDLER + this.getTitleCamelCase() ;
    }
}
