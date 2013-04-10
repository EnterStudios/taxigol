package com.fer.taxis;

import android.app.Application;

import com.fer.taxis.activities.AuthActivity.AuthHandler;
import com.fer.taxis.activities.ConfirmacionActivity.ConfirmacionHandler;
import com.fer.taxis.activities.MapActivity;
import com.fer.taxis.activities.PanicActivity.PanicHandler;
import com.fer.taxis.controllers.AuthController;
import com.fer.taxis.controllers.PanicController;
import com.fer.taxis.controllers.PositionController;
import com.fer.taxis.controllers.ServiceController;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class App extends Application{

	private PositionController locationController;
	private ServiceController confirmacionController;
	private PanicController panicController;
	private AuthController authController; 
	 
	private ServiceFactory serviceFactory;
	 
	@Override
	public void onCreate() {
		Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key)); 
		ParseInstallation.getCurrentInstallation().saveInBackground();		
		PushService.subscribe(this, "Giants", MapActivity.class);
		PushService.setDefaultPushCallback(this, MapActivity.class);
		
		serviceFactory = new ServiceFactory("http://gpsteleclub.herokuapp.com");
		authController = new AuthController(getApplicationContext(),serviceFactory.getTaxiService());
		
		locationController = new PositionController(authController,getApplicationContext(), serviceFactory.getPositionService());
		confirmacionController = new ServiceController(authController,getApplicationContext(), serviceFactory.getTaxiServiceService());
		panicController = new PanicController(authController,getApplicationContext(), serviceFactory.getPanicService());
		
	}
	
	public PositionController getLocationController() {
		return locationController;
	}

	public ServiceController getConfirmacionController() {
		return confirmacionController;
	}
	
	public ConfirmacionHandler getConfirmacionHandler(){
		return confirmacionController;
	}
	
	public PanicHandler getPanicHandler(){
		return panicController;
	}

	public AuthHandler getAuthHandler() {
		return authController;
	}

	
}
