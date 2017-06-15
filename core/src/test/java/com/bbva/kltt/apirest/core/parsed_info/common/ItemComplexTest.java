package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemComplexTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemComplex itemComplex = ItemComplexTest.generateDummyItemComplex() ;
		
		itemComplex.getItemsMap() ;
		itemComplex.getType() ;
		itemComplex.getClassName() ;
	}
	
	/**
	 * @return a dummy item complex
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemComplex generateDummyItemComplex() throws APIRestGeneratorException
	{
		return new ItemComplex(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
}
