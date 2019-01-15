window.onload = initAll;
var iframes,submitButton;
var fakeUrl = "skills.do";
var requestObject;
var candidateSkills;

function initAll(){
	createElements();
	console.log(iframes = document.getElementById("certificate"));	
	console.log(submitButton = iframes.contentDocument.querySelector("input[type='submit']"));
	setAllActions();
}

function setAllActions(){
	setTableReportClickEvent();
	window.onsubmit = updateSkills;
	submitButton.onclick = updateSkills;
}

function updateSkills(){
	console.log(iframes.value);
	console.log(fullPath = iframes.contentDocument.querySelector("#uploadcertificate").value.split("\\"));
	console.log(document.querySelector("#certificate").value = fileName = fullPath[fullPath.length - 1]);
	url = fakeUrl + "?";
	for(var i = 0;i<form_data.length;i++){
		url = url + form_data[i].name + "="  +  form_data[i].value + "&";
	}
	console.log(url = url + "code=1");//here certification is extra paramter that gets created
	requestObject = createRequestObject();
	requestObject.open("get",url,true);
	requestObject.onreadystatechange = 
										function(){
											if(requestObject.readyState == 4 && requestObject.status == 200){
												console.log(iframes.contentDocument.querySelector("#candidateskillid").value = requestObject.responseText.trim());
											}
										}
	requestObject.send(null);
	submitButton.click();
//	return false;
}