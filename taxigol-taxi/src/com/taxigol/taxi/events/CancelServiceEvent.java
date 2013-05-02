package com.taxigol.taxi.events;


public class CancelServiceEvent extends CallBackEvent<Void, String>{

	public CancelServiceEvent(String serviceId, AsyncCallback<Void> onSuccess) {
		super(serviceId, onSuccess);
	}

	
}
