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

package com.bbva.kltt.apirest.generator.client.web.velocity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bbva.kltt.apirest.core.generator.GeneratorBase;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * Base methods for Web generation
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBaseClientWeb extends GeneratorBase
{
	/**
	 * Attribute - fileExtension
	 */
	private final String fileExtension ;
	
    /**
     * Build the generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the TypeScript
     * @param parsedInfoHandler with the parsed information handler
     * @param fileExtension		with the file extension
     */
    public GeneratorBaseClientWeb(final File baseDestDir,
		                          final GenerationParameters generationParams,
		                          final ParsedInfoHandler parsedInfoHandler,
		                          final String fileExtension)
    {
        super(baseDestDir, generationParams, parsedInfoHandler) ;

        this.fileExtension = fileExtension ;
    }

    /**
     * Generates the code, this is the entry point of the generator
     *
     * @throws APIRestGeneratorException with an occurred exception
     */
    @Override
    public void generate() throws APIRestGeneratorException
    {
        super.generateFile(this.createVelocityContext()) ;
    }

    @Override
    protected String getOutputExtension()
    {
        return ConstantsCommon.STRING_DOT + this.fileExtension ;
    }
    
    /**
     * Return the entity name inside the output file. It is usually the same than the output file name
     * 
     * @return the entity name
     */
    protected abstract String getEntityName() ;
    
    /**
     * Generate additional model names list
     *
     * @return the model name list
     */
    protected List<String> generateModelNamesList()
    {
        final List<String> modelImports = new ArrayList<String>();

        // Import all the necessary definitions used in the paths
        for (final String complexName : this.getOutputLanguageItems().getDefinitionsListNames())
        {
            modelImports.add(this.getOutputLanguageNaming().prefixClassName(complexName));
        }

        return modelImports;
    }
}
