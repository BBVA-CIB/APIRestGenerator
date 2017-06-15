package com.bbva.kltt.apirest.core.parsed_info;

import java.util.Map;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.common.Item;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRefTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class DefinitionsTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final Definitions definitions = DefinitionsTest.generateDummyDefinitions() ;
		
		definitions.getItemFactory() ;
		
		definitions.generateDefinitionsMap() ;
	}
	
	/**
	 * @return a dummy definitions
	 * @throws APIRestGeneratorException with an occurred exception
	 */
	public static Definitions generateDummyDefinitions() throws APIRestGeneratorException
	{
		// New instance of Definitions
		final Definitions definitions    = new Definitions() ;
		
		final Map<String, Item> itemsMap = definitions.getItemsMap() ;
		itemsMap.put(ConstantsTest.ITEM_NAME, ItemRefTest.generateDummyItemRef()) ;
		
		return definitions ;
	}
}
