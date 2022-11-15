package com.mycompany.oms.exceptions;

//Exception in case the mobile is not found
public class MobileNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public MobileNotFoundException(String msg) {
		super(msg);
	}
}