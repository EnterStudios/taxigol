package com.taxigol.taxi.controllers;

import com.taxigol.taxi.events.request.HasData;
import com.taxigol.taxi.model.Service;

public class ResponseCumplirServicio extends HasData<Service> {

	public ResponseCumplirServicio(Service data) {
		super(data);
	}
	
	public Service getService(){
		return getData();
	}

}
