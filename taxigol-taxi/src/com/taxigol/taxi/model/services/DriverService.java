package com.taxigol.taxi.model.services;

import com.taxigol.taxi.model.Driver;

public interface DriverService extends IService<Driver> {

	public Driver create(String name, String cedula, String password, String placa);
	public Driver auth(String cedula, String password);
}
