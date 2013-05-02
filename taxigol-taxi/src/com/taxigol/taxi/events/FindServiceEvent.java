package com.taxigol.taxi.events;

import com.taxigol.taxi.model.Service;

public class FindServiceEvent extends CallBackEvent<Service, String> {

	public FindServiceEvent(String serviceId, AsyncCallback<Service> onSuccess) {
		super(serviceId,onSuccess);
	}

	
	
}
