package com.fer.taxis.controllers;

import android.content.Context;

import com.fer.taxis.activities.AuthActivity.AuthHandler;
import com.fer.taxis.model.Taxi;
import com.fer.taxis.model.services.TaxiService;
import com.parse.ParseInstallation;
import com.taxigol.restz.async.Task;

public class AuthController extends Controller implements AuthHandler  {

	private String taxiId;
	private TaxiService service;
	
	public AuthController(Context context, TaxiService service) {
		super(context);
		
	}
	
	public String getId(){
		return taxiId;
	}
	
	@Override
	public void onLogin(String username, String password) {
		runAsync(new Task<Taxi>() {
			@Override
			public Taxi execute() {
				String parseId = ParseInstallation.getCurrentInstallation().getObjectId();
				return service.auth(parseId);
			}
			@Override
			public void onSuccess(Taxi result) {
				taxiId = result.getId();
			}
		});
	}
	

}
