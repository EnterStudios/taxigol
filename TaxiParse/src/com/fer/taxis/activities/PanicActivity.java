package com.fer.taxis.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.fer.taxis.App;
import com.fer.taxis.R;
import com.taxigol.restz.async.OnSuccess;

public class PanicActivity extends Activity implements OnClickListener {

	private PanicHandler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panic);
		findViewById(R.id.btnPanic).setOnClickListener(this);
		
		this.handler = getApp().getPanicHandler();
	}
	
	public App getApp(){
		return (App)getApplication(); 
	}
	
	@Override
	public void onClick(View view) {
		handler.onPanicTouched(new OnSuccess<Void>() {
			@Override
			public void onSuccess(Void result) {
				Toast.makeText(PanicActivity.this, "Notificación de pánico enviada a las autoridades", Toast.LENGTH_LONG).show();
			}
		});
	}

	public interface PanicHandler{
		void onPanicTouched(OnSuccess<Void> panic);
	}

}
