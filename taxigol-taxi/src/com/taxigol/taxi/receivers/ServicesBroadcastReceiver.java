package com.taxigol.taxi.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public abstract class ServicesBroadcastReceiver extends BroadcastReceiver{

	/**
	 * Used for the intent filter, this broadcast receiver will only listen to that action
	 */
	public final static String INTENT_FILTER_ACTION = "services.intent_filter";
	
	public static final String EXTRA_NAME_ACTION = "services.action";
	public static final String EXTRA_SERVICE_ID = "services.service_id";
	
	static final String EXTRA_ACTION_NEW_TAXI_SERVICE = "services.action.new_taxi_service";
	static final String EXTRA_ACTION_SERVICE_SAVED = "services.action.service_saved";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String actionName = intent.getStringExtra(EXTRA_NAME_ACTION);
		if (actionName.equals(EXTRA_ACTION_NEW_TAXI_SERVICE)){
			int serviceId = intent.getIntExtra(EXTRA_SERVICE_ID, -1);
			onNewServiceArrived(serviceId);
		}
		else if (actionName.equals(EXTRA_ACTION_SERVICE_SAVED)){
			
			int serviceId = intent.getIntExtra(EXTRA_SERVICE_ID, -1);
			onServiceSaved(serviceId);
		}
		
	}
	
	/**
	 * This method is executed when a new service has arrived to the device
	 * @param serviceId
	 */
	public abstract void onNewServiceArrived(int serviceId);
	
	/**
	 * This method is executed whenever a service has been saved on the server. This could
	 * be used to refresh the services list
	 * @param serviceId
	 */
	public abstract void onServiceSaved(int serviceId);
	
	public IntentFilter getIntentFilter(){
		return new IntentFilter(EXTRA_NAME_ACTION);
	}
}
