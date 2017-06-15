package com.bbva.kltt.apirest.core.parsed_info;

import org.junit.Test;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ContactValuesTest
{
	/** Constant - Name */
	private static final String NAME  = "Francisco Manuel Benitez Chico" ;
	
	/** Constant - URL */
	private static final String URL   = "http://www.pacobenitezchico.com" ;
	
	/** Constant - Email */
	private static final String EMAIL = "paco.benitez.chico@gmail.com" ;
	
	@Test
	public void fullTest()
	{
		final ContactValues contactValues = ContactValuesTest.generateDummyContactValues() ;
		
		contactValues.getName() ;
		contactValues.getUrl() ;
		contactValues.getEmail() ;
	}
	
	/**
	 * @return a dummy contact values
	 */
	public static ContactValues generateDummyContactValues()
	{
		// New instance of ContactValues
		final ContactValues contactValues = new ContactValues() ;
		
		contactValues.addValues(NAME, URL, EMAIL) ;
		
		// Return the new instance of ContactValues
		return contactValues ;
	}
}
