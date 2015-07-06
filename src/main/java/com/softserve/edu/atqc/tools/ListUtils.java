package com.softserve.edu.atqc.tools;

import java.util.List;

public class ListUtils {

	public static Object[][] toMultiArray(List<?> list) {
		Object[][] array = new Object[list.size()][1];
		for (int i = 0; i < list.size(); i++) {
			array[i][0] = list.get(i);
		}
		return array;
	}

	public static Object[][] toMultiArray(List<?> list, Object... parameters) {
		Object[][] array = new Object[list.size()][parameters.length + 1];
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < parameters.length; j++) {
				array[i][j] = parameters[j];
			}
			array[i][parameters.length] = list.get(i);
		}
		return array;
	}

}