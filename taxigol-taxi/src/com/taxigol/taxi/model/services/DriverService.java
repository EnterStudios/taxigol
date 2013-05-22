package com.taxigol.taxi.model.services;

import com.taxigol.taxi.model.Driver;

public interface DriverService extends IService<Driver> {

	public Driver create(String name, String cedula, String password, String placa) throws Exception;
	public Driver auth(String cedula, String password) throws Exception;
	public void delete(String id) throws Exception;
}
