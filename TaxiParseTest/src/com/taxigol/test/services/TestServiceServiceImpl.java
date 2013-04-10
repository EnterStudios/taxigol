package com.taxigol.test.services;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import com.fer.taxis.model.Service;
import com.fer.taxis.model.services.TaxiServiceService.State;
import com.fer.taxis.model.services.impl.TaxiServiceServiceImpl;
import com.restz.net.DefaultRestz;

public class TestServiceServiceImpl extends TestCase{

	private TaxiServiceServiceImpl service;
	
	public TestServiceServiceImpl() {
		
		service = new TaxiServiceServiceImpl(new DefaultRestz(), "http://192.168.0.10:3000");
	}
	
	public void testDeleteAll() throws IOException{
		service.create("calle 132 a # 19-43", "123");
		service.deleteAll();
		int size = service.getAll().size();
		assertEquals(0, size);
	}
	
	public void testCreateService() throws IOException{
		service.deleteAll();
		String address = "calle 132 a # 19-43";
		service.create(address, "code");
		List<Service> services = service.getAll();
		assertEquals(1,services.size());
		assertEquals(address, services.get(0).getAddress());
	}
	
	public void testUpdate() throws IOException{
		service.deleteAll();
		Service serv = service.create("calle 132 a # 19-43", "code");
		service.update(State.confirmado, serviceId);
	}
	
}
