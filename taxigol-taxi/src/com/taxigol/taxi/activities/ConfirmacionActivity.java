package com.taxigol.taxi.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.taxigol.restz.async.OnSuccess;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.events.ConfirmServiceEvent;
import com.taxigol.taxi.views.widgets.Dialog;

public class ConfirmacionActivity extends Activity implements OnClickListener {

	public final static String EXTRA_ADDRESS = "address"; 
	public final static String EXTRA_ID = "id"; 

	private String servicioId;
	private String direccion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmacion);

		direccion = getIntent().getStringExtra(EXTRA_ADDRESS);
		servicioId = getIntent().getStringExtra(EXTRA_ID);
		TextView txtDireccion = (TextView)findViewById(R.id.txtDireccion);
		txtDireccion.setText(direccion);
		findViewById(R.id.btnConfirmar).setOnClickListener(this);
		findViewById(R.id.btnCancelar).setOnClickListener(this);

		getActionBar().setDisplayHomeAsUpEnabled(true);
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

	public App getApp(){
		return (App)getApplication();
	}

	@Override
	public void onClick(View v) {
		if (v.equals(findViewById(R.id.btnCancelar)))
		{
			finish();
		}
		else if (v.equals(findViewById(R.id.btnConfirmar)))
		{
			Dialog.showAccept("Confirmar Servicio", "Estas seguro que deseas confirmar el servicio de taxi",
					this, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
					getApp().getEventBus().post(new ConfirmServiceEvent(servicioId, new OnSuccess<Void>() {
						@Override
						public void onSuccess(Void result) {

							Toast.makeText(getApplicationContext(), "Confirmacion enviada", Toast.LENGTH_LONG).show();
						}
					}));
				}
			});
		}
	}

	public interface ConfirmacionHandler{

		public void onConfirmar(String servicioId);

	}



}
