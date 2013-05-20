package com.taxigol.taxi.model.services.impl;

import co.fernandohur.restz.Restz;

import com.taxigol.taxi.model.Driver;
import com.taxigol.taxi.model.services.DriverService;
import com.taxigol.taxi.model.services.parsers.AbstractParser;

public class DriverServiceImpl extends AbstractService<Driver> implements DriverService {

	public DriverServiceImpl(String baseUrl, Restz client) {
		super(baseUrl, "drivers", client, parser);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Driver create(String name, String cedula, String password,
			String placa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Driver auth(String cedula, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
