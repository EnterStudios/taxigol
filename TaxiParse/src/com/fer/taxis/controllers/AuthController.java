package com.fer.taxis.controllers;

import android.content.Context;
import android.content.Intent;

import com.fer.taxis.activities.AuthActivity.AuthHandler;
import com.fer.taxis.activities.MapActivity;
import com.fer.taxis.model.Taxi;
import com.taxigol.restz.async.Task.FinishedHandler;
import com.taxigol.restz.async.TaskRunnable;

public class AuthController extends Controller implements AuthHandler  {

	private String taxiId;
	
	public AuthController(Context context) {
		super(context);
		
	}
	
	public String getId(){
		return taxiId;
	}
	
	@Override
	public void onLogin(String username, String password) {
		runAsync(getAuthTast(), new FinishedHandler<Taxi>(){
			@Override
			public void onResult(Taxi result) {
				taxiId = result.getId();
				Intent i = new Intent(context, MapActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
			}
			
			
		});
	}

	private TaskRunnable<Taxi> getAuthTast() {
		return new TaskRunnable<Taxi>() {
			@Override
			public Taxi execute() {
				return new Taxi("1");
			}
		};
	}
	
	

}
