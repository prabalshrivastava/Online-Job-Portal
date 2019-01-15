window.onload = initAll;

var company;
var cross;
var jobwrapper,jobcontent,designation_experience,companylogo,designation,department;
var salary,city,jobskills,viewdetails,apply;





function initAll(){
	createElements();

	console.log(company = document.querySelector("#company"));
	console.log(cross = document.querySelectorAll(".cross"));
//	console.log(experience = document.querySelectorAll(".experience"));
	console.log(jobcontent = document.querySelectorAll(".jobcontent"));
	console.log(designation_experience = document.querySelectorAll(".designation_experience"));
	console.log(designation = document.querySelectorAll(".designation"));
	console.log(experience = document.querySelectorAll(".experience"));
	console.log(department = document.querySelectorAll(".department"));
	console.log(companylogo = document.querySelectorAll(".companylogo"));
	console.log(jobwrapper = document.querySelectorAll(".jobwrapper"));

	console.log(salary = document.querySelectorAll(".designation"));
	console.log(city = document.querySelectorAll(".city"));
	console.log(jobskills = document.querySelectorAll(".jobskills"));
	console.log(viewdetails = document.querySelectorAll(".viewdetails"));
	console.log(apply = document.querySelectorAll(".apply"));

	setAllActions();
}
function setAllActions(){
	for(var i=0;i < cross.length;i++){
		cross[i].onclick = 
							function(){
								console.log(this);
								console.log(id = "jobwrapper_." + this.id.split("_.")[1]);
								console.log(currentJob = document.getElementById(id));
								console.log(currentJob.style.opacity = "0");
								console.log(currentJob.style.display = "none");
							}
	}

	for (var i=0;i < apply.length ; i++){
		apply[i].onclick = applied;
	}
}
function applied(){
	console.log(this);
	console.log(this.innerText = "Applied");
	console.log(this.style.backgroundColor = "blue");
	console.log(this.style.color = "white");
	console.log(this.onclick = function(){alert("successfully Applied")});
	
	requestObject = createRequestObject();
	console.log(jobId = this.id.split("_.")[1]);
	console.log(url = "applyjob.do?jobId=" + jobId);
	requestObject.open("get",url,true);
	requestObject.onreadystatechange = 
										function(){
											if(this.readyState == 4 && this.status == 200){
												console.log(this.responseText);
											}
										};
	requestObject.send(null);
}