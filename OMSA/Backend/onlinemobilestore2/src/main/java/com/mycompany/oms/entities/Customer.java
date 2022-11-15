package com.mycompany.oms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//Customer entity
@Entity
@Table(name="Customers")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id",nullable=false)
	private int customerId;
	@Column(name="customer_name",nullable=false)
	@NotEmpty(message="Customer name cannot be blank")
	@Size(min=2, message="Customer name should be atleast 2 characters long")
	private String customerName;
	@Column(name="email_id",nullable=false)
	@NotEmpty(message="Email cannot be blank")
	@Email(message="Please enter a valid email address")
	private String emailId;
	@Column(name="mobile_number",nullable=false)
	@NotNull(message="Mobile number cannot be null")
	private Long mobileNumber;
	@Column(name="address",nullable=false)
	@NotEmpty(message="Address cannot be blank")
	private String address;
	public Customer() {
		
	}
	public Customer(int customerId, String customerName, String emailId, Long mobileNumber, String address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", emailId=" + emailId
				+ ", mobileNumber=" + mobileNumber + ", address=" + address + "]";
	}
	
	
}
