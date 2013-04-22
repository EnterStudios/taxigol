package com.taxigol.taxi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.taxigol.restz.async.OnSuccess;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;

public class PanicActivity extends Activity implements OnClickListener {

	private PanicHandler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panic);
		findViewById(R.id.btnPanic).setOnClickListener(this);
		
		this.handler = getApp().getPanicHandler();
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
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.map, menu);
	    return true;
	}
	
	public App getApp(){
		return (App)getApplication(); 
	}
	
	@Override
	public void onClick(View view) {
		handler.onPanicTouched(new OnSuccess<Void>() {
			@Override
			public void onSuccess(Void result) {
				Toast.makeText(PanicActivity.this, "Notificaci�n de p�nico enviada a las autoridades", Toast.LENGTH_LONG).show();
			}
		});
	}

	public interface PanicHandler{
		void onPanicTouched(OnSuccess<Void> panic);
	}

}
