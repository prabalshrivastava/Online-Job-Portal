var requestObject = null;
function createRequestObject(){
	try{
		console.log(requestObject = new XMLHttpRequest());
	}
	catch(e){
	
		try{
			console.log(requestObject = new ActiveXObject("Msxml2.XMLHTTP"));
		}
		catch(e){
			try{
				console.log(requestObject = new ActiveXObject("Microsoft.XMLHTTP"));
			}
			catch(e){
				alert("update your browser");
			}
		}
	}
	return requestObject;
}


