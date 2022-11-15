package com.mycompany.oms.exceptions;

//Exception in case a record already exists in the database and its is not permitted to add another record
public class RecordExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public RecordExistsException(String msg) {
		super(msg);
	}
}
