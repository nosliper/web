LIMITER = 0;
var test = {
	currentWord: 0,
	children: document.getElementById("spacer").children,
	keystrokes: document.getElementById("keystrokes"),
	correctKeystrokes: document.getElementById("correct-keystrokes"),
	correctWords: document.getElementById("correct-words"),
	misspelled: document.getElementById("misspelled"),
	started: false
};
var timer = {
	updateTimer: function() {
		var clock = document.getElementById("timer");
		var currentTime = timeFromString(clock.innerHTML) - 1;
		clock.innerHTML = timeToString(currentTime);
	},
	startClock: function() {
		this.intervalID = setInterval(this.updateTimer, 1000);
	}
};
function timeFromString(string) {
	var time = string.split(":");
	var min = parseInt(time[0]);
	var secs = parseInt(time[1]);
	return min * 60 + secs;
}
function timeToString(time) {
	var mins = Math.floor(time / 60);
	time -= mins * 60;
	time = parseInt(time);
	return mins + ":" + (time >= 10? time : "0" + time);
}
function randInt(min, max) {
	return Math.floor(Math.random() * (max - min + 1)) + min;
}
function generateSample(sampleStr, size) {
	var sample = [];
	var arr = sampleStr.split("|");
	var max = arr.length - 1;
	for (var i = 0; i < size; i++) {
		sample.push(arr[randInt(0, max)]);
	}
	return sample;
}
function fillSampleContainer(container, sample) {
	for(var i in sample) {
		var word = document.createElement("span");
		word.setAttribute("class", i == 0? "sample-word-highlight" : "sample-word");
		word.innerHTML = sample[i];
		container.appendChild(word);
	}
}
function updateSampleBox() { // TODO: update this jerry-rig to keep on first line like 10ff
	var spacer = document.getElementById("spacer");
	var top = spacer.style.top;
	top = "" == top? 1 : parseInt(top) - 47;
	var outOfLimits = undefined;
	if (test.currentWord < test.children.length - 1) {
		var ow = test.children[test.currentWord + 1].offsetWidth;
		var boxWidth = document.getElementById("spacer").offsetWidth;
		outOfLimits = LIMITER + parseInt(ow) + 10 > parseInt(boxWidth);
	}
	if (outOfLimits) {
		spacer.setAttribute("style", "top: " + top + "px;");
		LIMITER = 0;
	}
}
function clearField(field) {
	field.value = "";
}
function onSpacebarToClear(event) {
	if (32 == event.which) {
		clearField(this);
		highlightCurrentWord(test);
	}
}
function highlightCurrentWord(test) {
	var hl = test.currentWord;
	test.children[hl].setAttribute("class", "sample-word-highlight");
}
function onSpacebarNextWord(event) {
	if (32 == event.which && getTypedWord().length > 0) {
		var current = test.children[test.currentWord];
		updateKeystrokesField(this);
		updateLimiter();
		updateSampleBox();
		if (isCorrect(current.innerHTML, getTypedWord())) {
			setCorrectStyle(test.currentWord);
			updateDataField("correctWords");
			updateCorrectKeystrokesField(this);
		}
		else {
			setWrongStyle(test.currentWord);
			updateDataField("misspelled");
		}
		if (test.currentWord < test.children.length - 1) {
			test.currentWord += 1;
		}
	}
}
function updateLimiter() {
	var curr = test.children[test.currentWord];
	LIMITER += parseInt(curr.offsetWidth) + 10;
}
function setWrongStyle(index) {
	test.children[index].setAttribute("class", "sample-word-wrong");
}
function setCorrectStyle(index) {
	test.children[index].setAttribute("class", "sample-word-correct");
}
function setInputBoxStyle(style) {
	if (style == "wrong") {
		document.getElementById("typing-area").setAttribute("class", "typing-area-wrong");
	}
	else if(style == "normal") {
		document.getElementById("typing-area").setAttribute("class", "typing-area-normal");
	}
}
function getTypedWord() {
	var text = document.getElementById("typing-area").value;
	text = text.replace(" ", "");
	console.log("typed word: " + text);
	return text;
}
function isCorrect(current, typed) {
	return current == typed;
}
function updateKeystrokesField(sourceField) {
	var ks = test.keystrokes;
	ks.value = "" == ks.value? 0 : ks.value;
	ks.value = parseInt(ks.value) + sourceField.value.length + 1;
	console.log("keystrokes: " + ks.value);
}
function updateCorrectKeystrokesField(correctWord) {
	var cks = test.correctKeystrokes;
	cks.value = "" == cks.value? 0 : cks.value;
	cks.value = parseInt(cks.value) + correctWord.value.length + 1;
}
function updateDataField(fieldName) {
	var value = test[fieldName].value;
	value = "" == value? 1 : value;
	test[fieldName].value = parseInt(value) + 1;
	console.log(fieldName + ": " + value);
}
function preventTabBehavior(event) {
	if (9 == event.which) {
		event.preventDefault();
		return false;
	}
	return true;
}
function preventEnterBehavior(event) {
	if (13 == event.which) {
		event.preventDefault();
		return false;
	}
	return true;
}
function onStartedTest() {
	if (test.started) return;
	test.started = true;
	timer.startClock();
	setTimeout(onFinishedTest, 60 * 1000);
}
function onFinishedTest() {
	clearInterval(timer.intervalID);
	//bug fix:
	var fields = document.getElementById("test-form").children;
	for(var i in fields) {
		if ("" == fields[i].value){
			fields[i].value = "0";
		}
	}
	document.getElementById("sample").setAttribute("style", "display: none;");
	document.getElementById("timer-box").setAttribute("style", "display: none;");
	document.getElementById("test-form").submit();
}
function onTyping(event) {
	onStartedTest();
	var curr = test.children[test.currentWord].innerHTML;
	var typed = getTypedWord();
	typed = typed.replace(" ", "");
	console.log("length: " + typed.length);
	if (typed.length > 0 && curr.indexOf(typed) == -1) {
		setInputBoxStyle("wrong");
	}
	else {
		setInputBoxStyle("normal");
	}
}
window.addEventListener("load", function() {
	var container = document.getElementById("spacer");
	var typingArea =  document.getElementById("typing-area");
	fillSampleContainer(container, generateSample(TEST_SAMPLES["normal"], 350));
	typingArea.addEventListener("input", onTyping);
	typingArea.addEventListener("keyup", onSpacebarToClear);
	typingArea.addEventListener("keydown", onSpacebarNextWord);
	// prevents the user from changing the focus out of the typing box:
	typingArea.addEventListener("keydown", preventTabBehavior);
	// prevents the user from sending the form before the test ends:
	typingArea.addEventListener("keydown", preventEnterBehavior);
});