window.onload = initAll;


var wrapper,appendHere;
var cloneHTML;
var rowCount;
var fakeUrl = "qualificationdetails.do";
var save;
var iframeDoc,iframeCollection;

function initAll(){

	console.log(wrapper = document.getElementById("wrapper"));
	console.log(appendHere = document.getElementById("appendcontenthere"));
	console.log(rowCount = document.getElementById("rowcount"));
	console.log(addMore = document.getElementById("addmorebutton"));
	console.log(iframeCollection = document.getElementsByTagName("iframe"));
	console.log(options = document.getElementsByTagName("option"));
	console.log(courseType = document.getElementsByName("coursetype"));

	initTd();
	//createExtraElements();
	//setActionsOfExtraElements();
	//initAllForms();

	initializeSelect();			
	init4Rows();
	setAllActions();
	fitIframeSize();
}

function init4Rows(){
	wrapperHTML = cloneHTML = wrapper.innerHTML;
	wrapper.innerHTML = "";                            

	console.clear();

	for(var i = 0 ; i < 4;i++){
		addMoreElements();
	}

	console.log(courseType = document.getElementsByName("coursetype"));
	for(var i = 0;i<courseType.length;i++){
		console.log(id = courseType[i].id);

		if(id.indexOf("0")>-1){
			console.log(_option = "school");
			courseType[i].parentNode.innerHTML += "10th";
		}else if(id.indexOf("1")>-1){
			console.log(_option = "school");
			courseType[i].parentNode.innerHTML += "12th";
		}else if(id.indexOf("2")>-1){
			console.log(_option = "graduation");
			courseType[i].parentNode.innerHTML += "College";
		}else if(id.indexOf("3")>-1){
			console.log(_option = "postgraduation");
		}
		console.log(ct = courseType[i]);
		console.log(ct.options);
		for(var j=0;j<ct.options.length;j++){
			console.log(ct.options[j]);
			if(ct.options[j].innerHTML.toLowerCase() == _option)
				console.log(ct.selectedIndex = ct.options[j].index);
		}
	}
	

}


function setAllActions(){

	addEvents();
	window.onsubmit = function(){
									for(var i=0;i<save.length;i++)
										save[i].click();
									return false;
								};

	addMore.onclick = 
						function(){
							addMoreElements();
						}

	
}

function addEvents(){
	console.log(save = document.getElementsByName("save"));
	for(var i=0;i<save.length;i++){
		console.log(save[i]);	
		save[i].onclick = function(){	console.log(this);
										uploadFile.call(this);
										updateQualificationDetails.call(this);
									};
	}

	for(var i=0;i<selectTag.length;i++){
		selectTag[i].onchange = filterOptions;
	}
}

function filterOptions(){
	/*
	console.log(this.selectedIndex);
	console.log(nextElement = this.id);
	if(this.name == "coursetype"){
		console.log(nextElement = nextElement.replace(/coursetype/gi,"organization"));
	}else if(this.name == "organization"){
		console.log(nextElement = nextElement.replace(/organization/gi,"course"));
	}
	console.log(form_data[nextElement]);
	
	
	console.log(optionElements = form_data[nextElement].options);
	console.log(selectEvaluatedObject[this.id]);
	console.log(selectEvaluatedObject[form_data[nextElement].id]);
	for(var i=0;i<optionElements.length;i++){
		if(optionElements[i].value == selectEvaluatedObject[this.id]){

		}
	}
	*/
}


var count = -1;
function addMoreElements(){
	var tr = document.createElement("tr");
	console.log(++count);
	cloneHTML = wrapperHTML;
	cloneHTML = cloneHTML.replace(/....0/gi, "...." + (count)); 
	tr.innerHTML = cloneHTML;
	appendHere.appendChild(tr);
	rowCount.value = count;

	initTd();
	addEvents();
}

function updateQualificationDetails(){
	// this function ismeant to update data using ajax 
	// incomplete
	console.log(this);
	console.log(_count = this.id.replace(/save..../gi,""));
	url = fakeUrl + "?";
	var numberOfElements = 0;
	for(var i=0;i<form_data.length;i++){
		console.log(form_data[i]);
		if(form_data[i].id.indexOf("...." + _count)>-1 && form_data[i].tagName != "IFRAME"){
			console.log(url = url + form_data[i].name + "=" +form_data[i].value + "&");
		}
	}
	//console.log(url = url.slice(0,url.lastIndexOf("&"))); //slice(startIndex,endIndex)
	//slice slices the string from specified startIndex upto endIndex & returns the string

	url = url + "rowcount=0&code=1";


	//sendAjaxRequest(type,url,async,payload,performAction,field)
	//method defiened in extra.js
	sendAjaxRequest("get",url,true,null,function(field,response){console.log(field);console.log(response);},this);
}

function uploadFile(){
	console.log(this);
	url = "uploadresume.do";
	console.log(_count = this.id.replace(/save..../gi,""));
	console.log(iframe = document.getElementsByTagName("iframe")["certificatepath...." + _count]);
	console.log(iframeDoc = iframe.contentDocument || iframe.contentWindow.document);
	console.log(uploadType = iframeDoc.getElementById("uploadtype"));
	console.log(iframeDoc.getElementById("uploadtype").value = "certificates");
	console.log(uploadType.value);
	console.log(iframeDoc.getElementById("upload").click());
	//iframeDoc.window.submit();
}