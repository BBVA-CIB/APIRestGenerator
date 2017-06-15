/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var generatorService = angular.module('generatorService', []) ;
generatorService.factory('generatorService', ['codeEditorService', function (codeEditorService)
{
	var instance = {} ;
	
	instance.generateTranslator = function(generatorAddress, userName, fileName, translatorType)
	{
		var specificationContent = codeEditorService.getFullCodeText() ;
		
  		var xmlHttpRequest = new XMLHttpRequest() ;

        var formData = new FormData();
        formData.append("specificationContent", specificationContent) ;
		
		xmlHttpRequest.open('POST', generatorAddress + "api/generate/" + userName + "/swagger/?fileName=" + fileName + "&translatorType=" + translatorType, true) ;
		xmlHttpRequest.responseType = "blob" ;
		xmlHttpRequest.withCredentials = false ;
		
		xmlHttpRequest.onreadystatechange = function ()
		{
		    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
		    {
		    	var lastDot = fileName.lastIndexOf(".") ;
				var deliverableFileName = fileName.substring(0, lastDot) + "-" + translatorType + ".zip" ;
		    	
		        var blob = xmlHttpRequest.response ;
		        saveAs(blob, deliverableFileName) ;
		    }
		};
		
		xmlHttpRequest.send(formData) ;
	}

	return instance ;
}]) ;