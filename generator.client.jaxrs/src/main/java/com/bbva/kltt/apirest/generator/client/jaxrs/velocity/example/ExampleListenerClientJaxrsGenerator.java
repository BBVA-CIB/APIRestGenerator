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

package com.bbva.kltt.apirest.generator.client.jaxrs.velocity.example;

import java.io.File;
import java.util.List;

import com.bbva.kltt.apirest.generator.client.jaxrs.velocity.GeneratorBaseClientJaxrs;
import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleListenerClientJaxrsGenerator extends GeneratorBaseClientJaxrs
{
    /**
     * Build the launcher examples generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleListenerClientJaxrsGenerator(final File baseDestDir,
                                               final GenerationParameters generationParams,
                                               final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler);
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        // Parameters
        context.put(ConstantsOutput.VP_PACKAGE_NAME, this.getOutputPackage());
        context.put(ConstantsOutput.VP_ADDITIONAL_IMPORTS, this.generateAdditionalImports());
        context.put(ConstantsOutput.VP_CLASS_NAME, this.getOutputFileName());
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutputJava.INTERFACE_NAME_REST_LISTENER + this.getTitleCamelCase());
        context.put(ConstantsOutputJava.VP_RANDOM_UTILS_CL_NAME, ConstantsOutputJava.CLASSNAME_RANDOM_UTILS + this.getTitleCamelCase());

        // Java Templates
        context.put(ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS,
                    this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_TEMPLATES, ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS)) ;
        
        context.put(ConstantsOutputJava.VP_COMMON_J_T_LISTE_EXA_CL, 
    				this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_LISTENER_EXA, ConstantsOutputJava.VP_COMMON_J_T_LISTE_EXA_CL)) ;
    
        context.put(ConstantsOutputJava.VP_COMM_J_T_LISTE_EXA_CL_ME, 
    				this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_LISTENER_EXA, ConstantsOutputJava.VP_COMM_J_T_LISTE_EXA_CL_ME)) ;
        
        // Java Macros
        context.put(ConstantsOutputJava.VP_JAVA_MACRO_COMMON,
        			this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_MACROS, ConstantsOutputJava.VP_JAVA_MACRO_COMMON)) ;
        
        context.put(ConstantsOutputJava.VP_JAVA_RANDOM_GENERAT_METH,
        			this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_MACROS, ConstantsOutputJava.VP_JAVA_RANDOM_GENERAT_METH)) ;
        
        context.put(ConstantsOutputJava.VP_COMM_J_M_LISTE_EXA_CL_ME, 
					this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_LISTENER_EXA, ConstantsOutputJava.VP_COMM_J_M_LISTE_EXA_CL_ME)) ;
        
        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getExamplesPackage(this.getTranslatorType());
    }

    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutputJava.CLASSNAME_EXAMPLE_LISTENER + this.getTitleCamelCase();
    }

    /**
     * @return the additional imports
     */
    private List<String> generateAdditionalImports()
    {
        // Model imports
        final List<String> additionalImports = this.generateImportsModel();
        
		// Add the common exception class
		additionalImports.add(this.generateImportExceptionCommonException()) ;

        // Listener interface - import
        additionalImports.add(this.generateImportListenerInterface(this.getTranslatorType()));

        return additionalImports;
    }
}
