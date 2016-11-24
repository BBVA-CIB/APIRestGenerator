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

package com.bbva.kltt.webClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ------------------------------------------------
 * @author Jorge Sánchez
 * ------------------------------------------------
 */
@RestController
@ConfigurationProperties
public class ConfigController
{

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/getGeneratorAddress", method = RequestMethod.GET, produces = "text/plain")
    public ResponseEntity<String> getGeneratorAddress()
    {
        String address = this.environment.getProperty("generator.address");
        if (address == null || address.equals(""))
        {
            address = "localhost";
        }

        String port = this.environment.getProperty("generator.port");
        if (port == null || port.equals(""))
        {
            port = "8080";
        }

        String service = this.environment.getProperty("generator.service");
        if (service == null)
        {
            service = "";
        }
        else if (!service.equals(""))
        {
            service += "/";
        }

        String fullURL = String.format("http://%s:%s/%s", address, port, service);

        return ResponseEntity.ok().body(fullURL);
    }
}
