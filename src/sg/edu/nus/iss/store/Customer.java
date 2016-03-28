package sg.edu.nus.iss.store;

/*
 * Customer class:to to create Customer Object (member + non member).
 * Author: Sanskar Deepak
 */

public class Customer {
	private String customerName;
	
	public Customer(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	

}
