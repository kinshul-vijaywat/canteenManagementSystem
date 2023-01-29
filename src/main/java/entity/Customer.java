package entity;

import java.util.List;

public class Customer {

	private Integer customerId;
	private String customerName;
	private String customerEmail;
	private String mobileNumber;
	private String address;
	private String password;
	private List<OrderDetails> previousOrders;	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<OrderDetails> getPreviousOrders() {
		return previousOrders;
	}
	public void setPreviousOrders(List<OrderDetails> previousOrders) {
		this.previousOrders = previousOrders;
	}
	
	
	
}
