package com.taxigol.test.services;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import com.fer.taxis.model.Service;
import com.fer.taxis.model.Taxi;
import com.fer.taxis.model.services.TaxiServiceService.State;
import com.fer.taxis.model.services.impl.TaxiServiceImpl;
import com.fer.taxis.model.services.impl.TaxiServiceServiceImpl;
import com.taxigol.restz.DefaultRestz;
import com.taxigol.restz.Restz;

public class TestServiceServiceImpl extends TestCase{

	private TaxiServiceServiceImpl service;
	private TaxiServiceImpl taxiService;
	
	public TestServiceServiceImpl() {
		Restz client = new DefaultRestz();
		String baseUrl = "http://192.168.0.10:3000";
		service = new TaxiServiceServiceImpl(client, baseUrl);
		taxiService = new TaxiServiceImpl(baseUrl, client);
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
	
	public void testUpdateBien() throws IOException{
		service.deleteAll();
		Service serv = service.create("calle 132 a # 19-43", "54");
		//creo un nuevo taxi
		Taxi taxi = taxiService.createTaxi();
		service.update(State.confirmado, "" + serv.getId(), taxi.getId());
		List<Service> services = service.getAll();
		Service serv2 = services.get(0);
		assertEquals(State.confirmado.toString(), serv2.getState());
		assertEquals(taxi.getId(), serv2.getTaxiId());
		
		service.update(State.cumplido, "" + serv.getId(), taxi.getId(), "54");
		services = service.getAll();
		serv2 = services.get(0);
		assertEquals(State.cumplido.toString(), serv2.getState());
		
		
	}
	
	public void testUpdateAbdandonar() throws IOException{
		service.deleteAll();
		Service serv = service.create("calle 132 a # 19-43", "54");
		//creo un nuevo taxi
		Taxi taxi = taxiService.createTaxi();
		service.update(State.confirmado, "" + serv.getId(), taxi.getId());
		List<Service> services = service.getAll();
		Service serv2 = services.get(0);
		assertEquals(State.confirmado.toString(), serv2.getState());
		assertEquals(taxi.getId(), serv2.getTaxiId());
		
		service.update(State.abandonado, "" + serv.getId(), taxi.getId());
		services = service.getAll();
		serv2 = services.get(0);
		assertEquals(State.abandonado.toString(), serv2.getState());
		
	}
	
	public void testUpdateCancelar() throws IOException{
		service.deleteAll();
		Service serv = service.create("calle 132 a # 19-43", "54");
		//creo un nuevo taxi
		Taxi taxi = taxiService.createTaxi();
		service.update(State.confirmado, "" + serv.getId(), taxi.getId());
		service.update(State.cancelado, "" + serv.getId(), taxi.getId());
		List<Service> services = service.getAll();
		Service serv2 = services.get(0);
		assertEquals(State.cancelado.toString(), serv2.getState());
		assertEquals(taxi.getId(), serv2.getTaxiId());
	}
	
	public void testUpdateNoDeberia() throws IOException{
		service.deleteAll();
		Service serv = service.create("calle 132 a # 19-43", "54");
		//creo un nuevo taxi
		Taxi taxi = taxiService.createTaxi();
		service.update(State.cumplido, "" + serv.getId(), taxi.getId(), "54");
		List<Service> services = service.getAll();
		Service serv2 = services.get(0);
		assertNotSame(taxi.getId(), serv2.getTaxiId());
		assertNotSame(State.cumplido.toString(), serv2.getState());
		
		service.update(State.abandonado, "" + serv.getId(), taxi.getId());
		services = service.getAll();
		serv2 = services.get(0);
		assertNotSame(taxi.getId(), serv2.getTaxiId());
		assertNotSame(State.abandonado.toString(), serv2.getState());
		
	}
	
}
