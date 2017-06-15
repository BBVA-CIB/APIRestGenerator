package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemSimpleLongTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemSimpleLong itemSimpleLong = ItemSimpleLongTest.generateDummyItemSimpleLong() ;
		
		itemSimpleLong.isRequired() ;
		itemSimpleLong.getAlias() ;
		itemSimpleLong.getClassName() ;
		itemSimpleLong.getDescription() ;
		itemSimpleLong.getName() ;
		itemSimpleLong.getType() ;
		
		itemSimpleLong.setRequired(true) ;
	}
	
	/**
	 * @return a dummy item simple long
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemSimpleLong generateDummyItemSimpleLong() throws APIRestGeneratorException
	{
		return new ItemSimpleLong(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
}
