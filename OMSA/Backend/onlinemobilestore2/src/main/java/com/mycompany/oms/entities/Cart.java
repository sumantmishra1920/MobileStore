package com.mycompany.oms.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//Cart entity
@Entity
@Table(name="Carts")
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id",nullable=false)
	private int cartId;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cart_id",referencedColumnName="cart_id")
	private List<Mobile> mobiles;
	@Column(name="quantity",nullable=false)
	private int quantity;
	@Column(name="cost",nullable=false)
	private int cost;
	public Cart() {
		
	}
	public Cart(int cartId, Customer customer, List<Mobile> mobiles, int quantity, int cost) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.mobiles = mobiles;
		this.quantity = quantity;
		this.cost = cost;
	}
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
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customer=" + customer + ", mobiles=" + mobiles + ", quantity=" + quantity
				+ ", cost=" + cost + "]";
	}
	
}
