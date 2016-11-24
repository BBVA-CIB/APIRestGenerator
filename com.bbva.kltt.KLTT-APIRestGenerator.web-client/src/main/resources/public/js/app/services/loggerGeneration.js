/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var loggerGenerationService = angular.module('loggerGenerationService', []) ;
loggerGenerationService.factory('loggerGenerationService', function ()
{
	var instance    	 = {} ;
	
	instance.userId		 = '' ;
	instance.socket 	 = '' ;
	instance.stompClient = '' ;
	instance.parentScope = '' ;
	instance.generatorAddress = '' ;
	
	instance.initialization = function(parentScope, generatorAddress)
	{
		// Firstly, we will get the user id from the server
		var xmlHttpRequest = new XMLHttpRequest() ;

		this.generatorAddress = generatorAddress ;
		
		xmlHttpRequest.open('GET', generatorAddress + "api/getUserId", false) ;
		
		xmlHttpRequest.send(null) ;
		
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
		{
			this.userId = xmlHttpRequest.responseText ;
			this.subscribeToTopic(parentScope) ;
		}
	} ;
	
	instance.subscribeToTopic = function(parentScope)
	{
		this.parentScope = parentScope ;
		
		// Start the connection
		instance.startConnection() ;
	}
	
	instance.startConnection = function()
	{
		console.log("Starting the websocket connection with the server! userId: '" + instance.userId + "'") ;
		
		this.socket 	 = new SockJS(this.generatorAddress + 'myStompEndPoint');
		this.stompClient = Stomp.over(this.socket) ;
		this.stompClient.connect({}, function(frame)
							         {
										instance.stompClient.subscribe('/topic/'+ instance.userId + '/job', function(jobMessage)
							            {
							            	instance.parentScope.loggingGeneration = instance.parentScope.loggingGeneration + jobMessage.body ;
							            	instance.parentScope.$applyAsync() ;
							            });
							         },
								     function(error)
								     {
							        	 var timeout = 1000 ;
								        console.log("Unexpected close connection '" + error + "'. Trying to reconnect in " + timeout + " milliseconds") ;
								        setTimeout(instance.startConnection(), timeout) ;
								     }
		);
	}
	
	instance.getUserId = function()
	{
		return this.userId ;
	}
	
	return instance ;
}) ;