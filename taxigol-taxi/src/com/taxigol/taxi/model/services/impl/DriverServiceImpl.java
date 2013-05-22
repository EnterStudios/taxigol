package com.taxigol.taxi.model.services.impl;

import java.util.HashMap;
import java.util.Map;

import co.fernandohur.restz.Restz;

import com.taxigol.taxi.model.Driver;
import com.taxigol.taxi.model.services.DriverService;
import com.taxigol.taxi.model.services.parsers.DriverJsonParser;

public class DriverServiceImpl extends AbstractService<Driver> implements DriverService {

	public DriverServiceImpl(String baseUrl, Restz client) {
		super(baseUrl, "drivers", client, new DriverJsonParser());
	}

	@Override
	public Driver create(String name, String cedula, String password,
			String placa) throws Exception {
		return client.post(baseUrl+"/"+resourceName+".json", parser.getType(), "placa", placa,"cedula",cedula,"password",password,"name",name);
	}

	@Override
	public Driver auth(String cedula, String password) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cedula", cedula);
		params.put("password", password);
		String response = client.post(baseUrl+"/"+resourceName+"/"+"auth.json",params);
		if (response.contains("\"message\":")){
			throw new IllegalArgumentException("wrong credentials");
		}
		else{
			return parser.parse(response, parser.getType());
		}
	}
	
	@Override
	public void delete(String id) throws Exception {
		client.delete(baseUrl+"/"+resourceName+"/"+id+".json", new HashMap<String, Object>());
	}

}
