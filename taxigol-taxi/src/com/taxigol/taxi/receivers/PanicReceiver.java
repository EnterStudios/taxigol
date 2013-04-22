package com.taxigol.taxi.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PanicReceiver extends BroadcastReceiver {

	/**
	 * Intent param para la direccion que env�a Parse del servicio de taxi
	 */
	public final static String PARAM_DIR = "com.taxigol.receiver.ParseReceiver.DIR";
	/**
	 * Intent param para el id del servicio de taxi
	 */
	public final static String PARAM_ID = "com.taxigol.receiver.ParseReceiver.ID";
	
	/**
	 * la direccion del taxi
	 */
	public final static String PARSE_PARAM_PANIC_ID = "id";
	
	@Override
	public void onReceive(Context context, Intent intent) {

		//TODO: This method should be executed when receiving a Panic
		
	}


}
