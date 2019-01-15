

function arrayListParser(arrayList,elementToBeCreated,parentElement,regex){
	
	//instructions : dont use loop variable i when calling this parser
	//somehow i is getting modified


	var a,pattern,b;
	var childElementName = elementToBeCreated;
	var childElement;
	
	try{
		parentElement.innerHTML = "";
		console.log(a = arrayList.toString());

		if(regex){
			console.log(pattern = new RegExp(regex,"gi"));
		}else{
			console.log(pattern = new RegExp(parentElement.getAttribute("name"),"gi"));
		}
			

		console.log(a = a.replace(pattern,"name"));
		console.log(a = a.replace(/nameid/gi,"id"));
		console.log(b = eval(a));	


		for(i = 0;i < b.length ;i++){
			console.log(childElement = document.createElement(childElementName));
			console.log(childElement.id = b[i].id);
			console.log(childElement.value = b[i].id);
			console.log(childElement.innerHTML = b[i].name);
			console.log(parentElement.appendChild(childElement));
		}
	}catch(error){
		console.log(error.message);
	}
}