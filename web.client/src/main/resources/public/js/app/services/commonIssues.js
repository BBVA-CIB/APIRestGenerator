/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var commonIssuesService = angular.module('commonIssuesService', []) ;
commonIssuesService.factory('commonIssuesService', function ()
{
	var instance    	 = {} ;
	
	instance.get = function()
	{
		return [
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "jax-rs",
		        		type: "restriction",
		        		summary: "PATCH is not supported in JAX-RS",
		        		description: "This type of operations is not supported in the project"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "server",
		        		flavour: "jax-rs",
		        		type: "restriction",
		        		summary: "@MatrixParam is not supported in JAX-RS",
		        		description: "This type of annotation is not supported in the project, please use @QueryParam instead of @MatrixParam"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The Business Unit is invalid, it must match with the following Regular Expression: [A-Za-z]{4}",
		        		description: "The Business Unit value must match with the previous regular expression"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "restriction",
		        		summary: "Cannot upload/download multiple files in one parameter: <PARAMETER_NAME>",
		        		description: "This is a restriction for this project Try to send/receive using differente parameters"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "server",
		        		flavour: "jax-rs",
		        		type: "error",
		        		summary: "The pathValue '<PATH_VALUE>' must match with the following pattern: [a-zA-Z0-9]+",
		        		description: "JAXRS Server flavour was generated to be compatible with ePhoenix and this architecture cannot return any file"
		        	},		        	
		        	{
		        		version: "1.0.0",
		        		side: "server",
		        		flavour: "jax-rs",
		        		type: "error",
		        		summary: "JAXRS cannot contains any return value as 'file' type",
		        		description: "JAXRS Server flavour was generated to be compatible with ePhoenix and this architecture cannot return any file"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "server",
		        		flavour: "jax-rs",
		        		type: "error",
		        		summary: "JAXRS cannot contains any input parameter as 'file' type",
		        		description: "JAXRS Server flavour was generated to be compatible with ePhoenix and this architecture cannot receive any file"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "server",
		        		flavour: "jax-rs",
		        		type: "error",
		        		summary: "JAXRS cannot contains any input query parameter as 'array' type",
		        		description: "JAXRS Server flavour was generated to be compatible with ePhoenix and this architecture cannot receive any arrays in the query parameters"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "BuildException while calling the ANT task with the following parameters: [antFile: <ANT_FILE>, filePath: <FILE_PATH>]",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Error while merging the template in output file",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Error while closing the bufferedWriter related to the template in output file",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Error while closing the fileWriter related to the template in output file",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Invalid file path of the specification content: <FILE_PATH>",
		        		description: "This is an internal Generator error, please contact us"
		        	},		        	
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Error parsing command line arguments",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "CodeGen output directory is null or empty: <OUTPUT_DIR>",
		        		description: "This is an internal Generator error, please contact us"
		        	},		        	
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The parsed information provided is null",
		        		description: "There is no content in the attached file"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The parsed information does not contain any path to generate",
		        		description: "There is no content in the attached file"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Error parsing command line arguments",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The following attribute 'name' was not defined properly to be a class attribute: <ATTRIBUTE_NAME>",
		        		description: "The attribute must match with the following regular expression '[A-Za-z0-9_]+'"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The following 'name' was defined but it is a reserved word in a specific language: <ATTRIBUTE_NAME>",
		        		description: "The attribute must be different than reserved words in the language"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The 'name' field (with the value '<ATTRIBUTE_NAME>') needs some (or both) of the following types in the consumes definition: 'multipart/form-data', 'application/x-www-form-urlencoded'",
		        		description: "The formData parameter which misses the consumes definition must have the previous Media Types"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The 'name' field '<FIELD_NAME>' must not contain a null 'type'",
		        		description: "The body parameter must have a 'type' property defined"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The 'name' field '<FIELD_NAME>' must contain any 'type' as following: array, boolean, integer, number, string",
		        		description: "The body parameter must have one of the previous types"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The 'name' field '<FIELD_NAME>' in PATH must contain a field 'required' as 'true'",
		        		description: "The path parameter must contain the a field 'required' as 'true'"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Error while adding a new body parameter '<PARAM_NAME>'. It is already defined a body parameter",
		        		description: "There is another body parameter with the same name"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The following reference was not defined properly to be search in the global parameters: <PARAM_REFERENCE>",
		        		description: "The value of the referenced parameter must match with the following regular expression: \\#\\/parameters\\/([a-zA-Z0-9_\\-]+)\\/?"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "'type' or 'format' values are invalid for the item values: [name: <NAME>, description: <DESC>, type: <TYPE>, format: <FORMAT>]",
		        		description: "The type or format of the previous item are invalid"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The following reference was not found in the items map: <REF>",
		        		description: "There is not any definitions with the previous reference"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The following reference was not defined properly to be search in the items map: <REF>",
		        		description: "The value of the referenced definition must match with the following regular expression: \\#\\/definitions\\/([a-zA-Z0-9_\\-]+)\\/?"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The node '<NODE_NAME>' must contain any of these attributes: type, $ref",
		        		description: "Whatever parameter or definition must contain 'type' or '$ref' property to be defined"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The node '<NODE_NAME>' must contain any of the following attributes to be defined: type or $ref",
		        		description: "Whatever parameter or definition must contain 'type' or '$ref' property to be defined"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The node '<NODE_NAME>' is using the 'type' attribute, but it should contain the following the value: object",
		        		description: "The previous node name must be an object"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The parameter 'in' is invalid. The expected values are one of the following: query, header, path, formData",
		        		description: "The 'in' parameter must be one of the previous values"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The node '<KEY_DEFINITION>' contains an invalid '$ref' at the first level",
		        		description: "Parameter cannot have a $ref value at the first level"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The node '<KEY_PATH>' must start with a slash (/) character",
		        		description: "The previous <KEY_PATH> value must start with a slash character"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The path '<PATH_VALUE>' has the following missed path values in the '<PATH_OPERATION_TYPE>' operation: <OPERATION>",
		        		description: "The swagger specification was not defined properly"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The path '<PATH_VALUE>' cannot have body and formData parameters together in the common path parameters",
		        		description: "Body and FormData parameters are incompatible in the same request"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The path '<PATH_VALUE>' cannot have body and formData parameters together in the '<PATH_OPERATION_TYPE>' operation",
		        		description: "Body and FormData parameters are incompatible in the same request"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The path '<PATH_VALUE>' cannot multiple body parameters in the '<PATH_OPERATION_TYPE>' operation",
		        		description: "There is only one body parameter per request"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "There are two parameters in the path '<PATH_VALUE>' with the same name '<KEY_NAME>' and differents types: <TYPE_ONE> <TYPE_TWO>",
		        		description: "Two parameters cannot have the same name and different types"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "restriction",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Head operation in the path '<PATH_VALUE>' must not have any response due to W3 Specification",
		        		description: "Head operations cannot have responses"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "restriction",
		        		flavour: "all",
		        		type: "error",
		        		summary: "<PATH_OPERATION_TYPE> operation in the path '<PATH_VALUE>' must not have any body parameter due to W3 Specification",
		        		description: "W3 Restriction"
		        	}, 
		        	{
		        		version: "1.0.0",
		        		side: "restriction",
		        		flavour: "all",
		        		type: "error",
		        		summary: "<PATH_OPERATION_TYPE> operation in the path '<PATH_VALUE>' must not have any form data parameter due to W3 Specification",
		        		description: "W3 Restriction"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The path '<PATH_VALUE>' (operation '<PATH_OPERATION_TYPE>') needs a responses array defined",
		        		description: "Response node is compulsory for whatever path"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The path '<PATH_VALUE>' (operation '<PATH_OPERATION_TYPE>') needs a key response defined",
		        		description: "Response node must contain any key response"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The following reference was not found in the responses map: <RESPONSE_REF>",
		        		description: "The previous reference was not found in the response map"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The following reference was not defined properly to be search in the responses map: <RESPONSE_REF>",
		        		description: "The value of the referenced response must match with the following regular expression: \\#\\/responses\\/([a-zA-Z0-9_\\-]+)\\/?"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The following reference was not defined properly to be search in the responses map: <RESPONSE_REF>",
		        		description: "The value of the referenced response must match with the following regular expression: \\#\\/responses\\/([a-zA-Z0-9_\\-]+)\\/?"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The path '<PATH_VALUE>' ('<PATH_OPERATION_TYPE>' operation) needs a defined response with code 200",
		        		description: "The minimum number of response defined is one with the key code 200"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The path '<PATH_VALUE>' ('<PATH_OPERATION_TYPE>' operation) cannot contain a response with an array of files",
		        		description: "Only one file can be sent as response of a request"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The title is invalid, it must match with the following Regular Expression: [A-Za-z][A-Za-z0-9_]+",
		        		description: "The title of the specification must match with the previous regular expression"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The current file has not a valid extension to be parsed: <FILE_PATH>",
		        		description: "The file extension must be 'json', 'yml' or 'yaml'"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "IOException when reading the file: <EXCEPTION>",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The field name '<FIELD_NAME>' is required in the node: <NODE_NAME>",
		        		description: "The previous field name is required in the node"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "The content of the field '<FIELD_NAME>' is required in the node: <NODE_NAME>",
		        		description: "The previous field content name is required in the node"
		        	},
		        	
		        	
		        	
		        	
		        	
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Error creating directory tree for dir: <DIRECTORY>",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "FileNotFoundException while copying the following source file: <EXCEPTION>",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "IOException while copying the file to the following destination: <EXCEPTION>",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "IOException while closing the input stream after copied the file",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "IOException while closing the output stream after copied the file",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Could not read <LOCATION> from the classpath to get the content",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "Could not find <LOCATION> on the classpath to get the content",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "IOException while removing the temporary directory: <DIRECTORY>",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "IOException when reading the file '<FILE_PATH>': <EXCEPTION>",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "both",
		        		flavour: "all",
		        		type: "error",
		        		summary: "IOException when reading the content file : <LIST_OF_PROPERTIES> with the following exception: <EXCEPTION>",
		        		description: "This is an internal Generator error, please contact us"
		        	},
		        	{
		        		version: "1.0.0",
		        		side: "client",
		        		flavour: "javascript",
		        		type: "error",
		        		summary: "Are you getting an error but the code error is 200 and the outcome seems correct?",
		        		description: "This issue happens when your response is a String and you wrote 'application/json' as 'produces' in your SPEC. You must remove the 'application/json' and everything will work properly"
		        	}
		       ] ;
	} ;
	
	return instance ;
}) ;