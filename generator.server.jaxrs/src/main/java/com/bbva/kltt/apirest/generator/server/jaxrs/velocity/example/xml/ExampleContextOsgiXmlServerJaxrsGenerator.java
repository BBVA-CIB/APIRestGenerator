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

package com.bbva.kltt.apirest.generator.server.jaxrs.velocity.example.xml;

import com.bbva.kltt.apirest.generator.server.jaxrs.util.ConstantsOutputServerJaxrs;
import com.bbva.kltt.apirest.generator.server.jaxrs.velocity.ContextGeneratorXmlServerJaxrs;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;

import org.apache.velocity.VelocityContext;

import java.io.File;

/**
 * Generate the XML file configuration - Context (OSGi)
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ExampleContextOsgiXmlServerJaxrsGenerator extends ContextGeneratorXmlServerJaxrs
{
    /**
     * Build the context XML file (OSGi)
     *
     * @param baseDestDir       with the base destination directory
     * @param generationParams  with the parameters for the generation
     * @param parsedInfoHandler with the parsed info handler
     */
    public ExampleContextOsgiXmlServerJaxrsGenerator(final File baseDestDir,
                                                     final GenerationParameters generationParams,
                                                     final ParsedInfoHandler parsedInfoHandler)
    {
        super(baseDestDir, generationParams, parsedInfoHandler);
    }

    @Override
    public VelocityContext createVelocityContext()
    {
        final VelocityContext context = new VelocityContext();

        // Add the full package for the listener interface
        context.put(ConstantsOutputServerJaxrs.VP_JAXRS_REST_LISTENE_IN_FP,
                    this.generateImportListenerInterface(this.getTranslatorType()));

        context.put(ConstantsOutputServerJaxrs.VP_JAXRS_REST_LISTENER_IMPL,
                    ConstantsOutputServerJaxrs.VP_JAXRS_REST_LISTENER_IMPL + this.getTitleCamelCase());

        context.put(ConstantsOutputServerJaxrs.VP_JAXRS_REST_LISTENER_OSGI,
                    ConstantsOutputServerJaxrs.VP_JAXRS_REST_LISTENER_OSGI + this.getTitleCamelCase());

        return context;
    }

    @Override
    protected String getOutputFileName()
    {
        return ConstantsOutputServerJaxrs.VP_JAXRS_XML_CTX_OSGI_FN;
    }
}
