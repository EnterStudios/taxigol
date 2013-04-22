package com.taxigol.taxi;

import com.taxigol.restz.DefaultRestz;
import com.taxigol.restz.Restz;
import com.taxigol.taxi.model.services.MapObjectService;
import com.taxigol.taxi.model.services.PanicService;
import com.taxigol.taxi.model.services.PositionService;
import com.taxigol.taxi.model.services.TaxiService;
import com.taxigol.taxi.model.services.TaxiServiceService;
import com.taxigol.taxi.model.services.impl.MapObjectServiceImpl;
import com.taxigol.taxi.model.services.impl.PanicServiceImpl;
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
	
	public PanicService getPanicService(){
		return new PanicServiceImpl(client, baseUrl);
	}

	public MapObjectService getMapObjectsService() {
		return new MapObjectServiceImpl(baseUrl, client);
	}
	
	
	
}




