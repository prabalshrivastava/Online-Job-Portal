window.onload = initAll;

var industryType,department,designation,arrayList;
var selectTag;
//var td_report,td_form,td_label,tr_element,form_data;	//part of extra.js
var fakeUrl = "experiencedetails.do";

//variables for extra.js
/*
var td_update,td_reset,td_cancel,td_message,originalClass;
var extra;
*/

function initAll(){
	
	console.log(selectTag = document.getElementsByTagName("select"));

	setAllActions();
	
	
	
	//all commented things have been shifted to extra.js
	/*
	console.log(td_form = document.getElementsByClassName("td_form"));
	console.log(td_report = document.getElementsByClassName("td_report"));
	console.log(td_label = document.getElementsByClassName("td_label"));
	console.log(form_data = document.getElementsByClassName("form_data"));
	*/

	//initAllForms(); 
	//initializeSelect();

	/*
	industryType = document.getElementById("industrytype");
	department = document.getElementById("department");
	designation = document.getElementById("deisgnation");
	*/
}

function setAllActions(){


	createElements();
	/*
	for(i = 0 ; i < td_report.length ; i++){
		td_report[i].onclick = onClickReport;
		td_report[i].onmouseover = onOverReport;
		td_report[i].onmouseout = onOutReport;
		//td_report[i].style.display = "block";		//just to view the elements
	}

	for(i = 0; i < td_form.length; i++){
		td_form[i].onclick = onClickForm;
		td_form[i].onmouseover = onOverForm;
		td_form[i].onmouseout = onOutForm;
	}
	*/

	console.log(setTableReportClickEvent());
}