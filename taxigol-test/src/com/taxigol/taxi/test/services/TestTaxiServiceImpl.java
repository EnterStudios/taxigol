package com.taxigol.taxi.test.services;

import junit.framework.TestCase;
import co.fernandohur.restz.DefaultRestz;

import com.taxigol.taxi.model.services.impl.TaxiServiceImpl;
import com.taxigol.taxi.test.Properties;

public class TestTaxiServiceImpl extends TestCase {

	private TaxiServiceImpl service;
	
	public TestTaxiServiceImpl() {
		
		service = new TaxiServiceImpl(Properties.server_url,new DefaultRestz());
	}
	
	public void testDeleteAll() throws Exception{
		service.auth("234");
		service.deleteAll();
		int size = service.getAll().size();
		assertEquals(0, size);
	}
	
	public void testAuthTaxi() throws Exception{
		service.deleteAll();
		int size = service.getAll().size();
		assertEquals(0, size);
		service.auth("1");
		
		size = service.getAll().size();
		assertEquals(1, size);
		service.auth("2");
		
		size = service.getAll().size();
		assertEquals(2, size);
		service.auth("3");
		
		size = service.getAll().size();
		assertEquals(3, size);
		service.auth("4");
	}
}
