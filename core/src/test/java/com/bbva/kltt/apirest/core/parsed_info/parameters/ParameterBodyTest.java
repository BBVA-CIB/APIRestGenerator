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
public class ParameterBodyTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ParameterBody parameterBody = ParameterBodyTest.generateDummyParameterBody() ;

		parameterBody.getAlias() ;
		parameterBody.getClassName() ;
		parameterBody.getDescription() ;
		parameterBody.getItem() ;
		parameterBody.getName() ;
		parameterBody.getType() ;
		parameterBody.isAutoInjected() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidParameterBodyAutoinjectedTrue() throws APIRestGeneratorException
	{
		final ParameterBody parameterBody = new ParameterBody(ConstantsTest.NAME,
															  ConstantsTest.ALIAS,
															  ConstantsTest.DESCRIPTION,
															  ConstantsTest.REQUIRED,
															  ConstantsTest.TYPE,
															  "true") ;
		parameterBody.validate() ;
	}
	
	/**
	 * @return a dummy parameter body
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ParameterBody generateDummyParameterBody() throws APIRestGeneratorException
	{
		final ParameterBody parameterBody = new ParameterBody(ConstantsTest.NAME,
															  ConstantsTest.ALIAS,
															  ConstantsTest.DESCRIPTION,
															  ConstantsTest.REQUIRED,
															  ConstantsTest.TYPE,
															  ConstantsTest.AUTO_INJECTED) ;
		
		parameterBody.setItem(ItemTest.generateDummyItem()) ;
		
		return parameterBody ;
	}
}
