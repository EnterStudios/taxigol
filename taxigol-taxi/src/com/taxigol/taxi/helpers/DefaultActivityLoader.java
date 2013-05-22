package com.taxigol.taxi.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class DefaultActivityLoader implements ActivityLoader{

	private Context context;
	
	@Override
	public void load(Class<? extends Activity> activity, String... params) {
		
		Intent intent = new Intent(context, activity);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		for (int i = 0; i < params.length-1; i=i+2) {
			String nam = params[i];
			String val = params[i+1];
			intent.putExtra(nam, val);
		}
		
		context.startActivity(intent);
	}
	
	@Override
	public void setContext(Context applicationContext) {
		this.context = applicationContext;
	}
	
}
