package com.taxigol.taxi.events;

import com.taxigol.restz.async.OnError;
import com.taxigol.restz.async.OnSuccess;

import android.util.Pair;

public class CompleteServiceEvent extends CallBackEvent<Void, Pair<String, String>>{

	/**
	 * 
	 * @param data first is the serviceId second is the verificationCode
	 * @param onSuccess
	 * @param onError
	 */
	public CompleteServiceEvent(Pair<String, String> data,
			OnSuccess<Void> onSuccess, OnError onError) {
		super(data, onSuccess, onError);
		
	}

	
}
