package com.bbva.kltt.apirest.core.parsed_info;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RootValuesTest
{
	/** Constant - Host */
	private static final String HOST 	  			= "localhost:8090" ;
	
	/** Constant - Base path */
	private static final String BASE_PATH 			= "/" ;
	
	@Test
	public void fullTest()
	{
		final RootValues rootValues = RootValuesTest.generateDummyRootValues() ;
		
		rootValues.getBasePath() ;
		rootValues.getConsumes() ;
		rootValues.getHost() ;
		rootValues.getProduces() ;
		rootValues.getSchemes() ;
	}
	
	/**
	 * @return a dummy root values
	 */
	public static RootValues generateDummyRootValues()
	{
		// Return the new instance of RootValues
		return new RootValues(HOST, BASE_PATH, ConstantsTest.SCHEMES, ConstantsTest.CONSUMES_APP_JSON, ConstantsTest.PRODUCES_APP_JSON) ;
	}
}
