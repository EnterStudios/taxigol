package com.taxigol.taxi;

import android.app.Application;

import com.google.common.eventbus.EventBus;
import com.taxigol.taxi.controllers.AuthController;
import com.taxigol.taxi.controllers.PositionController;
import com.taxigol.taxi.controllers.ServiceController;
import com.taxigol.taxi.receivers.AUReceiver;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;

public class App extends Application{

	private PositionController locationController;
	private ServiceController servicesController;
	private AuthController authController;
	 
	private ServiceFactory serviceFactory;
	
	private EventBus bus;
	private ActivityLoader loader;
		
	@Override
	public void onCreate() {
		
		//init urban airhship stuff
		AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions(getApplicationContext());
		UAirship.takeOff(this,options);
		PushManager.shared().setIntentReceiver(AUReceiver.class);
		PushManager.enablePush();
		
		serviceFactory = new ServiceFactory(getApplicationContext().getString(R.string.server_url));
		
		//Init EventBus
		bus = new EventBus();
		//Init ActivityLoader
		loader = new DefaultActivityLoader();
		loader.setContext(this.getApplicationContext());
		
		//Init controllers
		authController = new AuthController(serviceFactory.getTaxiService());
		locationController = new PositionController(authController,getApplicationContext(), serviceFactory.getPositionService());
		servicesController = new ServiceController(authController, serviceFactory.getTaxiServiceService());
		
		//Register dynamic broadcast receivers
		registerReceiver(servicesController.getReceiver(), servicesController.getIntentFilter());
		
		//Register buses
		servicesController.setEventBus(bus);
		bus.register(servicesController);
		
		locationController.setEventBus(bus);
		bus.register(locationController);
		
		authController.setEventBus(bus);
		bus.register(authController);
		
		
	}
	
	public PositionController getLocationController() {
		return locationController;
	}

	public ServiceController getServiceController() {
		return servicesController;
	}

	public EventBus getEventBus() {
		return bus;
	}
	
}
