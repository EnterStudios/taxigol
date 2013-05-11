package com.taxigol.taxi.events;

import com.taxigol.taxi.events.request.HasData;
import com.taxigol.taxi.model.Service;

public class ServiceChangedEvent extends HasData<Service> {

	public ServiceChangedEvent(Service data) {
		super(data);
	}


	
}
