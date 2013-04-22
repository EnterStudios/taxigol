package com.taxigol.taxi.model;

import com.taxigol.taxi.R;

public class MapObject {

	public enum Category{hueco, accidente, gasolina, trancon}
	
	private double latitude;
	private double longitude;
	private String category;

	public MapObject() {}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getCategory() {
		return category;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "<latitude="+latitude+ " longitude="+longitude+ " category="+category+">";
	}
	
	public int getIconResource(){
		Category cat = Category.valueOf(category);
		if (cat.equals(Category.hueco)){
			return R.drawable.map_hole;
		}
		else if (cat.equals(Category.trancon)){
			return R.drawable.map_traffic_jam;
		}
		else if (cat.equals(Category.accidente)){
			return R.drawable.map_accident;
		}
		else if (cat.equals(Category.gasolina)){
			return R.drawable.map_gas_stations;
		}
		throw new IllegalArgumentException("Unrecognized category '"+category+"'");
	}


}
