package com.softserve.edu.oms.logics;

import com.softserve.edu.oms.pages.CustomerHomePage;

public class CustomerHomePageLogic extends HomePageLogic {
	// Elements
	private CustomerHomePage customerHomePage;

	public CustomerHomePageLogic(CustomerHomePage customerHomePage) {
		super(customerHomePage);
		this.customerHomePage = customerHomePage;
	}

	// getters

	public CustomerHomePage getCustomerHomePage() {
		return customerHomePage;
	}

	// business

	public OrderingPageLogic orderingClick() {
		// TODO this.customerHomePage ... click();
		return new OrderingPageLogic();
	}

}
