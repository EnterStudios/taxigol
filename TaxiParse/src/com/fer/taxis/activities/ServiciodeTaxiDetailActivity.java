package com.fer.taxis.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.fer.taxis.R;
import com.fer.taxis.model.Service;
import com.taxigol.restz.async.OnSuccess;


public class ServiciodeTaxiDetailActivity extends Activity {

	public final static String EXTRA_SERVICE_ID = "service_id";
	private ServiceHandler handler;
	
	private TextView txtDireccion;
	private TextView txtEstado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serviciodetaxi_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		handler.getService(getIntent().getStringExtra(EXTRA_SERVICE_ID), new OnSuccess<Service>(){

			@Override
			public void onSuccess(Service result) {
				txtDireccion.setText(result.getAddress());
				txtEstado.setText(result.getState());
			}
			
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpTo(this, new Intent(this,
					ServiciodeTaxiListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public interface ServiceHandler{
		public void getService(String id, OnSuccess<Service> service);
	}
}
