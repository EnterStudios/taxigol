package com.fer.taxis;

import com.fer.taxis.model.services.PanicService;
import com.fer.taxis.model.services.PositionService;
import com.fer.taxis.model.services.TaxiService;
import com.fer.taxis.model.services.TaxiServiceService;
import com.fer.taxis.model.services.impl.PanicServiceImpl;
import com.fer.taxis.model.services.impl.PositionServiceImpl;
import com.fer.taxis.model.services.impl.TaxiServiceImpl;
import com.fer.taxis.model.services.impl.TaxiServiceServiceImpl;
import com.taxigol.restz.DefaultRestz;
import com.taxigol.restz.Restz;

public class ServiceFactory {

	private Restz client;
	private String baseUrl;
	
	
	public ServiceFactory(String baseUrl) {
		client = new DefaultRestz();
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
	
	
	
}




