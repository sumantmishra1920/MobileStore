package com.mycompany.oms.exceptions;

//Exception in case the category is not found
public class NoSuchCategoryException extends Exception{
	private static final long serialVersionUID = 1L;

	public NoSuchCategoryException(String msg) {
		super(msg);
	}
}
