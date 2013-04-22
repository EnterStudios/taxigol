package com.taxigol.taxi.model;

public class Service {

	private int id;
	private String state;
	private String taxi_id;
	private String verification_code;
	private String address;
	
	public Service(int id, String state, String verification_code,
			String address, String taxi_id) {
		super();
		this.id = id;
		this.state = state;
		this.verification_code = verification_code;
		this.address = address;
		this.taxi_id = taxi_id;
	}
	
	public int getId() {
		return id;
	}

	public String getState() {
		return state;
	}
 
	public String getVerification_code() {
		return verification_code;
	}

	public String getAddress() {
		return address;
	}

	public String getTaxiId(){
		return taxi_id;
	}
	
	@Override
	public String toString() {
		return "<"+state+","+address+">";
	}
	
}
