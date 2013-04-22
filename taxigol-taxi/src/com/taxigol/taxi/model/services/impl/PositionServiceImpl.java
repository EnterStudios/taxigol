package com.taxigol.taxi.model.services.impl;

import java.io.IOException;

import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.PostRequest;
import com.taxigol.taxi.model.Position;
import com.taxigol.taxi.model.services.PositionService;
import com.taxigol.taxi.model.services.parsers.PositionJsonParser;

public class PositionServiceImpl extends AbstractService<Position> implements PositionService {

	private Restz client;
	
	public PositionServiceImpl(Restz client, String baseUrl) {
		super(baseUrl, "positions", client, new PositionJsonParser());
		this.client = client;
		this.baseUrl = baseUrl;
	}
	
	@Override
	public Position updatePosition(String taxiId, double latitude, double longitude) throws IOException {
		PostRequest post = client.post(baseUrl+"/positions.json", "taxi_id",taxiId,"latitude",""+latitude,"longitude",""+longitude);
		String json = post.getContent();
		return parser.parse(json);
	}


}
