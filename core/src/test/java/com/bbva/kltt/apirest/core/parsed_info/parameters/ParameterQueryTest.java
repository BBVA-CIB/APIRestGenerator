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
public class ParameterQueryTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ParameterQuery parameterQuery = ParameterQueryTest.generateDummyParameterQuery() ;

		parameterQuery.getAlias() ;
		parameterQuery.getClassName() ;
		parameterQuery.getDescription() ;
		parameterQuery.getItem() ;
		parameterQuery.getName() ;
		parameterQuery.getType() ;
		parameterQuery.isAutoInjected() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidParameterQueryAutoinjectedTrue() throws APIRestGeneratorException
	{
		final ParameterQuery parameterQuery = new ParameterQuery(ConstantsTest.NAME,
																 ConstantsTest.ALIAS,
																 ConstantsTest.DESCRIPTION,
																 ConstantsTest.REQUIRED,
																 ConstantsTest.TYPE,
																 "true") ;
		parameterQuery.validate() ;
	}
	
	/**
	 * @return a dummy parameter query
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ParameterQuery generateDummyParameterQuery() throws APIRestGeneratorException
	{
		final ParameterQuery parameterQuery = new ParameterQuery(ConstantsTest.NAME,
																 ConstantsTest.ALIAS,
																 ConstantsTest.DESCRIPTION,
																 ConstantsTest.REQUIRED,
																 ConstantsTest.TYPE,
																 ConstantsTest.AUTO_INJECTED) ;
		
		parameterQuery.setItem(ItemTest.generateDummyItem()) ;
		
		return parameterQuery ;
	}
}
