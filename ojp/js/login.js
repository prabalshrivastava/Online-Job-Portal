window.onload = initAll;

var passwordSpan,emailSpan,password,email,login;
function initAll(){
	
	_password		=	document.getElementById("_password");
	_email			=	document.getElementById("_email");
	password		=	document.getElementById("password");
	email			=	document.getElementById("email");
	login			=	document.getElementById("login");

	password.onblur	=	passwordBlur;
	email.onblur	=	emailBlur;
	login.onclick	=	loginClick;

	password.onfocus	=	passwordFocus;
	email.onfocus		=	emailFocus;
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
			_email.innerHTML = "---> enter password "+requestObject.responseText;
		}
	}
}
function emailBlur(){
	if(this.value.length==0){
		_email.innerHTML = "email cannot be empty";
		return false;
	}else{
		_email.innerHTML="";
		ajaxRequest();
	}
}
function passwordBlur(){
	if(this.value.length==0){
		_password.innerHTML = "password cannot be empty";
		return false;
	}else{
		_password.innerHTML="";
	}
}

function loginClick(){
	var emailTest = emailBlur();
	var passwordTest = passworrdBlur();
	return emailTest && passwordTest;
}
function passwordFocus(){
	_password.innerHTML = "";
}
function emailFocus(){
	_email.innerHTML = "";
}
