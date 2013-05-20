package com.taxigol.taxi.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.events.request.RequestLogin;
import com.taxigol.taxi.events.response.ResponseLogin;
import com.taxigol.taxi.views.widgets.Dialog;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class AuthActivity extends Activity implements OnClickListener{
		
	private TextView txtLogin;
	private TextView txtPass;
	private Button btnLogin;
	
	private ProgressDialog dialog;
	
	private EventBus bus;
	
	private boolean loggedIn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		loggedIn = false;
		setContentView(R.layout.activity_auth);
		
		txtPass = (TextView)findViewById(R.id.txtLogin);
		txtLogin = (TextView)findViewById(R.id.txtPassword);
		btnLogin = (Button) findViewById(R.id.sign_in_button);
		btnLogin.setOnClickListener(this);
		
		bus = getApp().getEventBus();
		
		dialog = ProgressDialog.show(this, "Autenticando", "Espera un momento mientras te autenticamos");
		dialog.dismiss();
	}
	
	private App getApp() {
		return (App) getApplication();
	}

	@Override
	protected void onPause() {
		super.onPause();
		bus.unregister(this);
		if (dialog!=null){
			dialog.dismiss();
		}
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		if (intent.getStringExtra("exit")!=null){
			Process.killProcess(Process.myPid());
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		bus.register(this);
		if (loggedIn){
			Intent i = new Intent(this, MapActivity.class);
			startActivity(i);
			finish();
		}
	}
	
	@Subscribe
	public void onLoginResponse(ResponseLogin event){
		dialog.hide();
		loggedIn = event.isLoggedIn();
		if (loggedIn){
			Intent intent = new Intent(this, MapActivity.class);
			startActivity(intent);
		}
		else{
			Dialog.showMessage("Error autenticando", "Usuario o contraseña incorrectos", this);
		}
	}
	
	@Override
	public void onClick(View view) {
		
		requestLogin();
	}
	
	public void requestLogin(){
		dialog.show();
		String username = txtLogin.getText().toString();
		String password = txtPass.getText().toString();
		bus.post(new RequestLogin(new Pair<String, String>(username, password)));
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setBus(EventBus bus) {
		this.bus = bus;
	}
	
	public EventBus getBus() {
		return bus;
	}

	public TextView getUsernameView() {
		return txtLogin;
	}

	public TextView getPasswordView() {
		return txtPass;
	}

	public Button getLoginButton() {
		return btnLogin;
	}

	public AlertDialog getDialog() {
		return dialog;
	}

}
