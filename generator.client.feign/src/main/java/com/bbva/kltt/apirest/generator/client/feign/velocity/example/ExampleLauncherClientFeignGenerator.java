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

package com.bbva.kltt.apirest.generator.client.feign.velocity.example;

import java.io.File;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.feign.util.ConstantsOutputClientFeign ;
import com.bbva.kltt.apirest.generator.client.feign.velocity.GeneratorBaseClientFeign ;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleLauncherClientFeignGenerator extends GeneratorBaseClientFeign
{
	/**
     * Build the launcher examples generator
     *
     * @param baseDestDir 		with the base destination directory for the generated file
     * @param generationParams	with the parameters for the generation for Feign 
     * @param parsedInfoHandler with the parsed information
     */
	public ExampleLauncherClientFeignGenerator(final File baseDestDir,
												   final GenerationParameters generationParams,
												   final ParsedInfoHandler parsedInfoHandler)
	{
		super(baseDestDir, generationParams, parsedInfoHandler) ;
	}

	@Override
	protected VelocityContext createVelocityContext()
	{
        final VelocityContext context = new VelocityContext() ;

        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage()) ;
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports()) ;
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName()) ;
        
        context.put(ConstantsOutputClientFeign.VP_FEIGN_REST_INTERFACE_PACK,
        			this.getPackageUtilsJava().getRestHandlerInterfacesPackage(this.getTranslatorType())) ;
        
        final String titleCamelCase = this.getTitleCamelCase() ;
        
        context.put(ConstantsOutput.VP_R_HANDLER_INTERFACE_NAME, ConstantsOutputJava.INTERFACE_NAME_REST_HANDLER + titleCamelCase) ;
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutputJava.INTERFACE_NAME_REST_LISTENER + titleCamelCase) ;
        context.put(ConstantsOutput.VP_R_HANDLER_CL_NAME, ConstantsOutput.CLASSNAME_REST_HANDLER + titleCamelCase) ;
        context.put(ConstantsOutputJava.VP_RANDOM_UTILS_CL_NAME, ConstantsOutputJava.CLASSNAME_RANDOM_UTILS + titleCamelCase) ;
        context.put(ConstantsOutput.VP_LIST_EXAMP_CL_NAME, ConstantsOutputJava.CLASSNAME_EXAMPLE_LISTENER + this.getTitleCamelCase());
        
        // Java Macros
        context.put(ConstantsOutputJava.VP_JAVA_MACRO_COMMON,
            		this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_MACROS, ConstantsOutputJava.VP_JAVA_MACRO_COMMON)) ;
        context.put(ConstantsOutputJava.VP_JAVA_RANDOM_GENERAT_METH,
                	this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_MACROS, ConstantsOutputJava.VP_JAVA_RANDOM_GENERAT_METH)) ;
        
        // Feign Macros
        context.put(ConstantsOutputClientFeign.VP_FEI_EXAM_LAUNCHER_MACROS,
    				this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputClientFeign.VP_FEI_EXAM_LAUNCHER_MACROS)) ;
        
        // Feign Templates
        context.put(ConstantsOutputClientFeign.VP_FEI_TEMPL_EXAM_LAUNCHER,
        			this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputClientFeign.VP_FEI_TEMPL_EXAM_LAUNCHER)) ;
        return context ;
	}

	@Override
	protected String getOutputPackage()
	{
		return this.getPackageUtilsJava().getExamplesPackage(this.getTranslatorType()) ;
	}

	@Override
	protected String getOutputFileName()
	{
		return ConstantsOutputJava.CLASSNAME_EXAMPLE_LAUNCHER ;
	}

	/**
	 * @return the additional imports
	 */
	private List<String> generateAdditionalImports()
	{
        // Model imports
        final List<String> additionalImports = this.generateImportsModel();

        // Listener interface - import
        additionalImports.add(this.generateImportListenerInterface(this.getTranslatorType()));

        // Rest Handler interface - import
        additionalImports.add(this.generateImportRestHandlerInterface(this.getTranslatorType()));

        // Rest Handler implementation - import
        additionalImports.add(this.generateImportHandlerImpl(this.getTranslatorType()));

        // Model - Util - Random utils
        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_RANDOM_UTILS +
                                                                               this.getTitleCamelCase()));

		return additionalImports ;
	}
}
