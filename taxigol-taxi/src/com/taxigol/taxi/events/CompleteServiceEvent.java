package com.taxigol.taxi.events;

import android.util.Pair;

public class CompleteServiceEvent extends CallBackEvent<Void, Pair<String, String>>{

	/**
	 * 
	 * @param data first is the serviceId second is the verificationCode
	 * @param onSuccess
	 * @param onError
	 */
	public CompleteServiceEvent(Pair<String, String> data,
			AsyncCallback<Void> onSuccess) {
		super(data, onSuccess);
		
	}

	
}
