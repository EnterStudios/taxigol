package com.taxigol.taxi.events.request;

import android.util.Pair;

public class RequestCumplirService extends HasData<Pair<Integer, String>>{

	public RequestCumplirService(Integer serviceId, String verificationCode) {
		super(new Pair<Integer, String>(serviceId, verificationCode));
	}
	
	public Integer getServiceId(){
		return getData().first;
	}
	
	public String getVerificationCode(){
		return getData().second;
	}

	
}
