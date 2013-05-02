package com.taxigol.taxi.receivers;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.taxigol.taxi.App;
import com.taxigol.taxi.activities.ConfirmacionActivity;
import com.taxigol.taxi.events.AsyncCallback;
import com.taxigol.taxi.model.Service;

public class TaxiServiceReceiver extends BroadcastReceiver {

	/**
	 * Intent param para el id del servicio de taxi
	 */
	public final static String PARAM_ID = "com.taxigol.receiver.ParseReceiver.ID";
	
	/**
	 * El id del servicio de taxi
	 */
	public final static String PARSE_PARAM_ID = "id";
	
	@Override
	public void onReceive(final Context context, Intent intent) {


		try {
			
			JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
			
			final String idServicio = json.getString(PARSE_PARAM_ID);
			System.out.println("Received ID:"+idServicio);
			App app = (App)context.getApplicationContext(); 
			if (app.getAuthHandler().getId()!=null){
				app.getServiceController().getNewService(idServicio, new AsyncCallback<Service>() {

					@Override
					public void onSuccess(Service result) {
						Intent i = new Intent(context,ConfirmacionActivity.class);
						i.putExtra(ConfirmacionActivity.EXTRA_ADDRESS,result.getAddress());
						i.putExtra(ConfirmacionActivity.EXTRA_ID, idServicio);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(i);
					}
					
				});
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}




		
	}


}
