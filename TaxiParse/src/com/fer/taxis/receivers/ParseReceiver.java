package com.fer.taxis.receivers;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fer.taxis.activities.ConfirmacionActivity;

public class ParseReceiver extends BroadcastReceiver {

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
	public final static String PARSE_PARAM_DIR = "dir";
	/**
	 * El id del servicio de taxi
	 */
	public final static String PARSE_PARAM_ID = "id";
	
	@Override
	public void onReceive(Context context, Intent intent) {


		try {
			JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
			String direccion = json.getString(PARSE_PARAM_DIR);
			String idServicio = json.getString(PARSE_PARAM_ID);
			
			Intent i = new Intent(context,ConfirmacionActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.putExtra(PARAM_DIR, direccion);
			i.putExtra(PARAM_ID, idServicio);
			context.startActivity(i);
		} catch (JSONException e) {
			e.printStackTrace();
		}




		
	}


}
