package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemSimpleFloatTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemSimpleFloat itemSimpleFloat = ItemSimpleFloatTest.generateDummyItemSimpleFloat() ;
		
		itemSimpleFloat.isRequired() ;
		itemSimpleFloat.getAlias() ;
		itemSimpleFloat.getClassName() ;
		itemSimpleFloat.getDescription() ;
		itemSimpleFloat.getName() ;
		itemSimpleFloat.getType() ;
		
		itemSimpleFloat.setRequired(true) ;
	}
	
	/**
	 * @return a dummy item simple float
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemSimpleFloat generateDummyItemSimpleFloat() throws APIRestGeneratorException
	{
		return new ItemSimpleFloat(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
}
