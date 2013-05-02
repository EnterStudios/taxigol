package com.taxigol.taxi.model.services.impl;

import java.util.List;

import co.fernandohur.restz.Restz;

import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.model.State;
import com.taxigol.taxi.model.services.TaxiServiceService;
import com.taxigol.taxi.model.services.parsers.ServiceJsonParser;

public class TaxiServiceServiceImpl extends AbstractService<Service> implements
TaxiServiceService {

	public TaxiServiceServiceImpl(Restz client,String baseUrl) {
		super(baseUrl, "services", client, new ServiceJsonParser());
		client.setParser(parser);
	}

	@Override
	public Service create(String addess, String verificationCode) throws Exception {
		if (addess.contains("#")){
			addess.replace("#", "No.");
		}
		return client.post(baseUrl+"/"+resourceName+".json",parser.getType(),"address",addess,"verification_code",verificationCode);
	}

	@Override
	public void confirmarServicio(String serviceId, String taxiId) throws Exception{
		client.put(baseUrl+"/"+resourceName+"/"+serviceId+".json",parser.getType(),"state", State.confirmado.toString(), "taxi_id", taxiId);
	}
	
	public void cumplirServicio(String serviceId, String taxiId, String verificationCode) throws Exception{
		client.put(baseUrl+"/"+resourceName+"/"+serviceId+".json",parser.getType(),"state", State.cumplido.toString(), "taxi_id", taxiId, "verification_code", verificationCode);
	}
	
	@Override
	public List<Service> getAll(String taxiId) throws Exception {
		return client.get(baseUrl+"/"+resourceName+".json",parser.getListType(), "taxi_id",taxiId);
	}

	public void cancelarServicio(String serviceId, String taxiId) throws Exception {
		client.put(baseUrl+"/"+resourceName+"/"+serviceId+".json",parser.getType(),"state", State.cancelado.toString(), "taxi_id", taxiId);
	}
	
}
