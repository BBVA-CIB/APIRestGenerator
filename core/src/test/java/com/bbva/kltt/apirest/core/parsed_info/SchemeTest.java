package com.bbva.kltt.apirest.core.parsed_info;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class SchemeTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		// Test all the valid schemes
		Scheme.fromStringName(Scheme.SCHEMA_HTTP.getName()) ;
		Scheme.fromStringName(Scheme.SCHEMA_HTTPS.getName()) ;
		Scheme.fromStringName(Scheme.SCHEMA_WS.getName()) ;
		Scheme.fromStringName(Scheme.SCHEMA_WSS.getName()) ;
		
		Scheme.SCHEMA_HTTP.toString() ;
	}
	
	@Test(expected=APIRestGeneratorException.class)
	public void invalidTest() throws APIRestGeneratorException
	{
		Scheme.fromStringName("invalidScheme") ;
	}
}
