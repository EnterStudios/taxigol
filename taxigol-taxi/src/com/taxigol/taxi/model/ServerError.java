package com.taxigol.taxi.model;

public class ServerError {

	private String error;
	private String message;
	
	public ServerError(String error, String message) {
		super();
		this.error = error;
		this.message = message;
	}
	
	public String getError() {
		return error;
	}
	public String getMessage() {
		return message;
	}
	
	
}
