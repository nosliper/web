package br.ufc.quixada.webdev;

public class User {
	private String username;
	private String fullName;
	private String email;
	private String birth;
	private String password;
	private String address;
	private String gender;
	private boolean receiveFeeds;
	public User(String username, String fullName, String email, String birth, String password, String address,
			String gender, boolean receiveFeeds) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.birth = birth;
		this.password = password;
		this.address = address;
		this.gender = gender;
		this.receiveFeeds = receiveFeeds;
	}
	public User() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isReceiveFeeds() {
		return receiveFeeds;
	}
	public void setReceiveFeeds(boolean receiveFeeds) {
		this.receiveFeeds = receiveFeeds;
	}
}
