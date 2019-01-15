window.onload = initAll;

var country,state,city,pincode;
//var form_data;
var _response;
var td_pincode;
var alternateSelectTag;
var fakeUrl = "contactdetails.do";

var localpermanent,local,lp,originalLocalStyle;


function initAll(){

	form_data = document.getElementsByClassName("form_data");
	country = document.getElementById("country");
	state = document.getElementById("state");
	city = document.getElementById("city");
	pincode = document.getElementById("pincode");

	td_pincode = document.getElementById("td_pincode");

	console.log(alternateSelectTag = document.getElementsByTagName("select"));
	console.log(localpermanent = document.getElementById("localpermanent"));
	console.log(local = document.getElementsByClassName("local"));
	console.log(lp = document.getElementsByClassName("lp"));
	originalLocalStyle = new Array(local.length);

	setAllAction();
	
	//calling extra.js methods
	initTd();
	createExtraElements();
	setActionsOfExtraElements();
	initAllForms();

}


function setAllAction(){
	country.onchange = state.onchange = city.onchange = onChange;
	pincode.onkeyup = pinKeyUp;

	for(j=0;j<alternateSelectTag.length;j++){
		console.log(regex = alternateSelectTag[j].id);
		if(regex.charAt(0) == 'l'){
			console.log(regex = regex.substr(1));
			console.log(arrayListParser(alternateSelectTag[j].innerHTML,"option",alternateSelectTag[j],regex));
		}
	}
	localpermanent.onclick = localCheck;
}

function localCheck(){
	if(localpermanent.checked){
		console.log("checked");
		localHide();
	}else{
		console.log("unchecked");
		localShow();
	}
}

function localHide(){
	for(i = 0 ; i< local.length; i++)
		originalLocalStyle[i] = "";
	for(i = 0; i < local.length; i++){
		console.log(local[i]);
		//console.log(originalLocalStyle[i] = local[i].style);
		//console.log(local[i].style.display);
		console.log(originalLocalStyle[i] = local[i].getAttribute("class"));
		//local[i].className = "localhide";
		local[i].style.display = "none";
	}
}

function localShow(){
	for(i = 0;i <local.length ; i++){
		console.log(local[i]);
		//console.log(local[i].style = originalLocalStyle[i]);
		//console.log(local[j].className = originalLocalStyle[i]);
		console.log(local[i].nodeName);
		console.log(a = local[i].className,a.indexOf("td_report") > -1);

		if(local[i].className.indexOf("td_report") > -1)
			local[i].style.display = "none";
		else if(local[i].nodeName == "TD")
			local[i].style.display = "table-cell"
		else if(local[i].tagName == "TR")
			local[i].style.display = "table-row";
		else 
			local[i].style.display = "block";


	}
}


function onClickForm(){
	
}


function onChange(){
	var field = this.getAttribute("id");
	console.log(this);
	console.log(field);
	console.log(this.value);

	if(field == "country"){
		url = "locationhandler.do?countryId="+this.value;	
		field = "state";	//modified field because now we need to work on state
	}else if(field == "state"){
		url = "locationhandler.do?stateId="+this.value;
		field = "city"
	}else if(field == "city"){
		/*
		url = "locationhandler.do?cityId="+this.value;
		field = "pin"
		*/
	}	

	//console.log(field+" changed");
	console.log(url);
	sendAjaxRequestContactDetails(url,field);
}



function sendAjaxRequestContactDetails(url,field){

	var requestObject = createRequestObject();
	requestObject.open("get",url,true);
	requestObject.onreadystatechange = function(){
											if(requestObject.readyState == 4 && requestObject.status == 200){
												if(_response = requestObject.responseText){
													console.log("successful trip : "+_response);
													setData(_response,field);
												}
											}
										};
	requestObject.send(null);
}


function setData(_response,field){
	//console.log(_response+field);
	var obj,childElement,childElementName;
	console.log(obj = eval("("+_response+")"));

	if( field == "pincode" && pincode.value.length!=0){
		
		//field is pin code so we create div tag to append the results into the fields
		childElementName = "div";
//		field_tag = td_pincode;
		field_tag = document.getElementById("pincode_div");

	}else{

		//field is either country or state or city
		//so we create an option tag for appending it into the select tag
		childElementName = "option";
		field_tag = form_data[field];	
		//here we get field_tag = state(select tag) when country is changed
		//similarly we get field_tag = city(select tag) when state is changed
		
	}

	console.log(field_tag);
	field_tag.innerHTML = "";
	//empty the previous data
	console.log("previous data cleared");


	for(i = 0 ; i < obj.length ; i++){
		childElement = document.createElement(childElementName);
		childElement.value = obj[i].id;
		childElement.innerHTML = obj[i].name;
		childElement.className = "pincode_divchild";
		childElement.onclick = onclickPinCode;
		childElement.onmouseover = function(){this.style.backgroundColor = "#FFCFFF";};
		childElement.onmouseout = function(){this.style.backgroundColor = "white";}
		field_tag.appendChild(childElement);
		if(field == "country"){
			sendAjaxRequestContactDetails("locationhandler.do?countryId="+country.value,"state");
		}else if(field == "state"){
			sendAjaxRequestContactDetails("locationhandler.do?stateId="+state.value,"city");
		}
		console.log(childElement);
	}
}


function pinKeyUp(){
	var url;
	console.log(this.value);
	pincode_div.innerHTML = ""; //to empty the previous data record from div from 
	console.log(url = "locationhandler.do?pincode="+this.value);
	sendAjaxRequestContactDetails(url,this.getAttribute("id"));
}
function onclickPinCode(){
	console.log(pincode);
	pincode.value = this.innerHTML;
	pincode_div.innerHTML = "";
}