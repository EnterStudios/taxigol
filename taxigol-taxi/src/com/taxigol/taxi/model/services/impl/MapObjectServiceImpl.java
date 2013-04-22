package com.taxigol.taxi.model.services.impl;

import java.io.IOException;
import java.util.List;

import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.PostRequest;
import com.taxigol.taxi.model.MapObject;
import com.taxigol.taxi.model.services.MapObjectService;
import com.taxigol.taxi.model.services.parsers.MapObjectJsonParser;

public class MapObjectServiceImpl extends AbstractService<MapObject> implements
		MapObjectService {

	public MapObjectServiceImpl(String baseUrl,	Restz client) {
		super(baseUrl, "map_objects", client, new MapObjectJsonParser());
	}

	@Override
	public MapObject create(String category, double latitude, double longitude) throws IOException {
		PostRequest post = client.post(baseUrl+"/"+resourceName+".json", "category",category,"latitude",latitude+"","longitude",longitude+"");
		return parser.parse(post.getContent());
	}
	
	@Override
	public List<MapObject> getAll() throws IOException {
		List<MapObject> res = super.getAll();
		System.out.println(res);
		return res;
	}

}
