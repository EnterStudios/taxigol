package com.fer.taxis.model;

import java.util.Date;

public class Position {

	private double latitude;
	private double longitude;
	private int taxi_id;
	private Date created_at;
	private int id;
	
	public Position(float latitude, float longitude, int taxi_id, Date created_at, int id) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.taxi_id = taxi_id;
		this.created_at = created_at;
	}
	
	public Date getCreatedAt() {
		return created_at;
	}

	public String toString(){
		return "lat:"+latitude+", lon:"+longitude + ", id:"+taxi_id;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int getTaxiId() {
		return taxi_id;
	}
	
	public int getId(){
		return id;
	}
	
}
