package com.taxigol.taxi.events.response;

import com.taxigol.taxi.events.request.HasData;
import com.taxigol.taxi.model.Service;

public class ResponseService extends HasData<Service> {

	public ResponseService(Service service) {
		super(service);
	}

}
