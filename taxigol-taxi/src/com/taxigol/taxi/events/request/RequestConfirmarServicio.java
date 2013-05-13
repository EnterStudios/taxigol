package com.taxigol.taxi.events.request;

public class RequestConfirmarServicio extends HasData<Integer> {

	public RequestConfirmarServicio(Integer data) {
		super(data);
	}
	
	public int getServiceId(){
		return getData();
	}

}
