function alertCustomer() {

	alert("Successfully added customer");
}
function getCustomer(){
	const http = new XMLHttpRequest();
	http.open("GET", "/list", false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText);
	}
}

function getCurrentUser(){
	const http = new XMLHttpRequest();
	http.open("GET", "/username", false);
	http.send();
	if(http.status == 200){
		return http.responseText;
	}
}

function getUserId(){
	var email = getCurrentUser();
	const http = new XMLHttpRequest();
	var url = "/customer/" + email;
	http.open("GET", url, false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText).id;
	}
}

function saveImage(){
	const http = new XMLHttpRequest();
	http.open("POST", "/design/save", false);
	http.send();
	if(http.status == 200){
		return http.responseText;
	}
}

function getDesigns(){
	const http = new XMLHttpRequest();
	http.open("GET", "/design/list", false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText);
	}
}

function getDesign(id){
	const http = new XMLHttpRequest();
	http.open("GET", "/design/id=" + id, false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText);
	}
}

function getCustomerById(id){
	const http = new XMLHttpRequest();
	http.open("GET", "/customer/id=" + id, false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText).firstName + " " + JSON.parse(http.responseText).lastName;
	}
}

function getCustomerInfoById(id){
	const http = new XMLHttpRequest();
	http.open("GET", "/customer/id=" + id, false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText);
	}
}

function getOrders(id){
	const http = new XMLHttpRequest();
	http.open("GET", "/order/list/userId=" + id, false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText);
	}
}

function deleteOrder(id){
	const http = new XMLHttpRequest();
	http.open("DELETE", "/order/deleteId=" + id, false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText);
	}
}

