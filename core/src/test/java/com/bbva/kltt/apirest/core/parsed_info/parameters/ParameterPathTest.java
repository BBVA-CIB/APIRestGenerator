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
public class ParameterPathTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ParameterPath parameterPath = ParameterPathTest.generateDummyParameterPath() ;

		parameterPath.getAlias() ;
		parameterPath.getClassName() ;
		parameterPath.getDescription() ;
		parameterPath.getItem() ;
		parameterPath.getName() ;
		parameterPath.getType() ;
		parameterPath.isAutoInjected() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidParameterRequiredFalse() throws APIRestGeneratorException
	{
		final ParameterPath parameterPath = new ParameterPath(ConstantsTest.NAME,
															  ConstantsTest.ALIAS,
															  ConstantsTest.DESCRIPTION,
															  "false",
															  ConstantsTest.TYPE,
															  ConstantsTest.AUTO_INJECTED) ;
		parameterPath.validate() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidParameterAliasNotEqualsName() throws APIRestGeneratorException
	{
		final ParameterPath parameterPath = new ParameterPath(ConstantsTest.NAME,
															  "differentValueThanName",
															  ConstantsTest.DESCRIPTION,
															  ConstantsTest.REQUIRED,
															  ConstantsTest.TYPE,
															  ConstantsTest.AUTO_INJECTED) ;
		parameterPath.validate() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidParameterPathAutoinjectedTrue() throws APIRestGeneratorException
	{
		final ParameterPath parameterPath = new ParameterPath(ConstantsTest.NAME,
															  ConstantsTest.ALIAS,
															  ConstantsTest.DESCRIPTION,
															  ConstantsTest.REQUIRED,
															  ConstantsTest.TYPE,
															  "true") ;
		parameterPath.validate() ;
	}
	
	/**
	 * @return a dummy parameter path
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ParameterPath generateDummyParameterPath() throws APIRestGeneratorException
	{
		final ParameterPath parameterPath = new ParameterPath(ConstantsTest.NAME,
															  ConstantsTest.ALIAS,
															  ConstantsTest.DESCRIPTION,
															  ConstantsTest.REQUIRED,
															  ConstantsTest.TYPE,
															  ConstantsTest.AUTO_INJECTED) ;
		
		parameterPath.setItem(ItemTest.generateDummyItem()) ;
		
		return parameterPath ;
	}
}
