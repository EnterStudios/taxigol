package com.taxigol.taxi.events;

import java.util.List;

import com.taxigol.taxi.events.request.HasData;
import com.taxigol.taxi.model.Service;

public class ServicesChangedEvent extends HasData<List<Service>> {

	public ServicesChangedEvent(List<Service> data) {
		super(data);
	}

}
