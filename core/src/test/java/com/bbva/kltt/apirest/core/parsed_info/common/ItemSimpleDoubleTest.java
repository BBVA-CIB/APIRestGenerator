package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemSimpleDoubleTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemSimpleDouble itemSimpleDouble = ItemSimpleDoubleTest.generateDummyItemSimpleDouble() ;
		
		itemSimpleDouble.isRequired() ;
		itemSimpleDouble.getAlias() ;
		itemSimpleDouble.getClassName() ;
		itemSimpleDouble.getDescription() ;
		itemSimpleDouble.getName() ;
		itemSimpleDouble.getType() ;
		
		itemSimpleDouble.setRequired(true) ;
	}
	
	/**
	 * @return a dummy item simple double
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemSimpleDouble generateDummyItemSimpleDouble() throws APIRestGeneratorException
	{
		return new ItemSimpleDouble(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
}
