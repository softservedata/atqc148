package com.softserve.edu.page.login;

public enum User {
	CUSTOMER("login1", "qwerty", "Customer"), MERCHANDISER("login2", "qwerty",
			"Merchandiser");
	private String login;
	private String password;
	private String role;

	private User(String login, String password, String role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}

	public String getRole() {
		return this.role;
	}
}
