

let selectExpense = document.getElementById("expense-type-select");


let onChange = function(e) {
	console.log(selectExpense.value);
	
	if (selectExpense.value == "Other") {
		// show text box
	}
   
}

selectExpense.addEventListener("change", onChange);