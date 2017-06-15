package com.bbva.kltt.apirest.parser.swagger.util;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ConstantsInputParserTest
{
	@Test
	public void invokeConstructor() throws Exception 
	{
		Whitebox.invokeConstructor(ConstantsInputParser.class, new Object[0]);
		Assert.assertTrue(true);
	}
}
