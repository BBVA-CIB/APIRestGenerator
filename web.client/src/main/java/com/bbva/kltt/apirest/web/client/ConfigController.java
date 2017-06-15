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

package com.bbva.kltt.apirest.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
@RestController
@ConfigurationProperties
public class ConfigController
{
	/** Constant - Default - Scheme */
	private static final String DEFAULT_SCHEME 		 = "http" ;
	
	/** Constant - Default - Host */
	private static final String DEFAULT_HOST 		 = "localhost" ;
	
	/** Constant - Default - Port */
	private static final String STRING_COLON 		 = ":" ;
	
	/** Constant - Default - Port */
	private static final String STRING_BAR 		 	 = "/" ;
	
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/getGeneratorAddress", method = RequestMethod.GET, produces = "text/plain")
    public ResponseEntity<String> getGeneratorAddress()
    {
    	final String scheme 	 = this.environment.getProperty("web.client.scheme") ;
    	final String host		 = this.environment.getProperty("web.client.host") ;
    	final String port   	 = this.environment.getProperty("web.client.port") ;
    	final String servicePath = this.environment.getProperty("web.client.servicePath") ;
    	
    	final StringBuilder generatorAddress = new StringBuilder() ;
    	
    	this.getGeneratorAddressScheme(scheme, generatorAddress) ;
    	this.getGeneratorAddressHost(host, generatorAddress) ;
    	this.getGeneratorAddressPort(port, generatorAddress) ;
    	this.getGeneratorServicePath(servicePath, generatorAddress) ;

        return ResponseEntity.ok().body(generatorAddress.toString()) ;
    }

    /**
     * @param scheme		   with the scheme
     * @param generatorAddress with the current generator address string
     */
	private void getGeneratorAddressScheme(final String scheme, final StringBuilder generatorAddress)
	{
		if (scheme == null || scheme.isEmpty())
		{
			generatorAddress.append(DEFAULT_SCHEME) ;
		}
		else
		{
			generatorAddress.append(scheme) ;
		}
		
		generatorAddress.append(STRING_COLON) ;
		generatorAddress.append(STRING_BAR) ;
		generatorAddress.append(STRING_BAR) ;
	}

	/**
	 * 
	 * @param host			   with the host
	 * @param generatorAddress with the current generator address string
	 */
	private void getGeneratorAddressHost(final String host, final StringBuilder generatorAddress)
	{
		if (host == null || host.isEmpty())
		{
			generatorAddress.append(DEFAULT_HOST) ;
		}
		else
		{
			generatorAddress.append(host) ;
		}
	}

	/**
	 * 
	 * @param port			   with the port
	 * @param generatorAddress with the current generator address string
	 */
	private void getGeneratorAddressPort(final String port, final StringBuilder generatorAddress)
	{
		if (port != null && !port.isEmpty())
		{
			generatorAddress.append(STRING_COLON) ;
			generatorAddress.append(port) ;
		}
		
		generatorAddress.append(STRING_BAR) ;
	}

	/**
	 * 
	 * @param servicePath	   with the service path
	 * @param generatorAddress with the current generator address string
	 */
	private void getGeneratorServicePath(final String servicePath, final StringBuilder generatorAddress)
	{
        if (servicePath != null && !servicePath.isEmpty())
        {
        	generatorAddress.append(servicePath) ;
        	generatorAddress.append(STRING_BAR) ;
        }
	}
}
