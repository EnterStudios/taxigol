package com.fer.taxis.model.services.parsers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.fer.taxis.model.Panic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PanicJsonParser implements JsonParser<Panic>{

	private Gson gson;
	
	public PanicJsonParser() {
		gson = new Gson();
	}
	
	@Override
	public Panic parse(String json) {
		return gson.fromJson(json, Panic.class);
	}
	
	@Override
	public List<Panic> parseList(String json) {
		Type type = new TypeToken<List<Panic>>(){}.getType();
		List<Panic> result =  gson.fromJson(json, type);
		return result==null?new ArrayList<Panic>():result;
	}
	
	
}
