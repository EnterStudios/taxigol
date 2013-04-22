package com.taxigol.taxi.model.services.impl;

import java.io.IOException;

import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.PostRequest;
import com.taxigol.taxi.model.Taxi;
import com.taxigol.taxi.model.services.TaxiService;
import com.taxigol.taxi.model.services.parsers.TaxiJsonParser;

public class TaxiServiceImpl extends AbstractService<Taxi> implements TaxiService {

	public TaxiServiceImpl(String baseUrl, Restz client) {
		super(baseUrl, "taxis", client, new TaxiJsonParser());
	}
	
	@Override
	public Taxi getTaxi(String placa) {
		return new Taxi(placa);
	}

	
	@Override
	public Taxi auth(String parseInstallationId) throws IOException {
		PostRequest post = client.post(baseUrl+"/"+resourceName+"/auth.json","installation_id",parseInstallationId);
		String json = post.getContent();
		return parser.parse(json);
	}

}
