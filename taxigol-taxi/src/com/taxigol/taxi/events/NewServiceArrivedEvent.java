package com.taxigol.taxi.events;

import com.taxigol.taxi.events.request.HasData;
import com.taxigol.taxi.model.Service;

public class NewServiceArrivedEvent extends HasData<Service>{

	public NewServiceArrivedEvent(Service data) {
		super(data);
	}

}
