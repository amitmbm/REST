package com.ami.exceptions;

public class ResourceNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8539141639213987877L;

	private String message;
	
	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(String message){
		super(message);
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
