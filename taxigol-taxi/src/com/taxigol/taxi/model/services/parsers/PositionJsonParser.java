package com.taxigol.taxi.model.services.parsers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.taxigol.taxi.model.Position;

public class PositionJsonParser extends AbstractParser<Position>{

	@Override
	public Type getListType() {
		return new TypeToken<List<Position>>(){}.getType();
	}
	
	@Override
	public Class<Position> getType() {
		return Position.class;
	}
	
	
}
