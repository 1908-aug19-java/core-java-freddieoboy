console.log("Hello from checkToken.js")
let token = sessionStorage.getItem("token");

if(!token){
	window.location.href="http://localhost:8081/ProjectOne/login";
} else {
	let tokenArr = token.split(":");
	console.log(tokenArr);
	if(tokenArr.length >= 3){
		// prob should authenticate 
		
		//let baseUrl = "http://localhost:8080/AuthDemo/api/users?id=";
		//sendAjaxGet(baseUrl+tokenArr[0], displayName);
	} else {
		window.location.href="http://localhost:8081/ProjectOne/login";
	}
}