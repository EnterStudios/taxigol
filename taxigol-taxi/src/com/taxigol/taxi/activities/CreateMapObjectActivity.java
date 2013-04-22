package com.taxigol.taxi.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Spinner;

import com.taxigol.taxi.R;

public class CreateMapObjectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_map_object);
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_map_object, menu);
		return true;
	}

}
