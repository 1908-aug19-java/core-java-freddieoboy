console.log("Hello from populateManReimTableSortByDepartment.js");

function popManReimTableSortByDept () {
	console.log("inside func popmanreimtablesortbydept");
	
	let baseUrl = "http://localhost:8081/ProjectOne/api/reimbursements?deptId=";
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
	function autopopulateManPageDeptField(xhr){
		let user = JSON.parse(xhr.response);
		console.log(user);
		document.getElementById("reim-form-department").value = user.department.deptName;
		
	}
	
	function autopopulateManEmpTable(xhr){
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
		//end
		baseUrl = "http://localhost:8081/ProjectOne/api/employees?emplId=";
		sendAjaxGet(baseUrl+tokenArr[3], autopopulateManPageDeptField);
		
	}
	
	function autopopulate(xhr){
		let reimbursements = JSON.parse(xhr.response);
		console.log(reimbursements);
		
		// let tableListOfEmployees =
		// document.getElementById("table-list-of-employees");
		let table = document.getElementById("table-list-of-manager-dept-reimbursements")
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
                            <th>Update</th>
			 */
			/*
			 * 				<td>Row 1 Data 1</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 3</td>
                            <td>Row 1 Data 4</td>
                            <td>Row 1 Data 5</td>
                            <td>Row 1 Data 6</td>
                            <td>
                                <input id="reim_id_approve" type="button" class="btn btn-sm" value="Approve" />
                                <input id="reim_id_reject" type="button" class="btn btn-sm" value="Decline" /></td>
                            </td>
			 */
			//if (r.emplId != tokenArr[3]) {
			let row = document.createElement("tr");
		    let cell1 = document.createElement("td");
		    let cell2 = document.createElement("td");
		    let cell3 = document.createElement("td");
		    let cell4 = document.createElement("td");
		    let cell5 = document.createElement("td");
		    let cell6 = document.createElement("td");
		    let cell7 = document.createElement("td");

		    row.appendChild(cell1);
		    row.appendChild(cell2);
		    row.appendChild(cell3);
		    row.appendChild(cell4);
		    row.appendChild(cell5);
		    row.appendChild(cell6);
		    row.appendChild(cell7);

		    console.log(row);
		    /*
			 *  			<th>Reim_Id</th>
                            <th>Submitted</th>
                            <th>Employee</th>
                            <th>Expense</th>
                            <th>Amount</th>
                            <th>Status</th>
                            <th>Update</th>
			 */
		    
		    cell1.innerText = r.reimId;
		    cell2.innerText = (new Date(r.submitDate).toString()).substring(0, 24);
		    cell3.innerText = r.employee.firstname + " " + r.employee.lastname;
		    cell4.innerText = r.expenseType;
		    cell5.innerText = r.amt;
		    cell6.innerText = r.status;
		    							//`<input type="text" class="form-control" value="${val}">`;
		    if(r.status == "Pending") {
		    	console.log("pending: " + r.status);
		    	//cell7.innerHtml = `<input type="text" class="form-control" value="${r.status}">`;
		    	
		    	cell7.innerHTML =  `<input id="${r.reimId}_reim_id_approve" type="button" class="btn btn-sm approveBtn" value="Approve" />
                    <input id="${r.reimId}_reim_id_reject" type="button" class="btn btn-sm rejectBtn" value="Decline" /></td>`
                    
		    }
		    

		    table.appendChild(row);
			// //////////////////////
			//}
			
		}
		//end
		baseUrl = "http://localhost:8081/ProjectOne/api/employees?deptId=";
		sendAjaxGet(baseUrl+tokenArr[4], autopopulateManEmpTable);
		
	}
	
}

popManReimTableSortByDept();

