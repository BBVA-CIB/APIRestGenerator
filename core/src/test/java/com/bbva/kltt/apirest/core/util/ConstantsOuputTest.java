package com.bbva.kltt.apirest.core.util;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ConstantsOuputTest
{
	@Test
	public void invokeConstructor() throws Exception 
	{
		Whitebox.invokeConstructor(ConstantsOutput.class, new Object[0]);
		Assert.assertTrue(true);
	}
}
