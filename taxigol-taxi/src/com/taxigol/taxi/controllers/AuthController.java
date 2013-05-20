package com.taxigol.taxi.controllers;

import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.controllers.async.DefaultTask;
import com.taxigol.taxi.events.request.RequestLogin;
import com.taxigol.taxi.events.response.ResponseLogin;
import com.taxigol.taxi.model.IdProvider;
import com.taxigol.taxi.model.Taxi;
import com.taxigol.taxi.model.services.TaxiService;
import com.urbanairship.push.PushManager;

public class AuthController extends Controller implements IdProvider{

	private String taxiId;
	private TaxiService service;
	
	public AuthController(TaxiService service) {
		this.service = service;
		taxiId = null;
	}
	
	public String getId(){
		return taxiId;
	}
	
	@Subscribe
	public void onLogin(RequestLogin event) {
		runAsync(new DefaultTask<Taxi>() {
			@Override
			public Taxi execute() throws Exception{
				String apid = PushManager.shared().getAPID();
				return service.auth(apid);
			}
			@Override
			public void onSuccess(Taxi result) {
				System.out.println("Result:"+result);
				if (result!=null){
					taxiId = result.getId();
					sendLogin(true);
				}
				else{
					sendLogin(false);
				}
			}
		});
	}

	public void sendLogin(boolean b) {
		getEventBus().post(new ResponseLogin(b));
	}
	

}
