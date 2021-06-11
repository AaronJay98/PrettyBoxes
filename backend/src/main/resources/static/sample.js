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

function deleteDesign(id){
	const http = new XMLHttpRequest();
	http.open("DELETE", "/design/id=" + id, false);
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
	http.open("GET", "/order/list/cartId=" + id, false);
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

function getCarts(){
	const http = new XMLHttpRequest();
	http.open("GET", "/cart/id=" + getUserId(), false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText);
	}
}

function getActiveCart(){
	const http = new XMLHttpRequest();
	http.open("GET", "/cart/getActiveCart/id=" + getUserId(), false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText);
	}
}

function getActiveCartId(){
	const http = new XMLHttpRequest();
	http.open("GET", "/cart/getActiveCart/id=" + getUserId(), false);
	http.send();
	if(http.status == 200){
		return JSON.parse(http.responseText).id;
	}
}


function checkout(){
	const http = new XMLHttpRequest();
	http.open("PUT", "/cart/checkout/id=" + getUserId(), false);
	http.send();
	if(http.status == 200){
		window.location = "/orders?cartId=" + JSON.parse(http.responseText).id;
		return JSON.parse(http.responseText);
	}
}

