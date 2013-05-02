package com.taxigol.taxi.events;

import java.util.List;

import com.taxigol.taxi.model.Service;

public class GetAllServicesEvent extends CallBackEvent<List<Service>,Void> {

	public GetAllServicesEvent(AsyncCallback<List<Service>> onSuccess) {
		super(null, onSuccess);
	}


}
