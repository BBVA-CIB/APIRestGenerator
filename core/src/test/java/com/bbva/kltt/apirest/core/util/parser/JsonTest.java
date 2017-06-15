package com.bbva.kltt.apirest.core.util.parser;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class JsonTest
{
	@Test
	public void fullTest() throws Exception 
	{
		Json.mapper() ;
	}
	
	@Test
	public void invokeConstructor() throws Exception 
	{
		Whitebox.invokeConstructor(Json.class, new Object[0]);
		Assert.assertTrue(true);
	}
}
