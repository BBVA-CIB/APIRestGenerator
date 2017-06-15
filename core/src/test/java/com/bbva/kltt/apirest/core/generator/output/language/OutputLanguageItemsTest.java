package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemArrayTest;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemComplexTest;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemRefTest;
import com.bbva.kltt.apirest.core.parsed_info.common.ItemTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

public class OutputLanguageItemsTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageItems outputLanguageItems = new MyOutputLanguageItems(ParsedInfoHandlerTest.generateDummyParsedInfoHandler()) ;
		
		outputLanguageItems.getDefinitionsListNames() ;
		
		outputLanguageItems.getFullTypeOutput(ItemTest.generateDummyItem()) ;
		
		outputLanguageItems.getModelNameIfExist(ItemTest.generateDummyItem()) ;
		
		outputLanguageItems.getOutboundServerItemType(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		outputLanguageItems.getTypeOutputDefault() ;
		
		outputLanguageItems.getTypeBaseArray(ItemArrayTest.generateDummyItemArray()) ;
		
		outputLanguageItems.getTypeOutputItemComplex(ItemComplexTest.generateDummyItemComplex()) ;
		outputLanguageItems.getTypeOutputItemFile() ;
		outputLanguageItems.getTypeOutputItemRef(ItemRefTest.generateDummyItemRef()) ;
		outputLanguageItems.getTypeOutputSimpleBoolean(true) ;
		outputLanguageItems.getTypeOutputSimpleDouble(true) ;
		outputLanguageItems.getTypeOutputSimpleFloat(true) ;
		outputLanguageItems.getTypeOutputSimpleInteger(true) ;
		outputLanguageItems.getTypeOutputSimpleLong(true) ;
		outputLanguageItems.getTypeOutputSimpleString(true) ;
	}
}
