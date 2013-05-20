package com.taxigol.taxi.helpers;

import java.util.List;

import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.model.services.TaxiServiceService;

public class MockTaxiService implements TaxiServiceService {

	@Override
	public Service create(String address, String verificationCode)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Service> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service get(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service confirmarServicio(String serviceId, String taxiId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service cumplirServicio(String serviceId, String taxiId,
			String verificationCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Service> getAll(String taxiId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service cancelarServicio(String serviceId, String taxiId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Service getService(){
		return new Service(1, "pendiente", "23", "calle 132 a # 19-43", "1");
	}

}
