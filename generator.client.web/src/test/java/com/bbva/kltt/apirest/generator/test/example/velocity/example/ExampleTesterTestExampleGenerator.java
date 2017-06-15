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

package com.bbva.kltt.apirest.generator.test.example.velocity.example;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.client.web.util.ConstantsOutputClientWeb;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;
import com.bbva.kltt.apirest.generator.test.example.velocity.GeneratorBaseTestExample;

import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleTesterTestExampleGenerator extends GeneratorBaseTestExample
{
    /**
     * Build the launcher examples generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the JavaScript
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleTesterTestExampleGenerator(final File baseDestDir,
                                            final GenerationParameters generationParams,
                                            final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler);
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        context.put(ConstantsOutput.VP_R_HANDLER_CL_NAME, ConstantsOutput.CLASSNAME_REST_HANDLER) ;
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutputClientWeb.CLASSNAME_REST_LISTENER_WEB);
        context.put(ConstantsOutputClientWeb.VP_WEB_RAND_VAL_CL_NAME, ConstantsOutputClientWeb.CLASSNAME_RANDOM_VALUES_WEB);
        context.put(ConstantsOutput.VP_SCHEMES_VAL_CL_NAME, ConstantsOutput.CLASSNAME_SCHEMES_VALUES);

        context.put(ConstantsOutputTestExample.VP_REST_HANDLER_FILE,
                    ConstantsOutput.DIRECTORY_BACK_ONE 	   + ConstantsOutputClientWeb.WEB_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                    ConstantsOutput.FOLDER_REST 	   	   + ConstantsCommon.STRING_SLASH 											+
                    ConstantsOutput.CLASSNAME_REST_HANDLER + this.getPackageUtilsJavascript().getProjectVersionSuffix() 			+
                    ConstantsCommon.STRING_DOT 			   + ConstantsOutput.EXTENSION_TYPESCRIPT) ;
        
        context.put(ConstantsOutputTestExample.VP_REST_LISTENER_FILE,
                    ConstantsOutput.DIRECTORY_BACK_ONE 	   + ConstantsOutputClientWeb.WEB_GENERATED_FOLDER + ConstantsCommon.STRING_SLASH +
                    ConstantsOutput.FOLDER_REST			   + ConstantsCommon.STRING_SLASH 								 			+
                    ConstantsOutputClientWeb.CLASSNAME_REST_LISTENER_WEB + this.getPackageUtilsJavascript().getProjectVersionSuffix() 	+
                    ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_TYPESCRIPT) ;
        
        context.put(ConstantsOutputTestExample.VP_MODULE_NAME, this.getPackageUtilsJavascript().getModuleProjectName());

        // Templates
        context.put(ConstantsOutputTestExample.VP_JS_TEMPL_EXA_TEST_MET,
                	this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputTestExample.VP_JS_TEMPL_EXA_TEST_MET));

        // Macros
        context.put(ConstantsOutputTestExample.VP_JS_MACRO_COMMON,
                	this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE, ConstantsOutputTestExample.VP_JS_MACRO_COMMON));

        context.put(ConstantsOutputTestExample.VP_JS_MACRO_EXAMPLE_TEST,
                	this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputTestExample.VP_JS_MACRO_EXAMPLE_TEST));

        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return ConstantsOutputClientWeb.WEB_JS_MAIN_FOLDER + File.separator + ConstantsOutputClientWeb.WEB_EXAMPLE_FOLDER;
    }

    @Override
    protected String getOutputFileName()
    {
        return this.getEntityName() + this.getPackageUtilsJavascript().getProjectVersionSuffix() ;
    }
    
    @Override
    protected String getEntityName()
    {
    	return ConstantsOutputClientWeb.EXAMPLE_TESTER_WEB;
    }
}
