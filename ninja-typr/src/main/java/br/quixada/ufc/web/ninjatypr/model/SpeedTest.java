package br.quixada.ufc.web.ninjatypr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class SpeedTest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String dificulty;
	
	@NotNull
	private Integer keystrokes;
	
	@NotNull
	private Integer correctKeystrokes;
	
	@NotNull
	private Integer correct;
	
	@NotNull
	private Integer misspelled;
	
	@NotNull
	private Integer speed;
	
	@NotNull
	private float accuracy;
	
	@ManyToOne
	private User user;
	
	public SpeedTest() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDificulty() {
		return dificulty;
	}
	public void setDificulty(String dificulty) {
		this.dificulty = dificulty;
	}
	public Integer getKeystrokes() {
		return keystrokes;
	}
	public void setKeystrokes(int keystrokes) {
		this.keystrokes = keystrokes;
	}
	public Integer getCorrectKeystrokes() {
		return correctKeystrokes;
	}
	public void setCorrectKeystrokes(Integer correctKeystrokes) {
		this.correctKeystrokes = correctKeystrokes;
	}
	public Integer getCorrect() {
		return correct;
	}
	public void setCorrect(Integer correct) {
		this.correct = correct;
	}
	public Integer getMisspelled() {
		return misspelled;
	}
	public void setMisspelled(Integer misspelled) {
		this.misspelled = misspelled;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(accuracy);
		result = prime * result + ((correct == null) ? 0 : correct.hashCode());
		result = prime * result + ((dificulty == null) ? 0 : dificulty.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keystrokes == null) ? 0 : keystrokes.hashCode());
		result = prime * result + ((misspelled == null) ? 0 : misspelled.hashCode());
		result = prime * result + ((speed == null) ? 0 : speed.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpeedTest other = (SpeedTest) obj;
		if (Float.floatToIntBits(accuracy) != Float.floatToIntBits(other.accuracy))
			return false;
		if (correct == null) {
			if (other.correct != null)
				return false;
		} else if (!correct.equals(other.correct))
			return false;
		if (dificulty == null) {
			if (other.dificulty != null)
				return false;
		} else if (!dificulty.equals(other.dificulty))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keystrokes == null) {
			if (other.keystrokes != null)
				return false;
		} else if (!keystrokes.equals(other.keystrokes))
			return false;
		if (misspelled == null) {
			if (other.misspelled != null)
				return false;
		} else if (!misspelled.equals(other.misspelled))
			return false;
		if (speed == null) {
			if (other.speed != null)
				return false;
		} else if (!speed.equals(other.speed))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
