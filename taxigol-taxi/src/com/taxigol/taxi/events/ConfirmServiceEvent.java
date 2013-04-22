package com.taxigol.taxi.events;

import com.taxigol.restz.async.OnSuccess;

public class ConfirmServiceEvent extends CallBackEvent<Void, String> {

	
	public ConfirmServiceEvent(String serviceId, OnSuccess<Void> onSuccess) {
		super(serviceId,onSuccess);
	}

}
