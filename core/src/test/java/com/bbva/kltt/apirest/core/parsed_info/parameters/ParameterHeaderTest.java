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
public class ParameterHeaderTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ParameterHeader parameterHeader = ParameterHeaderTest.generateDummyParameterHeader() ;

		parameterHeader.getAlias() ;
		parameterHeader.getClassName() ;
		parameterHeader.getDescription() ;
		parameterHeader.getItem() ;
		parameterHeader.getName() ;
		parameterHeader.getType() ;
		parameterHeader.isAutoInjected() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidParameterTypeNull() throws APIRestGeneratorException
	{
		final ParameterHeader parameterHeader = new ParameterHeader(ConstantsTest.NAME,
																	ConstantsTest.ALIAS,
																	ConstantsTest.DESCRIPTION,
																	ConstantsTest.REQUIRED,
																	null,
																	ConstantsTest.AUTO_INJECTED) ;
		parameterHeader.validate() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidParameterType() throws APIRestGeneratorException
	{
		final ParameterHeader parameterHeader = new ParameterHeader(ConstantsTest.NAME,
																	ConstantsTest.ALIAS,
																	ConstantsTest.DESCRIPTION,
																	ConstantsTest.REQUIRED,
																	"noKnownType",
																	ConstantsTest.AUTO_INJECTED) ;
		parameterHeader.validate() ;
	}
	
	/**
	 * @return a dummy parameter header
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ParameterHeader generateDummyParameterHeader() throws APIRestGeneratorException
	{
		final ParameterHeader parameterHeader = new ParameterHeader(ConstantsTest.NAME,
																	ConstantsTest.ALIAS,
																	ConstantsTest.DESCRIPTION,
																	ConstantsTest.REQUIRED,
																	ConstantsTest.TYPE,
																	ConstantsTest.AUTO_INJECTED) ;
		parameterHeader.setItem(ItemTest.generateDummyItem()) ;
		
		return parameterHeader ;
	}
}
