package models;

public class User {

	private String login;
	private String password;
	private String name;
	private String region;
	private Boolean gender;
	private String comment;
	
	public User() {
	}
	
	public User(String login, String password, String name, String region, Boolean gender, String comment) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.region = region;
		this.gender = gender;
		this.comment = comment;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
