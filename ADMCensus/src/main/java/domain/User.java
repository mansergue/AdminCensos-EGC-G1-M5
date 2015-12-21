package domain;

public class User {
	private int u_id;
	private String username;
	private String email;
	private String genre;
	private String autonomous_community;
	private int age;

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getAutonomous_community() {
		return autonomous_community;
	}

	public void setAutonomous_community(String autonomous_community) {
		this.autonomous_community = autonomous_community;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
