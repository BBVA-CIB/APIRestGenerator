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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.test.example.velocity.GeneratorBaseTestExample;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleLauncherTestExampleGenerator extends GeneratorBaseTestExample
{
	/**
     * Build the launcher examples generator
     *
     * @param baseDestDir 		with the base destination directory for the generated file
     * @param generationParams	with the parameters for the generation for spring
     * @param parsedInfoHandler with the parsed information
     */
	public ExampleLauncherTestExampleGenerator(final File baseDestDir,
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
        context.put(ConstantsOutput.VP_R_HANDLER_CL_NAME,
                    ConstantsOutput.CLASSNAME_REST_HANDLER + this.getTitleCamelCase()) ;
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME,
        			ConstantsOutputJava.INTERFACE_NAME_REST_LISTENER + this.getTitleCamelCase()) ;
        context.put(ConstantsOutput.VP_LIST_EXAMP_CL_NAME,
        			ConstantsOutputJava.CLASSNAME_EXAMPLE_LISTENER + this.getTitleCamelCase()) ;
        context.put(ConstantsOutputJava.VP_INJECT_METHOD_NAME,
                    ConstantsOutputJava.VP_INJECT_METHOD_VALUE + this.getTitleCamelCase()) ;

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
		final List<String> additionalImports = new ArrayList<String>() ;

		additionalImports.add(this.getPackageUtilsJava().getRestHandlerImplPackage(this.getTranslatorType())  +
							  ConstantsCommon.STRING_DOT 													  +
							  ConstantsOutput.CLASSNAME_REST_HANDLER + this.getTitleCamelCase()) 										  ;

		additionalImports.add(this.getPackageUtilsJava().getRestHandlerInterfacesPackage(this.getTranslatorType()) +
							  ConstantsCommon.STRING_DOT 				   										   +
							  ConstantsOutputJava.INTERFACE_NAME_REST_LISTENER + this.getTitleCamelCase()) 									   ;

		return additionalImports ;
	}
}
