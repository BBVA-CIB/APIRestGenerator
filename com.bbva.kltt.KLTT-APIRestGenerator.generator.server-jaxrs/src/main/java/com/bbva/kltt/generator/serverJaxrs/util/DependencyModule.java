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

package com.bbva.kltt.generator.serverJaxrs.util;


import com.bbva.kltt.core.util.AbstractDependencyUtility;

/**
 * ------------------------------------------------
 * @author Jorge Sánchez
 * ------------------------------------------------
 */
public class DependencyModule extends AbstractDependencyUtility {

    /**
     * Constructor with the module data
     */
    public DependencyModule()
    {
        super(ConstantsOutputServerJaxrs.TYPE_MODULE,
                ConstantsOutputServerJaxrs.OFF_MODULE_NAME,
                ConstantsOutputServerJaxrs.MODULE_NAME);
    }
}