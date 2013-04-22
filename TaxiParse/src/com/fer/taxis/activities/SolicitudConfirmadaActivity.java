package com.fer.taxis.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.fer.taxis.R;

public class SolicitudConfirmadaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solicitud_confirmada);
		
		TextView txtMessage = (TextView)findViewById(R.id.textView1);
		txtMessage.setText(getIntent().getStringExtra("message"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_solicitud_confirmada, menu);
		return true;
	}

}
