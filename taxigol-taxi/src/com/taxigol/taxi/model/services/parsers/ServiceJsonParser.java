package com.taxigol.taxi.model.services.parsers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.taxigol.taxi.model.Service;

public class ServiceJsonParser extends AbstractParser<Service> {

	@Override
	public Type getListType() {
		return new TypeToken<List<Service>>(){}.getType();
	}
	
	@Override
	public Class<Service> getType() {
		return Service.class;
	}
}
