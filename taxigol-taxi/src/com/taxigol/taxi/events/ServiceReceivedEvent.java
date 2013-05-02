package com.taxigol.taxi.events;

/**
 * This event is launched when a new service is pushed to the device
 * @author mono
 *
 */
public class ServiceReceivedEvent extends CallBackEvent<Void, String> {

	/**
	 * 
	 * @param data the service's id
	 * @param cb
	 */
	public ServiceReceivedEvent(String data, AsyncCallback<Void> cb) {
		super(data, cb);
	}


}
