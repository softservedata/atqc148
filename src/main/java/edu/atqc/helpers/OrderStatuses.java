package main.java.edu.atqc.helpers;

public enum OrderStatuses {
	None ("None",0),
	Created ("Created", 1),
	Pending ("Pending", 2),
	Ordered ("Ordered", 3),
	Delivered ("Delivered", 4);

	private final String name;
	private final int id;

	private OrderStatuses(String name, int id) {
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
