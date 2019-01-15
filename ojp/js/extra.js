
var td_update,td_reset,td_cancel,td_message,originalClass;
var extra,originalData,originalClass;
var selectTagElements;
var td_report,td_form,form_data;
var selectTag,selectInnerHTML = new Array(),selectEvaluatedObject = new Array(),selectEvaluatedObjectWithLowerCaseObjects = new Array();

function createElements(){
	//requirements :-
	//td_report containing report fields
	//form_data are form fields such as input select or textarea
	//td_form containing all form_data field

	initTd();					//this function initizalizes gets all td and form_data elements as a collection
	createExtraElements();		//this function creates 4 tdelements update,rest,cancel,message
	setActionsOfExtraElements();//this function sets action of the exta elements
	initAllForms();				//this function initializes the form elements with the data in report elements
	initializeSelect();			//this function gets all the select elements and calls arraylistparser on it with option tag as parameter
}

function createExtraElements(){

	for(i = 0;i<td_form.length;i++){
		try{
			var field;
			console.log(field = td_form[i].children[0].getAttribute("id"));
			extraField = field;
			
			td_update = document.createElement("td");
			td_reset = document.createElement("td");
			td_cancel = document.createElement("td");
			td_message = document.createElement("td");

			td_update.id = "update_"+field;
			td_reset.id = "reset_"+field;
			td_cancel.id = "cancel_"+field;
			td_message.id = "message_"+field;
			
			td_update.className = "td_update extra extra_"+field;
			td_reset.className = "td_reset extra extra_"+field;
			td_cancel.className = "td_cancel extra extra_"+field;
			td_message.className = "td_message extra extra_"+field;

			td_update.onclick = updateClick;
			td_reset.onclick = resetClick;
			td_cancel.onclick = cancelClick;
			//td_message.onclick = messageClick;

			td_form[i].parentElement.appendChild(td_update);
			td_form[i].parentElement.appendChild(td_reset);
			td_form[i].parentElement.appendChild(td_cancel);
			td_form[i].parentElement.appendChild(td_message);
		}
		catch(error){
			console.log(error);
		}
	}
}




function initializeSelect(){

	for(j = 0 ;j < selectTag.length ;j++){
		console.log(selectTag[j]);
		console.log("calling : " + j);
		selectInnerHTML[j] = selectTag[j].innerHTML;
		console.log(selectEvaluatedObjectWithLowerCaseObjects[selectTag[j].id] = eval(selectInnerHTML[j].toLowerCase()));
		console.log(selectEvaluatedObject[selectTag[j].id] = eval(selectInnerHTML[j]));
		//saving all the objects and its properties in the form of attribute of the select tag
		console.log(selectEvaluatedObject[selectTag[j].id]);
//		selectTag[j].setAttribute("objectgraph",selectEvaluatedObject[j]);
		console.log(selectTag[j].objectGraph = selectEvaluatedObject[selectTag[j].id]);
		arrayListParser(selectInnerHTML[j],"option",selectTag[j],null);

		for(var k = 0;k < (options = selectTag[j].options).length;k++){
				for(var h=0;h < selectEvaluatedObject[selectTag[j].id].length;h++){
					console.log(_seo = selectEvaluatedObjectWithLowerCaseObjects[selectTag[j].id][h]);
					if((prop = _seo[selectTag[j].id + "id"]) == options[k].value){
						console.log("match found",options[k],prop,_seo);
						setAllProperties(_seo,selectTag[j].options[k]);
					}
				}
//			setAllProperties(selectEvaluatedObject[selectTag[j].id],selectTag[j].options[k]);
		}
	}
//	console.clear();
}

function setAllProperties(_selectEvaluatedObject,_optionTag){
	//console.log(this);
	for (var propertyName in _selectEvaluatedObject){
		if (typeof _selectEvaluatedObject[propertyName] == "object" && _selectEvaluatedObject[propertyName] !== null)
			setAllProperties.call(this,_selectEvaluatedObject[propertyName],_optionTag);
//		else if((propertyName == (_optionTag.parentNode.id + "Id")) && _selectEvaluatedObject[propertyName] == _optionTag.value){
		else{
			// do something... 
			console.log(propertyName,_selectEvaluatedObject[propertyName],_optionTag);
			_optionTag.setAttribute(propertyName,_selectEvaluatedObject[propertyName]);
		}
	}
}




function initTd(){

	console.log(td_report = document.getElementsByClassName("td_report"));
	console.log(td_form = document.getElementsByClassName("td_form"));
	console.log(form_data = document.getElementsByClassName("form_data"));
	console.log(td_label = document.getElementsByClassName("td_label"));
	console.log(selectTag = document.getElementsByTagName("select"));

	for (i=0;i<td_report.length ;i++ ){
		td_report[i].onmouseover = onOverReport;
		td_report[i].onmouseout = onOutReport;
		td_report[i].onclick = onClickReport;
	}
}




function setActionsOfExtraElements(){
	extra =  document.getElementsByClassName("extra");
	var all = document.getElementsByTagName("*");
	for(i = 0;i<extra.length;i++){
		extra[i].innerHTML = extra[i].id.replace("_"," ");
		extra[i].onmouseover = 
								function(){	
									for(i = 0;i<all.length;i++){
										//console.log(all[i]);
										//console.log(all[i].style.zIndex ="-1");	
									}
									//this.style.position ="absolute";
									//console.log(this.style.zIndex = "10000");
									originalClass = this.className;
									this.className = this.className + " overextra";
								};
		extra[i].onmouseout = 
								function(){
									for(i = 0;i<all.length;i++){
							
									}
									//this.style.position = "relative";
									this.className = originalClass;
								};
	}
}




function initAllForms(){

	//this function is meant to fill the text from the report field onto the form field
	try{
		for(i=0;i<form_data.length;i++){
			try{
				if(form_data[i].id != "update"){
					console.log(form_data[i]);
					console.log(td_report[i]);	
					console.log(form_data[i].tagName);
					if(form_data[i].nodeName == "SELECT"){
						//console.log(form_data[i]);
						//console.log(td_report[i]);
						console.log(form_data[i].value = td_report[i].getAttribute("value").trim());
						//fill value with the id of the city,state,country in the database
					}else if(form_data[i].tagName == "INPUT" && form_data[i].type == "checkbox"){
						console.log(form_data[i]);
						//alert("checkbox");
					}else{
						//console.log(form_data[i]);
						//console.log(td_report[i]);
						console.log(form_data[i].value = td_report[i].innerHTML.trim());
					}
				}
			}
			catch(error){
				console.log(error.message);
			}
		}
	}
	catch(error){
		console.log(error.message);
	}
}













function updateClick(){

	//requirements :-
	//fakeurl isthe fakeurl name that is mapped to the servlet
	var field,originalData;
	console.log(this);
	console.log(field = this.id.replace("update_",""));
	console.log(form_data[field].value);

	url = fakeUrl+"?code=1&";
	for(i=0;i<form_data.length;i++){
		if(form_data[i].id != "update"){
			console.log(form_data[i]);
			console.log(form_data[i].value);
			console.log(url =url + form_data[i].name + "=" + form_data[i].value);
			if((form_data.length - 2) != i){
				console.log(form_data.length + " != " + i);
				url = url + "&";
			}
		}
	}


	//be very carefull while using sendAjaxRequest and refer
	//http://stackoverflow.com/questions/13286233/pass-a-javascript-function-as-parameter
	//http://stackoverflow.com/questions/1959040/is-it-possible-to-send-a-variable-number-of-arguments-to-a-javascript-function

	sendAjaxRequest("get",url,true,null,updateFieldData,this);
	
}
function updateFieldData(updateField,responseText){
	try{
		console.log(updateField);
		console.log(responseText);
		console.log(field = updateField.id.replace("update_",""));

		console.log(td_report["_" + field].style.display = "block");
		console.log(form_data[field].parentElement.style.display = "none");
		console.log(extra["update_"+field].style.display = "none");
		console.log(extra["reset_"+field].style.display = "none");
		console.log(extra["cancel_"+field].style.display = "none");
		if(form_data[field].tagName == "SELECT")
			console.log(td_report["_" + field].innerHTML = (form_data[field].children[form_data[field].selectedIndex]).innerHTML);
		else
			console.log(td_report["_" + field].innerHTML = form_data[field].value);

	}
	catch(error){
		console.log(error.message);
		console.error();
	}
}

function resetClick(){
	console.log(this);
	console.log(field = this.id.replace("reset_",""));
	console.log(originalData = td_report["_"+field].innerHTML.trim());
	console.log(form_field = form_data[field]);		
	console.log(form_field.tagName != "SELECT");

	if(form_field.tagName == "SELECT"){
		console.log(_options = form_field.children);
		for(i=0;i<_options.length;i++){
			console.log(_options[i]);
			console.log(_options[i].innerHTML + "==" + originalData+ " : " + (_options[i].innerHTML == originalData));

			if(_options[i].innerHTML.trim() === originalData.trim()){
				console.log(form_field.selectedIndex = _options[i].index);
			}
		}
	}else if(form_field.tagName == "INPUT" && form_field.type == "checkbox"){

		console.log(rep = td_report["_" + field]);
		console.log(repValue = rep.getAttribute("value"));
		console.log(repValue + " === null ----> " + (typeof(repValue === null)));
		if(repValue == "" || repValue == null){
			console.log(form_field.checked = false);
		}else{
			console.log(form_field.checked = true);
		}

	}else{
		form_field.value = originalData;
	}
}



function cancelClick(){
	console.log(this);
	console.log(field = this.id.replace("cancel_",""));
	var fieldObjects = document.getElementsByClassName("extra_"+field);
	for(i=0;i<fieldObjects.length;i++){
		fieldObjects[i].style.display = "none";
	}
	form_field = form_data[field];
	form_field.parentElement.style.display = "none";
	report_field = td_report["_"+field];
	report_field.style.display = "block";
}

function sendAjaxRequest(type,url,async,payload,performAction,field){
	//field is the field or button on which this evetn gets triggered

	requestObject = createRequestObject();
	requestObject.open(type,url,async);
	console.log("url : "+url);
	console.log("type : "+type);
	console.log("async : "+async);
	console.log("payload :"+payload);
	console.log("field :");
	console.log(field);
	requestObject.onreadystatechange = 
										function(){
											if(requestObject.readyState == 4 && requestObject.status == 200){
												//if(requestObject.reponseText){
													console.log("update succcessful"+requestObject.responseText);
													console.log(this);
													performAction(field,requestObject.responseText);
													//performAction.Apply(this,arr);
												//}
											}
										};

	requestObject.send(payload);
}



function onOverReport(){
	//this function is meant for actions to be performed onmouseover of report 
	this.style.color = "red";
	console.log(this);
	console.log(field = this.id.replace("_",""));
	console.log(fieldObject = extra["message_"+field]);
	fieldObject.innerHTML = "click to edit "+field;
	fieldObject.style.display = "block";
}

function onOutReport(){
	//this function is meant for actions to be performed onmouse out of report
	this.style.color = "black";
	console.log(field = this.id.replace("_",""));
	console.log(fieldObject = extra["message_"+field]);
	fieldObject.style.display = "none";
//	console.clear();
}

function onClickReport(){
	//function for action on clicking the report field

	console.log(this);
	console.log(field = this.id.replace("_",""));
	
	console.log(fieldObject = document.getElementsByClassName("extra_" + field));
	
	for(i=0;i<fieldObject.length-1;i++){
		fieldObject[i].style.display = "block";
	}
	
	this.style.display = "none";

	form_data[field].style.display = form_data[field].parentElement.style.display = "block";

	
	//td_extra.style = "none";
}

function contains(baseString,stringToBeMatched,splitString){
	/*
	var pieces;
	console.log(baseString  + " --- " + stringToBeMatched + " --- " + splitString);
	console.log(pieces = baseString.split(splitString));
	for(var z = 0;z<baseString.split(splitString).length;z++){
		console.log(baseString.split(splitString)[z]);
		console.log(baseString.split(splitString)[0]);
		console.log(baseString.split(splitString)[1]);
		console.log(baseString.split(splitString).length);
		var flag = false;
		console.log(baseString.split(splitString)[z] + " == " + stringToBeMatched);
		if(baseString.split(splitString)[z] == stringToBeMatched){
			console.log(" match found : " + stringToBeMatched + " in " + baseString);
			flag = true;
		}
		return flag;
	}
	*/
	if(baseString.indexOf(stringToBeMatched)>-1 || this.indexOf(stringToBeMatched))
		return true;
	else
		return false;
}

function fitIframeSize(){
	//function for setting iframe of the size of the content inside it
	console.log(iframes = document.getElementsByTagName("iframe"));
	for(var i = 0;i < iframes.length ; i++){
		console.log(iframes[i].parentNode.height = iframes[i].height = iframes[i].contentWindow.document.body.scrollHeight);
		console.log(iframes[i].parentNode.width = iframes[i].width = iframes[i].contentWindow.document.body.scrollWidth);
	}
}


function setIframeValue(){
	console.log(iframeField = document.querySelectorAll("iframe"));
	for(var i=0;i<iframeField.length;i++){
		console.log(splittedValues = iframeField[i].contentDocument.querySelector("input[type='file']").value.split("\\"));
		console.log(iframeField[i].value = splittedValues[splittedValues.length - 1]);
		console.log(iframeSubmit = iframeField[i].contentDocument.querySelector("input[type='submit']"));
		console.log(iframeSubmit.onclick = setIframeValue);
	}
	window.onsubmit = setIframeValue;
	//return false;
}