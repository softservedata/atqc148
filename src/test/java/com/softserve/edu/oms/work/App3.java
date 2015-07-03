package com.softserve.edu.oms.work;

public class App3 {
	private static final String BR_DIV_IMG = "<br><div><image style=max-width:100%% src= %s  /></div>";

	public static void main(String[] args) {
		System.out.println(String.format(BR_DIV_IMG, "+++"));
		System.out.println("PATH: "
				+ App3.class.getResource("/users.properties").getPath().substring(1));
	}
}
