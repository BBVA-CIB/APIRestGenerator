package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemFileTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemFile itemFile = ItemFileTest.generateDummyItemFile() ;
		
		itemFile.isRequired() ;
		itemFile.getAlias() ;
		itemFile.getClassName() ;
		itemFile.getDescription() ;
		itemFile.getName() ;
		itemFile.getType() ;
		
		itemFile.setRequired(true) ;
	}
	
	/**
	 * @return a dummy item file
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemFile generateDummyItemFile() throws APIRestGeneratorException
	{
		return new ItemFile(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED) ;
	}
}
