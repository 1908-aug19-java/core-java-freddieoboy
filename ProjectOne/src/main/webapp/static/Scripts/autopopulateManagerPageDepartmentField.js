console.log("Hello from autopopulateManagerPageDepartmentField.js");

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
	document.getElementById("reim-form-department").value = user.department.deptName;
	
}
