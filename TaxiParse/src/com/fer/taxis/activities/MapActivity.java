package com.fer.taxis.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.TabListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.fer.taxis.App;
import com.fer.taxis.R;
import com.fer.taxis.model.LocationReceiver.Handler;
import com.fer.taxis.views.widgets.Dialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends Activity implements OnItemClickListener, OnClickListener, Handler{

	private GoogleMap map;
	private MapHandler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		handler = getApp().getLocationController();
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		handler.register(this);
		
		onLocationChanged(handler.getLocation());
		
		ActionBar actionBar = getActionBar();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.map, menu);
	    return true;
	}
	

	private App getApp() {
		return (App) getApplication();
	}


	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int i, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBackPressed() {
		Dialog.showAccept("Salir", "Estás seguro que deseas salir de la aplicación?", this, this);
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		finish();
		Intent i = new Intent(this,AuthActivity.class);
		i.putExtra("exit", "exit");
		startActivity(i);
	}


	@Override
	public void onLocationChanged(Location location) {
		if (location!=null){
			LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
		}
	}
	
	public interface MapHandler{
		public void register(Handler locationHandler);
		public Location getLocation();
	}

}
