package com.bbva.kltt.apirest.core.parsed_info ;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParsedInfoHandlerTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ParsedInfoHandler parsedInfoHandler = new ParsedInfoHandler(ParsedInfoTest.generateDummyParsedInfo()) ;
		
		parsedInfoHandler.getParametersDescriptionBody(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		parsedInfoHandler.getParametersDescriptionFormData(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		parsedInfoHandler.getParametersDescriptionHeader(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		parsedInfoHandler.getParametersDescriptionPath(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		parsedInfoHandler.getParametersDescriptionQuery(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		parsedInfoHandler.toString() ;
	}
	
	/**
	 * @return a dummy parsed info handler
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ParsedInfoHandler generateDummyParsedInfoHandler() throws APIRestGeneratorException
	{
		return new ParsedInfoHandler(ParsedInfoTest.generateDummyParsedInfo()) ;
	}
}
