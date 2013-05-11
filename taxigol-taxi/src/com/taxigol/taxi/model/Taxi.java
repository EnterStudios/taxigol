package com.taxigol.taxi.model;

public class Taxi {
	
	private String id;
	private String installation_id;

	public Taxi(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getInstallationId() {
		return installation_id;
	}
	
	
}
