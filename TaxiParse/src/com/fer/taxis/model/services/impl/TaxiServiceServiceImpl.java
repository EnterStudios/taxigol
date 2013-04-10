package com.fer.taxis.model.services.impl;

import java.io.IOException;

import com.fer.taxis.model.Service;
import com.fer.taxis.model.Taxi;
import com.fer.taxis.model.services.TaxiServiceService;
import com.fer.taxis.model.services.parsers.ServiceJsonParser;
import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.PostRequest;
import com.taxigol.restz.requests.PutRequest;

public class TaxiServiceServiceImpl extends AbstractService<Service> implements
		TaxiServiceService {

	public TaxiServiceServiceImpl(Restz client,String baseUrl) {
		super(baseUrl, "services", client, new ServiceJsonParser());
	}
	
	@Override
	public Service create(String addess, String verificationCode) throws IOException {
		PostRequest post = client.post(baseUrl+"/"+resourceName+".json","address",addess,"verification_code",verificationCode);
		String json = post.getContent();
		return parser.parse(json);
		
	}

	@Override
	public void update(State s, String serviceId) throws IOException {
		PutRequest put = client.put(baseUrl+"/"+resourceName+"/"+serviceId+".json");
		
		String result = put.getContent();
	}
	
	//update para hacer una confirmación de un pedido
	public void update(State s, String serviceId, String taxiId) throws IOException {
		PutRequest put = client.put(baseUrl+"/"+resourceName+"/"+serviceId+".json","state", s.toString(), "taxi_id", taxiId);
		
		String result = put.getContent();
	}
	
	//update para cumplir una confirmación
		public void update(State s, String serviceId, String taxiId, String verCode) throws IOException {
			PutRequest put = client.put(baseUrl+"/"+resourceName+"/"+serviceId+".json","state", s.toString(), "taxi_id", taxiId, "verification_code", verCode);
			
			String result = put.getContent();
		}

}
