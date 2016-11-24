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

package com.bbva.kltt.controller.serverSpring.util;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ConstantsLauncherWeb
{
    /**************************** Configuration parameters *************************/

    /**
     * Configuration parameter - removeAfterSendZip
     */
    public static final String REMOVE_AFTER_SEND_ZIP      = "removeAfterSendZip";

    /**
     * Configuration parameter - generator builder path
     */
    public static final String GEN_BUILDER_PATH           = "generatorBuilderPath";

    /**
     * Header - Request - iv-user
     */
    public static final String HEADER_REQ_IV_USER         = "iv-user";

    /******************* Deliverables constants *************************/

    /**
     * Deliverable - COMPRESS - file name
     */
    public static final String DELIVER_COMPRESS_FILE_NAME = "deliverables.zip";

    /**
     * Private constructor
     */
    private ConstantsLauncherWeb()
    {
        // Empty constructor
    }
}
