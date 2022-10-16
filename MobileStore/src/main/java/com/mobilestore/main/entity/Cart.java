package com.mobilestore.main.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Cart {
	
	@Id
	@GeneratedValue
	private int cartId;
	@OneToOne
	@JoinColumn(name="customer_fk",referencedColumnName="customerId")
	private Customer customer;
	@OneToMany
	@JoinColumn(name="cart_fk",referencedColumnName="cartId")
	private List<Mobile> mobiles;
	@Column
	private int quantity;
	@Column
	private int cost;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Mobile> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<Mobile> mobiles) {
		this.mobiles = mobiles;
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
	public Cart(int cartId, Customer customer, List<Mobile> mobiles, int quantity, int cost) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.mobiles = mobiles;
		this.quantity = quantity;
		this.cost = cost;
	}
	
}
