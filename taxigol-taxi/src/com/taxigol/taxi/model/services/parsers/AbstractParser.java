package com.taxigol.taxi.model.services.parsers;

import java.io.IOException;
import java.lang.reflect.Type;

import co.fernandohur.restz.parsers.GsonParser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.taxigol.taxi.model.ServerError;


public abstract class AbstractParser<T> extends GsonParser {
	
	private Gson gson;
	
	public AbstractParser() {
		super(new Gson());
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
	
	@Override
	public String getError(String json, Type type) {
		if (isError(json)){
			return ""+json;
		}
		return null;
	}
	
	public void throwIfError(String json) throws IOException
	{
		if (isError(json)){
			ServerError error = gson.fromJson(json, ServerError.class);
			throw new IOException(error.getError()+": "+error.getMessage());
		}
	}

	
	public abstract Class<T> getType();
	public abstract Type getListType();

	
	
}
