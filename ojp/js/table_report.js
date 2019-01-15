var report_tr;
var addMoreData;
var filteredOptions = new Array();

function setTableReportClickEvent(methodName){
	console.log(report_tr = document.getElementsByClassName("report_tr"));
	for(var i=0;i<report_tr.length;i++){
		console.log(report_tr[i]);
		if(methodName)
			console.log(report_tr[i].onclick = methodName);
		else
			console.log(report_tr[i].onclick = setDataIntoForm);
	}

	console.log(addMoreData = document.getElementById("addmoredata"));
	console.log(addMoreData.onclick = addMoreTableData);
}

function setDataIntoForm(){
	console.log(this);
	console.log("current projectId : ",id = this.id.replace("report_tr_",""));
	console.log(trchilds = this.children);
	var i = 0;
	while(trchilds[i]){
		try{
			console.log(currentElement = trchilds[i]);
			console.log(elementName = currentElement.id.split("_.")[0]);
			console.log(rep = td_report["_" + elementName]);
			console.log(rep.innerHTML = currentElement.innerHTML);
			console.log(rep.style.display = "none");
			console.log(form = form_data[elementName]);

			if(form.tagName == "SELECT"){
				console.log(form);
				console.log(currentElement);
				if(currentElement.getAttribute("value"))
					console.log(form.value = form.selectedIndex = currentElement.getAttribute("value"));
			}else{
				console.log(form.value = currentElement.innerHTML);
			}
			console.log(form.parentNode.style.display = "block");
		}
		catch(error){
			console.log(error);
		}
		i++;
	}
		console.log(document.querySelector("input[type='date']").value);
}









function addMoreTableData(){
	console.log(this);
	var i = 0;

	for(;rep = td_report[i];i++){
		console.log(rep);
		rep.innerHTML = null;
		rep.setAttribute("value", null);
		rep.style.display = "none";
		
	}
	
	i = 0;
	for(;form = form_data[i];i++){
		console.log(form);
		if(form.tagName != "SELECT" && form.type != "submit"){
			form.innerHTML = null;
			form.value = null;
		}
		form.parentNode.style.display = "block";

	}
}










function onChangeLocation(){
	console.log(selectTag);
	var i = 0;
	while(selectTag[i]){
		//selectTag[i++].onchange = locationChange;
		selectTag[i++].onchange = onChangeSelect;
	}
}




var changeCount = 1;
var flag;
function onChangeSelect(){
	
	var elem = "";
	flag = true;
	console.log("Call : " + changeCount);
	console.log(this);
	if(this.id == "country"){
		//elem = "country";
		nextElem = "state";
		options = selectTag["state"].options;

	}else if(this.id == "state"){
		//elem = "state";
		nextElem = "city";
		options = selectTag["city"].options;

	}else if(this.id == "city"){
		//elem = "city";
		nextElem = "pinCode";
		options = selectTag["pincode"].options;
		
	}else if(this.id == "pincode"){
		elem = "pincode";
		nextElem = "country";
		options = selectTag["country"].options;
	}else{
	}
	elem = this.id;

	for(var i=0 ; i<options.length ;i++){
		try{
			console.log(options[i]);
			console.log(this[this.selectedIndex].value ,"==",options[i].getAttribute(elem + "id"));


			if(this[this.selectedIndex].value == options[i].getAttribute(elem + "id")){
				if(flag){
					options[i].selected = true;
					flag = false;
				}
				options[i].style.display = "block";
				//filteredOptions.push(options[i]);
			}else if(this.id == "pincode" && this[this.selectedIndex].getAttribute("countryid") == options[i].value){
				if(flag){
					options[i].selected = true;
					flag = false;
				}


				/*
				console.log(this.id , changeCount);
				if(this.id == "city" && changeCount == 2){
					for(var f = 0 ;f<this.options.length;f++){
						console.log(selectTag["pincode"][selectTag["pincode"].selectedIndex].id, "==" ,this.options[f].getAttribute("pincodeid"));
						if(selectTag["pincode"][selectTag["pincode"].selectedIndex].id == this.options[f].getAttribute("pincodeid")){
							//alert(" ");
							//console.log(
							console.log(this.selectedIndex = this.options[f].index,this.options[f]);
						}
					}
				}*/


				for(var h = 0;h < (child = selectTag["city"].options).length;h++){		//here this is pinCode
					console.log(this[this.selectedIndex].getAttribute("cityid"), "==" ,child[h].id);
					if(this[this.selectedIndex].getAttribute("cityid") == child[h].id){
						//this is not working must be asked
						console.log(selectTag["city"].selectedIndex = child[h].index,child[h].selected = true,child[h]);
						console.log(selectTag["city"].selectedIndex,selectTag["city"]);
						changeCount++;
					}
				}


				break;
				options[i].style.display = "block";
			}else{	
				options[i].style.display = "none";
			}
		}
		catch(error){
			console.log(error);
		}
	}
	for(var k=0;k <	filteredOptions.length; k++){
		//console.log(filteredOptions[k]);
		//filteredOptions[k].style.display = "block";
	}
	if(changeCount++ < 3){
		console.log(form_data[nextElem]);
		onChangeSelect.call(form_data[nextElem]);
	}else{
		changeCount = 1;
	}
}



//this is a very large method :(
/*
function locationChange(){
	console.log(currentOptionElement = this[this.selectedIndex]);
	//console.log(seo = selectEvaluatedObject[this.id]);
	var filteredOptions = new Array();
	if(this.id == "country"){

		console.log(options = selectTag["state"].options);
		console.log(seo = selectEvaluatedObject["state"]);

		//iterating over all the state objects to compare current countryId which is selected to the
		//selectEvaluatedObject["state"] collection of state objects which contains this countryId
		//then we'll extract such objects from selectEvaluatedObject["state"]
		for(var j = 0;j < seo.length ;j++){
			if(this[this.selectedIndex].value == seo[j].country.countryId){
				//console.log("match found",this[this.selectedIndex]);
				console.log(currentSeoChild = seo[j]);				//we have extracted the state objects that contain the countryId which is presently selected
				for(var k = 0;k < options.length ; k++){
					if(options[k].id == currentSeoChild.stateId){
						console.log(filteredOptions.push(options[k]),options[k],currentSeoChild);
					}else{
						
					}
					options[k].style.display = "none";
					//console.log(options[k]);
				}
			}
		}
	}else if(this.id == "state"){
		console.log(selectEvaluatedObject["state"]);

		//for(var i=0;i<selectEvaluatedObject["state"].length;i++){
		//	for(var j in selectEvaluatedObject["state"][i]){
		//		console.log(j,selectEvaluatedObject["state"][i][j]);
		//	}
		//}

	
		eachRecursive(selectEvaluatedObject);

	}else if(this.id == "city"){
		
	}else if(this.id == "pincode"){
		
	}else {
		
	}
		
	for(var z = 0 ; z < filteredOptions.length ; z++){

		if(z == 0){
			filteredOptions[z].selected = "selected";
		}
		console.log(filteredOptions[z].style.display = "block");
	}
}*/