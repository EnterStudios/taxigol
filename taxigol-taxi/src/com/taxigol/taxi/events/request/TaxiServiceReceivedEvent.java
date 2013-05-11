package com.taxigol.taxi.events.request;

public class TaxiServiceReceivedEvent extends HasData<String> {

	/**
	 * 
	 * @param data the service id
	 */
	public TaxiServiceReceivedEvent(String data) {
		super(data);
	}

	public String getServiceId(){
		return getData();
	}
	
}
