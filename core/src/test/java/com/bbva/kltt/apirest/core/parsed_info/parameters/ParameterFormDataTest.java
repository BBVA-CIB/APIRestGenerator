package com.bbva.kltt.apirest.core.parsed_info.parameters;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.common.ItemTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsCommon;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParameterFormDataTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ParameterFormData parameterFormData = ParameterFormDataTest.generateDummyParameterFormData() ;

		parameterFormData.getAlias() ;
		parameterFormData.getClassName() ;
		parameterFormData.getDescription() ;
		parameterFormData.getItem() ;
		parameterFormData.getName() ;
		parameterFormData.getType() ;
		parameterFormData.isAutoInjected() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidParameterTypeNull() throws APIRestGeneratorException
	{
		final ParameterFormData parameterFormData = new ParameterFormData(ConstantsTest.NAME,
																		  ConstantsTest.ALIAS,
																		  ConstantsTest.DESCRIPTION,
																		  ConstantsTest.REQUIRED,
																		  null,
																		  ConstantsTest.CONSUMES_MULTIPAR,
																		  ConstantsTest.AUTO_INJECTED) ;
		parameterFormData.validate() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidParameterAutoInjectedTrue() throws APIRestGeneratorException
	{
		final ParameterFormData parameterFormData = new ParameterFormData(ConstantsTest.NAME,
																		  ConstantsTest.ALIAS,
																		  ConstantsTest.DESCRIPTION,
																		  ConstantsTest.REQUIRED,
																		  ConstantsCommon.TYPE_FILE,
																		  ConstantsTest.CONSUMES_MULTIPAR,
																		  "true") ;
		parameterFormData.validate() ;
	}
	
	/**
	 * @return a dummy parameter formData
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ParameterFormData generateDummyParameterFormData() throws APIRestGeneratorException
	{
		final ParameterFormData parameterFormData = new ParameterFormData(ConstantsTest.NAME,
																		  ConstantsTest.ALIAS,
																		  ConstantsTest.DESCRIPTION,
																		  ConstantsTest.REQUIRED,
																		  ConstantsCommon.TYPE_FILE,
																		  ConstantsTest.CONSUMES_MULTIPAR,
																		  ConstantsTest.AUTO_INJECTED) ;
		parameterFormData.setItem(ItemTest.generateDummyItem()) ;
		
		return parameterFormData ;
	}
}
