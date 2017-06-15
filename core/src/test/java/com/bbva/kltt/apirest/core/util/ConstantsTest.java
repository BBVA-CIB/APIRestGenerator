package com.bbva.kltt.apirest.core.util;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.bbva.kltt.apirest.core.parsed_info.Scheme;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class ConstantsTest
{
	/** Constant - Generator Builder Path */
	public static final String SRC_TEST_RESOURCES_PATH = "src/test/resources" ;
	
	/** Constant - Ant File */
	public static final String ANT_FILE			       = "LauncherGeneratorTestExample.xml" ;
	
	/** Constant - Parser type */
	public static final String PARSER_TYPE 		  	   = "parserType" ;
	
	/** Constant - Translator type */
	public static final String TRANSLATOR_TYPE 	       = "test.example" ;
	
	/** Constant - Specification File Path */
	public static final String SPECIF_FILE_PATH 	   = "fileName.yml" ;
	
	/** Constant - Generator Builder Path */
	public static final String GENERATOR_BUILDER_PATH  = "target" + File.separator + "generatorBuilderPath" ;
	
	/** Constant - Temporary Directory */
	public static final String TEMPORARY_DIR 		   = "temporaryDir" ;
	
	/** Constant - Full temporary directory */
	public static final String FULL_TEMPORARY_DIR 	   = GENERATOR_BUILDER_PATH + File.separator + TEMPORARY_DIR ;
	
	/** Constant - Operation identifier */
	public static final String OPERATION_ID   	  	   = "operationId" ;
	
	/** Constant - Path value */
	public static final String PATH_VALUE 	  	  	   = "pathValue" ;
	
	/** Constant - Path operation */
	public static final String PATH_OPERATION 	  	   = "pathOperation" ;
	
	/** Constant - Item - Name */
	public static final String ITEM_NAME 		  	   = "itemName" ;
	
	/** Constant - Item - Response */
	public static final String ITEM_RESPONSE_NAME 	   = "200" ;
	
	/** Constant - Description */
	public static final String DESCRIPTION		  	   = "description" ;

	/** Constant - Class name */
	public static final String CLASS_NAME 		  	   = "MyClassName" ;
	
	/** Constant - Name */
	public static final String NAME 				   = "attributeName" ;
	
	/** Constant - Alias */
	public static final String ALIAS 				   = "attributeName" ;
	
	/** Constant - Required */
	public static final String REQUIRED 		 	   = "true" ;
	
	/** Constant - Type */
	public static final String TYPE 			       = "string" ;
	
	/** Constant - Format */
	public static final String FORMAT 			       = "format" ;
	
	/** Constant - Auto Injected */
	public static final String AUTO_INJECTED	  	   = "false" ;
	
	/** Constant - Media Type - application/json */
	public static final String MEDIA_TYPE_APP_JSON 	   = "application/json" ;
	
	/** Constant - Schemes */
	public static final Set<Scheme> SCHEMES  	   	   = new HashSet<Scheme>(Arrays.asList(Scheme.SCHEMA_HTTP)) ;
	
	/** Constant - Consumes */
	public static final Set<String> CONSUMES_APP_JSON  = new HashSet<String>(Arrays.asList(MEDIA_TYPE_APP_JSON)) ;
	
	/** Constant - Consumes */
	public static final Set<String> CONSUMES_MULTIPAR  = new HashSet<String>(Arrays.asList(ConstantsInput.CON_MULTIPART_FORM_DAT)) ;
	
	/** Constant - Produces */
	public static final Set<String> PRODUCES_APP_JSON  = new HashSet<String>(Arrays.asList(MEDIA_TYPE_APP_JSON)) ;
	
	/** Constant - Reference - Parameter */
	public static final String REFERENCE_PARAMETER 	   = "#/parameters/Player" ;
	
	/** Constant - Reference - Definitions */
	public static final String REFERENCE_DEFINITIONS   = "#/defintions/Player" ;

	/** Constant - Generation package */
	public static final String GENERATION_PACKAGE 	   = "test.example" ;
	
	/** Constant - Deprecated */
	public static final boolean DEPRECATED		 	   = false ;
	
	/** Constant - Business Unit */
	public static final String BUSINESS_UNIT 	  	   = "uuaa" ;
	
	/** Constant - Architecture type */
	public static final String ARCHITECTURE_TYPE 	   = "architectureType" ;
	
	/** Constant - CXF Address */
	public static final String CXF_ADDRESS 	  		   = "cxfAddress" ;
	
	/** Constant - CXF Context */
	public static final String CXF_CONTEXT 	  		   = "cxfContent" ;
	
	/** Constant - Title */
	public static final String PROJECT_TITLE 		   = "projectTitle" ;
	
	/** Constant - Description */
	public static final String PROJECT_DESCRIPTION     = "description" ;
	
	/** Constant - Version */
	public static final String PROJECT_VERSION 	       = "1.0.0" ;
	
	/** Constant - Node name */
	public static final String NODE_NAME			   = "nodeName" ;
	
	/** Constant - Field name */
	public static final String FIELD_NAME			   = "fieldName" ;
	
	@Test
	public void invokeConstructor() throws Exception 
	{
		Whitebox.invokeConstructor(ConstantsTest.class, new Object[0]);
		Assert.assertTrue(true);
	}
}
