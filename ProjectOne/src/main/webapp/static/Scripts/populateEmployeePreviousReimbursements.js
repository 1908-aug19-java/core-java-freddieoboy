console.log("Hello from populateEmployeePreviousReimbursements.js");

function popEmpPrevReim () {
	
	
	let baseUrl = "http://localhost:8081/ProjectOne/api/reimbursements?emplId=";
	let tokenArr = token.split(":");

	sendAjaxGet(baseUrl+tokenArr[3], autopopulateReimbursements);

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
	function autopopulateTables(xhr){
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
	
	function autopopulateReimbursements(xhr){
		let reimbursements = JSON.parse(xhr.response);
		console.log(reimbursements);
		
		// let tableListOfEmployees =
		// document.getElementById("table-list-of-employees");
		let table = document.getElementById("table-list-employee-previous-reimbursements")
		for (let r of reimbursements) {
			/*
			 * <th>Empl_Id</th> <th>Employee</th> <th>Title</th> <th>Department</th>
			 * 
			 */
	// ////////////////////
			/*
			 *  			<th>Reim_Id</th>
                            <th>Submitted</th>
                            <th>Employee</th>
                            <th>Expense</th>
                            <th>Amount</th>
                            <th>Status</th>
                         
			 */
		
			
			let row = document.createElement("tr");
		    let cell1 = document.createElement("td");
		    let cell2 = document.createElement("td");
		    let cell3 = document.createElement("td");
		    let cell4 = document.createElement("td");
		    let cell5 = document.createElement("td");
		    let cell6 = document.createElement("td");
		 

		    row.appendChild(cell1);
		    row.appendChild(cell2);
		    row.appendChild(cell3);
		    row.appendChild(cell4);
		    row.appendChild(cell5);
		    row.appendChild(cell6);
		    

		    console.log(row);
		    /*
			 *  			<th>Reim_Id</th>
                            <th>Submitted</th>
                            <th>Employee</th>
                            <th>Expense</th>
                            <th>Amount</th>
                            <th>Status</th>
                          
			 */
		    
		    cell1.innerText = r.reimId;
		    cell2.innerText = (new Date(r.submitDate).toString()).substring(0, 24);
		    cell3.innerText = r.employee.firstname + " " + r.employee.lastname;
		    cell4.innerText = r.expenseType;
		    cell5.innerText = r.amt;
		    cell6.innerText = r.status;
		    							
		    
		    

		    table.appendChild(row);
			// //////////////////////
			//}
			
		}
		baseUrl = "http://localhost:8081/ProjectOne/api/employees?emplId=";
		sendAjaxGet(baseUrl+tokenArr[3], autopopulateTables);
	}
	////////////
	//sendAjaxGet(baseUrl+tokenArr[3], autopopulateTables);
	////////////
	
}

popEmpPrevReim();

