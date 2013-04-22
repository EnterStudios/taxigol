package com.taxigol.taxi.model.services.impl;

import java.io.IOException;
import com.taxigol.taxi.model.State;
import java.util.List;

import com.taxigol.restz.Restz;
import com.taxigol.restz.requests.GetRequest;
import com.taxigol.restz.requests.PostRequest;
import com.taxigol.restz.requests.PutRequest;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.model.services.TaxiServiceService;
import com.taxigol.taxi.model.services.parsers.ServiceJsonParser;

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
	public void confirmarServicio(String serviceId, String taxiId)
			throws IOException {
		PutRequest put = 
				client.put(baseUrl+"/"+resourceName+"/"+serviceId+".json","state", State.confirmado.toString(), "taxi_id", taxiId);
		String json = put.getContent();
		parser.throwIfError(json);
	}
	
	public void cumplirServicio(String serviceId, String taxiId, String verificationCode) throws IOException{
		PutRequest put = 
				client.put(baseUrl+"/"+resourceName+"/"+serviceId+".json","state", State.cumplido.toString(), "taxi_id", taxiId, "verification_code", verificationCode);
		String json = put.getContent();
		parser.throwIfError(json);
	}
	
	@Override
	public List<Service> getAll(String taxiId) throws IOException {
		GetRequest get = client.get(baseUrl+"/"+resourceName+".json", "taxi_id",taxiId);
		String json = get.getContent();
		return parser.parseList(json);
	}

	public void cancelarServicio(String serviceId, String taxiId) throws IOException {
		PutRequest put = 
				client.put(baseUrl+"/"+resourceName+"/"+serviceId+".json","state", State.cancelado.toString(), "taxi_id", taxiId);
		String json = put.getContent();
		parser.throwIfError(json);
	}
	
}
