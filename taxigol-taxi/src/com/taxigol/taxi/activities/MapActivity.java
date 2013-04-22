package com.taxigol.taxi.activities;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.taxigol.restz.async.OnSuccess;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.events.GetAllMapObjectsEvent;
import com.taxigol.taxi.events.GetAllServicesEvent;
import com.taxigol.taxi.model.LocationReceiver.Handler;
import com.taxigol.taxi.model.MapObject;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.views.widgets.Dialog;

public class MapActivity extends Activity implements OnClickListener, Handler, OnMapLongClickListener{

	private GoogleMap map;
	private MapHandler handler;

	private Geocoder geoCoder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		handler = getApp().getLocationController();

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		handler.register(this);

		onLocationChanged(handler.getLocation());
		
		geoCoder = new Geocoder(this);
		
		map.setOnMapLongClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getApp().getEventBus().post(new GetAllMapObjectsEvent(new OnSuccess<List<MapObject>>() {

			@Override
			public void onSuccess(List<MapObject> result) {
				map.clear();
				for (MapObject mapObject : result) {
					MarkerOptions options = new MarkerOptions();
					options.position(new LatLng(mapObject.getLatitude(), mapObject.getLongitude()));
					options.icon(BitmapDescriptorFactory.fromResource(mapObject.getIconResource()));
					map.addMarker(options);
				}
			}
		}));
		
		getApp().getEventBus().post(new GetAllServicesEvent(new OnSuccess<List<Service>>() {
			@Override
			public void onSuccess(List<Service> result) {
				System.out.println("Services returned: "+result);
				for (Service service : result) {
					try {
						List<Address> addresses = geoCoder.getFromLocationName(service.getAddress(), 1, 4.570333,-74.181061, 4.766062,-73.998413);
						System.out.println("Geocoding:"+addresses);
						if (addresses.size()>0){
							Address address = addresses.get(0);
							MarkerOptions options = new MarkerOptions();
							options.position(new LatLng(address.getLatitude(), address.getLongitude()));
							options.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_user));
							map.addMarker(options);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}));
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_map:
			return true;
		case R.id.menu_panic:
			startActivity(new Intent(this, PanicActivity.class));
			return true;
		case R.id.menu_services:
			Intent i = new Intent(this, ServiciodeTaxiListActivity.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		Dialog.showAccept("Salir", "Estás seguro que deseas salir de la aplicacián?", this, this);
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

			map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));
		}
	}

	public interface MapHandler{
		public void register(Handler locationHandler);
		public Location getLocation();
	}

	@Override
	public void onMapLongClick(LatLng point) {
		
		Dialog.showAccept("Agregar un objeto al mapa", "¿Desear agregar un objeto al mapa?", this, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//startActivityForResult(ne, requestCode)
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
	}

}
