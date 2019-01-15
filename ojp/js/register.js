window.onload=initAll;

var userName,email,password,retypePassword,candidate,company;
var _userName,_email,_password,_retypePassword;

function initAll(){
	getAllElements();
	setAllAction();
}
function getAllElements(){
	candidate = document.getElementById("company");
	company	= document.getElementById("company");
	userName = document.getElementById("username");
	email = document.getElementById("email");
	password = document.getElementById("password");
	retypePassword = document.getElementById("retypepassword");
	register = document.getElementById("register");

	_userName = document.getElementById("_username");
	_email = document.getElementById("_email");
	_password = document.getElementById("_password");
	//alert(_password+" mohan");
	_retypePassword = document.getElementById("_retypepassword");
//	alert(_retypePassword);
}
function setAllAction(){
	userName.onblur = userNameOnBlur;
	userName.onfocus = userNameOnFocus;

	email.onblur = emailOnBlur;
	email.onfocus = emailOnFocus;

	retypePassword.onblur = retypePasswordOnBlur;
	retypePassword.onfocus = retypePasswordOnFocus;	
	
	password.onfocus = passwordOnFocus;
	password.onblur = passwordOnBlur;
	
	register.onclick = registerOnClick;
}
var requestObject = null;
function ajaxRequest(){
	requestObject = createRequestObject();
	if(requestObject){
		requestObject.open("GET","checkemail.do?email="+email.value,true);
		requestObject.onreadystatechange = checkEmail;
		requestObject.send(null);
	}
}
function checkEmail(){
	if(requestObject.readyState == 4){
		if(requestObject.status == 200){
			if(requestObject.responseText){
				_email.innerHTML = "Email already exists";
			}
		}
	}
}

function userNameOnBlur(){
	var flag = true;
	if(userName.value.length==0){
		_userName.innerHTML = "user name is required";
		flag = false;
	}else{
		_userName.innerHTML = "";
	}
	return flag;
}
function emailOnBlur(){
	var flag = true;
	if(email.value.length==0){
		_email.innerHTML = "email is required";
		flag = false;
	}else{
		_email.innerHTML = "";
		ajaxRequest();
	}
	return flag;
}

function passwordOnBlur(){
	//alert("====");
//	alert("_Password.innerHTML = "+_password);
	flag = true;

	//_password.innerHTML = "password is required";
	if(password.value.length==0){
		_password.innerHTML = "password is required";
		flag = false;
	}else{
		_password.innerHTML = "";
	}
	return flag;
//	alert(_password.innerHTML.value);
}
function retypePasswordOnBlur(){
	flag = true;
//	alert("_retypePassword.innerHTML = "+_retypePassword.innerHTML);
//	alert(_retypePassword);
	if(retypePassword.value != password.value){
		_retypePassword.innerHTML = "password's do not match" ;
		flag = false;
	}else{
		_retypePassword.innerHTML = "";
	}
}
function userNameOnFocus(){
	_userName.innerHTML = "";
}
function emailOnFocus(){
	_email.innerHTML = "";
}
function passwordOnFocus(){
	_password.innerHTML = "";
}
function retypePasswordOnFocus(){
	_retypePassword.innerHTML = "";
}

function registerOnClick(){
	var userTest = userNameOnBlur();
	var passwordTest = passwordOnBlur();
	var emailTest = emailOnBlur();
	var retypeTest = retypePasswordOnBlur();
	return userTest && passwordTest && retypePassword && emailTest ? true : false;
}
