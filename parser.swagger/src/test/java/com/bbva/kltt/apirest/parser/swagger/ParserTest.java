package com.bbva.kltt.apirest.parser.swagger;

import java.io.File;

import org.junit.Test;

import com.bbva.kltt.apirest.core.parser.IParser;
import com.bbva.kltt.apirest.core.util.APIRestGeneratorException;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ParserTest
{
	/** Constants - src/test/resources folder */
	private static final String SRC_TEST_RESOURCES_FOLDER   = "src" + File.separator + "test" + File.separator + "resources" + File.separator ;
	
	/** Constant - YAML Test Correct */
	private static final String YAML_TEST_CORRECT 		    = SRC_TEST_RESOURCES_FOLDER + "footballPlayers.yaml" ;
	
	/** Constant - YAML Test Correct - OSGI */
	private static final String YAML_TEST_CORRECT_OSGI	    = SRC_TEST_RESOURCES_FOLDER + "footballPlayers_osgi.yaml" ;
	
	/** Constant - YAML Test Correct - OSGI */
	private static final String YAML_TEST_CORRECT_SCHEME_OP = SRC_TEST_RESOURCES_FOLDER + "footballPlayersSchemesInOperation.yaml" ;
	
	/** Constant - YAML Test Correct - Header example */
	private static final String YAML_TEST_CORRECT_HEADER    = SRC_TEST_RESOURCES_FOLDER + "head.yaml" ;
	
	/** Constant - YAML Test Correct - FormData example */
	private static final String YAML_TEST_CORRECT_FORM_DATA = SRC_TEST_RESOURCES_FOLDER + "formData.yaml" ;
	
	/** Constant - YAML Test Correct - Global Parameters Test */
	private static final String YAML_TEST_CORRECT_GLB_PARAM = SRC_TEST_RESOURCES_FOLDER + "instagram.yaml" ;
	
	/** Constant - YAML Test Correct - Global Responses Test */
	private static final String YAML_TEST_CORRECT_GLB_RESPO = SRC_TEST_RESOURCES_FOLDER + "specExample.yaml" ;
	
	/** Constant - YAML Test Correct - Head, Options and Patch example */
	private static final String YAML_TEST_CORRECT_HE_OP_PAT = SRC_TEST_RESOURCES_FOLDER + "footballPlayersHeadOptionsPatchExample.yaml" ;
	
	/** Constant - YAML Test File Incorrect - No Type No Ref Definition */
	private static final String YAML_NOTYPE_NOREF_DEFINIT   = SRC_TEST_RESOURCES_FOLDER + "footballPlayersNoTypeNoRefDefinedDefinition.yaml" ;
	
	/** Constant - YAML Test File Incorrect - No Type Object Definition */
	private static final String YAML_NOTYPE_OBJECT_DEFINIT  = SRC_TEST_RESOURCES_FOLDER + "footballPlayersNoTypeObjectDefinition.yaml" ;
	
	/** Constant - YAML Test File Incorrect - Invalid UUAA */
	private static final String YAML_INVALID_UUAA 		    = SRC_TEST_RESOURCES_FOLDER + "footballPlayersInvalidUuaa.yaml" ;
	
	/** Constant - YAML Test File Incorrect - Invalid 'in' value */
	private static final String YAML_INVALID_IN_VALUE	    = SRC_TEST_RESOURCES_FOLDER + "footballPlayersInvalidInValue.yaml" ;
	
	/** Constant - YAML Test File Incorrect - No expected Ref Parameter */
	private static final String YAML_INVALID_NO_EXP_REF_PAR = SRC_TEST_RESOURCES_FOLDER + "instagramNoExpectedRefParameter.yaml" ;
	
	/** Constant - YAML Test File Incorrect - Invalid Path value */
	private static final String YAML_INVALID_PATH_VALUE		= SRC_TEST_RESOURCES_FOLDER + "footballPlayersInvalidPathValue.yaml" ;
	
	/** Constant - YAML Test File Incorrect - No type for array response */
	private static final String YAML_INVALID_NO_TYPE_ARR_RE = SRC_TEST_RESOURCES_FOLDER + "footballPlayersNoTypeForArrayResponse.yaml" ;
	
	/** Constant - YAML Test File Incorrect - Duplicated response codes */
	private static final String YAML_INVALID_DUPL_RESP_CODE = SRC_TEST_RESOURCES_FOLDER + "footballPlayersDuplicatedResponseCodes.yaml" ;
	
	/** Constant - YAML Test File Incorrect - No reference response defined */
	private static final String YAML_INVALID_NO_REF_RES_DEF = SRC_TEST_RESOURCES_FOLDER + "specExampleResponseRefNotFound.yaml" ;
	
	/** Constant - YAML Test File Incorrect - Invalid Regular Expression Response */
	private static final String YAML_INVALID_REG_EXP_RESPON = SRC_TEST_RESOURCES_FOLDER + "specExampleInvalidRegExpResponse.yaml" ;
	
	/** Constant - YAML Test File Incorrect - No Response 200 */
	private static final String YAML_INVALID_NO_RESP_200 	= SRC_TEST_RESOURCES_FOLDER + "footballPlayersNoResponse200.yaml" ;
	
	/** Constant - YAML Test File Incorrect - Response - Invalid Array of files */
	private static final String YAML_INVALID_RES_ARR_OF_FIL = SRC_TEST_RESOURCES_FOLDER + "footballPlayersInvalidArrayOfFiles.yaml" ;
	
	/** Constant - YAML Test File Incorrect - Invalid - Duplicated operationId */
	private static final String YAML_INVALID_DUPL_OPERAT_ID = SRC_TEST_RESOURCES_FOLDER + "footballPlayersDuplicatedOperationId.yaml" ;
	
	@Test
	public void successfulTest() throws APIRestGeneratorException
	{
		// Common YAML
		IParser parser = new Parser(YAML_TEST_CORRECT) ;
		parser.parse() ;
		
		parser.getParserProjectInfo() ;
		
		// OSGI YAML
		IParser parserOsgi 			   = new Parser(YAML_TEST_CORRECT_OSGI) ;
		parserOsgi.parse() ;
		
		// Schemes in operation
		IParser parserSchemeOperation  = new Parser(YAML_TEST_CORRECT_SCHEME_OP) ;
		parserSchemeOperation.parse() ;
		
		// Header example
		IParser parserHeaderExample    = new Parser(YAML_TEST_CORRECT_HEADER) ;
		parserHeaderExample.parse() ;
		
		// FormData example
		IParser parserFormdDataExample = new Parser(YAML_TEST_CORRECT_FORM_DATA) ;
		parserFormdDataExample.parse() ;
		
		// Global parameters test
		IParser parserGlobalParams     = new Parser(YAML_TEST_CORRECT_GLB_PARAM) ;
		parserGlobalParams.parse() ;
		
		// Head, Options and Patch example
		IParser parserHeadOptionsPatch = new Parser(YAML_TEST_CORRECT_HE_OP_PAT) ;
		parserHeadOptionsPatch.parse() ;
		
		// Head, Options and Patch example
		IParser parserGlobalResponses  = new Parser(YAML_TEST_CORRECT_GLB_RESPO) ;
		parserGlobalResponses.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidTestNoTypeNoRefDefinition() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_NOTYPE_NOREF_DEFINIT) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidTestNoTypeObjectDefinition() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_NOTYPE_OBJECT_DEFINIT) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidUuaa() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_UUAA) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidInValue() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_IN_VALUE) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidNoExpectedRefParameter() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_NO_EXP_REF_PAR) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidPathValue() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_PATH_VALUE) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidNoTypeForArrayResponse() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_NO_TYPE_ARR_RE) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidDuplicatedResponseCodes() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_DUPL_RESP_CODE) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidNoReferencedResponsesDefined() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_NO_REF_RES_DEF) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidRegularExpressionResponse() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_REG_EXP_RESPON) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidNoResponse200() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_NO_RESP_200) ;
		parser.parse() ;
	}

	@Test(expected = APIRestGeneratorException.class)
	public void invalidResponseArrayOfFiles() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_RES_ARR_OF_FIL) ;
		parser.parse() ;
	}
	
	@Test(expected = APIRestGeneratorException.class)
	public void invalidDuplicatedOperationId() throws APIRestGeneratorException
	{
		IParser parser = new Parser(YAML_INVALID_DUPL_OPERAT_ID) ;
		parser.parse() ;
	}
	
	
}
