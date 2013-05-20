package com.taxigol.taxi.activities;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.activities.service.ServiceShowActivity;
import com.taxigol.taxi.events.NewServiceArrivedEvent;
import com.taxigol.taxi.events.ServicesChangedEvent;
import com.taxigol.taxi.events.request.RequestLocation;
import com.taxigol.taxi.events.request.RequestServices;
import com.taxigol.taxi.model.LocationReceiver.Handler;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.views.widgets.Dialog;

public class MapActivity extends Activity implements OnClickListener, OnInfoWindowClickListener{

	private HashMap<Marker, Service> serviceMap;
	private EventBus bus;
	private GoogleMap map;
	private LatLng latestLocation;
	/**
	 * variable tells if its the first onResume()
	 */
	private boolean firstOnResume;
	
	private List<Service> services;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		serviceMap = new HashMap<Marker, Service>();
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMyLocationEnabled(true);
		map.setOnInfoWindowClickListener(this);
		
		bus = getApp().getEventBus();
		firstOnResume = true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		bus.register(this);
		bus.post(new RequestServices());
		bus.post(new RequestLocation());
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		bus.unregister(this);
	}
	
	@Subscribe
	public void onServicesChanged(ServicesChangedEvent event){
		
		services = event.getData();
		updateView();
	}
	
	private void updateView(){
		map.clear();
		if (services!=null){
			for (Service service : services) {
				
				if (service.getLatitude()!=null && service.getLongitude() != null
						&& service.isConfirmado() || service.isPendiente()){
					MarkerOptions options = getMarkerOptions(service);
					Marker marker = map.addMarker(options);
					serviceMap.put(marker, service);
					
				}
			}
		}
	}
	
	public MarkerOptions getMarkerOptions(Service service){
		
		BitmapDescriptor icon = null;
		if (service.isConfirmado()){
			icon = BitmapDescriptorFactory.fromResource(R.drawable.map_marker_large);		
		}
		else if (service.isPendiente()){
			icon = BitmapDescriptorFactory.fromResource(R.drawable.map_user);
		}
		
		MarkerOptions options = new MarkerOptions();
		options.position(new LatLng(service.getLatitude(), service.getLongitude()));
		options.icon(icon);
		options.title(service.getAddress());
		options.snippet("Toca para más detalles");
		return options;
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
			if (latestLocation!=null){
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(latestLocation, 14));
			}
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
	public void onInfoWindowClick(Marker marker) {
		Service s = serviceMap.get(marker);
		if (s!=null){
			Intent i = new Intent(this, ServiceShowActivity.class);
			i.putExtra(ServiceShowActivity.EXTRA_SERVICE_ID, s.getId());
			startActivity(i);
		}
	}


	@Subscribe
	public void onLocationChanged(Location location) {
		if (location!=null){
			LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
			latestLocation = pos;
			if (firstOnResume){
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));
				firstOnResume = false;
			}
		}
	}
	
	@Subscribe
	public void onNewServicedArrived(NewServiceArrivedEvent event){
		Service service = event.getData();
		
		Intent i = new Intent(this, ServiceShowActivity.class);
		i.putExtra(ServiceShowActivity.EXTRA_SERVICE_ID, service.getId());
		int flags = PendingIntent.FLAG_UPDATE_CURRENT | Intent.FLAG_ACTIVITY_NEW_TASK;
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, flags);
		
		NotificationCompat.Builder builder = new Builder(this);
		
		builder.setContentText(service.getAddress());
		builder.setContentTitle("Nuevo servicio de taxi");
		builder.setSmallIcon(R.drawable.map_user);
		builder.setContentIntent(pendingIntent);
		builder.setAutoCancel(true);
		NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification notif = builder.build();
		notif.defaults |= Notification.DEFAULT_SOUND;
		notif.defaults |= Notification.DEFAULT_VIBRATE;
		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(service.getLatitude(), service.getLongitude()),13));
		
		manager.notify(service.getId(), notif);
	}

	public interface MapHandler{
		public void register(Handler locationHandler);
		public Location getLocation();
	}

	

}
