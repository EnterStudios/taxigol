package com.fer.taxis.model.services.impl;

import java.io.IOException;

import com.fer.taxis.model.Taxi;
import com.fer.taxis.model.services.TaxiService;
import com.fer.taxis.model.services.parsers.TaxiJsonParser;
import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.PostRequest;

public class TaxiServiceImpl extends AbstractService<Taxi> implements TaxiService {

	public TaxiServiceImpl(String baseUrl, Restz client) {
		super(baseUrl, "taxis", client, new TaxiJsonParser());

	}
	
	@Override
	public Taxi getTaxi(String placa) {
		return new Taxi(placa);
	}

	@Override
	public Taxi createTaxi() throws IOException{
		PostRequest post = client.post(baseUrl+"/"+resourceName+".json");
		String json = post.getContent();
		return parser.parse(json);
	}
	
	@Override
	public Taxi auth(String parseInstallationId) throws IOException {
		PostRequest post = client.post(baseUrl+"/"+resourceName+".json","installation_id",parseInstallationId);
		String json = post.getContent();
		return parser.parse(json);
	}

}
