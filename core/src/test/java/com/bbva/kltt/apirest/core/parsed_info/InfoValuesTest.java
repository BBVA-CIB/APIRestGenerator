package com.bbva.kltt.apirest.core.parsed_info;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class InfoValuesTest
{
	@Test
	public void fullTest()
	{
		final InfoValues infoValues = InfoValuesTest.generateDummyInfoValues() ;
		
		infoValues.getContactValues() ;
		infoValues.getDescription() ;
		infoValues.getTitle() ;
		infoValues.getVersion() ;
	}
	
	/**
	 * @return a dummy info values
	 */
	public static InfoValues generateDummyInfoValues()
	{
		// Return a new instance of InfoValues
		return new InfoValues(ConstantsTest.PROJECT_TITLE, ConstantsTest.PROJECT_DESCRIPTION, ConstantsTest.PROJECT_VERSION) ;
	}
}
