package com.mycompany.oms.exceptions;

//Exception in case the user is not found
public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
