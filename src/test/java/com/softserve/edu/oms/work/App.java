package com.softserve.edu.oms.work;

class App {

	void m1(A a) {
		System.out.println("m1(A a)");
	}

	void m1(B b) {
		System.out.println("m1(B b)");
	}

	public static void main(String[] args) {
		App app = new App();
		app.m1(new C());
		app.m1(new B());
		app.m1(null);
		//
		App2 app2 = new App2();
		app2.m2(new C());
		app2.m2(new B());
		app2.m2(null);

	}
}