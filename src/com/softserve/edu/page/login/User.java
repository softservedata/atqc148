package com.softserve.edu.page.login;

public class User implements IUser {

	String firstName;
	String lastName;
	String region;
	String role;
	String login;
	String password;
	String email;
	
	
	public User(){
		
	}
	
	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}
	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return this.lastName;
	}
	@Override
	public String getRegion() {
		// TODO Auto-generated method stub
		return this.region;
	}
	@Override
	public String getLogin() {
		// TODO Auto-generated method stub
		return this.login;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return this.role;
	}
	
	
}
