package com.taxigol.taxi.events;

import com.taxigol.restz.async.OnSuccess;
import com.taxigol.taxi.model.Service;

public class FindServiceEvent extends CallBackEvent<Service, String> {

	public FindServiceEvent(String serviceId, OnSuccess<Service> onSuccess) {
		super(serviceId,onSuccess);
	}

	
	
}
