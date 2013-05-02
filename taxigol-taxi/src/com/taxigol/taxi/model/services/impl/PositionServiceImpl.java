package com.taxigol.taxi.model.services.impl;

import co.fernandohur.restz.Restz;

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
	public Position updatePosition(String taxiId, double latitude, double longitude) throws Exception {
		return client.post(baseUrl+"/positions.json",parser.getType(), "taxi_id",taxiId,"latitude",""+latitude,"longitude",""+longitude);
	}


}
