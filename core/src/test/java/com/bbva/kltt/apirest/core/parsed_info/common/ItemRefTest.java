package com.bbva.kltt.apirest.core.parsed_info.common;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ItemRefTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final ItemRef itemRef = ItemRefTest.generateDummyItemRef() ;
		
		itemRef.isRequired() ;
		itemRef.getAlias() ;
		itemRef.getClassName() ;
		itemRef.getDescription() ;
		itemRef.getName() ;
		itemRef.getType() ;
		itemRef.getItemRef() ;
		
		itemRef.setRequired(true) ;
		itemRef.setItemRef(ConstantsTest.REFERENCE_PARAMETER) ;
	}
	
	/**
	 * @return a dummy item reference
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static ItemRef generateDummyItemRef() throws APIRestGeneratorException
	{
		return new ItemRef(ConstantsTest.ITEM_NAME, ConstantsTest.ALIAS, ConstantsTest.DESCRIPTION, ConstantsTest.REQUIRED, ConstantsTest.REFERENCE_PARAMETER) ;
	}
}
