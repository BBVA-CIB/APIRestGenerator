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

package com.bbva.kltt.apirest.generator.java.velocity.top;

import com.bbva.kltt.apirest.generator.java.util.ConstantsOutputJava;
import com.bbva.kltt.apirest.generator.java.velocity.GeneratorBasePOM;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;

import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * Generate the top level POM for all the generated modules
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class TopLevelJavaPOM extends GeneratorBasePOM
{
    /**
     * Build the top level pom generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param translatorType    with the translator type
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed info handler
     * @param pomFileName       with the specific file name to use for the POM (without extension)
     */
    public TopLevelJavaPOM(final File baseDestDir,
                           final String translatorType,
                           final GenerationParameters generationParams,
                           final ParsedInfoHandler parsedInfoHandler,
                           final String pomFileName)
    {
        super(baseDestDir, translatorType, generationParams, parsedInfoHandler, pomFileName) ;
    }

    @Override
    protected void addAdditionalContextParameters(final VelocityContext velocityContext)
    {
        velocityContext.put(ConstantsOutputJava.VP_TOP_LEVEL_POM_PROJ_MODULE, this.getTranslatorType());
    }
}
