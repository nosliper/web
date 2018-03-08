/*
 * Ninja Typr
 * 2017
 * Diego Ávila
 * @nosliper at Github
 * Compile command: tsc ninja-script.ts --target es5 --outDir ../js --noImplicitThis
 */
var Util = /** @class */ (function () {
    function Util() {
    }
    /**
     * Returns the time in seconds from given string.
     * the passed time must be in the format M:SS.
     */
    Util.timeFromString = function (timeStr) {
        var time = timeStr.split(":");
        var min = parseInt(time[0]);
        var secs = parseInt(time[1]);
        return min * 60 + secs;
    };
    /**
     * Converts the time given in seconds into a string in the format M:SS.
     *
     */
    Util.timeToString = function (timeSec) {
        var mins = Math.floor(timeSec / 60);
        timeSec -= mins * 60;
        return mins + ":" + (timeSec >= 10 ? timeSec : "0" + timeSec);
    };
    /**
     * Randomly generates an integer number between min and max, inclusive.
     */
    Util.randInt = function (min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    };
    /**
     * Returns an object generated from a JSON file, given the path for the file.
     */
    Util.objFromJSON = function (filePath) {
        var req = new XMLHttpRequest();
        req.open("GET", filePath, false);
        req.send();
        return JSON.parse(req.responseText);
    };
    return Util;
}());
var Test = /** @class */ (function () {
    function Test() {
        this.index = 0;
        this.words = [];
        this.keystrokes = document.getElementById("keystrokes");
        this.correctKeystrokes = document.getElementById("correct-keystrokes");
        this.correctWords = document.getElementById("correct-words");
        this.misspelled = document.getElementById("misspelled");
        this.started = false;
    }
    Test.prototype.addWord = function (word) {
        this.words.push(word);
    };
    Test.prototype.goNext = function () {
        if (this.index < this.words.length - 1) {
            this.index += 1;
            return this.words[this.index];
        }
        return undefined;
    };
    Test.prototype.current = function () {
        return this.words[this.index];
    };
    Test.prototype.updateKeystrokes = function (qtd, correct) {
        if (correct === void 0) { correct = false; }
        this.incrementField(this.keystrokes, qtd);
        if (correct) {
            this.incrementField(this.correctKeystrokes, qtd);
        }
    };
    Test.prototype.updateCorrectWords = function () {
        this.incrementField(this.correctWords);
    };
    Test.prototype.updateMisspelled = function () {
        this.incrementField(this.misspelled);
    };
    Test.prototype.incrementField = function (field, qtd) {
        if (qtd === void 0) { qtd = 1; }
        if (!field.value) {
            field.value = "0";
        }
        var oldValue = parseInt(field.value);
        field.value = String(oldValue + qtd);
    };
    return Test;
}());
var Timer = /** @class */ (function () {
    function Timer(time) {
        if (time === void 0) { time = 1; }
        this.time = time * 60;
    }
    Timer.prototype.updateTimerCallback = function () {
        var clock = document.getElementById("timer");
        var currentTime = Util.timeFromString(clock.innerHTML) - 1;
        clock.innerHTML = Util.timeToString(currentTime);
    };
    Timer.prototype.start = function () {
        var _this = this;
        this.callbackID = setInterval(function () { return _this.updateTimerCallback(); }, 1000);
    };
    Timer.prototype.stop = function () {
        clearInterval(this.callbackID);
    };
    Timer.prototype.getTime = function () {
        return this.time;
    };
    return Timer;
}());
var Word = /** @class */ (function () {
    function Word(value) {
        this.element = document.createElement("span");
        this.element.innerHTML = value;
        this.element.setAttribute("class", "sample-word");
    }
    Word.prototype.getValue = function () {
        return this.element.innerHTML;
    };
    Word.prototype.setValue = function (value) {
        this.element.innerHTML = value;
    };
    Word.prototype.setStyle = function (style) {
        var styles = ["normal", "highlight", "wrong", "correct"];
        if (styles.indexOf(style) != -1) {
            style = style.replace("normal", "");
            this.element.setAttribute("class", "sample-word-" + style);
        }
    };
    Word.prototype.appendTo = function (parent) {
        parent.appendChild(this.element);
    };
    Word.prototype.getElement = function () {
        return this.element;
    };
    return Word;
}());
var NinjaTypr = /** @class */ (function () {
    function NinjaTypr() {
        this.test = new Test();
        this.timer = new Timer(1);
        this.limiter = 0;
    }
    NinjaTypr.prototype.generateSample = function (sourceStr, size) {
        var sample = [];
        var source = sourceStr.split("|");
        var max = source.length - 1;
        for (var i = 0; i < size; i++) {
            sample.push(source[Util.randInt(0, max)]);
        }
        return sample;
    };
    NinjaTypr.prototype.fillSampleBox = function (sample) {
        for (var _i = 0, sample_1 = sample; _i < sample_1.length; _i++) {
            var value = sample_1[_i];
            var word = new Word(value);
            word.appendTo(this.sampleBox);
            this.test.addWord(word);
        }
        this.test.current().setStyle("highlight");
    };
    NinjaTypr.prototype.updateSampleBox = function () {
        var top = this.sampleBox.style.top;
        top = top ? String(parseInt(top) - 47) : "1";
        var outOfLimits = false;
        var wordW = this.test.current().getElement().offsetWidth;
        var boxW = this.sampleBox.offsetWidth;
        outOfLimits = this.limiter + wordW + 10 > boxW;
        if (outOfLimits) {
            this.sampleBox.setAttribute("style", "top:" + top + "px;");
            this.limiter = 0;
        }
    };
    NinjaTypr.prototype.main = function () {
        var _this = this;
        this.typingArea = document.getElementById("typing-area");
        this.sampleBox = document.getElementById("spacer");
        window.addEventListener("load", function () {
            var source = Util.objFromJSON("../../json/samples.json");
            var sample = _this.generateSample(source["normal"], 400);
            var typing = function (event) { return _this.onTyping(); };
            var next = function (event) { return _this.onSpacebarNext(event); };
            var tab = function (event) { return _this.onTab(event); };
            var enter = function (event) { return _this.onEnter(event); };
            _this.fillSampleBox(sample);
            _this.typingArea.addEventListener("input", typing);
            _this.typingArea.addEventListener("keyup", next);
            _this.typingArea.addEventListener("keydown", tab);
            _this.typingArea.addEventListener("keydown", enter);
        });
    };
    NinjaTypr.prototype.setTypingAreaStyle = function (style) {
        if (style == "wrong" || style == "normal") {
            this.typingArea.setAttribute("class", "typing-area-" + style);
        }
    };
    // Must listen to 'keyup'
    NinjaTypr.prototype.onSpacebarNext = function (event) {
        var typedContent = this.typingArea.value.replace(" ", "");
        var correct = typedContent == this.test.current().getValue();
        if (32 == event.which) {
            if (typedContent.length > 0) {
                this.test.updateKeystrokes(typedContent.length + 1, correct);
                this.limiter += this.test.current().getElement().offsetWidth + 10;
                this.updateSampleBox();
                if (correct) {
                    this.test.current().setStyle("correct");
                    this.test.updateCorrectWords();
                }
                else {
                    this.test.current().setStyle("wrong");
                    this.test.updateMisspelled();
                }
                this.test.goNext();
                this.test.current().setStyle("highlight");
            }
            this.typingArea.value = "";
        }
    };
    NinjaTypr.prototype.onTab = function (event) {
        if (9 == event.which) {
            event.preventDefault();
            return false;
        }
        return true;
    };
    NinjaTypr.prototype.onEnter = function (event) {
        if (13 == event.which) {
            event.preventDefault();
            return false;
        }
        return true;
    };
    NinjaTypr.prototype.onTypingStartTest = function () {
        var _this = this;
        if (this.test.started) {
            return;
        }
        this.test.started = true;
        this.timer.start();
        setTimeout(function () { return _this.onFinishedTest(); }, this.timer.getTime() * 1000);
    };
    NinjaTypr.prototype.onTyping = function () {
        this.onTypingStartTest();
        var typedContent = this.typingArea.value.replace(" ", "");
        var curr = this.test.current().getValue();
        if (typedContent.length > 0 && curr.indexOf(typedContent) == -1) {
            this.setTypingAreaStyle("wrong");
        }
        else {
            this.setTypingAreaStyle("normal");
        }
    };
    NinjaTypr.prototype.onFinishedTest = function () {
        this.timer.stop();
        var testForm = document.getElementById("test-form");
        testForm.submit();
        document.getElementById("sample").setAttribute("style", "display: none;");
        document.getElementById("timer-box").setAttribute("style", "display: none;");
    };
    return NinjaTypr;
}());
var nt = new NinjaTypr();
nt.main();
