package com.taxigol.taxi.controllers;

import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.controllers.async.DefaultTask;
import com.taxigol.taxi.controllers.async.Task;
import com.taxigol.taxi.events.RegisterFailedEvent;
import com.taxigol.taxi.events.RegistrationEvent;
import com.taxigol.taxi.events.request.RequestLogin;
import com.taxigol.taxi.events.response.RegisterSuccessfulEvent;
import com.taxigol.taxi.events.response.ResponseLogin;
import com.taxigol.taxi.model.Driver;
import com.taxigol.taxi.model.IdProvider;
import com.taxigol.taxi.model.services.DriverService;

public class DriverController extends Controller implements IdProvider{

	private Driver driver;
	private DriverService driverService;
	
	public DriverController(DriverService driverService) {
		this.driverService = driverService;
		driver = null;
	}
	
	@Subscribe
	public void onRegistrationEvent(RegistrationEvent event){
		final String name = event.getName();
		final String cedula = event.getCedula();
		final String placa = event.getPlaca();
		final String password = event.getPassword();
		
		runAsync(new Task<Driver>() {
			
			@Override
			public Driver execute() throws Exception {
				return driverService.create(name, cedula, password, placa);
			}
			@Override
			public void onSuccess(Driver result) {
				getEventBus().post(new RegisterSuccessfulEvent(result));
				driver = result;
			}
			@Override
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
				getEventBus().post(new RegisterFailedEvent("Registration failed"));
			}
		});
	}
	
	@Subscribe
	public void onReguestLogin(RequestLogin event){
		final String cedula = event.getLogin();
		final String password = event.getPassword();
		
		runAsync(new DefaultTask<Driver>() {
			@Override
			public Driver execute() throws Exception {
				return driverService.auth(cedula, password);
			}
			@Override
			public void onSuccess(Driver result) {
				driver = result;
				getEventBus().post(new ResponseLogin(true));
			}
			@Override
			public void onFailure(Throwable throwable) {
				if (throwable instanceof IllegalArgumentException){
					getEventBus().post(new ResponseLogin(false));
				}
				else{
					super.onFailure(throwable);
				}
			}
		});
	}

	@Override
	public String getId() {
		if (driver!=null){
			return driver.getTaxi_id()+"";
		}
		else{
			return null;
		}
	}
	
}





