package com.bbva.kltt.apirest.core.generator.output.parameters;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.common.ItemComplexTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputParameterTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputParameter outputParameter = OutputParameterTest.generateDummyOutputParameter() ;
		
		outputParameter.getItem() ;
		outputParameter.getName() ;
		outputParameter.getOutputTypeName() ;
		outputParameter.getParameterCall() ;
		outputParameter.getParameterHeader() ;
		outputParameter.getParameterHeaderRest() ;
		outputParameter.getParameterOfficialName() ;
		outputParameter.getParameterTest() ;
		outputParameter.getType() ;
		outputParameter.generateParameterDescription() ;
	}
	
	/**
	 * @return a dummy output parameter
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static OutputParameter generateDummyOutputParameter() throws APIRestGeneratorException
	{
		return new MyOutputParameter(ItemComplexTest.generateDummyItemComplex(), ConstantsTest.DESCRIPTION, ConstantsTest.TYPE, ConstantsTest.NAME) ;
	}
	
	/**
	 * @return a dummy list of output parameter
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static List<OutputParameter> generateDummyListOutputParameter() throws APIRestGeneratorException
	{
		final List<OutputParameter> outputParameterList = new ArrayList<OutputParameter>() ;
		outputParameterList.add(OutputParameterTest.generateDummyOutputParameter()) ;
		
		return outputParameterList ;
	}
}
