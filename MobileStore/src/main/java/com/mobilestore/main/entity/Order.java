package com.mobilestore.main.entity;

import java.time.LocalDate;
//import java.util.List;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Orders")
public class Order {
	
	@Id
	@GeneratedValue
	private int orderId;
	@OneToMany
	@JoinColumn(name="order_fk",referencedColumnName="orderId")
	private List<Mobile> mobiles;
	@Column
	private LocalDate orderDate;
	@Column
	private LocalDate dispatchDate;
	@Column
	private int quantity;
	@Column
	private int cost;
	@Column
	private int totalCost;
	@ManyToOne
	private Customer customer;
	@Column
	private String status;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public List<Mobile> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<Mobile> mobiles) {
		this.mobiles = mobiles;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDate getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Order(int orderId, List<Mobile> mobiles, LocalDate orderDate, LocalDate dispatchDate, int quantity, int cost,
			int totalCost, Customer customer, String status) {
		super();
		this.orderId = orderId;
		this.mobiles = mobiles;
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.quantity = quantity;
		this.cost = cost;
		this.totalCost = totalCost;
		this.customer = customer;
		this.status = status;
	}
	
}
