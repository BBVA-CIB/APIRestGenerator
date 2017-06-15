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

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.generator.GeneratorBase;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsOutput;
import com.bbva.kltt.apirest.generator.test.example.util.ConstantsOutputTestExample;

/**
 * Generate the application properties for Spring example
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ApplicationPropertiesGenerator extends GeneratorBase
{
    /**
     * Build the application properties for Spring example
     *
     * @param baseDestDir 	    with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed info handler
     */
	public ApplicationPropertiesGenerator(final File baseDestDir, final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
	{
		super(baseDestDir, generationParams, parsedInfoHandler) ;
	}

	@Override
	public VelocityContext createVelocityContext()
	{
		final VelocityContext context = new VelocityContext();

		context.put(ConstantsOutputTestExample.VP_SPR_PORT_NUMBER, this.getOutputLanguageRootValues().getOnlyPort()) ;
		
		return context;
	}

	@Override
	public IOutputLanguageItems getOutputLanguageItems()
	{
		// It is not necessary to be implemented
		return null ;
	}

	@Override
	public IOutputLanguageParameters getOutputLanguageParameters()
	{
		// It is not necessary to be implemented
		return null ;
	}

	@Override
	public IOutputLanguageConsumesProduces getOutputLanguageConsProd()
	{
		// It is not necessary to be implemented
		return null ;
	}

	@Override
	protected String getOutputPackage()
	{
		// Don't use output package, this file will be generated directly in the given destination directory
		return null ;
	}

	@Override
	protected String getOutputFileName()
	{
		return ConstantsOutputTestExample.APP_PROPERTIES_FILE_NAME ;
	}

	@Override
	protected String getOutputExtension()
	{
		return ConstantsCommon.STRING_DOT + ConstantsOutput.EXTENSION_PROPERTIES ;
	}
}
