console.log("Hello from autopopulate.js");

let baseUrl = "http://localhost:8081/ProjectOne/api/employees?emplId=";
let tokenArr = token.split(":");

sendAjaxGet(baseUrl+tokenArr[3], autopopulate);

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} 
	}
	xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

function autopopulate(xhr){
	let user = JSON.parse(xhr.response);
	console.log(user);
	document.getElementById("submit-new-reim-name").value = user.firstname + " " + user.lastname;
	document.getElementById("table-employee-name").innerText = user.firstname + " " + user.lastname;
	document.getElementById("table-username").innerText = user.username;
	document.getElementById("table-email").innerText = user.email;
	document.getElementById("table-department").innerText = user.department.deptName;
	document.getElementById("table-job-title").innerText = user.jobTitle;
	if (user.lastLogin != null) {
		document.getElementById("table-last-login").innerText = user.lastLogin;
	} else {
		document.getElementById("table-last-login").innerText = "1 Second Ago";
	}
	sendAjaxGet(baseUrl+user.department.deptManagerEmplId, populateDept);
}

function populateDept(xhr){
	let user = JSON.parse(xhr.response);
	document.getElementById("table-manager").innerText = user.firstname + " " + user.lastname;
}

/*

token = sessionStorage.getItem("token");
//console.log(token);

if(!token){
	window.location.href="http://localhost:8080/AuthDemo/login";
} else {
	let tokenArr = token.split(":");
	console.log(tokenArr);
	if(tokenArr.length===2){
		let baseUrl = "http://localhost:8080/AuthDemo/api/users?id=";
		sendAjaxGet(baseUrl+tokenArr[0], displayName);
	} else {
		window.location.href="http://localhost:8080/AuthDemo/login";
	}
}

/*
* if we are not redirected when checking for the token, a request will be made to 
* the url for that particular user 
*

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



////////////////////////////////////////////////////


let requestUrl = "http://localhost:8081/ProjectOne/api/employees";

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState===4 && xhr.status===200){
			callback(xhr.response);
		}
	}
	
	xhr.send();
}

function displayEmployees(employeeJSON){
//	console.log(employeeJSON);
	if(employeeJSON){
		let employees = JSON.parse(employeeJSON);
		
		let table = document.getElementById("empl-table");
		table.hidden = false;
		
		for(let employee of employees){
			let newRow = document.createElement("tr");
			let deptName = employee.department.name;
			if(!deptName){
				deptName = "n/a";
			}
			newRow.innerHTML = `<td>${employee.name}</td><td>${deptName}</td>`;
			table.appendChild(newRow);
			
		}
	} else {
		console.log("issue getting employees");
	}
}


sendAjaxGet(requestUrl, displayEmployees);


*/