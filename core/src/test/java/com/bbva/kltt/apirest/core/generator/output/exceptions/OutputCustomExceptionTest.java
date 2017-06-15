package com.bbva.kltt.apirest.core.generator.output.exceptions;

import org.junit.Test;

import com.bbva.kltt.apirest.core.generator.output.language.MyOutputLanguageNaming;
import com.bbva.kltt.apirest.core.parsed_info.responses.ResponseTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputCustomExceptionTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputCustomException outputCustomException = new OutputCustomException(new MyOutputLanguageNaming(),
																					  ConstantsTest.OPERATION_ID,
																					  ResponseTest.generateDummyResponse()) ;
		outputCustomException.getAsClassName() ;
		outputCustomException.getAsMethodName() ;
		
		outputCustomException.getOutputLanguageNaming() ;
		
		outputCustomException.getResponseItem() ;
		
		outputCustomException.getStatusCode() ;
	}
}
