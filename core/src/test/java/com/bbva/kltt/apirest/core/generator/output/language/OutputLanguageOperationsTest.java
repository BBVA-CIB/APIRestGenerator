package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageOperationsTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageOperations outputLanguageOperations = new OutputLanguageOperations(ParsedInfoHandlerTest.generateDummyParsedInfoHandler()) ;
		
		outputLanguageOperations.getAllOperationIds() ;
		outputLanguageOperations.getOperationId(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageOperations.hasAnyOperationId(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
	}
}
