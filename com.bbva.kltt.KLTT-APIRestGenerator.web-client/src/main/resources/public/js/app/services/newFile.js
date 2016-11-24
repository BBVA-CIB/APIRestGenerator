/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var newFileService = angular.module('newFileService', ['codeEditorService']) ;
newFileService.factory('newFileService', function (codeEditorService)
{
	var instance = {} ;
	
    instance.initialization = function(fileName, language)
	{
		// Firstly, verify the language to create a dummy string
		
		var initialCode = "## Let's start the YAML specification!\n\n" ;
		if (language != "yaml" && language != "yml")
		{
			initialCode = "{\n// Let's start the JSON specification!\n}" ;
		}
		
		// Add the initial code
		codeEditorService.addInitialCode(initialCode) ;
		
		// Secondly, load code flask
		codeEditorService.initialization(language) ;
	} ;
		
    return instance ;
});