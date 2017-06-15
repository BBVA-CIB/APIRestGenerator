package com.bbva.kltt.apirest.core.parsed_info.responses;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.common.ItemSimpleStringTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ResponseTest
{
	/** Constant - Response name */
	private static final String RESPONSE_NAME 		 = "200" ;
	
	/** Constant - Response alias */
	private static final String RESPONSE_ALIAS 		 = "responseAlias" ;
	
	/** Constant - Response description */
	private static final String RESPONSE_DESCRIPTION = "responseDescription" ;
	
	/** Constant - Response type */
	private static final String RESPONSE_TYPE 		 = "responseType" ;
	
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final Response response = ResponseTest.generateDummyResponse() ;
		
		response.getAlias() ;
		response.getClassName() ;
		response.getDescription() ;
		response.getItem() ;
		response.getName() ;
		response.getType() ;
		
		response.validate() ;
	}
	
	/**
	 * @return a dummy response
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static Response generateDummyResponse() throws APIRestGeneratorException
	{
		final Response response = new Response(RESPONSE_NAME, RESPONSE_ALIAS, RESPONSE_DESCRIPTION, RESPONSE_TYPE) ;
		
		response.setItem(ItemSimpleStringTest.generateDummyItemSimpleString()) ;
		
		return response ;
	}
}
