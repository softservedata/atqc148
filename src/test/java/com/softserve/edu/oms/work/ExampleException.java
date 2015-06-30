package com.softserve.edu.oms.work;

public class ExampleException {
	static void doSomthing(int n) throws MyException {
		if (n > 0) {
			int a = 100 / n;
		} else {
			// Creation and call exemption
			throw new MyException("input value is below zero!");
		}
	}

	public static void main(String[] args) {
		try {
			doSomthing(-1);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.exit(1);
			System.out.println("return start");
			return;
			//System.out.println("return DONE");
		} finally {
			System.out.println("HAHAHA");
		}
		System.out.println("after");
	}
}