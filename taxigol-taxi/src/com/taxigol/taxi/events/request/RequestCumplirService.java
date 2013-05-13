package com.taxigol.taxi.events.request;

public class RequestCumplirService extends HasData<Integer>{

	public RequestCumplirService(Integer serviceId) {
		super(serviceId);
	}
	
	public Integer getServiceId(){
		return getData();
	}

	
}
