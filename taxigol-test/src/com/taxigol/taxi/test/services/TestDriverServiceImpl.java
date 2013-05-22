package com.taxigol.taxi.test.services;

import java.util.Date;

import co.fernandohur.restz.DefaultRestz;

import com.taxigol.taxi.model.Driver;
import com.taxigol.taxi.model.services.impl.DriverServiceImpl;
import com.taxigol.taxi.test.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestDriverServiceImpl extends TestCase {

	private DriverServiceImpl service;
	
	public TestDriverServiceImpl() {
		DefaultRestz restz = new DefaultRestz();
		service = new DriverServiceImpl(Properties.server_url,restz);
	}
	
	public void testCreatingADriver() throws Exception{
		String name = "robert";
		String cedula = new Date().getTime()+"";
		String pass = "mypass123";
		String placa = "ABC123";
		
		Driver d = service.create(name, cedula, pass, placa);
		
		assertDriver(d, cedula, name, pass);
		
		Driver d2 = service.auth(cedula, pass);
		
		Assert.assertNotNull(d2);
		assertDriver(d2, cedula, name, pass);
		
		service.delete(d.getId()+"");

		try {
			service.auth(cedula, pass);
			fail();
		} catch (Exception e) {
			//All is good my friend
		}
		
	}
	
	public void testAuthingADriver() throws Exception{
	
		String name = "robert";
		String cedula = new Date().getTime()+"";
		String pass = "mypass123";
		String placa = "ABC123";
		
		Driver d = service.create(name, cedula, pass, placa);
		
		assertDriver(d, cedula, name, pass);
		
		Driver d2 = service.auth(cedula, pass);
		
		Assert.assertNotNull(d2);
		assertDriver(d2, cedula, name, pass);
		
		
	}
	
	private void assertDriver(Driver d, String cedula, String name, String password ){
		assertEquals(d.getCedula(), cedula);
		assertEquals(d.getName(), name);
		assertEquals(d.getPassword(), password);
	}
	
	
	
}
