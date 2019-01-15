window.onload = initAll;

var fakeUrl="others.do";
var tr_addmorelang,td_addmorelang;
var td_addmorelang_style;
var languagesKnown;
var tbody = new Array();
var lang,langCount,langValues,languagesKnownElement;
var rwsCheckBox;
function initAll(){

	console.log(td_addmorelang = document.getElementById("td_addmorelang"));
	console.log(tr_addmorelang = td_addmorelang.parentNode);
	console.log(languagesKnown = document.getElementById("languagesknown"));
	console.log(addrws = document.getElementById("addrws"));
	console.log(rws = document.getElementsByClassName("rws"));
	console.log(lang = document.getElementsByClassName("lang"));
	console.log(langCount = document.getElementById("langcount"));
	console.log(langValues = document.getElementById("langvalues"));
	console.log(langLabel = document.getElementById("lang_label"));
	console.log(candidateLanguageIds = document.getElementsByName("candidatelanguageid"));

	setAllActions();	
	console.log(languagesKnownElement = languagesKnown.innerHTML);	//1
	languagesKnown.innerHTML = "";
	
	initLanguages();

	



	//languagesKnown.appendChild(langLabel);
//	alert(contains("abcdef","df","c"));
	/*
	for(var a = 0 ; a<lang.length;a++){
		if(lang[a].name == "candidatelanguageid"){
			console.log(lang[a]);
			lang[a].style.display = "none";
		}else{
			lang[a].style.display = "block";
		}
	}*/
}

function setAllActions(){
	createElements();
	/*
	initTd();					
	createExtraElements();		
	setActionsOfExtraElements();
	initializeSelect();
	*/
	td_addmorelang.onmouseover = 
									function(){
										console.log(td_addmorelang_style = this.style);
										//if((new Date()).getMilliseconds()%2 == 0){
										//if(false){
											this.style.color = "white";
											this.style.backgroundColor = "#FFCFFF";
											this.style.border = "10px outset #FFCFFF";
											console.log((new Date()).getMilliseconds());
											console.log(parseInt(Math.random()*10));
											if(parseInt(Math.random()*10)%2 == 0){
												this.style.borderRadius = "10px";
											}
										/*}else{
											this.style.border = "none";
											//for border shadow
											//x,y coordinate , blurring , distanceof blurring ,color
											console.log(this.style.boxShadow = "5px 5px 30px 0px black" );
											//console.log(this.style.boxShadow = "5px 10px 30px 0px rgba("+ Math.random()*100 + ","+Math.random()*100 + "," + Math.random()*100 + ")" );	
										}*/
									}
	td_addmorelang.onmouseout = 
									function(){
										this.setAttribute("style",td_addmorelang_style);
									}
	td_addmorelang.onclick = function(){
											addMoreLang.call(this);
											console.log(document.getElementById("_language_" + langCount.value).style.display = "none");
											console.log(document.getElementById("_candidatelanguageid_" + langCount.value).style.display = "none");
											console.log(document.getElementById("candidatelanguageid_" + langCount.value).parentNode.style.display = "none");
										};
}

function initLanguages(){

	//this element creates elements from EL
	console.log(langValues.value);
	console.log(langCount);
	console.log(langCount.value);
	console.log(langObjects = eval(langValues.value));
	console.log(langObjects.length);
	
	if(langObjects.length != 0){
		for(var z=0;z<langObjects.length;z++){
			//console.log("\n\n\n\n\n\n\n\n" + z);
			addMoreLang();
			console.log(langElement = document.getElementsByClassName("language_"+langCount.value));
			console.log(langObjects[z]);
			for(var j = 0 ;j < langElement.length ;j++){
				//console.log(langElement[j]);
				if(langElement[j].id == ("_language_" + langCount.value)){

					//report elements
					console.log("_language_" + langCount.value);
					console.log(langElement[j]);
					console.log(langElement[j].innerHTML = langObjects[z].language.language);
					console.log(langElement[j].value = langObjects[z].language.languageId);

				}
				
				if(langElement[j].id == ("candidatelanguageid_" + langCount.value)){

					//candidatelanguageid forms
					console.log(langElement[j]);
					console.log(langElement[j].value = langObjects[z].candidateLanguageId);
					langElement[j].parentNode.style.display = langElement[j].style.display = "none";

				}
				
				if(langElement[j].id == ("_candidatelanguageid_" + langCount.value)){

					//canddidatelanguageid reports
					langElement[j].style.display = "none";

				}
				
				if(langElement[j].id == "language_" + langCount.value){

					console.log(langElement[j]);
					langElement[j].parentNode.style.display = langElement[j].style.display = "none";

				}
				
					
				var lc,r,w,s;
				console.log(lc = langObjects[z].languageCapability.languageCapability);

				if(lc.indexOf("read") > -1)
					console.log(r = true);

				if(lc.indexOf("write") > -1)
					console.log(w = true);
				
				if(lc.indexOf("speak") > -1)
					console.log(s = true);
				
				console.log(langElement[j].id + "==" + ("_read_" + langCount.value));

				if(langElement[j].id == ("_read_" + langCount.value)){
					//alert(" ");	
					console.log(langElement[j]);

					if(r){		
						//if user can read hide form and show report

						//hide form
						console.log(langElement["read_" + langCount.value].parentNode.style.display = langElement["read_" + langCount.value].style.display = "none");

						//show report
						console.log(langElement[j].style.display = "block");

						//mark checkbox checked because user can read
						console.log(langElement["read_" + langCount.value].checked = true);
						console.log(langElement[j].setAttribute("value" , "read"));
						//console.log(langElement["read_" + langCount.value].onChange = checkIt);
					}else{	
						//if user read hide report && show form 
						//alert(" ");
						//console.log(langElement["read_" + langCount.value].parentNode);

						//hide report
						console.log(langElement[j].style.display = "none");

						//show form
						console.log(langElement["read_" + langCount.value].parentNode.style.display	= langElement["read_" + langCount.value].style.display = "block");

						//mark checkbox unchecked because user cant read
						console.log(langElement["read_" + langCount.value].checked = false);						
						console.log(langElement["read_" + langCount.value].setAttribute("value",null));
					}
				}
	

				if(langElement[j].id == ("_write_" + langCount.value)){
					//alert(" ");
					console.log(langElement[j]);
					if(w){
						console.log(langElement[j].style.display = "block");
						console.log(langElement["write_" + langCount.value].parentNode.style.display = langElement["write_" + langCount.value].style.display = "none");
						console.log(langElement["write_" + langCount.value].checked = true);
						console.log(langElement[j].setAttribute("value" , "write"));
					}else{
						console.log(langElement[j].style.display = "none");
						console.log(langElement["write_" + langCount.value].parentNode.style.display = langElement["write_" + langCount.value].style.display = "block");
						console.log(langElement["write_" + langCount.value].checked = false);
						console.log(langElement["write_" + langCount.value].setAttribute("value",null));
					}
				}
				
				if(langElement[j].id == ("_speak_" + langCount.value)){
					//alert(" ");
					console.log(langElement[j]);
					if(s){
						console.log(langElement[j].style.display = "block");
						console.log(langElement["speak_" + langCount.value].parentNode.style.display = langElement["speak_" + langCount.value].style.display = "none");	
						console.log(langElement["speak_" + langCount.value].checked = true);
						console.log(langElement[j].setAttribute("value" , "speak"));
					}else{
						console.log(langElement[j].style.display = "none");
						console.log(langElement["speak_" + langCount.value].parentNode.style.display = langElement["speak_" + langCount.value].style.display = "block");
						console.log(langElement["speak_" + langCount.value].checked = false);
						console.log(langElement["speak_" + langCount.value].setAttribute("value",null));
					}
				}
			}
		}
		console.log(langCount.value);

	}else{
		addMoreLang();
		for(var i=0;i<td_report.length;i++){
			console.log(td_report[i]);
			console.log("className : " + td_report[i].className + ">>>> language_" + langCount.value);
			if(td_report[i].className.indexOf(("language_"+langCount.value)) > -1)
				console.log(td_report[i].style.display = "none");
		}
		for(x = 0 ; x < candidateLanguageIds.length ; x++){
			candidateLanguageIds[x].style.display = "none";
		}
	}
	setCheckBoxAction();
}

function checkIt(){
	console.log(td_report["_" + this.id]);
	this.setAttribute("value" , td_report["_" + this.id]);
}

function setCheckBoxAction(){	
//	console.log(rwsCheckBox = document.getElementsByName("rws_" + langCount.value));
	console.log(rwsCheckBox = document.querySelectorAll("input[type='checkbox']"));
	for(var i = 0;i<rwsCheckBox.length;i++){
		console.log(rwsCheckBox[i]);
		rwsCheckBox[i].onclick = 					
									function(){
										//alert(" ");
										console.log(this);
										this.value = "";

										if(this.checked){
											boxChecked(this)
										}else{
											boxUnChecked(this);
										}
									};
	}
}

function boxChecked(field){
	console.log(field);
	console.log(report_field = td_report["_" + field.id]);

	//call and apply sets 'this' of the method ie the context n which it must be called
	onClickReport.call(report_field);
	//onClickReport.apply(report_field);
	//report_field.onClickReport();		//----X this can't be done
	console.log(field.setAttribute("value",report_field.innerHTML.trim()));
}

function boxUnChecked(field){
	console.log(field);
	console.log(report_field = td_report["_" + field.id]);
}



var count = -1;
function addMoreLang(){
	//this will only be called when add more button gets clicked


	console.log("creating element no : " + ++count);
//	console.log(this);

	console.log(element = languagesKnownElement);
	
	element = element.replace(/_0/gi,('_'+count+'"'));
	element = element.replace(/display:none/gi,"display:block");
	console.log(pattern = new RegExp('class="',"gi"));
	element = element.replace(pattern,'class="language_' + count + ' ');

	console.log(tbody[count] = document.createElement("tbody"));
	tbody[count].innerHTML = element;
	
	console.log(addrws.appendChild(tbody[count]));

	for(var h = 0;h<addrws.length;h++){
		console.log(childrens =	addrws.children);
		for (var i=0;i<childrens.length;i++){
			console.log(grandChilds = childrens[i].children);
			for(var j=0;j<grandChilds.length;j++){
				console.log(ggc = grandChilds[j].children);
				for(var k=0;k<ggc.length;k++){
					
					console.log(ggc[k]);
					console.log(yn = ggc[k].className.split(" ")[1] == "td_report");
					if(yn && ggc[k].id.split("_")[1] !="candidatelanguageid"){
					
						ggc[k].style.display = "block";

					}

					for(var r = 0;r<rws.length;r++){
						console.log(rws);
						if(ggc[k].id = rws[r].id){
							//alert(" ");
							//ggc[k].style.display = "block";
						}else{
						//ggc[k].style.display = "none";
						}
					}
				}
			}
		}
	}
	//alert(" ");
	initTd();
	setActionsOfExtraElements();//this function sets action of the exta elements
	//console.log(extra);

	for(i=0;i<extra.length;i++){
		//console.log(extra[i].id);
		console.log(buttonName = extra[i].id.split("_"));
		if(buttonName[0] == "update"){
			extra[i].onclick = updateClick;
			//console.log(extra[i]);
		}
		if(buttonName[0] == "reset"){
			extra[i].onclick = resetClick;
			//console.log(extra[i]);
		}
		if(buttonName[0] == "cancel"){
			extra[i].onclick = cancelClick;
			//console.log(extra[i]);
		}
	}
	
	console.log(langCount.value = count);
	console.log(langCount);

	//console.log(elementStyle = languagesKnown.style);
	//console.log(tbody[count].setAttribute("style",elementStyle)); //not working
}
	
