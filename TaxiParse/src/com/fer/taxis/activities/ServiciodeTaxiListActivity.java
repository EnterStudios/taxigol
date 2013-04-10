package com.fer.taxis.activities;

import java.util.ArrayList;

import com.fer.taxis.R;
import com.fer.taxis.model.Service;
import com.fer.taxis.model.services.TaxiServiceService.State;
import com.fer.taxis.views.widgets.SolicitudesAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


public class ServiciodeTaxiListActivity extends ListActivity{

	private SolicitudesAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_serviciodetaxi_list);
		adapter = new SolicitudesAdapter(getSolicitudes());
		setListAdapter(adapter);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private ArrayList<Service> getSolicitudes() {
		ArrayList<Service> services = new ArrayList<Service>();
		services.add(new Service(1, State.cancelado.toString(), "123", "calle 132 a # 19-43", "1"));
		return services;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			startActivity(new Intent(this, MapActivity.class));
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
