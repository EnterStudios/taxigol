package com.fer.taxis.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.fer.taxis.App;
import com.fer.taxis.R;
import com.fer.taxis.receivers.ParseReceiver;
import com.fer.taxis.views.widgets.Dialog;

public class ConfirmacionActivity extends Activity implements OnClickListener {

	private ConfirmacionHandler handler;
	private String servicioId;
	private String direccion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmacion);

		handler = getApp().getConfirmacionHandler();

		servicioId = getIntent().getStringExtra(ParseReceiver.PARAM_ID);
		direccion = getIntent().getStringExtra(ParseReceiver.PARAM_DIR);

		TextView txtDireccion = (TextView)findViewById(R.id.txtDireccion);
		txtDireccion.setText(direccion);
		findViewById(R.id.btnConfirmar).setOnClickListener(this);
		findViewById(R.id.btnCancelar).setOnClickListener(this);

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
					handler.onConfirmar(servicioId);
					Toast.makeText(getApplicationContext(), "Confirmacion enviada", Toast.LENGTH_LONG).show();
				}
			});
		}
	}

	public interface ConfirmacionHandler{

		public void onConfirmar(String servicioId);

	}



}
