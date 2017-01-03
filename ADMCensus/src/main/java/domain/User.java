package domain;

public class User {
	
	private String username;
	private String password;
	private String email;
	private String genre;
	private String autonomousCommunity;
	private int age;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPasswod(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAutonomousCommunity() {
		return autonomousCommunity;
	}

	public void setAutonomousCommunity(String autonomousCommunity) {
		this.autonomousCommunity = autonomousCommunity;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
