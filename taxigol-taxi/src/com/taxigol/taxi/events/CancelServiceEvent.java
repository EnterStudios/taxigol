package com.taxigol.taxi.events;

import com.taxigol.restz.async.OnSuccess;

public class CancelServiceEvent extends CallBackEvent<Void, String>{

	public CancelServiceEvent(String serviceId, OnSuccess<Void> onSuccess) {
		super(serviceId, onSuccess);
	}

	
}
