package com.mycompany.oms.exceptions;

//Exception in case the order is not found
public class OrderNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String msg) {
		super(msg);
	}
}
