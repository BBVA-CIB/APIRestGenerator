package com.bbva.kltt.apirest.core.util;

import org.junit.Test;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class APIRestGeneratorExceptionTest
{
	@Test(expected = APIRestGeneratorException.class)
	public void fullTest() throws APIRestGeneratorException 
	{
		throw new APIRestGeneratorException(new RuntimeException()) ;
	}
}
