package com.taxigol.taxi.model.services.parsers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.taxigol.taxi.model.Panic;

public class PanicJsonParser extends AbstractParser<Panic>{


	@Override
	public Type getListType() {
		Type type = new TypeToken<List<Panic>>(){}.getType();
		return type;
	}
	
	@Override
	public Class<Panic> getType() {
		return Panic.class;
	}
}
