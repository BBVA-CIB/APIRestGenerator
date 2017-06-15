package com.bbva.kltt.apirest.core.generator;

import java.io.File;

import org.apache.velocity.VelocityContext;
import org.junit.Test;

import com.bbva.kltt.apirest.core.launcher.GenerationParametersTest;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class GeneratorBaseTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final GeneratorBase generatorBase = new MyGeneratorBase(new File("target"),
																GenerationParametersTest.generateDummyGenerationParameters(),
																ParsedInfoHandlerTest.generateDummyParsedInfoHandler()) ;
		generatorBase.generate() ;
		generatorBase.generateFile(new VelocityContext()) ;
		
		generatorBase.getTitleCamelCase() ;
		generatorBase.getGenerationParams() ;
		generatorBase.getOutputLanguageInfoValues() ;
	}
}
