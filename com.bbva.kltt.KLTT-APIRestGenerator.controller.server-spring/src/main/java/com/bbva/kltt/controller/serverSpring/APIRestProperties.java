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

package com.bbva.kltt.controller.serverSpring;

import com.bbva.kltt.controller.serverSpring.util.ConstantsLauncherWeb;
import com.bbva.kltt.core.util.APIRestGeneratorException;
import com.bbva.kltt.core.web.APIRestGeneratorConfig;

import java.util.Properties;

/**
 * ------------------------------------------------
 * @author Jorge Sánchez
 * ------------------------------------------------
 */
public class APIRestProperties extends APIRestGeneratorConfig
{

    public APIRestProperties()
    {
        super() ;
    }

    /**
     * @param properties with the properties
     * @throws APIRestGeneratorException with an occurred exception
     */
    public void processProperties(final Properties properties) throws APIRestGeneratorException
    {
        if (!properties.containsKey(ConstantsLauncherWeb.REMOVE_AFTER_SEND_ZIP))
        {
            final String errorString = ConstantsLauncherWeb.REMOVE_AFTER_SEND_ZIP + " was not added in the configuration file" ;

            APIRestGeneratorConfig.LOGGER.error(errorString) ;
            throw new APIRestGeneratorException(errorString) ;

        }

        // Set the value to the attribute
        this.setRemoveAfterSendZip(Boolean.valueOf((String)properties.get(ConstantsLauncherWeb.REMOVE_AFTER_SEND_ZIP))) ;

        // Log it
        APIRestGeneratorConfig.LOGGER.info(ConstantsLauncherWeb.REMOVE_AFTER_SEND_ZIP +
                                           " was added in the configuration file as " +
                                           this.getRemoveAfterSendZip()) ;

        if (!properties.containsKey(ConstantsLauncherWeb.GEN_BUILDER_PATH))
        {
            final String errorString = ConstantsLauncherWeb.GEN_BUILDER_PATH + " was not added in the configuration file" ;

            APIRestGeneratorConfig.LOGGER.error(errorString) ;
            throw new APIRestGeneratorException(errorString) ;
        }

        // Set the value to the attribute
        this.setGeneratorBuilderPath((String) properties.get(ConstantsLauncherWeb.GEN_BUILDER_PATH));

        // Log it
        APIRestGeneratorConfig.LOGGER.info(ConstantsLauncherWeb.GEN_BUILDER_PATH + " was added in the configuration file as " +
                                           this.getGeneratorBuilderPath()) ;
    }
}
