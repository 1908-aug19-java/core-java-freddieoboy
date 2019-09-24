console.log("Hello from toggleManagerBtn.js")
let managerBtn = document.getElementById("toggle-view-btn");
let token1 = sessionStorage.getItem("token");

if(!token1){
	window.location.href="http://localhost:8081/ProjectOne/login";
} else {
	let tokenArr = token1.split(":");
	//console.log(tokenArr);
	if(tokenArr.length >= 3){
		if (tokenArr[2] === "m") {
			managerBtn.removeAttribute("hidden");
		}
		//let baseUrl = "http://localhost:8080/AuthDemo/api/users?id=";
		//sendAjaxGet(baseUrl+tokenArr[0], displayName);
	} else {
		window.location.href="http://localhost:8081/ProjectOne/login";
	}
}
/*
let displayManagerBtn = function(e) {
	
   let image = document.getElementById("user-default-photo");
   console.log("old: " + image.src);
   console.log("btnsrc: " + btn.src);
   //let path = "file:///C:/Users/fredj/Documents/week3javaRevature/Project1/";
   let path = "http://localhost:8081/ProjectOne/static/Images/";


    if (image.src == path+"user.png") {
        image.src = "static/Images/footbal_user.png";
        console.log(image.src);
    }
   
}
*/
//managerBtn.addEventListener("click", f);

//////////////////////////////////////////////////////////////////////////////////////////



//console.log(token);



/*
* if we are not redirected when checking for the token, a request will be made to 
* the url for that particular user 
*/
/*
function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
			window.location.href="http://localhost:8080/AuthDemo/login";
		}
	}
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayName(xhr){
	let user = JSON.parse(xhr.response);
//	console.log(user);
	document.getElementById("user").innerHTML = user.username;
	
}
*/