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

package com.bbva.kltt.apirest.generator.server.spring.velocity.rest.impl.test;

import java.io.File;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.server.spring.util.ConstantsOutputServerSpring;
import com.bbva.kltt.apirest.generator.server.spring.velocity.GeneratorBaseServerSpring;

/**
 * Generator to create the class that represents the rest handler
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RestListenerServerSpringImplGeneratorTest extends GeneratorBaseServerSpring
{
    /**
     * Build the rest listener test generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for spring
     * @param parsedInfoHandler with the parsed information handler
     */
    public RestListenerServerSpringImplGeneratorTest(final File baseDestDir,
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
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutputJava.INTERFACE_NAME_REST_LISTENER + this.getTitleCamelCase()) ;

        // Java Templates
        context.put(ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS,
                	this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_TEMPLATES, ConstantsOutputJava.VP_JAVA_TEMPL_METH_COMMENTS));

        // Spring Templates
        context.put(ConstantsOutputJava.VP_COMMON_J_T_LISTE_EXA_SE,
                	this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_LISTENER_EXA, ConstantsOutputJava.VP_COMMON_J_T_LISTE_EXA_SE));

        context.put(ConstantsOutputJava.VP_COMM_J_T_LISTE_EXA_SE_ME,
                	this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_LISTENER_EXA, ConstantsOutputJava.VP_COMM_J_T_LISTE_EXA_SE_ME));
        // Java Macros
        context.put(ConstantsOutputJava.VP_JAVA_MACRO_COMMON,
                	this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_MACROS, ConstantsOutputJava.VP_JAVA_MACRO_COMMON));

        context.put(ConstantsOutputJava.VP_JAVA_RANDOM_GENERAT_METH,
                	this.getCommonJavaResourcePath(ConstantsOutputJava.COMMON_JAVA_DIR_MACROS, ConstantsOutputJava.VP_JAVA_RANDOM_GENERAT_METH));

        context.put(ConstantsOutputJava.VP_RANDOM_UTILS_CL_NAME,
        			ConstantsOutputJava.CLASSNAME_RANDOM_UTILS + this.getTitleCamelCase());

        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return this.getPackageUtilsJava().getRestHandlerImplPackage(this.getTranslatorType());
    }

    /**
     * @return the additional imports
     */
    private List<String> generateAdditionalImports()
    {
        final List<String> additionalImports = this.generateImportsModel();
        
		// Import the exception models
		additionalImports.addAll(this.generateImportsExceptions()) ;

        // Import the Listener interface
        additionalImports.add(this.generateImportListenerInterface(this.getTranslatorType()));

        // Import the Random Utils
        additionalImports.add(this.getPackageUtilsJava().getModelsUtilsPackage(ConstantsOutputJava.CLASSNAME_RANDOM_UTILS
                                                                               + this.getTitleCamelCase()));

        // Import several classes to handle the Multipart files and catch exception
        additionalImports.add(ConstantsOutputServerSpring.SPRING_MULTIPART_CLASS_PKG);
        additionalImports.add(ConstantsOutputServerSpring.JAVA_IO_IO_EXCEPTION_PKG);

        return additionalImports;
    }

    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutputJava.CLASSNAME_REST_LISTENER_TES;
    }
}
