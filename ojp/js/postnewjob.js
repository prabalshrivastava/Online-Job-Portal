window.onload = initAll;

var branch;
var fakeUrl = "postnewjob.do";
function initAll(){
	console.log(branch = document.querySelector("#branch"));
	bringBranchInProperFormat();
	createElements();
	setAllActions();
	setTableReportClickEvent();
}

function bringBranchInProperFormat(){
	console.log(content = new RegExp("'branchName'","gi"));
	console.log(branch.innerHTML = branch.innerHTML.replace(content,"branch"));
}

function setAllActions(){
}