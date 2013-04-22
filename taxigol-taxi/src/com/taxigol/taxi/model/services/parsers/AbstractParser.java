package com.taxigol.taxi.model.services.parsers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.taxigol.taxi.model.ServerError;


public abstract class AbstractParser<T> implements JsonParser<T> {


	protected Gson gson;
	
	public AbstractParser() {
		gson = new Gson();
	}
	
	public boolean isError(String json){
		
		com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
		JsonElement el = parser.parse(json);
		JsonObject obj = null;
		try{
			obj = el.getAsJsonObject();
		}catch (IllegalStateException e){
			return false;
		}
		return obj.has("error") && obj.has("message");
		
	}
	
	public void throwIfError(String json) throws IOException
	{
		if (isError(json)){
			ServerError error = gson.fromJson(json, ServerError.class);
			throw new IOException(error.getError()+": "+error.getMessage());
		}
	}
	
	@Override
	public T parse(String json) throws IOException {
		return gson.fromJson(json, getType());
	}
	
	public abstract Class<T> getType();
	public abstract Type getListType();
	
	@Override
	public List<T> parseList(String json) throws IOException {
		throwIfError(json);
		
		List<T> result =  gson.fromJson(json, getListType());
		return result==null?new ArrayList<T>():result;
	}
	
	
}
