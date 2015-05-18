package com.softserve.edu.dao.order;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.softserve.edu.dao.orderstatus.OrderStatus;
import com.softserve.edu.dao.user.User;

@DatabaseTable(tableName = "Orders", daoClass = OrderDaoImpl.class)
public class Order {

	@DatabaseField(columnName = "ID", canBeNull = false)
	private int id;

	// datetime in mysql db, no idea how it will work with date
	@DatabaseField(columnName = "DeliveryDate")
	private Date deliveryDate;

	@DatabaseField(columnName = "IsGift")
	private boolean isGift;

	@DatabaseField(columnName = "MaxDiscount")
	private int maxDiscount;

	@DatabaseField(columnName = "OrderDate")
	private Date orderDate;

	@DatabaseField(columnName = "OrderName")
	private String orderName;

	@DatabaseField(columnName = "OrderNumber")
	private int orderNumber;

	@DatabaseField(columnName = "PreferableDeliveryDate")
	private Date preferableDeliveryDate;

	@DatabaseField(columnName = "TotalPrice")
	private double totalPrice;

	// assigne(link to User dao)
	@DatabaseField(columnName = "Assigne", foreign = true)
	private User assigne;

	// customer(dont need atm)
	@DatabaseField(columnName = "Customer")
	private int customer;

	// status(link to OrderStatus dao)
	@DatabaseField(columnName = "OrderStatusRef", foreign = true)
	private OrderStatus orderStatusRef;

	public Order() {

	}

	// public Order(int id, Date deliveryDate, boolean isGift, int maxDiscount,
	// Date orderDate, String orderName, int orderNumber,
	// Date preferableDeliveryDate, double totalPrice, int assigne,
	// int customer, int orderStatusRef) {
	// this.id = id;
	// this.deliveryDate = deliveryDate;
	// this.isGift = isGift;
	// this.maxDiscount = maxDiscount;
	// this.orderDate = orderDate;
	// this.orderName = orderName;
	// this.orderNumber = orderNumber;
	// this.preferableDeliveryDate = preferableDeliveryDate;
	// this.totalPrice = totalPrice;
	// this.assigne = assigne;
	// this.customer = customer;
	// this.orderStatusRef = orderStatusRef;
	// }

	public int getId() {
		return this.id;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public boolean getGift() {
		return this.isGift;
	}

	public double getMaxDiscount() {
		return this.maxDiscount;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public String dateToString() {
		if (this.deliveryDate == null) {
			return "";
		}
		String pattern = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String date = format.format(this.deliveryDate);
		// adding .0 to match format from ui
		return date + ".0";
	}

	public String getOrderName() {
		if (this.orderName == null) {
			return "";
		}
		return this.orderName;
	}

	public int getOrderNumber() {
		return this.orderNumber;
	}

	public Date getPreferableDeliveryDate() {
		return this.preferableDeliveryDate;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public User getAssigne() {
		return this.assigne;
	}

	public int getCustomer() {
		return this.customer;
	}

	public OrderStatus getOrderStatusRef() {
		return this.orderStatusRef;
	}

	public void print() {
		System.out.println("--------------------------------------");
		System.out.println("ID: " + this.id);
		System.out.println("Delivery Date: " + this.deliveryDate);
		System.out.println("Gift: " + this.isGift);
		System.out.println("Max Discount: " + this.maxDiscount);
		System.out.println("Order Date: " + this.orderDate);
		System.out.println("Order Name: " + this.orderName);
		System.out.println("Order Number: " + this.orderNumber);
		System.out.println("Preferable Delivery Date: "
				+ this.preferableDeliveryDate);
		System.out.println("Total Price: " + this.totalPrice);
		System.out.println("Assigne Login: " + this.assigne.getLogin());
		System.out.println("Assigne Role: "
				+ this.assigne.getRole().getRoleName());
		System.out.println("Customer: " + this.customer);
		System.out.println("Order Status: "
				+ this.orderStatusRef.getOrderStatusName());
	}

	public OrderFromUI toOrderToCompare() {
		return new OrderFromUI(getOrderName(), this.totalPrice,
				this.maxDiscount, dateToString(),
				this.orderStatusRef.getOrderStatusName(),
				this.assigne.getLogin(), this.assigne.getRole().getRoleName());
	}
}
