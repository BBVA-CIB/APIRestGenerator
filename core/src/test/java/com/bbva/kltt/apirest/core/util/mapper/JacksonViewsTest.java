package com.bbva.kltt.apirest.core.util.mapper;

import org.junit.Test;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class JacksonViewsTest
{
	@Test
	public void fullTest() throws Exception 
	{
		new JacksonViews.GeneratorView();
		new JacksonViews.Public() ;
	}
}
