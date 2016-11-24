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

package com.bbva.kltt.core.parser;

import com.bbva.kltt.core.parsed_info.common.*;
import com.bbva.kltt.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IItemFactory
{
    /**
     * @param type with the type
     * @return true if the type is simple
     */
    public boolean isSimpleItem(final String type);

    /**
     * @param name        with the item name
     * @param alias       with the alias name
     * @param description with the description
     * @param required    true if the item is required
     * @param type        with the type
     * @param format      with the format
     * @return a new item
     * @throws APIRestGeneratorException with an occurred exception
     */
    Item createNewSimpleItem(final String name,
                             final String alias,
                             final String description,
                             final String required,
                             final String type,
                             final String format) throws APIRestGeneratorException;

    /**
     * @param type with the type
     * @return true if the type is array type
     */
    boolean isArrayItem(final String type);

    /**
     * @param name        with the item name
     * @param alias       with the alias name
     * @param description with the description
     * @param required    true if the item is required
     * @return a new item
     * @throws APIRestGeneratorException with an occurred exception
     */
    ItemArray createNewArray(final String name, final String alias, final String description, final String required) throws APIRestGeneratorException;

    /**
     * @param name        with the item name
     * @param alias       with the alias name
     * @param description with the description
     * @param required    true if the item is required
     * @param reference   with the reference
     * @return a new instance of ItemRef
     * @throws APIRestGeneratorException with an occurred exception
     */
    ItemRef createNewItemRef(final String name,
                             final String alias,
                             final String description,
                             final String required,
                             final String reference) throws APIRestGeneratorException;

    /**
     * @param type with the type
     * @return true if the type is complex type
     */
    boolean isComplexItem(final String type);

    /**
     * @param name        with the name
     * @param alias       with the alias name
     * @param description with the description
     * @param required    true if the item is required
     * @return a new item complex
     * @throws APIRestGeneratorException with an occurred exception
     */
    ItemComplex createNewItemComplex(final String name,
                                     final String alias,
                                     final String description,
                                     final String required) throws APIRestGeneratorException;

    /**
     * @param type with the type
     * @return true if the type is file type
     */
    boolean isFileItem(final String type);

    /**
     * @param name        with the name
     * @param alias       with the alias name
     * @param description with the description
     * @param required    true if the item is required
     * @return a new item file
     * @throws APIRestGeneratorException with an occurred exception
     */
    ItemFile createNewItemFile(final String name,
                               final String alias,
                               final String description,
                               final String required) throws APIRestGeneratorException;
}
