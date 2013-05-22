package com.taxigol.taxi;

import co.fernandohur.restz.DefaultRestz;
import co.fernandohur.restz.Restz;

import com.taxigol.taxi.model.services.DriverService;
import com.taxigol.taxi.model.services.PositionService;
import com.taxigol.taxi.model.services.TaxiService;
import com.taxigol.taxi.model.services.TaxiServiceService;
import com.taxigol.taxi.model.services.impl.DriverServiceImpl;
import com.taxigol.taxi.model.services.impl.PositionServiceImpl;
import com.taxigol.taxi.model.services.impl.TaxiServiceImpl;
import com.taxigol.taxi.model.services.impl.TaxiServiceServiceImpl;

public class ServiceFactory {

	private Restz client;
	private String baseUrl;
	
	
	public ServiceFactory(String baseUrl) {
		client = new DefaultRestz();
		this.baseUrl = baseUrl;
	}
	
	public PositionService getPositionService(){
		return new PositionServiceImpl(client, baseUrl);
	}
	
	public TaxiServiceService getTaxiServiceService(){
		return new TaxiServiceServiceImpl(client, baseUrl);
	}
	
	public TaxiService getTaxiService(){
		return new TaxiServiceImpl(baseUrl,client);
	}

	public DriverService getDriverService() {
		return new DriverServiceImpl(baseUrl, client);
	}

	
	
	
}




