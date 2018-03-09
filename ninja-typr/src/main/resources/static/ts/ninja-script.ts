/*
 * Ninja Typr
 * 2017
 * Diego Ávila
 * @nosliper at Github
 * Compile command: tsc ninja-script.ts --target es5 --outDir ../js --noImplicitThis
 */

class Util {

    /**
     * Returns the time in seconds from given string.
     * the passed time must be in the format M:SS.
     */
    public static timeFromString(timeStr: string): number {
        let time = timeStr.split(":");
        let min = parseInt(time[0]);
        let secs = parseInt(time[1]);
        return min * 60 + secs;
    }

    /**
     * Converts the time given in seconds into a string in the format M:SS.
     * 
     */
    public static timeToString(timeSec: number): string {
        let mins = Math.floor(timeSec / 60);
        timeSec -= mins * 60;
        return mins + ":" + (timeSec >= 10? timeSec : "0" + timeSec);
    }

    /**
     * Randomly generates an integer number between min and max, inclusive.
     */
    public static randInt(min: number, max: number): number {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    /**
     * Returns an object generated from a JSON file, given the path for the file.
     */
    public static objFromJSON(filePath: string): Object {
        let req: XMLHttpRequest = new XMLHttpRequest();
        req.open("GET", filePath, false);
        req.send();
        return JSON.parse(req.responseText);
    }
}

class Test {

    private index: number;
    private words: Word[];
    private keystrokes: HTMLInputElement;
    private correctKeystrokes: HTMLInputElement;
    private correctWords: HTMLInputElement;
    private misspelled: HTMLInputElement;
    public started: boolean;

    constructor() {
        this.index = 0;
        this.words = [];
        this.keystrokes = document.getElementById("keystrokes") as HTMLInputElement;
        this.correctKeystrokes = document.getElementById("correct-keystrokes") as HTMLInputElement;
        this.correctWords = document.getElementById("correct-words") as HTMLInputElement;
        this.misspelled = document.getElementById("misspelled") as HTMLInputElement;
        this.started = false;
    }

    public addWord(word: Word): void {
        this.words.push(word);
    }

    public goNext(): Word {
        if(this.index < this.words.length - 1) {
            this.index += 1;
            return this.words[this.index];
        }
        return undefined;
    }

    public current(): Word {
        return this.words[this.index];
    }

    public updateKeystrokes(qtd: number, correct: boolean = false): void {
        this.incrementField(this.keystrokes, qtd);
        if(correct) {
            this.incrementField(this.correctKeystrokes, qtd);
        }
    }

    public updateCorrectWords(): void {
        this.incrementField(this.correctWords);
    }

    public updateMisspelled(): void {
        this.incrementField(this.misspelled);
    }

    private incrementField(field: HTMLInputElement, qtd: number = 1): void {
        if(!field.value) {
            field.value = "0";
        }
        let oldValue: number = parseInt(field.value);
        field.value = String(oldValue + qtd);
    }
}

class Timer {

    private time: number;
    private callbackID: number;

    constructor(time: number = 1) {
        this.time = time * 60;
    }

    private updateTimerCallback(): void {
        let clock: HTMLElement = document.getElementById("timer");
        let currentTime: number = Util.timeFromString(clock.innerHTML) - 1;
        clock.innerHTML = Util.timeToString(currentTime);
    }

    public start(): void {
        this.callbackID = setInterval(() => this.updateTimerCallback(), 1000);
    }

    public stop(): void {
        clearInterval(this.callbackID);
    }

    public getTime(): number {
        return this.time;
    }
}

class Word {
    
    private element: HTMLSpanElement;
    
    constructor(value: string) {
        this.element = document.createElement("span");
        this.element.innerHTML = value;
        this.element.setAttribute("class", "sample-word");
    }

    public getValue(): string {
        return this.element.innerHTML;
    }

    public setValue(value: string): void {
        this.element.innerHTML = value;
    }

    public setStyle(style: string): void {
        let styles: string[] = ["normal", "highlight", "wrong", "correct"];
        if(styles.indexOf(style) != -1) {
            style = style.replace("normal", "");
            this.element.setAttribute("class", "sample-word-" + style);
        }
    }

    public appendTo(parent: HTMLElement): void {
        parent.appendChild(this.element);
    }

    public getElement(): HTMLSpanElement {
        return this.element;
    }
}

class NinjaTypr {

    private test: Test;
    private timer: Timer;
    private typingArea: HTMLInputElement;
    private sampleBox: HTMLDivElement;
    private limiter: number;

    constructor() {
        this.test = new Test();
        this.timer = new Timer(1);
        this.limiter = 0;
    }

    private generateSample(sourceStr: string, size: number): string[] {
        let sample: string[] = [];
        let source: string[] = sourceStr.split("|");
        let max = source.length - 1;
        for(let i = 0; i < size; i++) {
            sample.push(source[Util.randInt(0, max)]);
        }
        return sample;
    }

    private fillSampleBox(sample: string[]): void {
        for(let value of sample) {
            let word: Word = new Word(value);
            word.appendTo(this.sampleBox);
            this.test.addWord(word);
        }
        this.test.current().setStyle("highlight");
    }

    private updateSampleBox(): void {
        let top: string = this.sampleBox.style.top;
        top = top? String(parseInt(top) - 47) : "1";
        let outOfLimits: boolean = false;
        let wordW: number = this.test.current().getElement().offsetWidth;
        let boxW: number = this.sampleBox.offsetWidth;
        outOfLimits = this.limiter + wordW + 10 > boxW;
        if(outOfLimits) {
            this.sampleBox.setAttribute("style", "top:" + top + "px;"); 
            this.limiter = 0;
        }
    }

    public main(): void {
        this.typingArea = document.getElementById("typing-area") as HTMLInputElement;
        this.sampleBox = document.getElementById("spacer") as HTMLDivElement;
        window.addEventListener("load", () => {
            let source: Object = Util.objFromJSON("../../json/samples.json");
            let sample: string[] = this.generateSample(source["normal"], 400);
            let typing: (event) => any = (event) => this.onTyping();
            let next: (event) => any = (event) => this.onSpacebarNext(event);
            let tab: (event) => any = (event) => this.onTab(event);
            let enter: (event) => any = (event) => this.onEnter(event);
            this.fillSampleBox(sample);
            this.typingArea.addEventListener("input", typing);
            this.typingArea.addEventListener("keyup", next);
            this.typingArea.addEventListener("keydown", tab);
            this.typingArea.addEventListener("keydown", enter);
        });
    }

    private setTypingAreaStyle(style: string): void {
        if(style == "wrong" || style == "normal") {
            this.typingArea.setAttribute("class", "typing-area-" + style);
        }
    }

    // Must listen to 'keyup'
    private onSpacebarNext(event: KeyboardEvent): void {
        let typedContent: string = this.typingArea.value.replace(" ", "");
        let correct: boolean = typedContent == this.test.current().getValue();
        if(32 == event.which) {
            if(typedContent.length > 0) {
                this.test.updateKeystrokes(typedContent.length + 1, correct);
                this.limiter += this.test.current().getElement().offsetWidth + 10;
                this.updateSampleBox();
                if(correct) {
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
    }

    private onTab(event: KeyboardEvent): boolean {
        if(9 == event.which) {
            event.preventDefault();
            return false;
        }
        return true;
    }

    private onEnter(event: KeyboardEvent): boolean {
        if(13 == event.which) {
            event.preventDefault();
            return false;
        }
        return true;
    }
    
    private onTypingStartTest(): void {
        if(this.test.started) {
            return;
        }
        this.test.started = true;
        this.timer.start();
        setTimeout(() => this.onFinishedTest(), this.timer.getTime() * 1000);
    }

    private onTyping(): void {
        this.onTypingStartTest();
        let typedContent: string = this.typingArea.value.replace(" ", "");
        let curr: string = this.test.current().getValue();
        if(typedContent.length > 0 && curr.indexOf(typedContent) == -1) {
            this.setTypingAreaStyle("wrong");
        }
        else {
            this.setTypingAreaStyle("normal");
        }
    }

    private onFinishedTest(): void {
        this.timer.stop();
        let testForm: HTMLFormElement = document.getElementById("test-form") as HTMLFormElement;
        testForm.submit();
        document.getElementById("sample").setAttribute("style", "display: none;");
	    document.getElementById("timer-box").setAttribute("style", "display: none;");
    }
}

 let nt: NinjaTypr = new NinjaTypr();
 nt.main();