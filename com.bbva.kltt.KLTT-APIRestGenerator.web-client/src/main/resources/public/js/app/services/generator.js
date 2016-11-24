/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var generatorService = angular.module('generatorService', []) ;
generatorService.factory('generatorService', ['codeEditorService', function (codeEditorService)
{
	var instance = {} ;
	
	instance.generateLanguage = function(generatorAddress, userName, fileName, languageName)
	{
		var specificationContent = codeEditorService.getFullCodeText() ;
		
  		var xmlHttpRequest = new XMLHttpRequest() ;

        var formData = new FormData();
        formData.append("specificationContent", specificationContent) ;
		
		xmlHttpRequest.open('POST', generatorAddress + "api/generate/" + userName + "/swagger/" + languageName + "?fileName=" + fileName, true) ;
		xmlHttpRequest.responseType = "blob" ;
		xmlHttpRequest.withCredentials = false ;
		
		xmlHttpRequest.onreadystatechange = function ()
		{
		    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
		    {
		        var blob = xmlHttpRequest.response ;
		        saveAs(blob, "deliverables.zip") ;
		    }
		};
		
		xmlHttpRequest.send(formData) ;
	}

	return instance ;
}]) ;