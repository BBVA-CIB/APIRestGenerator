package com.bbva.kltt.apirest.core.generator.output.language;

import org.junit.Test;

import com.bbva.kltt.apirest.core.generator.output.parameters.OutputParameterTest;
import com.bbva.kltt.apirest.core.parsed_info.ParsedInfoHandlerTest;
import com.bbva.kltt.apirest.core.parsed_info.parameters.ParameterQueryTest;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;
import com.bbva.kltt.apirest.core.util.ConstantsTest;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class OutputLanguageParametersTest
{
	@Test
	public void fullTest() throws APIRestGeneratorException
	{
		final OutputLanguageParameters outputLanguageParameters = new MyOutputLanguageParameters(ParsedInfoHandlerTest.generateDummyParsedInfoHandler(),
																							   	 new MyOutputLanguageSeparators()) ;
		
		outputLanguageParameters.createNewOutputParameter(ParameterQueryTest.generateDummyParameterQuery()) ;
		outputLanguageParameters.createNewOutputParameters(OutputParameterTest.generateDummyListOutputParameter()) ;
		
		outputLanguageParameters.generateAllOutputParameters(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		outputLanguageParameters.generateOutputParameterBody(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageParameters.generateOutputParametersFormData(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageParameters.generateOutputParametersHeader(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageParameters.generateOutputParametersPath(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageParameters.generateOutputParametersQuery(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageParameters.generateAllOutputParameters(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		
		outputLanguageParameters.getOutputLangSeparators() ;
		
		outputLanguageParameters.getParameterListBody(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageParameters.getParametersListFormData(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageParameters.getParametersListHeader(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageParameters.getParametersListPath(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
		outputLanguageParameters.getParametersListQuery(ConstantsTest.PATH_VALUE, ConstantsTest.PATH_OPERATION) ;
	}
}
