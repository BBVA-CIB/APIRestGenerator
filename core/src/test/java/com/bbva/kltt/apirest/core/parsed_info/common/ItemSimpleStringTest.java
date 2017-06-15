package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemSimpleStringTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemSimpleString itemSimpleString = ItemSimpleStringTest.generateDummyItemSimpleString() ;
		
		itemSimpleString.isRequired() ;
		itemSimpleString.getAlias() ;
		itemSimpleString.getClassName() ;
		itemSimpleString.getDescription() ;
		itemSimpleString.getName() ;
		itemSimpleString.getType() ;
		
		itemSimpleString.setRequired(true) ;
	}
	
	/**
	 * @return a dummy item simple string
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemSimpleString generateDummyItemSimpleString() throws APIRestGeneratorException
	{
		return new ItemSimpleString(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
}
