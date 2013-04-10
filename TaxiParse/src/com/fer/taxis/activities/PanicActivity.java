package com.fer.taxis.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.fer.taxis.App;
import com.fer.taxis.R;

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
		handler.onPanicTouched();
	}

	public interface PanicHandler{
		void onPanicTouched();
	}

}
