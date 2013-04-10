package com.taxigol.test.services;

import java.io.IOException;

import junit.framework.TestCase;

import com.fer.taxis.model.services.impl.TaxiServiceImpl;
import com.taxigol.restz.DefaultRestz;

public class TestTaxiServiceImpl extends TestCase {

	private TaxiServiceImpl service;
	
	public TestTaxiServiceImpl() {
		service = new TaxiServiceImpl( "http://192.168.0.10:3000",new DefaultRestz());
	}
	
	public void testDeleteAll() throws IOException{
		service.createTaxi();
		service.deleteAll();
		int size = service.getAll().size();
		assertEquals(0, size);
	}
	
	public void testAddTaxi() throws IOException{
		service.deleteAll();
		int size = service.getAll().size();
		assertEquals(0, size);
		service.createTaxi();
		
		size = service.getAll().size();
		assertEquals(1, size);
		service.createTaxi();
		
		size = service.getAll().size();
		assertEquals(2, size);
		service.createTaxi();
		
		size = service.getAll().size();
		assertEquals(3, size);
		service.createTaxi();
	}
}
