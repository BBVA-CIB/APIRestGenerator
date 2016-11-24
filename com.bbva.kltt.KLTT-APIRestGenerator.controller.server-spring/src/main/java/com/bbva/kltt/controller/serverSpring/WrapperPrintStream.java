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

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class WrapperPrintStream extends PrintStream
{
	/** Attribute - Specifications Controller */
	private final SpecificationsController specsController ;
	
	/** Attribute - user name */
	private final String username ;
	
	/**
	 * @param outputStream    with the outputStream
	 * @param specsController with the specifications controller
	 * @param username		  with the user name
	 */
	public WrapperPrintStream(final OutputStream outputStream, final SpecificationsController specsController, final String username)
	{
		super(outputStream) ;
		
		this.specsController = specsController ;
		this.username		 = username ;
	}

    @Override
    public void write(byte buffer[], int off, int len) 
    {
    	super.write(buffer, off, len) ;
    	
    	final StringBuffer stringBuffer = new StringBuffer() ;
    	for (int i = 0 ; i < len ; i++)
    	{
    		final byte byteRead = buffer[off + i] ;
    		stringBuffer.append(new String(new byte[]{byteRead})) ;
        }
    	
    	this.specsController.sendDataToClient(this.username, stringBuffer.toString()) ;
    }
}
