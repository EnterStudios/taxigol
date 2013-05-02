package com.taxigol.taxi.model.services.impl;

import co.fernandohur.restz.Restz;

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
	public Taxi auth(String parseInstallationId) throws Exception {
		Taxi taxi = client.post(baseUrl+"/"+resourceName+"/auth.json", Taxi.class, "installation_id",parseInstallationId);
		return taxi;
	}

}
