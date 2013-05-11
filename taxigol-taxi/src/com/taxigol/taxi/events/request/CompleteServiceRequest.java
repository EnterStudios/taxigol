package com.taxigol.taxi.events.request;

import android.util.Pair;

public class CompleteServiceRequest extends HasData<Pair<String, String>> {

	public CompleteServiceRequest(Pair<String, String> serviceIdAndCode) {
		super(serviceIdAndCode);
	}
	
	public String getServiceId(){
		return getData().first;
	}
	
	public String getServiceCode(){
		return getData().second;
	}

}
