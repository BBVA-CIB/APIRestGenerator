package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.parsed_info.responses.ResponseTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageExceptionsTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageExceptions outputLanguageExceptions = new OutputLanguageExceptions(ParsedInfoHandlerTest.generateDummyParsedInfoHandler(), 
																						 	   new MyOutputLanguageNaming(),
																						 	   new MyOutputLanguageOperations()) ;
		
		outputLanguageExceptions.getCommonException() ;
		outputLanguageExceptions.getCommonExceptionClassName() ;
		outputLanguageExceptions.getCustomExceptionAsClassName(ConstantsTest.OPERATION_ID, ResponseTest.generateDummyResponse()) ;
		outputLanguageExceptions.getCustomExceptionsList(ConstantsTest.OPERATION_ID, ConstantsTest.PATH_OPERATION) ;
		outputLanguageExceptions.getOutboundServerExceptionsMap() ;
		
		outputLanguageExceptions.getParsedInfoHandler() ;
	}
}
