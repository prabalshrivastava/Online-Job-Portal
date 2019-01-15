window.onload = initAll;

fakeUrl = "projectdetails.do";
function initAll(){

	createElements();//create td elements from extra.js
	setAllAction();
}
function setAllAction(){
	console.log(setTableReportClickEvent());
}


