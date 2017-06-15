package com.bbva.kltt.apirest.core.parsed_info.responses;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.common.ItemSimpleStringTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ResponseFactoryTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ResponseFactory responseFactory = new ResponseFactory() ;
		
		responseFactory.addItemIntoResponse(ResponseTest.generateDummyResponse(), ItemSimpleStringTest.generateDummyItemSimpleString()) ;
		responseFactory.createNewResponse(ConstantsTest.ITEM_RESPONSE_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.TYPE) ;
	}
}
