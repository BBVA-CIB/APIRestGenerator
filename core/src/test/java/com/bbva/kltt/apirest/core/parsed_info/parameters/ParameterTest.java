package com.bbva.kltt.apirest.core.parsed_info.parameters;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.common.ItemTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParameterTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final Parameter parameter = ParameterTest.generateDummyParameter() ;

		parameter.getAlias() ;
		parameter.getClassName() ;
		parameter.getDescription() ;
		parameter.getItem() ;
		parameter.getName() ;
		parameter.getType() ;
		parameter.isAutoInjected() ;
	}
	
	/**
	 * @return a dummy parameter
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static Parameter generateDummyParameter() throws APIRestGeneratorException
	{
		final Parameter parameter = new MyParameter(ConstantsTest.NAME,
												    ConstantsTest.ALIAS,
												    ConstantsTest.DESCRIPTION,
												    ConstantsTest.REQUIRED,
												    ConstantsTest.TYPE,
												    ConstantsTest.AUTO_INJECTED) ;
		
		parameter.setItem(ItemTest.generateDummyItem()) ;
		
		return parameter ;
	}
}
