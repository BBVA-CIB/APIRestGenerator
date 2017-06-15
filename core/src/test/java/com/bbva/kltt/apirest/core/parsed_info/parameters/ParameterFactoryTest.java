package com.bbva.kltt.apirest.core.parsed_info.parameters;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.common.ItemSimpleStringTest;
import com.bbva.kltt.apirest.core.parser.IParameterFactory;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsMiddle;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParameterFactoryTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		IParameterFactory parameterFactory = new ParameterFactory(ConstantsTest.CONSUMES_APP_JSON) ;
		
		parameterFactory.createNewParameter(ConstantsMiddle.PARAM_IN_BODY,
											ConstantsTest.NAME,
											ConstantsTest.ALIAS,
											ConstantsTest.DESCRIPTION,
											ConstantsTest.REQUIRED, ConstantsTest.TYPE, ConstantsTest.AUTO_INJECTED) ;
		
		parameterFactory.createNewParameter(ConstantsMiddle.PARAM_IN_QUERY,
											ConstantsTest.NAME,
											ConstantsTest.ALIAS,
											ConstantsTest.DESCRIPTION,
											ConstantsTest.REQUIRED, ConstantsTest.TYPE, ConstantsTest.AUTO_INJECTED) ;
		
		parameterFactory.createNewParameter(ConstantsMiddle.PARAM_IN_HEADER,
											ConstantsTest.NAME,
											ConstantsTest.ALIAS,
											ConstantsTest.DESCRIPTION,
											ConstantsTest.REQUIRED, ConstantsTest.TYPE, ConstantsTest.AUTO_INJECTED) ;
		
		parameterFactory.createNewParameter(ConstantsMiddle.PARAM_IN_PATH,
											ConstantsTest.NAME,
											ConstantsTest.ALIAS,
											ConstantsTest.DESCRIPTION,
											ConstantsTest.REQUIRED, ConstantsTest.TYPE, ConstantsTest.AUTO_INJECTED) ;
		
		parameterFactory.createNewParameter(ConstantsMiddle.PARAM_IN_FORMDATA,
											ConstantsTest.NAME,
											ConstantsTest.ALIAS,
											ConstantsTest.DESCRIPTION,
											ConstantsTest.REQUIRED, ConstantsTest.TYPE, ConstantsTest.AUTO_INJECTED) ;
		
		parameterFactory.addItemIntoParameter(ParameterBodyTest.generateDummyParameterBody(), ItemSimpleStringTest.generateDummyItemSimpleString()) ;
		
		parameterFactory.isBodyParameter(ConstantsMiddle.PARAM_IN_BODY, ConstantsMiddle.PARAM_IN_BODY) ;
	}
}
