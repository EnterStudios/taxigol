package com.taxigol.taxi.events;


public class ConfirmServiceEvent extends CallBackEvent<Void, String> {

	
	public ConfirmServiceEvent(String serviceId, AsyncCallback<Void> onSuccess) {
		super(serviceId,onSuccess);
	}

}
