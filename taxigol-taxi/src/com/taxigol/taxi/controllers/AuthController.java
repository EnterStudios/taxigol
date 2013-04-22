package com.taxigol.taxi.controllers;

import android.content.Context;

import com.parse.ParseInstallation;
import com.taxigol.restz.async.OnSuccess;
import com.taxigol.restz.async.Task;
import com.taxigol.taxi.activities.AuthActivity.AuthHandler;
import com.taxigol.taxi.model.Taxi;
import com.taxigol.taxi.model.services.TaxiService;

public class AuthController extends Controller implements AuthHandler  {

	private String taxiId;
	private TaxiService service;
	
	public AuthController(Context context, TaxiService service) {
		super(context);
		this.service = service;
		taxiId = null;
	}
	
	public String getId(){
		return taxiId;
	}
	
	@Override
	public void onLogin(String username, String password, final OnSuccess<Void> success) {
		runAsync(new Task<Taxi>() {
			@Override
			public Taxi execute() throws Exception{
				System.out.println("Apptempting to log in");
				String parseId = ParseInstallation.getCurrentInstallation().getObjectId();
				System.out.println("Logging in with installation_id:"+parseId);
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
