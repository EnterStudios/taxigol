package com.taxigol.taxi.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.taxigol.restz.async.OnSuccess;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.events.CancelServiceEvent;
import com.taxigol.taxi.events.FindServiceEvent;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.views.widgets.Dialog;
import com.taxigol.taxi.views.widgets.OnInputDialogOkClicked;


public class ServiciodeTaxiDetailActivity extends Activity implements OnClickListener {

	public final static String EXTRA_SERVICE_ID = "service_id";

	private TextView txtDireccion;
	private TextView txtEstado;

	private AlertDialog dialog;

	private String serviceId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serviciodetaxi_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);


		txtDireccion = (TextView)findViewById(R.id.txtDireccion);
		txtEstado = (TextView)findViewById(R.id.txtEstado);

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
		serviceId = getIntent().getStringExtra(EXTRA_SERVICE_ID);
		getApp().getEventBus().post(new FindServiceEvent(serviceId, new OnSuccess<Service>() {
			@Override
			public void onSuccess(Service result) {
				System.out.println("RESULT:"+result);
				txtDireccion.setText(result.getAddress());
				txtEstado.setText(result.getState());
				dialog.dismiss();
			}
		}));
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
	public void onClick(View v) {
		if (v.equals(findViewById(R.id.btnCancelar))){
			getApp().getEventBus().post(new CancelServiceEvent(serviceId, new OnSuccess<Void>() {
				@Override
				public void onSuccess(Void result) {
					Toast.makeText(ServiciodeTaxiDetailActivity.this, "Servicio cancelado exitosamente", Toast.LENGTH_LONG).show();
				}
			}));
		}
		else if (v.equals(findViewById(R.id.btnCumplido))){
			Dialog.showInputDialog("Verificar el codigo", "Ingresa el código del servicio para verificarlo", this, new OnInputDialogOkClicked() {
				
				@Override
				public void onClicked(String body) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}

	
}
