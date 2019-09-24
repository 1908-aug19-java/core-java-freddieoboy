


let logoutBtn = document.getElementById("logout-btn");

let logout = function(e) {
	console.log("logout")
	sessionStorage.clear();
	// this should redirect to login
	window.location.href="http://localhost:8081/ProjectOne/employee";

}


logoutBtn.addEventListener("click", logout);