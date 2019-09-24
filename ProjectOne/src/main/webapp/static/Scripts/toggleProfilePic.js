let btn = document.getElementById("changeProfilePic");


let changeProfilePic = function(e) {
   let image = document.getElementById("user-default-photo");
   console.log("old: " + image.src);
   console.log("btnsrc: " + btn.src);
   //let path = "file:///C:/Users/fredj/Documents/week3javaRevature/Project1/";
   let path = "http://localhost:8081/ProjectOne/static/Images/";


   if (image.src == path+"mj_user.png") {
	   image.src = "static/Images/user.png";
       console.log(image.src);
   }	
   else if (image.src == path+"user.png") {
        image.src = "static/Images/footbal_user.png";
        console.log(image.src);
    }
    else if (image.src == path+"footbal_user.png") {
        image.src = "static/Images/farmer_user.png";
        console.log(image.src);
    }
    else if(image.src == path+"farmer_user.png") {
        image.src = "static/Images/angel_user.png";
        console.log(image.src);
    }
    else {
        // we are currently woman
        image.src = "static/Images/mj_user.png";
        console.log(image.src);
    }
}

btn.addEventListener("click", changeProfilePic);