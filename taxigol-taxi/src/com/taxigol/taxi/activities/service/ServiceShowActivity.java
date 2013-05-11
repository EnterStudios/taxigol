package com.taxigol.taxi.activities.service;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.activities.MapActivity;
import com.taxigol.taxi.events.request.CompleteServiceRequest;
import com.taxigol.taxi.events.request.RequestService;
import com.taxigol.taxi.events.response.ResponseService;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.views.widgets.Dialog;
import com.taxigol.taxi.views.widgets.OnInputDialogOkClicked;


public class ServiceShowActivity extends Activity implements OnClickListener, OnInputDialogOkClicked {

	public final static String EXTRA_SERVICE_ID = "service_id";

	
	
	private GoogleMap map;
	private TextView txtDireccion;
	private AlertDialog dialog;

	private int serviceId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serviciodetaxi_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		getApp().getEventBus().register(this);

		txtDireccion = (TextView)findViewById(R.id.txtDireccion);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.service_show_map)).getMap();

		dialog = Dialog.showMessage("Cargando", "Cargando el servicio", this);

		findViewById(R.id.btnCancelar).setOnClickListener(this);
		findViewById(R.id.btnCumplido).setOnClickListener(this);
	}

	public App getApp() {
		return (App) getApplication();
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
		serviceId = getIntent().getIntExtra(EXTRA_SERVICE_ID,-1);
		getApp().getEventBus().post(new RequestService(serviceId));
	}

	@Subscribe
	public void onServiceLoaded(ResponseService service) throws IOException{
		Service result = service.getData();
		System.out.println("RESULT:"+result);
		txtDireccion.setText(result.getAddress());
		
		Double lat = result.getLatitude();
		Double lon = result.getLongitude();
		LatLng pos = null;
		if (lat!=null & lon!=null){
			pos = new LatLng(lat, lon);
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
			MarkerOptions marker = new MarkerOptions();
			marker.position(pos);
			marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_user));
			map.addMarker(marker);
		}else{
			Toast.makeText(this, "No se encontró la dirección en el mapa", Toast.LENGTH_LONG).show();
		}
		dialog.dismiss();

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
			Intent i = new Intent(this, ServiceListActivity.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
//		if (v.equals(findViewById(R.id.btnCancelar))){
//			getApp().getEventBus().post(new CancelServiceEvent(serviceId, new AsyncCallback<Void>() {
//				@Override
//				public void onSuccess(Void result) {
//					Toast.makeText(ServiceShowActivity.this, "Servicio cancelado exitosamente", Toast.LENGTH_LONG).show();
//				}
//			}));
//		}
//		else if (v.equals(findViewById(R.id.btnCumplido))){
//			Dialog.showInputDialog("Verificar el codigo", "Ingresa el código del servicio para verificarlo", this, ServiceShowActivity.this);
//		}
	}


	/**
	 * This method is executed when the input dialog is accepted.
	 * @param body the input text
	 */
	@Override
	public void onClicked(String body) {
		Pair<String,String> data = new Pair<String,String>(serviceId+"",body.trim());
		CompleteServiceRequest event = new CompleteServiceRequest(data);
		getApp().getEventBus().post(event);
	}

}
