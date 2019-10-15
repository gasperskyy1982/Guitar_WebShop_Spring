package dao;

import models.User;

public interface UserDAO {

	public User createUser(String login, String password, String name, String region, boolean gender, String comment);
			
	public void setUser(String login, String password, String name, String region, boolean gender, String comment);

	public void correctUser(String login, String password, String name, String region, boolean gender, String comment);
	
	public boolean getUniqueLogin(String login);

	public boolean getAuth(String login, String password);
	
	
}

