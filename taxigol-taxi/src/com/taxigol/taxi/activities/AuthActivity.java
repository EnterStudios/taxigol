package com.taxigol.taxi.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.events.AsyncCallback;
import com.taxigol.taxi.model.IdProvider;
import com.taxigol.taxi.views.widgets.Dialog;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class AuthActivity extends Activity implements OnClickListener{
	
	private AuthHandler handler;
	
	private TextView txtLogin;
	private TextView txtPass;
	
	private AlertDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_auth);
		findViewById(R.id.sign_in_button).setOnClickListener(this);
		
		txtPass = (TextView)findViewById(R.id.txtLogin);
		txtLogin = (TextView)findViewById(R.id.txtPassword);
				
		this.handler = getApp().getAuthHandler();
		
		
	}
	
	private App getApp() {
		return (App) getApplication();
	}

	@Override
	public void onClick(View view) {
		
		
		dialog = Dialog.showMessage("Autenticando", "Autenticando tu usuario y contrase�a", this);
		String username = txtLogin.getText().toString();
		String password = txtPass.getText().toString();
		handler.onLogin(username,password, new AsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				Intent i = new Intent(AuthActivity.this, MapActivity.class);
				startActivity(i);
			}
		});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
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
		if (handler.getId()!=null){
			Intent i = new Intent(this, MapActivity.class);
			startActivity(i);
			finish();
		}
	}

	public interface AuthHandler extends IdProvider{
		public void onLogin(String username, String password, AsyncCallback<Void> callback);
	}
}
