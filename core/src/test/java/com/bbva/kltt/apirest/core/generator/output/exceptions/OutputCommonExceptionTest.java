package com.bbva.kltt.apirest.core.generator.output.exceptions;

import org.junit.Test;

import com.bbva.kltt.apirest.core.generator.output.language.MyOutputLanguageNaming;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputCommonExceptionTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputCommonException outputCommonException = new OutputCommonException(new MyOutputLanguageNaming()) ;
		
		outputCommonException.getAsClassName() ;
		outputCommonException.getAsMethodName(ConstantsTest.OPERATION_ID) ;
		
		outputCommonException.getOutputLanguageNaming() ;
	}
}
