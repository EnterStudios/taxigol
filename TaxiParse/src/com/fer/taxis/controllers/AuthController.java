package com.fer.taxis.controllers;

import android.content.Context;

import com.fer.taxis.activities.AuthActivity.AuthHandler;
import com.fer.taxis.model.Taxi;
import com.fer.taxis.model.services.TaxiService;
import com.parse.ParseInstallation;
import com.taxigol.restz.async.OnSuccess;
import com.taxigol.restz.async.Task;

public class AuthController extends Controller implements AuthHandler  {

	private String taxiId;
	private TaxiService service;
	
	public AuthController(Context context, TaxiService service) {
		super(context);
		this.service = service;
		
	}
	
	public String getId(){
		return taxiId;
	}
	
	@Override
	public void onLogin(String username, String password, final OnSuccess<Void> success) {
		runAsync(new Task<Taxi>() {
			@Override
			public Taxi execute() throws Exception{
				String parseId = ParseInstallation.getCurrentInstallation().getObjectId();
				return service.auth(parseId);
			}
			@Override
			public void onSuccess(Taxi result) {
				System.out.println("AUTH succesful:"+result);
				taxiId = result.getId();
				success.onSuccess(null);
			}
		});
	}
	

}
