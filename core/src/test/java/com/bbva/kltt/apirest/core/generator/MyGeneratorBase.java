package com.bbva.kltt.apirest.core.generator;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageConsumesProduces;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageItems;
import com.bbva.kltt.apirest.core.generator.output.language.IOutputLanguageParameters;
import com.bbva.kltt.apirest.core.launcher.GenerationParameters;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyGeneratorBase extends GeneratorBase
{
    /**
     * @param baseOutputDirectory with the File that represents the base directory where the file will be generated.
     * @param generationParams    with the parameters for the generation
     * @param parsedInfoHandler   with the parsed info handler
     */
	public MyGeneratorBase(final File baseOutputDirectory, final GenerationParameters generationParams, final ParsedInfoHandler parsedInfoHandler)
	{
		super(baseOutputDirectory, generationParams, parsedInfoHandler) ;
	}

	@Override
	public IOutputLanguageItems getOutputLanguageItems()
	{
		return null ;
	}

	@Override
	public IOutputLanguageParameters getOutputLanguageParameters()
	{
		return null ;
	}

	@Override
	public IOutputLanguageConsumesProduces getOutputLanguageConsProd()
	{
		return null ;
	}

	@Override
	protected VelocityContext createVelocityContext()
	{
		return new VelocityContext() ;
	}

	@Override
	protected String getOutputPackage()
	{
		return "example" ;
	}

	@Override
	protected String getOutputFileName()
	{
		return "test" ;
	}

	@Override
	protected String getOutputExtension()
	{
		return ".txt" ;
	}

}
