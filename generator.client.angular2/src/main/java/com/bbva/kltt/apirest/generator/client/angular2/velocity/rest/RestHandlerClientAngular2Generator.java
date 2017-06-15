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

package com.bbva.kltt.apirest.generator.client.angular2.velocity.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportExternalRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportGeneratedRefClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.util.class_imports.ClassImportsClientAngular2;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.angular2.util.ConstantsOutputClientAngular2;
import com.bbva.kltt.apirest.generator.client.angular2.velocity.GeneratorBaseClientAngular2;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;

/**
 * Generator to create the class that represents the rest handler
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RestHandlerClientAngular2Generator extends GeneratorBaseClientAngular2
{
    /**
     * Build the rest handler generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the Angular2
     * @param parsedInfoHandler with the parsed information handler
     */
    public RestHandlerClientAngular2Generator(final File baseDestDir,
                                          final GenerationParameters generationParams,
                                          final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;
    }
    
    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context 	   = new VelocityContext() ;
        
        final String projectTitleCamelCase = this.getTitleCamelCase() ;
        
        // Parameters
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports(projectTitleCamelCase));
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getEntityName()) ;
        
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutputClientWeb.CLASSNAME_REST_LISTENER_WEB + projectTitleCamelCase) ;
        context.put(ConstantsOutputClientWeb.VP_WEB_PAR_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_PARSER_VALUES_WEB) ;
        
        // Templates
        context.put(ConstantsOutputClientAngular2.VP_A2_TEMPL_REST_MET_HEA,
        			this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputClientAngular2.VP_A2_TEMPL_REST_MET_HEA)) ;
        
        context.put(ConstantsOutputClientAngular2.VP_A2_TEMPL_METH_COMMENTS,
        			this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE,
                                                 ConstantsOutputClientAngular2.VP_A2_TEMPL_METH_COMMENTS)) ;
        // Macros
        context.put(ConstantsOutputClientAngular2.VP_A2_MACRO_COMMON,
        			this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE,
                                                 ConstantsOutputClientAngular2.VP_A2_MACRO_COMMON)) ;
        
        context.put(ConstantsOutputClientAngular2.VP_A2_MACRO_REST_HANDLER,
        			this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputClientAngular2.VP_A2_MACRO_REST_HANDLER)) ;
        
        return context ;
    }

	@Override
    protected String getOutputPackage()
    {
		return this.getPackageUtilsAngular2().getHandlersPackage(this.getTranslatorType()) ;
    }
	
    @Override
    protected String getOutputFileName()
    {
        return this.getEntityName() + this.getPackageUtilsAngular2().getProjectVersionSuffix() ;
    }
    
    @Override
    protected String getEntityName()
    {
    	return ConstantsOutput.CLASSNAME_REST_HANDLER + this.getTitleCamelCase() ;
    }
    
    /**
     * @param projectTitleCamelCase with the project title as camel case
     * @return additional imports
     */
    private List<ClassImportsClientAngular2> generateAdditionalImports(final String projectTitleCamelCase)
    {
    	// Add the models
        final List<ClassImportsClientAngular2> additionalImports = this.generateAdditionalImportsModels(projectTitleCamelCase) ;

        // Add the Rest Listener
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(ConstantsCommon.STRING_DOT,
        													   	  ConstantsOutputClientWeb.CLASSNAME_REST_LISTENER_WEB + projectTitleCamelCase,
        													   	  this.getPackageUtilsAngular2().getProjectVersionSuffix())) ;
        // Add the utilities import
        additionalImports.addAll(this.generateAdditionalImportsUtilities()) ;
        
        // Add the External dependencies
        additionalImports.addAll(this.generateAdditionalImportsExternal()) ;

        return additionalImports ;
    }

    /**
     * @param projectTitleCamelCase with the project title as camel case
     * @return the additional imports - models
     */
    private List<ClassImportsClientAngular2> generateAdditionalImportsModels(final String projectTitleCamelCase)
    {
    	final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
    	final String relativeDirModels 					   = ConstantsOutput.DIRECTORY_BACK_ONE + ConstantsOutput.FOLDER_MODEL ;
    	
    	final List<String> modelNamesList = this.generateModelNamesList() ;
    	for (final String modelName : modelNamesList)
    	{
    		additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirModels,
    																  modelName + projectTitleCamelCase,
    																  this.getPackageUtilsAngular2().getProjectVersionSuffix())) ;
    	}
    	
    	return additionalImports ;
    }
    
    /**
     * @return the additional imports - utilities
     */
    private List<ClassImportsClientAngular2> generateAdditionalImportsUtilities()
    {
    	// Add the models
        final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
        
        final String relativeDirUtils = ConstantsOutput.DIRECTORY_BACK_FOUR + ConstantsOutput.FOLDER_UTILS ;
        
        // Add the Parser Values
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirUtils, ConstantsOutputClientWeb.CLASSNAME_PARSER_VALUES_WEB)) ;
        
        // Add the Common Exception
        additionalImports.add(new ClassImportGeneratedRefClientAngular2(relativeDirUtils, ConstantsOutput.CLASSNAME_COMMON_EXCEPTION)) ;
        
        return additionalImports ;
    }
    
    /**
     * @return the additional imports - external
     */
    private List<ClassImportsClientAngular2> generateAdditionalImportsExternal()
    {
    	final List<ClassImportsClientAngular2> additionalImports = new ArrayList<ClassImportsClientAngular2>() ;
    	
    	// RXJS
        
    	final List<String> rxjsLoadedDependencies = new ArrayList<String>() ;
    	rxjsLoadedDependencies.add(ConstantsOutputClientAngular2.D_1_EXT_LIB_RXJS) ;

    	additionalImports.add(new ClassImportExternalRefClientAngular2(rxjsLoadedDependencies, ConstantsOutputClientAngular2.R_EXT_LIB_RXJS)) ;
    	
    	// Angular Core - Injectable
        
    	final List<String> angularCoreLoadedDependencies = new ArrayList<String>() ;
    	angularCoreLoadedDependencies.add(ConstantsOutputClientAngular2.D_1_EXT_LIB_ANG_COR) ;
    
    	additionalImports.add(new ClassImportExternalRefClientAngular2(angularCoreLoadedDependencies, ConstantsOutputClientAngular2.R_EXT_LIB_ANG_COR)) ;
    
    	return additionalImports ;
    }
}
