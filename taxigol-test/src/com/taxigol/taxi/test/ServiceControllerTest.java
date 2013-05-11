/**
 * 
 */
package com.taxigol.taxi.test;

import android.test.ActivityUnitTestCase;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.activities.AuthActivity;
import com.taxigol.taxi.events.request.RequestLogin;

/**
 * @author mono
 *
 */
public class ServiceControllerTest extends ActivityUnitTestCase<AuthActivity>{

	private AuthActivity activity;
	private EventBus bus;
	public ServiceControllerTest() {
		super(AuthActivity.class);
		activity = getActivity();
		bus = new EventBus();
		bus.register(this);
		activity.setBus(bus);
	}

	
	public void testLogin(){
		activity.requestLogin();
	}
	
	@Subscribe
	public void receiveLogin(RequestLogin login){
		System.out.println(login);
	}
	
}
