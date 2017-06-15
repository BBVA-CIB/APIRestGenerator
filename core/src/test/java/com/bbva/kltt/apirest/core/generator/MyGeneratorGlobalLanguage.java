package com.bbva.kltt.apirest.core.generator;

import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyGeneratorGlobalLanguage extends GeneratorGlobalLanguage
{
	/**
	 * Constructs a new global generator for Java
	 * @param genParams  		with the parameters for the generation for java
	 * @param parsedInfoHandler with the parsed information handler to generate from
	 * @param generationPackage with the generation package
	 */
	public MyGeneratorGlobalLanguage(final GenerationParameters genParams, final ParsedInfoHandler parsedInfoHandler, final String generationPackage)
	{
		super(genParams, parsedInfoHandler, generationPackage) ;
	}

	@Override
	public void generate(String translatorType) throws APIRestGeneratorException
	{
		// Nothing to do
	}
}
