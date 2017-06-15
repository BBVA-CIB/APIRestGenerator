package com.bbva.kltt.apirest.core.generator.velocity;

import org.junit.Test;

import com.bbva.kltt.apirest.core.util.ConstantsCommon;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class VelocityTemplateLocatorTest
{
	@Test
	public void fullTest()
	{
		VelocityTemplateLocator.getTemplateResourceName(ConstantsCommon.STRING_EMPTY, ConstantsCommon.STRING_EMPTY) ;
	}
}
