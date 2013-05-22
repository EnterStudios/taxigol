package com.taxigol.taxi.model;

public class Driver {

	private String cedula;
	private String name;
	private int taxi_id;
	private String password;
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTaxi_id() {
		return taxi_id;
	}
	public void setTaxi_id(int taxi_id) {
		this.taxi_id = taxi_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "< "+cedula+" >";
	}
	
}
