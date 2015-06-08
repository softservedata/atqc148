package main.java.edu.atqc.helpers;

public enum UserRoles {

	None ("None",0),
	Administrator ("Administrator", 1),
	Customer ("Customer", 2),
	Merchandiser ("Merchandiser", 3),
	Supervisor ("Supervisor", 4);

	private final String name;
	private final int id;

	private UserRoles(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}

	public String toString() {
		return name + "_" + id;
	}
	
}
