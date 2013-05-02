package com.taxigol.taxi.activities;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.events.AsyncCallback;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.views.widgets.Dialog;
import com.taxigol.taxi.views.widgets.SolicitudesAdapter;


public class ServiciodeTaxiListActivity extends ListActivity{

	private ServiceHandler handler;
	private SolicitudesAdapter adapter;
	private AlertDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serviciodetaxi_list);
		
		App app = (App)getApplication();
		handler = app.getServiceController();
		
		adapter = new SolicitudesAdapter();
		setListAdapter(adapter);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		dialog = Dialog.showMessage("Cargando", "Cargando tu lista de servicios", this);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.map, menu);
	    return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		dialog.show();
		handler.getServices(new AsyncCallback<List<Service>>() {
			@Override
			public void onSuccess(List<Service> result) {
				System.out.println(result);
				adapter.setSolicitudes(result);
				adapter.notifyDataSetChanged();
				dialog.dismiss();
			}
		});
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		dialog.dismiss();
	}

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
    		finish();
	        case R.id.menu_map:
	        	startActivity(new Intent(this, MapActivity.class));
	            return true;
	        case R.id.menu_services:
	        	Intent i = new Intent(this, ServiciodeTaxiListActivity.class);
	        	startActivity(i);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public interface ServiceHandler{
		public void getServices(AsyncCallback<List<Service>> servicios);
	}
	
}
