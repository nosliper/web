package br.quixada.ufc.web.ninjatypr.service;

import java.util.List;
import java.util.Locale;

import br.quixada.ufc.web.ninjatypr.model.SpeedTest;
import br.quixada.ufc.web.ninjatypr.model.User;

public class UserStats {
	private User user;
	private Long allTimeWords = 0L;
	private int averageSpeed = 0;
	private double averageAccuracy = 0.0;
	private int testsTaken = 0;
	private int topSpeed = 0;
	public UserStats(User user) {
		this.user = user;
		List<SpeedTest> tests = user.getTests();
		testsTaken = tests.size();
		if (user != null && testsTaken > 0) {
			for (SpeedTest test: tests) {
				allTimeWords += test.getCorrect() + test.getMisspelled();
				topSpeed = test.getSpeed() > topSpeed? test.getSpeed() : topSpeed;
				averageAccuracy += test.getAccuracy();
				averageSpeed += test.getSpeed();
			}
			averageAccuracy /= testsTaken;
			averageSpeed /= testsTaken;
			averageAccuracy = Double.parseDouble(String.format(Locale.US, "%.2f", averageAccuracy));
	
		}
	}
	public UserStats() {
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getAllTimeWords() {
		return allTimeWords;
	}
	public void setAllTimeWords(Long allTimeWords) {
		this.allTimeWords = allTimeWords;
	}
	public int getAverageSpeed() {
		return averageSpeed;
	}
	public void setAverageSpeed(int averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	public double getAverageAccuracy() {
		return averageAccuracy;
	}
	public void setAverageAccuracy(double averageAccuracy) {
		this.averageAccuracy = averageAccuracy;
	}
	public int getTestsTaken() {
		return testsTaken;
	}
	public void setTestsTaken(int testsTaken) {
		this.testsTaken = testsTaken;
	}
	public int getTopSpeed() {
		return topSpeed;
	}
	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}
}
