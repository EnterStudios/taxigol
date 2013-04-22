package com.fer.taxis.receivers;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PanicReceiver extends BroadcastReceiver {

	/**
	 * Intent param para la direccion que envía Parse del servicio de taxi
	 */
	public final static String PARAM_DIR = "com.fer.receiver.ParseReceiver.DIR";
	/**
	 * Intent param para el id del servicio de taxi
	 */
	public final static String PARAM_ID = "com.fer.receiver.ParseReceiver.ID";
	
	/**
	 * la direccion del taxi
	 */
	public final static String PARSE_PARAM_PANIC_ID = "id";
	
	@Override
	public void onReceive(Context context, Intent intent) {


		try {
			JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
			String panicId = json.getString(PARSE_PARAM_PANIC_ID);
			
			//TODO
		
		} catch (JSONException e) {
			e.printStackTrace();
		}




		
	}


}
