package com.bbva.kltt.apirest.core.generator.output.parameters;

import org.junit.Test;

import com.bbva.kltt.apirest.core.generator.output.language.MyOutputLanguageSeparators;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputParametersTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputParameters outputParameters = OutputParametersTest.generateDummyOutputParameters() ;
		
		outputParameters.getMethodSign() ;
		outputParameters.getOutputLangSeparators() ;
		outputParameters.getParametersCall() ;
		outputParameters.getParametersDescription() ;
		outputParameters.getParametersHeader() ;
		outputParameters.getParametersList() ;
	}
	
	/**
	 * @return a dummy output parameters
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static OutputParameters generateDummyOutputParameters() throws APIRestGeneratorException
	{
		return new MyOutputParameters(new MyOutputLanguageSeparators(), OutputParameterTest.generateDummyListOutputParameter()) ;
	}
}
