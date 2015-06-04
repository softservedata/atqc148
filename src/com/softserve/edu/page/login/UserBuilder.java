package com.softserve.edu.page.login;

interface ILoginName {
	IFirstName setLoginName(String loginName);
}

interface IFirstName {
	ILastName setFirstName(String firstName);
}

interface ILastName {
	IPassword setLastName(String lastName);
}

interface IPassword {
	IEmail setPassword(String password);
}

interface IEmail {
	IRegion setEmail(String email);
}

interface IRegion {
	IRole setRegion(String region);
}

interface IRole {
	IBuild setRole(String role);
}

interface IBuild {
	IUser build();
}

public class UserBuilder implements ILoginName, IFirstName, ILastName, IPassword,
		IEmail, IRegion, IRole, IBuild, IUser {
	private String loginName;
	private String firstName;
	private String lastName;
	private String password; 
	private String email;
	private String region;
	private String role;

	private UserBuilder() {
	}

	/**
	 * Create new user object.
	 * @param loginName login name.
	 * @param firstName first name.
	 * @param lastName last name.
	 * @param password password.
	 * @param email email.
	 * @param region region.
	 * @param role role.
	 */
	private UserBuilder(String loginName, String firstName, String lastName,
			String password, String email, String region, String role) {
		this.loginName = loginName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.region = region;
		this.role = role;
	}

	// - - - - - - - - - - - - - - - - - - - -

	public static ILoginName get() {
		return new UserBuilder();
	}
	
	public IFirstName setLoginName(String loginName) {
		this.loginName = loginName;
		return this;
	}

	public ILastName setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public IPassword setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public IEmail setPassword(String password) {
		this.password = password;
		return this;
	}

	public IRegion setEmail(String email) {
		this.email = email;
		return this;
	}

	public IRole setRegion(String region) {
		this.region = region;
		return this;
	}

	public IBuild setRole(String role) {
		this.role = role;
		return this;
	}

	public IUser build() {
		return this;
	}

	// - - - - - - - - - - - - - - - - - - - -

	public String getLogin() {
		return loginName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getRegion() {
		return region;
	}

	public String getRole() {
		return role;
	}

}