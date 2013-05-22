package com.taxigol.taxi.helpers;

import android.app.Activity;
import android.content.Context;

public interface ActivityLoader {

	public void load(Class<? extends Activity> activity, String... params);

	public void setContext(Context applicationContext);
	
}
