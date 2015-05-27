package com.softserve.edu.dao.order;

import java.util.LinkedList;
import java.util.List;

public class OrderFromUI {

	private String orderName;
	private String totalPrice;
	private String maxDiscount;
	private String deliveryDate;
	private String status;
	private String assigne;
	private String role;

	private OrderFromUI() {
	}

	// effective java book constructors with multiple parameters
	public OrderFromUI(String orderName, String totalPrice, String maxDiscount,
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

	public static OrderFromUI get(){
		return new OrderFromUI();
	}
	
	
	public OrderFromUI setOrderName(String val) {
		this.orderName = val;
		return this;
	}
	
	public OrderFromUI setTotalPrice(String val) {
		this.totalPrice = val;
		return this;
	}

	public OrderFromUI setMaxDiscount(String val) {
		this.maxDiscount = val;
		return this;
	}

	public OrderFromUI setDeliveryDate(String val) {
		this.deliveryDate = val;
		return this;
	}
	
	public OrderFromUI setStatus(String val) {
		this.status = val;
		return this;
	}
	
	public OrderFromUI setAssigne(String val) {
		this.assigne = val;
		return this;
	}

	public OrderFromUI setRole(String val) {
		this.role = val;
		return this;
	}

	public OrderFromUI build() {
		return this;
	}

	public List<OrderFromUI> filterByStatus(List<OrderFromUI> list,
			String filter) {
		List<OrderFromUI> filteredOrders = new LinkedList<OrderFromUI>();
		for (OrderFromUI o : list) {
			if (o.status == filter) {
				filteredOrders.add(o);
			}
		}
		return filteredOrders;
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
		boolean price = ord.totalPrice.equals(totalPrice);
		boolean discount = ord.maxDiscount.equals(maxDiscount);
		boolean date = deliveryDate.equals(deliveryDate);
		boolean stat = status.equals(status);
		boolean assign = ord.assigne.equals(assigne);
		boolean rol = ord.role.equals(role);
		return name && price && discount && date && stat && assign && rol;
	}

	public String getOrderName() {
		return orderName;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public String getMaxDiscount() {
		return maxDiscount;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public String getStatus() {
		return status;
	}

	public String getAssigne() {
		return assigne;
	}

	public String getRole() {
		return role;
	}

}
