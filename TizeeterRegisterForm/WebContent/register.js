
messages = {
	username:"O nome de usu&aacute;rio deve conter entre 6 e 24 caracteres.",
	email:"Email inv&aacute;lido.",
	password:"As senhas n&atilde;o conferem.",
	empty:"Este campo &eacute; obrigat&oacute;rio."
}
validation = {
	//@param data is an array with at least 1 string
	emptyField: function(data, ids) {
		for(var i in data) {
			if (data[i].length == 0) {
				document.getElementById(ids[i]).innerHTML = messages.empty;
				console.log("emptyField");
				return true;
			}
		}
		return false;
	},
	username:function(data) {
		var isEmpty = this.emptyField(data, ["invalidUsername"]);
		if (!isEmpty) {
			var len = data[0].length
			if (len < 6 || len > 24) {
				document.getElementById("invalidUsername").innerHTML = messages.username
				console.log("username");
				return false;
			}
		}
		return !isEmpty;
	},
	email:function(data) {
		var isEmpty = this.emptyField(data, ["invalidEmail"]);
		if (!isEmpty) {	
			if (data[0].indexOf("@") < 0 || data[0].indexOf(".") < 0) {
				document.getElementById("invalidEmail").innerHTML = messages.email;
				console.log("email");
				return false;
			}
		}
		return !isEmpty;
	},
	password:function(data) {
		var isEmpty = this.emptyField(data, ["invalidPassword", "invalidPasswordConf"])
			if (!isEmpty && data[0] != data[1]) {
				document.getElementById("invalidPasswordConf").innerHTML = messages.password
				console.log("password");
				return false;
			}
			return !isEmpty;
	},
	fullName:function(data) {
		console.log("fullName");
		return !this.emptyField(data, ["invalidName"]);

	}
	,
	address:function(data) {
		console.log("address");
		return !this.emptyField(data, ["invalidAddress"]);
	}
}
function validateFields(event, callbacks) {
	for(var msg in document.querySelectorAll(".invalidField"))
		msg.innerHTML = "";
	var valid = true;
	var username = 	[document.getElementById("username").value];
	var email 	 = 	[document.getElementById("email").value];
	var password = 	[document.getElementById("password").value, document.getElementById("invalidPasswordConf").value];
	var fullName = 	[document.getElementById("fullName").value];
	var address  = 	[document.getElementById("address").value];
	valid = !callbacks.username(username)? 	false : true;
	valid = !callbacks.email(email)? 		false : true;
	valid = !callbacks.password(password)? 	false : true;
	valid = !callbacks.fullName(fullName)? 	false : true;
	valid = !callbacks.address(address)? 	false : true;
	if (!valid){
		event.preventDefault();
		console.log("coisou");
	}
	return valid;
}
window.onload = function() {
	var registerForm = document.getElementById("registerForm");
	registerForm.addEventListener("submit", function(event) {
		return validateFields(event, validation);
	});
}