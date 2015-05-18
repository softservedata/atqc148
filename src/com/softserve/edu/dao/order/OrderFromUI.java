package com.softserve.edu.dao.order;

public class OrderFromUI {

	String orderName;
	double totalPrice;
	int maxDiscount;
	String deliveryDate;
	String status;
	String assigne;
	String role;

	OrderFromUI() {

	}

	// effective java book constructors with multiple parameters
	public OrderFromUI(String orderName, double totalPrice, int maxDiscount,
			String deliveryDate, String status, String assigne, String role) {
		this.orderName = orderName;
		this.totalPrice = totalPrice;
		this.maxDiscount = maxDiscount;
		// if date contains . in the end all fails
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.assigne = assigne;
		this.role = role;
	}

	public void print() {
		System.out.println("Order name: " + this.orderName);
		System.out.println("Total price: " + this.totalPrice);
		System.out.println("discount: " + this.maxDiscount);
		System.out.println("Delivery date: " + this.deliveryDate);
		System.out.println("status: " + this.status);
		System.out.println("assigne: " + this.assigne);
		System.out.println("Role: " + this.role);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof OrderFromUI))
			return false;
		OrderFromUI ord = (OrderFromUI) o;
		boolean name = ord.orderName.equals(orderName);
		boolean price = ord.totalPrice == totalPrice;
		boolean discount = ord.maxDiscount == maxDiscount;
		boolean date = deliveryDate.equals(deliveryDate);
		boolean stat = status.equals(status);
		boolean assign = ord.assigne.equals(assigne);
		boolean rol = ord.role.equals(role);
		return name && price && discount && date && stat && assign && rol;
	}
}
