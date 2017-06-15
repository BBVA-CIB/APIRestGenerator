package com.bbva.kltt.apirest.core.generator.output.language;

import java.util.List;

import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParameter;
import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParameterTest;
import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParameters;
import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParametersTest;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandler;
import com.bbva.kltt.apirest.core.parsed_info.parameters.Parameter;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class MyOutputLanguageParameters extends OutputLanguageParameters
{
	/**
	 * Public constructor
	 * @param parsedInfoHandler with the parsed information handler
	 * @param outputLangSeparators with the output language separators
	 */
	public MyOutputLanguageParameters(final ParsedInfoHandler parsedInfoHandler, final IOutputLanguageSeparators outputLangSeparators)
	{
		super(parsedInfoHandler, outputLangSeparators) ;
	}

	@Override
	public OutputParameters createNewOutputParameters(List<OutputParameter> outputParamList)
	{
		OutputParameters outcome = null ;
		
		try
		{
			outcome = OutputParametersTest.generateDummyOutputParameters() ;
		} 
		catch (APIRestGeneratorException apiRestGeneratorExc)
		{
			apiRestGeneratorExc.printStackTrace() ;
		}
		
		return outcome ;
	}

	@Override
	public OutputParameter createNewOutputParameter(Parameter parameter)
	{
		OutputParameter outcome = null ;
		
		try
		{
			outcome = OutputParameterTest.generateDummyOutputParameter() ;
		} 
		catch (APIRestGeneratorException apiRestGeneratorExc)
		{
			apiRestGeneratorExc.printStackTrace() ;
		}
		
		return outcome ;
	}

}
