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

package com.bbva.kltt.generator.serverSpring.java.server.spring.rest.impl;

import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.util.ConstantsOutputJava;
import com.bbva.kltt.generator.serverSpring.java.server.spring.GeneratorBaseServerSpring;
import com.bbva.kltt.generator.serverSpring.util.ConstantsOutputServerSpring;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.List;

/**
 * Generator to create the class that represents the rest handler
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RestHandlerServerSpringImplGenerator extends GeneratorBaseServerSpring
{
    /**
     * Build the rest handler generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for spring
     * @param parsedInfoHandler with the parsed information handler
     */
    public RestHandlerServerSpringImplGenerator(final File baseDestDir,
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
        context.put(ConstantsOutput.VP_R_HANDLER_INTERFACE_NAME, ConstantsOutput.INTERFACE_NAME_REST_HANDLER +
                                                                 this.getTitleCamelCase()) ;
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutput.INTERFACE_NAME_REST_LISTENER +
                                                               this.getTitleCamelCase()) ;
        
        // Java Templates
        context.put(ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS,
        			this.getTemplateCommonJavaResourceName(ConstantsOutputJava.COMMON_JAVA_DIR_TEMPLATES,
        										 ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS)) ;
        // Spring Templates
        context.put(ConstantsOutputServerSpring.VP_SPR_TEMPL_REST_MET_HEA_IM,
        			this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputServerSpring.VP_SPR_TEMPL_REST_MET_HEA_IM)) ;
        
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
