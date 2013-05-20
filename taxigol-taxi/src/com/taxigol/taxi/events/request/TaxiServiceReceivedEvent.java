package com.taxigol.taxi.events.request;

public class TaxiServiceReceivedEvent extends HasData<String> {

	/**
	 * 
	 * @param serviceId the service id
	 */
	public TaxiServiceReceivedEvent(String serviceId) {
		super(serviceId);
	}

	public String getServiceId(){
		return getData();
	}
	
}
