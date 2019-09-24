console.log("Hello from populateManagersEmployeeTable.js");

function populateManagersEmployeeTable () {
	
	
	let baseUrl = "http://localhost:8081/ProjectOne/api/employees?deptId=";
	let tokenArr = token.split(":");

	sendAjaxGet(baseUrl+tokenArr[4], autopopulate);

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
		let users = JSON.parse(xhr.response);
		console.log(users);
		
		// let tableListOfEmployees =
		// document.getElementById("table-list-of-employees");
		let table = document.getElementById("table-list-of-employees")
		for (let user of users) {
			/*
			 * <th>Empl_Id</th> <th>Employee</th> <th>Title</th> <th>Department</th>
			 * 
			 */
	// ////////////////////
			
			if (user.emplId != tokenArr[3]) {
			let row = document.createElement("tr");
		    let cell1 = document.createElement("td");
		    let cell2 = document.createElement("td");
		    let cell3 = document.createElement("td");
		    let cell4 = document.createElement("td");
	
		    row.appendChild(cell1);
		    row.appendChild(cell2);
		    row.appendChild(cell3);
		    row.appendChild(cell4);
	

		    console.log(row);
		 
		    
		    cell1.innerText = user.emplId;
		    cell2.innerText = user.firstname + " " + user.lastname;
		    cell3.innerText = user.jobTitle;
		    cell4.innerText = user.department.deptName;
		   
		    

		    table.appendChild(row);
			// //////////////////////
			}
			
		}
		
		
	}
	
}

populateManagersEmployeeTable();

