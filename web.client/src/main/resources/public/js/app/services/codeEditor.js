/**
 * "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements;
 *  and to You under the Apache License, Version 2.0. "
 */
var codeEditorService = angular.module('codeEditorService', []) ;
codeEditorService.factory('codeEditorService', function ()
{
	var instance 		 	 = {} ;
	
	instance.textAreaClass   = ".code-wrappers" ;
	
    instance.update          = null  ;
    instance.onUpdate        = null  ;
    instance.defaultLanguage = ''    ;
	instance.linenumbers     = false ;
	instance.textarea		 = ''    ;
	instance.highlightCode	 = ''    ;
	instance.highlightPre    = ''    ;
	
	instance.initialization  = function(language)
	{
		instance.runInternal(instance.textAreaClass, { linenumbers: true, language: language}) ;
	}
	
	instance.runInternal = function(selector, opts)
	{
	    var target = document.querySelectorAll(selector);
	
	    if(target.length > 1)
	    {
	        throw 'ERROR: runInternal() expects only one element, ' + target.length + ' given' ;
	    }
	    
	    this.scaffold(target[0], opts) ;
	}

	instance.scaffold = function(target, opts)
	{
	    this.textarea      	 = document.createElement('TEXTAREA') ;
	    this.textarea.classList.add('CodeFlask__textarea') ;
	    this.textarea.value  = target.textContent ;
	    
	    this.highlightPre  	 = document.createElement('PRE') ;
	    this.highlightPre.classList.add('CodeFlask__pre') ;
	    
		this.highlightCode 	 = document.createElement('CODE') ;
		this.highlightCode.classList.add('CodeFlask__code') ;
		
		// Set the language
	    opts.language 		 = this.handleLanguage(opts.language) ;
	    this.defaultLanguage = target.dataset.language 	  || opts.language    || 'markup' ;
		this.highlightCode.classList.add('language-' + this.defaultLanguage) ;
		
		// Fixing iOS "drunk-text" issue
	    if(/iPad|iPhone|iPod/.test(navigator.platform))
	    {
	        this.highlightCode.style.paddingLeft = '3px' ;
	    }
		
		this.linenumbers 	 = target.dataset.linenumbers || opts.linenumbers || false ;
	
	    target.classList.add('CodeFlask') ;
	    
	    if(this.linenumbers)
	   	{
	        target.classList.add('CodeFlask__linenumbers') ;
	    }
	
	    // Appending editor elements to DOM
	    target.innerHTML = '' ;
	    target.appendChild(this.textarea) ;
	    target.appendChild(this.highlightPre) ;
	    
	    this.highlightPre.appendChild(this.highlightCode) ;
	
	    // Render initial code inside tag
	    this.renderOutput(this.highlightCode, this.textarea) ;
	
	    Prism.highlightAll();
	
	    this.handleInput(this.textarea, this.highlightCode, this.highlightPre) ;
	    this.handleScroll(this.textarea, this.highlightPre) ;
	
	}

	instance.renderOutput = function(highlightCode, input)
	{
	    highlightCode.innerHTML = input.value.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;") + "\n" ;
	}
	
	instance.handleInput = function(textarea, highlightCode, highlightPre)
	{
		var self		  = this ;
	    var input		  = '' ;
	    var selStartPos   = '' ;
		var inputVal	  = '' ;
	    var roundedScroll = '' ;
	
	    this.textarea.addEventListener('input', function(e)
	    {
	    	input = this ;
	    	
	        self.renderOutput(highlightCode, input) ;
	
	        Prism.highlightAll() ;
	    });
	
	    this.textarea.addEventListener('keydown', function(e)
	    {
	    	input = this ;
	        selStartPos = input.selectionStart ;
	        inputVal = this.value ;
	
	        // If TAB pressed, insert four spaces
	        if(e.keyCode === 9)
	        {
	            input.value          = inputVal.substring(0, selStartPos) + "    " + inputVal.substring(selStartPos, input.value.length) ;
	            input.selectionStart = selStartPos + 4 ;
	            input.selectionEnd   = selStartPos + 4 ;
	            e.preventDefault() ;
	
	            highlightCode.innerHTML = input.value.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;") + "\n" ;
	            Prism.highlightAll() ;
	        }
	    });
	}
	
	instance.handleScroll = function(textarea, highlightPre)
	{
	    textarea.addEventListener('scroll', function()
	    {
	        roundedScroll = Math.floor(this.scrollTop) ;
	
	        // Fixes issue of desync text on mouse wheel, fuck Firefox.
	        if(navigator.userAgent.toLowerCase().indexOf('firefox') < 0) 
	        {
	            this.scrollTop = roundedScroll ;
	        }
	
	        highlightPre.style.top = "-" + roundedScroll + "px" ;
	    });
	}
	
	instance.handleLanguage = function(lang)
	{
		var outcome = lang ;
	    if(lang.match(/html|xml|xhtml|svg/))
	    {
	        outcome = 'markup' ;
	    } 
	    else  if(lang.match(/js/))
	    {
	        outcome = 'javascript';
	    }
	    
	    return outcome ;
	}
	
	instance.onUpdate = function(cb)
	{
	    if(typeof(cb) != "function")
	    {
	    	throw 'CodeFlask.js ERROR: onUpdate() expects function, ' + typeof(cb) + ' given instead.' ;
	    }
	    
	    this.textarea.addEventListener('input', function(e) { cb(this.value) ; });
	}
	
	instance.update = function(string)
	{
	    var event = document.createEvent("HTMLEvents") ;
	
	    this.textarea.value = string ;
	    this.renderOutput(this.highlightCode, this.textarea) ;
	    Prism.highlightAll() ;
	
	    event.initEvent("input", false, true) ;
	    this.textarea.dispatchEvent(event) ;
	}
	
	instance.getFullCodeText = function()
	{
		return this.textarea.value ;
	}
	
	instance.addInitialCode = function(initialCode)
	{
		var textAreaRef = document.querySelector(instance.textAreaClass) ;
		angular.element(textAreaRef).html(initialCode) ;
	}
	
	return instance ;
}) ;
