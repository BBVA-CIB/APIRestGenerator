/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var examplesLoaderService = angular.module('examplesLoaderService', ['codeEditorService']) ;
examplesLoaderService.factory('examplesLoaderService', function (codeEditorService)
{
	var instance = {} ;
	
    instance.initialization = function(subfolder, fileName)
	{
		// Firstly, load the example file
		this.loadExampleFile(subfolder, fileName) ;
		
		// Secondly, load code flask
		codeEditorService.initialization("yaml") ;
	} ;
		
    instance.loadExampleFile = function(subfolder, fileName)
	{
		var xmlHttpRequest = new XMLHttpRequest() ;
	
		xmlHttpRequest.open('GET', "swagger_examples/" + subfolder + "/" + fileName, false) ;
		xmlHttpRequest.overrideMimeType('text\/plain; charset=x-user-defined') ;
		
		xmlHttpRequest.send(null) ;
		
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
		{
			codeEditorService.addInitialCode(xmlHttpRequest.responseText) ;
		}
	} ;
    
    return instance ;
});