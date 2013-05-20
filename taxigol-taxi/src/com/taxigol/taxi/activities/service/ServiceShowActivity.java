package com.taxigol.taxi.activities.service;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.activities.MapActivity;
import com.taxigol.taxi.controllers.ResponseCumplirServicio;
import com.taxigol.taxi.events.request.CompleteServiceRequest;
import com.taxigol.taxi.events.request.RequestConfirmarServicio;
import com.taxigol.taxi.events.request.RequestCumplirService;
import com.taxigol.taxi.events.request.RequestService;
import com.taxigol.taxi.events.response.ResponseConfirmarServicio;
import com.taxigol.taxi.events.response.ResponseService;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.views.widgets.Dialog;
import com.taxigol.taxi.views.widgets.OnInputDialogOkClicked;


public class ServiceShowActivity extends Activity implements OnClickListener, OnInputDialogOkClicked {

	public final static String EXTRA_SERVICE_ID = "service_id";

	private EventBus bus;
	
	private Button btnCumplido;
	
	private GoogleMap map;
	private TextView txtDireccion;
	private AlertDialog dialog;

	private int serviceId;
	private Service service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serviciodetaxi_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		this.bus = getApp().getEventBus();
		bus.register(this);
		
		txtDireccion = (TextView)findViewById(R.id.txtDireccion);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.service_show_map)).getMap();

		dialog = Dialog.showMessage("Cargando", "Cargando el servicio", this);

		findViewById(R.id.btnCancelar).setOnClickListener(this);
		btnCumplido = (Button)findViewById(R.id.btnCumplido);
		btnCumplido.setOnClickListener(this);
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
	
	private void updateView(){
		map.clear();
		txtDireccion.setText(service.getAddress());
		if(service.isConfirmado()){
			btnCumplido.setText("Verificar código");
		}
		else if (service.isPendiente()){
			btnCumplido.setText("Confirmar servicio");
		}
		
		BitmapDescriptor icon = service.isConfirmado()?BitmapDescriptorFactory.fromResource(R.drawable.map_marker_large):BitmapDescriptorFactory.fromResource(R.drawable.map_user);
		
		Double lat = service.getLatitude();
		Double lon = service.getLongitude();
		LatLng pos = null;
		if (lat!=null & lon!=null){
			pos = new LatLng(lat, lon);
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
			MarkerOptions marker = new MarkerOptions();
			marker.position(pos);
			marker.icon(icon);
			map.addMarker(marker);
		}else{
			Toast.makeText(this, "No se encontró la dirección en el mapa", Toast.LENGTH_LONG).show();
		}
		dialog.dismiss();
	}

	@Subscribe
	public void onResponseService(ResponseService event) throws IOException{
		this.service = event.getData();
		if(service==null){
			Toast.makeText(this, "El servicio ya no se encuentra disponible", Toast.LENGTH_SHORT).show();
		}
		else{
			updateView();
		}
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
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.equals(btnCumplido)){
			if(service.isConfirmado()){
				Dialog.showInputDialog("Codigo de seguridad", "Ingresa el codigo de seguridad", this, new OnInputDialogOkClicked() {
					
					@Override
					public void onClicked(String body) {
						String code = body.trim();
						if (service.validateCode(code)){
							bus.post(new RequestCumplirService(service.getId(), code));
						}else{
							Toast.makeText(ServiceShowActivity.this, "Codigo incorrecto, por favor intenta de nuevo", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
			else if (service.isPendiente()){
				dialog.setTitle("Confirmando servicio");
				dialog.setMessage("Espera un segundo mientras confirmamos el servicio");
				dialog.show();
				bus.post(new RequestConfirmarServicio(serviceId));				
			}
		}
	}
	
	@Subscribe
	public void onResponseConfirmarServicio(ResponseConfirmarServicio event){
		
		dialog.dismiss();
		if (event.isError()){
			NavUtils.navigateUpTo(this, new Intent(this, MapActivity.class));
			finish();
			Toast.makeText(this, "El servicio ya ha sido confirmado", Toast.LENGTH_LONG).show();
		}
		else{
			service = event.getData();
			updateView();
			Toast.makeText(this, "Servicio exitosamente confirmado", Toast.LENGTH_LONG).show();
		}
	}
	
	@Subscribe
	public void onResponseCumplirService(ResponseCumplirServicio event){
		NavUtils.navigateUpTo(this, new Intent(this, MapActivity.class));
		finish();
		Toast.makeText(this, "Codigo exitosamente verificado", Toast.LENGTH_SHORT).show();
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
