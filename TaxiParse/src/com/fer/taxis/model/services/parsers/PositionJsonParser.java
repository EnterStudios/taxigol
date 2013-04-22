package com.fer.taxis.model.services.parsers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.fer.taxis.model.Position;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PositionJsonParser implements JsonParser<Position>{

	private Gson gson;
	
	public PositionJsonParser() {
		gson = new Gson();
	}
	
	@Override
	public Position parse(String json) {
		return gson.fromJson(json, Position.class);
	}
	
	@Override
	public List<Position> parseList(String json) {
		Type type = new TypeToken<List<Position>>(){}.getType();
		List<Position> result =  gson.fromJson(json, type);
		return result==null?new ArrayList<Position>():result;
	}
	
	
}
