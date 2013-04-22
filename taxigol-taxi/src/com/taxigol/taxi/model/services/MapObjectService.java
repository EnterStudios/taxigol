package com.taxigol.taxi.model.services;

import java.io.IOException;

import com.taxigol.taxi.model.MapObject;

public interface MapObjectService extends IService<MapObject>{

	public MapObject create(String category, double latitude, double longitude) throws IOException;
	
}
