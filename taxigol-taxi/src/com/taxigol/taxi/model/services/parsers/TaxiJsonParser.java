package com.taxigol.taxi.model.services.parsers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.taxigol.taxi.model.Taxi;

public class TaxiJsonParser extends AbstractParser<Taxi>{

	
	@Override
	public Type getListType() {
		return new TypeToken<List<Taxi>>(){}.getType();
	}
	
	@Override
	public Class<Taxi> getType() {
		return Taxi.class;
	}
}
