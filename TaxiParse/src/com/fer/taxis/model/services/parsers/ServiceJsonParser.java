package com.fer.taxis.model.services.parsers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.fer.taxis.model.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ServiceJsonParser implements JsonParser<Service> {

	private Gson gson;
	
	public ServiceJsonParser() {
		this.gson = new Gson();
	}
	
	@Override
	public Service parse(String json) {
		return gson.fromJson(json, Service.class);
	}
	
	@Override
	public List<Service> parseList(String json) {
		Type type = new TypeToken<List<Service>>(){}.getType();
		List<Service> result =  gson.fromJson(json, type);
		return result==null?new ArrayList<Service>():result;
	}
}
