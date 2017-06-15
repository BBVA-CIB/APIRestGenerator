package com.bbva.kltt.apirest.core.parsed_info;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ResponsesTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final Responses responses = ResponsesTest.generateDummyResponses() ;
		
		responses.getItemFactory() ;
		responses.getOutboundServerExceptionsTypes() ;
		responses.getOutboundServerItemType() ;
		responses.getResponseFactory() ;
		responses.getResponsesMap() ;
	}
	
	/**
	 * @return a dummy responses
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static Responses generateDummyResponses() throws APIRestGeneratorException
	{
		// Return the new instance of Responses
		return new Responses(DefinitionsTest.generateDummyDefinitions()) ;
	}
}
