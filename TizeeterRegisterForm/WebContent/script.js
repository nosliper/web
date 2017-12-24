var typedChars = 0;

// function onOutOfLimits(tizeetField){
// 	var tizeetContent = tizeetField.value;
// 	if(tizeetContent.length > 140){
// 		var valid = tizeetContent.substring(0, 139);
// 		var invalid = tizeetContent.substring(140, tizeetContent.length - 1);
// 		var invalidEm = document.createElement("EM");
// 		invalidEm.appendChild(document.createTextNode(invalid));
// 		invalid.setAttribute("class", "invalidText");
// 		tizeetField.innerHTML = valid;
// 		tizeetField.appendChild(invalidEm);
// 	}
// }

function onTypingTizeet(){
	var tizeetField = document.getElementById("tizeetField");
	var tizeetButton = document.getElementById("tizeetButton");
	var dataTypedLen = tizeetField.value.length;
	typedChars = dataTypedLen;
	if(typedChars < 1 || typedChars > 140){
		tizeetButton.disabled = true;
		tizeetButton.setAttribute("style", "opacity: " + 0.2 + ";"); //TODO: set the class attribute instead
	}
	else{
		tizeetButton.disabled = false;
		tizeetButton.setAttribute("style", "opacity: " + 1 + ";");
	}
	var leftChars = document.getElementById("charsRestantes");
	if(leftChars && leftChars.innerHTML){
		leftChars.innerHTML = 140 - typedChars;
	}
	color = typedChars > 140 && "red" || "#8899a6";
	leftChars.setAttribute("style", "color: " + color + ";");
}
window.onload = function(){
	var tizeetButton = document.getElementById("tizeetButton");
	var tizeetField = document.getElementById("tizeetField");
	tizeetButton.disabled = true;
	tizeetField.addEventListener("input", onTypingTizeet);
}