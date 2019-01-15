window.onload = initAll;
var fakeUrl = "companyprofile.do";

function initAll(){
	createElements();
	//console.clear();
	//setActions();
	setIframeValue();
}
function setActions(){

	console.log(selectTag[0]);


	for(var i in selectTag){
//		console.log(i,selectTag[i],i.hasOwnProperty());
//		selectTag[i].onchange = locationHandler;
		selectTag[i].onchange = function(){
									console.log(obj = selectEvaluatedObject);
//									console.log(Object.getOwnPropertyNames(obj));
																		
									//eachRecursive(obj);
									//console.log(this);
									//eachRecursive.call(this,selectEvaluatedObject);
									if(this.id == "country"){
										field = "state";
										console.log(obj = selectEvaluatedObject[field]);
									}else if(this.id == "state"){
										console.log(obj = selectEvaluatedObject["city"]);
									}else if(this.id == "city"){
										console.log(obj = selectEvaluatedObject["pincode"]);
									}else {
										
									}
									for(var i in obj){
										console.log(obj[i]);
										console.log(field + "Id",obj[i][field + "Id"]);
//										if(selectTag[field].value == this.selectedIndex)
										console.log(selectTag[field]);
									}






									for(var i in selectEvaluatedObject){
										//inside collections of select tag objects

										//console.log(selectEvaluatedObject[i]);
										//console.log(selectEvaluatedObject[i][this.id]);
										//for(var j in (seo = selectEvaluatedObject[i])){

											//inside											
											//console.log(seo[this.id]);
										//}
									}
								}
	}	
}

function locationHandler(){
//	sendAjaxRequest(type,url,async,payload,performAction,field)

	
	console.log(nameValue = this.name + "=" + this.value);

	
	var url = "locationhandler.do?";
	console.log(url = url + nameValue);
}

function eachRecursive(obj){
	console.log(this);
	for (var k in obj){
		if (typeof obj[k] == "object" && obj[k] !== null)
			eachRecursive.call(this,obj[k]);
		else{
			// do something... 
			console.log(k,obj[k]);	
		}
		
	}
}

