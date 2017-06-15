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

package com.bbva.kltt.apirest.generator.test.example.velocity.rest;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;
import com.bbva.kltt.apirest.generator.test.example.velocity.GeneratorBaseTestExample;

import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Generator to create the class that represents the rest handler
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RestHandlerTestExampleGenerator extends GeneratorBaseTestExample
{
    /**
     * Build the rest handler generator
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the JavaScript
     * @param parsedInfoHandler with the parsed information handler
     */
    public RestHandlerTestExampleGenerator(final File baseDestDir,
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
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getEntityName()) ;
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports()) ;

        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutputClientWeb.CLASSNAME_REST_LISTENER_WEB) ;
        context.put(ConstantsOutputClientWeb.VP_WEB_PAR_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_PARSER_VALUES_WEB) ;

        context.put(ConstantsOutputTestExample.VP_MODULE_NAME, this.getPackageUtilsJavascript().getModuleProjectName());

        // Templates
        context.put(ConstantsOutputTestExample.VP_JS_TEMPL_REST_MET_HEA,
        			this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputTestExample.VP_JS_TEMPL_REST_MET_HEA)) ;
        
        context.put(ConstantsOutputTestExample.VP_JS_TEMPL_METH_COMMENTS,
        			this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE,
                                                 ConstantsOutputTestExample.VP_JS_TEMPL_METH_COMMENTS)) ;
        // Macros
        context.put(ConstantsOutputTestExample.VP_JS_MACRO_COMMON,
        			this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE,
                                                 ConstantsOutputTestExample.VP_JS_MACRO_COMMON)) ;
        
        context.put(ConstantsOutputTestExample.VP_JS_MACRO_REST_HANDLER,
        			this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY,
                                                 ConstantsOutputTestExample.VP_JS_MACRO_REST_HANDLER)) ;
        
        return context ;
    }

	@Override
    protected String getOutputPackage()
    {
		// For the JavaScript generation it is not necessary any subfolder
        return ConstantsCommon.STRING_EMPTY ;
    }
	
    @Override
    protected String getOutputFileName()
    {
        return this.getEntityName() + this.getPackageUtilsJavascript().getProjectVersionSuffix() ;
    }
    
    @Override
    protected String getEntityName()
    {
    	return ConstantsOutput.CLASSNAME_REST_HANDLER ;
    }

    /**
     * @return additional imports
     */
    private List<String> generateAdditionalImports()
    {
        final List<String> modelImports = new ArrayList<String>();

        final String pathToImport = ConstantsOutput.DIRECTORY_BACK_ONE +
                                    ConstantsOutput.FOLDER_MODEL       +
                                    ConstantsCommon.STRING_SLASH;

        for (final String modelName : this.generateModelNamesList())
        {
            modelImports.add(String.format(ConstantsOutputTestExample.VP_IMPORT_REST_HANDLER, pathToImport,
                                           modelName                                           		  +
                                           this.getPackageUtilsJavascript().getProjectVersionSuffix() +
                                           ConstantsCommon.STRING_DOT                          		  +
                                           ConstantsOutput.EXTENSION_TYPESCRIPT));
        }

        // Add RestListener import
        modelImports.add(String.format(ConstantsOutputTestExample.VP_IMPORT_REST_HANDLER, "",
                         ConstantsOutputClientWeb.CLASSNAME_REST_LISTENER_WEB      		+
                         this.getPackageUtilsJavascript().getProjectVersionSuffix() +
                         ConstantsCommon.STRING_DOT                          		+
                         ConstantsOutput.EXTENSION_TYPESCRIPT));

        return modelImports;
    }
}
