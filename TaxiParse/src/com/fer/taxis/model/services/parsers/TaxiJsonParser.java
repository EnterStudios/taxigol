package com.fer.taxis.model.services.parsers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.fer.taxis.model.Taxi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TaxiJsonParser implements JsonParser<Taxi>{

	private Gson gson;
	
	public TaxiJsonParser() {
		gson = new Gson();
	}
	
	@Override
	public Taxi parse(String json) {
		return gson.fromJson(json, Taxi.class);
	}
	
	public java.util.List<Taxi> parseList(String json) {
		Type type = new TypeToken<List<Taxi>>(){}.getType();
		List<Taxi> result =  gson.fromJson(json, type);
		return result==null?new ArrayList<Taxi>():result;
	};
}
