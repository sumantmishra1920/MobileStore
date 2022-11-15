package com.mycompany.oms.exceptions;

//Exception in case the cart is not found
public class CartNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public CartNotFoundException(String msg) {
		super(msg);
	}
}
