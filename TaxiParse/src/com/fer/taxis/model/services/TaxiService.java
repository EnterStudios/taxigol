package com.fer.taxis.model.services;

import java.io.IOException;

import com.fer.taxis.model.Taxi;

public interface TaxiService {

	public Taxi createTaxi() throws IOException;
	public Taxi getTaxi(String placa);
	public Taxi auth(String parseInstallationId) throws IOException;
}
