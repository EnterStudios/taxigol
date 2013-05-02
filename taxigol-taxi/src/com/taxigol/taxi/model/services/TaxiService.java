package com.taxigol.taxi.model.services;

import com.taxigol.taxi.model.Taxi;

public interface TaxiService extends IService<Taxi>{

	public Taxi getTaxi(String id);
	public Taxi auth(String parseInstallationId) throws Exception;
}
