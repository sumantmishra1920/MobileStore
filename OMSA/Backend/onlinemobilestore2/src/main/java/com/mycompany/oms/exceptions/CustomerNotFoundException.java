package com.mycompany.oms.exceptions;

//Exception in case the customer is not found
public class CustomerNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String msg) {
		super(msg);
	}
}
