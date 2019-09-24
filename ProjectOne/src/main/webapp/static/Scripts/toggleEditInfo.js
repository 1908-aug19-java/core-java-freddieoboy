let editInfoBtn = document.getElementById("edit-info-btn");

console.log("toggle.js");


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
///document.getElementById("submit-new-reim-name").value = user.firstname + " " + user.lastname;
// if user changes name we need to autopopulate reim name

function toggleUserInput() {
    let table = document.getElementById("table-myProfile");

    if (editInfoBtn.value == "Edit Info.") {
        editInfoBtn.value = "Submit"

        table.hidden = false;

        console.log(table);
        let isTH = true;

        for (let row of table.rows) {
            let th;
            for (let cell of row.cells) {
                if (isTH) {
                    th = cell.innerText;
                }
                else {
                    let val;
                    switch (th) {
                        case "Employee:":
                            val = cell.innerText; // your code below
                            console.log(val);
                            cell.innerHTML = `<input type="text" class="form-control" value="${val}">`;
                            console.log(cell.innerText);
                            break;
                        case "Username:":
                            val = cell.innerText; // your code below
                            console.log(val);
                            cell.innerHTML = `<input type="text" class="form-control" value="${val}">`;
                            console.log(cell.innerText);

                            break;
                        case "Email:":
                            val = cell.innerText; // your code below
                            console.log(val);
                            cell.innerHTML = `<input type="text" class="form-control" value="${val}">`;
                            console.log(cell.innerText);
                            break;
                        default:
                            console.log("default");
                            break;
                    }
                    //let val = cell.innerText; // your code below
                    // console.log(val);
                    //cell.innerHTML = `<input type="text" class="form-control" value="${val}">`;

                }
                isTH = !isTH;
            }
        }
    } else {
        editInfoBtn.value = "Edit Info."

        let isTH = true;

        for (let row of table.rows) {
            let th;
            for (let cell of row.cells) {

                if (isTH) {
                    //console.log("Header: " + cell.innerText);
                    th = cell.innerText;
                }
                else {
                    console.log("Header: " + th);
                    switch (th) {
                        case "Employee:":
                            cell.innerText = cell.firstChild.value; // your code below
                            console.log(cell.innerText);
                            break;
                        case "Username:":
                            cell.innerText = cell.firstChild.value; // your code below
                            console.log(cell.innerText);
                            break;
                        case "Email:":
                            cell.innerText = cell.firstChild.value; // your code below
                            console.log(cell.innerText);
                            break;
                        default:
                            console.log("default");
                            break;
                    }



                    // console.log(cell.getElementsByTagName("td").input.value);

                    //cell.innerHTML = `<input type="text" class="form-control" value="${val}">`;

                }
                isTH = !isTH;
            }
        }
    }

}




editInfoBtn.addEventListener("click", toggleUserInput);