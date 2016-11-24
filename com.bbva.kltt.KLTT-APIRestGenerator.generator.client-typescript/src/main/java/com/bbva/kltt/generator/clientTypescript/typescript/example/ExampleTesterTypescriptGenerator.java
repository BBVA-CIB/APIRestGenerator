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

package com.bbva.kltt.generator.clientTypescript.typescript.example;

import com.bbva.kltt.core.launcher.GenerationParameters;
import com.bbva.kltt.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.core.util.ConstantsCommon;
import com.bbva.kltt.core.util.ConstantsOutput;
import com.bbva.kltt.generator.clientTypescript.typescript.GeneratorBaseTypescript;
import com.bbva.kltt.generator.clientTypescript.util.ConstantsOutputTypescript;
import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleTesterTypescriptGenerator extends GeneratorBaseTypescript
{
    /**
     * Build the launcher examples generator
     *
     * @param baseDestDir       with the base destination directory for the generated file
     * @param generationParams  with the parameters for the generation for the TypeScript
     * @param parsedInfoHandler with the parsed information
     */
    public ExampleTesterTypescriptGenerator(final File baseDestDir,
                                            final GenerationParameters generationParams,
                                            final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler);
    }

    @Override
    protected VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        context.put(ConstantsOutput.VP_R_HANDLER_CL_NAME, ConstantsOutput.CLASSNAME_REST_HANDLER);
        context.put(ConstantsOutput.VP_R_LISTE_INTERFACE_NAME, ConstantsOutputTypescript.CLASSNAME_REST_LISTENER_TS);
        context.put(ConstantsOutputTypescript.VP_TS_RAND_VAL_CL_NAME, ConstantsOutputTypescript.CLASSNAME_RANDOM_VALUES_TS);
        context.put(ConstantsOutput.VP_SCHEMES_VAL_CL_NAME, ConstantsOutput.CLASSNAME_SCHEMES_VALUES);

        // Templates
        context.put(ConstantsOutputTypescript.VP_TS_TEMPL_EXA_TEST_MET,
                this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputTypescript.VP_TS_TEMPL_EXA_TEST_MET));

        // Macros
        context.put(ConstantsOutputTypescript.VP_TS_MACRO_COMMON,
                this.getTemplateResourceName(ConstantsOutput.DIRECTORY_BACK_ONE, ConstantsOutputTypescript.VP_TS_MACRO_COMMON));

        context.put(ConstantsOutputTypescript.VP_TS_MACRO_EXAMPLE_TEST,
                this.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsOutputTypescript.VP_TS_MACRO_EXAMPLE_TEST));

        return context;
    }

    @Override
    protected String getOutputPackage()
    {
        return ConstantsOutputTypescript.TYPESCRIPT_JS_MAIN_FOLDER + File.separator + ConstantsOutputTypescript.TYPESCRIPT_EXAMPLE_FOLDER;
    }

    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutputTypescript.EXAMPLE_TESTER_TS;
    }
}
