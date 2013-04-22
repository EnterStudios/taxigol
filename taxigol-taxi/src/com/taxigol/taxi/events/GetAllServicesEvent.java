package com.taxigol.taxi.events;

import java.util.List;

import com.taxigol.restz.async.OnSuccess;
import com.taxigol.taxi.model.Service;

public class GetAllServicesEvent extends CallBackEvent<List<Service>,Void> {

	public GetAllServicesEvent(OnSuccess<List<Service>> onSuccess) {
		super(null, onSuccess);
	}


}
