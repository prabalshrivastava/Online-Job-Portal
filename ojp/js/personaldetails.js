window.onload = initAll;


var username,email,password,usertype,dob,gender,fname,mname,photo,marital,nationality,update;
var _confirmpassword;
var form_data,report_data,user_data,message,td_form,td_update_reset_cancel;
var original_classname,originalData;
function initAll(){
	getAllElements();
	setAllActions();
}

function getAllElements(){
	username = document.getElementById("username");
	email = document.getElementById("email");
	password = document.getElementById("password");
	usertype = document.getElementById("usertype");
	dob = document.getElementById("dob");
	gender = document.getElementById("gender");
	fname = document.getElementById("fname");
	mname = document.getElementById("mname");
	photo = document.getElementById("photo");
	marital = document.getElementById("nationality");
	update = document.getElementById("update");

	_confirmpassword = document.getElementById("_confirmpassword");
	report_data = document.getElementsByClassName("report_data");
	form_data = document.getElementsByClassName("form_data");
	user_data = document.getElementsByClassName("user_data");
	message = document.getElementsByClassName("message");
	td_form = document.getElementsByClassName("td_form");
	td_update_reset_cancel = document.getElementsByClassName("td_update_reset_cancel");
	setAllActions();
}
function setAllActions(){
	
	update.onclick = formSubmit;
	//alert(report_data.length);
	for(var i=0;i<report_data.length;i++){
		report_data[i].onmouseover = onOverReport;
		report_data[i].onmouseout = onOutReport;
		report_data[i].onclick = onClickReport;
//		alert(report_data[i].innerHTML);
	}
	for(var i=0;i<form_data.length;i++){
		form_data[i].onmouseover = onOverForm;
		form_data[i].onmouseout = onOutForm;
		form_data[i].onclick = onClickForm;
//		alert(form_data[i].innerHTML);
	}
	for(var i=0;i<user_data.length;i++){
		user_data[i].onmouseover = onOverUser;
		user_data[i].onmouseout = onOutUser;
		user_data[i].onclick = onClickUser;
//		alert(user_data[i].innerHTML);
//		alert(user_data[i].getAttribute("id"));
	}
	for(i = 0;i<td_update_reset_cancel.length;i++){
		td_update_reset_cancel[i].onclick = update_reset_cancel_onclick;
	}
	for(var i=0;i<message.length;i++){
//		message[i].o
//		alert(message[i].getAttribute("id"));
	}
	//alert(message.length);

	/*
	username.onmouseover = email.onmouseover = usertype.onmouseover = dob.onmouseover = onOver;
	username.onmouseout = email.onmouseout = usertype.onmouseout = dob.onmouseout = onOut;
	username.onclick = email.onclick = usertype.onclick = dob.onclick = click();
	*/

}
function update_reset_cancel_onclick(){
	var field = this.getAttribute("id").split("_"); 
	var update_or_reset_or_cancel = field[0];		//update_or_reset_cancel="update" update_or_reset_cancel="cancel" update_or_reset_or_cancel="reset"
	field = field[1];								//field="username" field="password" etc..
	//console.log(update_or_reset_or_cancel);
	//console.log(field);
//	console.log("inside update_reset"+this);
	var report_field = report_data[field];			//report_field=report_data["username"]
													//returns username report field
//	console.log(report_field);
//	originalData = report_field.innerHTML.trim();
//	console.log(originalData);

	if(update_or_reset_or_cancel == "update"){
		updateFieldData(field,this,report_field);		

	}else if(update_or_reset_or_cancel == "reset"){
		resetFieldData(field,this,report_field,originalData);

	}else if(update_or_reset_or_cancel == "cancel"){
		cancelFieldData(field,this,report_field,originalData);
	
	}else{
		alert("niether update nor reset nor cancel");
	}
}
function cancelFieldData(field,cancelButton,report_field,originalData){
	var form_field = form_data["_"+field];
	form_field.style.display = "none";
	form_field.parentNode.style.display = "none";
	report_field.style.display = "block";
	report_field.parentNode.style.display = "block";
	report_field.value=originalData;
}

function resetFieldData(field,resetButton,report_field){
	console.log("inside reset");
//	console.log(report_field);	
//	console.log(resetButton);
	var form_field = form_data["_"+field];
	console.log(form_field);
	console.log(originalData);
	form_field.value = originalData;
}

function updateFieldData(field,updateButton,report_field){
	console.log("inside update");
//	console.log(report_field);
//	console.log(updateButton);
	var form_field = form_data["_"+field];
	console.log(form_field.value);
	if(field!="profilepicpath")
		sendAjaxRequest(form_field);
	
}
function sendAjaxRequest(form_field){
	var requestObject = createRequestObject();
	if(requestObject){
		var name = form_field.getAttribute("name");
		var value = form_field.value;
		var url= "personaldetails.do";
		var param = name+"="+value+"&code=1";
		console.log(url+"?"+param);
		requestObject.open("POST",url,true);
//		requestObject.open("get",url,true);

//		requestObject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var boundary = "----rrrrrr----";
		requestObject.setRequestHeader("enctype","multipart/form-data; charset=utf-8; boundary=" + boundary);
//		requestObject.setRequestHeader("Content-length",param.length);
//		requestObject.setRequestHeader("connection","close");
		requestObject.onreadystatechange = sendRequest;
		requestObject.send(param);
//		requestObject.send(null);
	}
}
function sendRequest(){
	if(requestObject.readyState == 4 && requestObject.status == 200){
		if(requestObject.responseText){
			//console.log(eval("("+requestObject.responseText+")"));
			console.log("update successfull");
		}
	}
}
function formSubmit(){	
	for(i=0;i<form_data.length;i++){
		console.log(form_data[i].value);
	}
}
function onOverReport(){
	var field = this.getAttribute("name");
	//alert(field);
	if(field!="usertype"){
		var msg = message["__"+field];
		msg.style.display = "block";
		msg.innerHTML = "click to edit " + field;
		original_classname = this.getAttribute("class");
		//alert(original_classname);
		this.className = 'over';
	}
}
function onOutReport(){
//	console.log(original_classname);
	this.className = original_classname;
	var field = this.getAttribute("name");
	var msg = 	message["__"+field];
	msg.style.display = "none";
//	alert(msg.getAttribute("id"));
//	msg.style.display = "none";
//	msg.innerHTML = "";
}
function onClickReport(){
	originalData = this.innerHTML.trim();
//	alert(originalData);
	//alert(this.getAttribute("id"));
	//alert(form_data["_username"].getAttribute("name"));
	
	var field = this.getAttribute("name");
	//console.log("name of the field clicked :"+field);
	if(field!="usertype"){
		
		//Displaying form fields
		var td_form_field = td_form["td_"+field];
//		console.log(td_form_field);
		var form_field = form_data["_"+field];
		td_form_field.style.display = "block";
		form_field.style.display = "block";
		//console.log(td_form_field);		
		//console.log(form_field);

		//Hiding report fields
		var td_report = this.parentNode;
//		console.log(td_report.innerHTML);
		td_report.style.display ="none";
		if(field == "password"){
			td_report.parentNode.style.height = "100";
//			console.log(_confirmpassword.getAttribute("id"));
			_confirmpassword.style.display = "block";
		}
		td_update_reset_cancel["update_"+field].style.display = "block";
		td_update_reset_cancel["reset_"+field].style.display = "block";
		td_update_reset_cancel["cancel_"+field].style.display = "block";
	}
}

function onOverForm(){
}
function onOutForm(){
}
function onClickForm(){
}

function onOverUser(){
}
function onOutUser(){
}
function onClickUser(){
}
/*
function nameOver(){
}
function emailOver(){
}
function passwordOver(){
}
function usertypeOver()[
}

function nameOut(){
}
function emailOut(){
}
function passwordOut(){
}
function usertypeOut(){
}

*/