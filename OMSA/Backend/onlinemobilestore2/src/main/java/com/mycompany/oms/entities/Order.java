package com.mycompany.oms.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//Order entity
@Entity
@Table(name="Orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id",nullable=false)
	private int orderId;
	@Column(name="order_date",nullable=false)
	private LocalDate orderDate;
	@Column(name="dispatch_date",nullable=false)
	private LocalDate dispatchDate;
	@Column(name="cost",nullable=false)
	private int cost;
	@Column(name="total_cost",nullable=false)
	private int totalCost;
	@Column(name="status",nullable=false)
	private String status;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="mobile_id",referencedColumnName="mobile_id")
	private Mobile mobile;
	public Order() {
		
	}
	public Order(int orderId, LocalDate orderDate, LocalDate dispatchDate, int cost, int totalCost,
			String status, Customer customer, Mobile mobile) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		//this.quantity = quantity;
		this.cost = cost;
		this.totalCost = totalCost;
		this.status = status;
		this.customer = customer;
		this.mobile = mobile;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Mobile getMobile() {
		return mobile;
	}
	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", dispatchDate=" + dispatchDate
				 + ", cost=" + cost + ", totalCost=" + totalCost + ", status=" + status
				+ ", customer=" + customer + ", mobile=" + mobile + "]";
	}
	
	
}
