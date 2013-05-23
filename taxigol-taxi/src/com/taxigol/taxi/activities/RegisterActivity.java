package com.taxigol.taxi.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.eventbus.EventBus;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.events.RegisterFailedEvent;
import com.taxigol.taxi.events.RegistrationEvent;
import com.taxigol.taxi.events.response.RegisterSuccessfulEvent;
import com.taxigol.taxi.views.widgets.Dialog;

public class RegisterActivity extends Activity implements OnClickListener {

	private EventBus eventBus;
	private AlertDialog dialog;
	private ProgressDialog progressDialog;
	
	private ArrayList<String> errors;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		eventBus = getApp().getEventBus();
		eventBus.register(this);
		
		getRegisterButton().setOnClickListener(this);
		errors = new ArrayList<String>();
		dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Espera un segundo mientras creamos el registro");
		progressDialog.hide();
	}
	
	public App getApp(){
		return (App)getApplication();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		dialog.hide();
		progressDialog.hide();
	}
	
	@Override
	public void onClick(View v) {
		errors.clear();
		dialog.hide();
		
		CharSequence password = getPasswordView().getText().toString();
		CharSequence passwordConfirmation = getPassConfirmView().getText().toString();
		CharSequence name = getNameView().getText();
		CharSequence cedula = getCedulaView().getText();
		CharSequence placa = getPlaca().getText();
		String placaStr = placa.toString();
		Log.d(RegisterActivity.class.toString(), placaStr);
		
		if (!password.equals(passwordConfirmation)){
			addError(R.string.error_passwords_match);
			setError(getPassConfirmView());
			setError(getPasswordView());
		}
		else if (password.length()<=5){
			addError(R.string.error_password_short);
			setError(getPassConfirmView());
			setError(getPasswordView());
		}
		else{
			setOk(getPasswordView());
			setOk(getPassConfirmView());
		}
		if(name.length()<=5){
			addError(R.string.error_name_short);
			setError(getNameView());
		}
		else{
			setOk(getNameView());
		}
		if (cedula.length()<= 5){
			addError(R.string.error_cedula_short);
			setError(getCedulaView());
		}
		else{
			setOk(getCedulaView());
		}
		
		if (!placaStr.matches("[a-zA-Z]{3}[\\d]{3}")){
			addError(R.string.error_placa_regex);
			setError(getPlaca());
		}
		else{
			setOk(getPlaca());
		}
		
		if (errors.size()==0){
			RegistrationEvent reg = new RegistrationEvent(name, cedula, password, placa);
			getEventBus().post(reg);
			
			progressDialog.show();
		}else{
			dialog = new AlertDialog.Builder(this).setTitle("Error!").setMessage(errors.get(0)).create();
			dialog.show();
		}
	}
	
	public void onRegisterSuccessful(RegisterSuccessfulEvent event){
		progressDialog.hide();
		Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_SHORT).show();
		NavUtils.navigateUpTo(this, new Intent(this, AuthActivity.class));
	}
	
	public void onRegisterFailed(RegisterFailedEvent event){
		progressDialog.hide();
		dialog = Dialog.showMessage("Error!", "No hemos podido registrarte. Verifica que tu cedula sea correcta.", this);
		dialog.show();
	}
	
	void setOk(TextView view){
		
		int c = Color.parseColor("#5bb75b");
		view.setBackgroundColor(c);
	}
	
	void setError(TextView view){
		int c = Color.parseColor("#bd362f");
		view.setBackgroundColor(c);
	}
	
	void addError(int resId){
		errors.add(getString(resId));
	}
	
	public List<String> getErrors(){
		return errors;
	}
	
	public AlertDialog getDialog() {
		return dialog;
	}
	
	public EventBus getEventBus() {
		return eventBus;
	}

	public void setEventBus(EventBus bus) {
		this.eventBus = bus;
	}

	public TextView getNameView() {
		return (TextView) findViewById(R.id.txtName);
	}

	public TextView getPasswordView() {
		return (TextView) findViewById(R.id.txtPass);
	}

	public TextView getPassConfirmView() {
		return (TextView) findViewById(R.id.txtPassConfirm);
	}

	public TextView getCedulaView() {
		return (TextView) findViewById(R.id.txtCedula);
	}

	public TextView getPlaca() {
		return (TextView) findViewById(R.id.txtPlaca);
	}

	public Button getRegisterButton() {
		return (Button) findViewById(R.id.btnRegister);
	}
	
	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}

}
