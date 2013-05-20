package com.taxigol.taxi.test.controllers;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import android.test.ApplicationTestCase;

import com.taxigol.taxi.App;
import com.taxigol.taxi.controllers.ServiceController;
import com.taxigol.taxi.events.request.RequestServices;
import com.taxigol.taxi.helpers.MockEventBus;
import com.taxigol.taxi.helpers.MockIdProvider;
import com.taxigol.taxi.helpers.MockTaxiService;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.model.services.TaxiServiceService;

public class TestServicesController extends ApplicationTestCase<App>{

	private MockEventBus bus;
	private ServiceController controller;
	private TaxiServiceService service;
	
	public TestServicesController() {
		super(App.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		bus = new MockEventBus();
		service = new MockTaxiService(){
			@Override
			public List<Service> getAll(String taxiId) throws Exception {
				return Arrays.asList(MockTaxiService.getService(),MockTaxiService.getService());
			}
		};
		controller = new ServiceController(null, new MockIdProvider(), service);
		
		controller.setEventBus(bus);
	}
	
	public void testRequestServices() throws InterruptedException{
		
		List<Service> services = controller.getServices();
		Assert.assertEquals(0, services.size());
		
		controller.onRequestServices(new RequestServices());
		
		Thread.sleep(300);
		
		services = controller.getServices();
		Assert.assertEquals(2, services.size());
	}
	
	
	
}





