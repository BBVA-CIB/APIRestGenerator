package com.bbva.kltt.apirest.core.util.mapper;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class JacksonMapperTest
{
	@Test
	public void fullTest() throws Exception 
	{
		JacksonMapper.getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(ParsedInfoTest.generateDummyParsedInfo());
	}
}
