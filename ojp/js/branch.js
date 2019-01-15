window.onload = initAll;
var fakeUrl = "branch.do";
var report_tr;

function initAll(){
	setActions();
	console.log(setTableReportClickEvent());
	onChangeLocation();
}

function setActions(){
	createElements();
}