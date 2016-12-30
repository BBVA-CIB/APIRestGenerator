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

package com.bbva.kltt.generator.java.fwk;

import com.bbva.kltt.generator.java.GeneratorBasePOM;
import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;

import java.io.File;

/**
 * Base methods for Java POM generator - framework
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public abstract class GeneratorBaseFrameworkPOM extends GeneratorBasePOM
{
    /**
     * Build the translators for Java POM generator - framework
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param translatorType    with the translator type
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed info handler
     * @param pomFileName       with the name of the POM file to generate
     * @param generationPackage with the package of the generator module
     */
    public GeneratorBaseFrameworkPOM(final File baseDestDir,
                                     final String translatorType,
                                     final GenerationParameters generationParams,
                                     final ParsedInfoHandler parsedInfoHandler,
                                     final String pomFileName,
                                     final String generationPackage)
    {
        super(baseDestDir, translatorType, generationParams, parsedInfoHandler, pomFileName, generationPackage);
    }
}