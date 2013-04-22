package com.taxigol.taxi.model.services.parsers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.taxigol.taxi.model.MapObject;

public class MapObjectJsonParser extends AbstractParser<MapObject> {

	@Override
	public Type getListType() {
		return new TypeToken<List<MapObject>>(){}.getType();
	}
	
	@Override
	public Class<MapObject> getType() {
		return MapObject.class;
	}

}
