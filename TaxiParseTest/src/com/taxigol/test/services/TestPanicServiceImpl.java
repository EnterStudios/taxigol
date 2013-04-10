package com.taxigol.test.services;

import java.io.IOException;

import junit.framework.TestCase;

import com.fer.taxis.model.Panic;
import com.fer.taxis.model.Position;
import com.fer.taxis.model.Taxi;
import com.fer.taxis.model.services.PanicService;
import com.fer.taxis.model.services.PositionService;
import com.fer.taxis.model.services.impl.PanicServiceImpl;
import com.fer.taxis.model.services.impl.PositionServiceImpl;
import com.fer.taxis.model.services.impl.TaxiServiceImpl;
import com.restz.net.DefaultRestz;
import com.restz.net.Restz;

public class TestPanicServiceImpl extends TestCase{

	private PanicService service;
	private TaxiServiceImpl taxiService;
	private PositionService positionService;
	
	public TestPanicServiceImpl() {
		Restz client = new DefaultRestz();
		String baseUrl = "http://192.168.0.10:3000";
		service = new PanicServiceImpl(client, baseUrl);
		taxiService = new TaxiServiceImpl(baseUrl, client);
		positionService = new PositionServiceImpl(client, baseUrl);
	}
	
	public void testCreatePanic() throws IOException{
		//borro todos los taxis
		taxiService.deleteAll();
		//creo un nuevo taxi
		Taxi taxi = taxiService.createTaxi();
		//le creo una pos a ese taxi
		Position position = positionService.updatePosition(taxi.getId(), 123,234);
		//creo un 
		Panic panic = service.createPanic(taxi.getId());
		assertEquals(panic.getPositionId(), position.getId());
	}
	
	
	
}
