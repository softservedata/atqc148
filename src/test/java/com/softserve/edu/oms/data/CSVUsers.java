package com.softserve.edu.oms.data;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.atqc.tools.CSVUtils;

public class CSVUsers {
	private static final String USERS_CSV_FILE_NAME = "/users.csv";

	public List<IUser> getAllUsers() {
		return getAllUsers(this.getClass()
				.getResource(USERS_CSV_FILE_NAME).getPath().substring(1));
	}

	public List<IUser> getAllUsers(String absoluteFilePath) {
		List<IUser> users = new ArrayList<IUser>();
		for (List<String> row : (new CSVUtils()).getAllCells(absoluteFilePath)) {
			users.add(User.get()
					.setLoginName(row.get(0))
					.setFirstName(row.get(1))
					.setLastName(row.get(2))
					.setPassword(row.get(3))
					.setEmail(row.get(4))
					.setRegion(row.get(5))
					.setRole(row.get(6))
					.build());
		}
		return users;
	}

}
