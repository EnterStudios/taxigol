package com.taxigol.taxi;

import android.app.Application;

import com.google.common.eventbus.EventBus;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.taxigol.taxi.activities.AuthActivity.AuthHandler;
import com.taxigol.taxi.activities.MapActivity;
import com.taxigol.taxi.activities.PanicActivity.PanicHandler;
import com.taxigol.taxi.controllers.AuthController;
import com.taxigol.taxi.controllers.MapObjectController;
import com.taxigol.taxi.controllers.PanicController;
import com.taxigol.taxi.controllers.PositionController;
import com.taxigol.taxi.controllers.ServiceController;

public class App extends Application{

	private PositionController locationController;
	private ServiceController servicesController;
	private PanicController panicController;
	private AuthController authController; 
	private MapObjectController mapObjectController;
	 
	private ServiceFactory serviceFactory;
	
	private EventBus bus;
	 
	@Override
	public void onCreate() {  
		Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key)); 
		ParseInstallation.getCurrentInstallation().saveInBackground();		
		PushService.subscribe(this, "Giants", MapActivity.class);
		PushService.setDefaultPushCallback(this, MapActivity.class);
		
		serviceFactory = new ServiceFactory(getApplicationContext().getString(R.string.server_url));
		
		bus = new EventBus();
		
		//Init controllers
		authController = new AuthController(getApplicationContext(),serviceFactory.getTaxiService());
		locationController = new PositionController(authController,getApplicationContext(), serviceFactory.getPositionService());
		servicesController = new ServiceController(authController,getApplicationContext(), serviceFactory.getTaxiServiceService());
		panicController = new PanicController(authController,getApplicationContext(), serviceFactory.getPanicService());
		mapObjectController = new MapObjectController(getApplicationContext(), serviceFactory.getMapObjectsService());
		
		//Register buses
		bus.register(servicesController);
		bus.register(mapObjectController);
	}
	
	public PositionController getLocationController() {
		return locationController;
	}

	public ServiceController getServiceController() {
		return servicesController;
	}
	
	public PanicHandler getPanicHandler(){
		return panicController;
	}

	public AuthHandler getAuthHandler() {
		return authController;
	}

	public EventBus getEventBus() {
		return bus;
	}

	
}
