console.log("checkIfManager.js")
let managerBtn = document.getElementById("toggle-view-btn");
let token2 = sessionStorage.getItem("token");

if(!token2){
	window.location.href="http://localhost:8081/ProjectOne/login";
} else {
	let tokenArr = token2.split(":");
	//console.log(tokenArr);
	if(tokenArr.length >= 3){
		
		// prob need auth here
		
		if (tokenArr[2] === "m") {
			//your allowed
		}
		else {
			window.location.href="http://localhost:8081/ProjectOne/employee";
		}
		//let baseUrl = "http://localhost:8080/AuthDemo/api/users?id=";
		//sendAjaxGet(baseUrl+tokenArr[0], displayName);
	} else {
		window.location.href="http://localhost:8081/ProjectOne/login";
	}
}