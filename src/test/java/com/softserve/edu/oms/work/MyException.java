package com.softserve.edu.oms.work;

class MyException extends Exception {
	private static final long serialVersionUID = 1L;

	// Classic constructor with a message of error
	public MyException(String msg) {
		super(msg);
	}

	// Empty constructor
	public MyException() {
	}
}
