package br.quixada.ufc.web.ninjatypr.service;

import br.quixada.ufc.web.ninjatypr.model.SpeedTest;
import br.quixada.ufc.web.ninjatypr.model.User;

public class SpeedTestSetup {
	public static SpeedTest setTest(String keystrokes, String correctKs, String correct, String misspelled, User user) {
		SpeedTest test = new SpeedTest();
		keystrokes = "".equals(keystrokes)? "0" : keystrokes;
		correctKs = "".equals(correctKs)? "0" : correctKs;
		misspelled = "".equals(misspelled)? "0" : misspelled;
		correct = "".equals(misspelled)? "0" : correct;
		int ks = Integer.parseInt(keystrokes);
		int cks = Integer.parseInt(correctKs);
		int ms = Integer.parseInt(misspelled);
		int corr = Integer.parseInt(correct);
		int speedWPM = cks / 5;
		float accuracy = (cks * 100) / ks;
		test.setAccuracy(accuracy);
		test.setDificulty("normal");
		test.setKeystrokes(ks);
		test.setCorrectKeystrokes(cks);
		test.setSpeed(speedWPM);
		test.setCorrect(corr);
		test.setMisspelled(ms);
		test.setUser(user);
		return test;
	}
}