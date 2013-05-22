/**
 * 
 */
package com.taxigol.taxi.test.activities;

import junit.framework.Assert;
import android.app.AlertDialog;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

import com.taxigol.taxi.activities.AuthActivity;
import com.taxigol.taxi.events.request.RequestLogin;
import com.taxigol.taxi.events.response.ResponseLogin;
import com.taxigol.taxi.helpers.MockEventBus;

/**
 * @author mono
 *
 */
public class AuthActivityTest extends ActivityInstrumentationTestCase2<AuthActivity>{

	private AuthActivity activity;
	private MockEventBus bus;
	
	//widgets
	private TextView username;
	private TextView password;
	private Button btnLogin;
	private AlertDialog dialog;
	
	public AuthActivityTest() {
		super(AuthActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		
		bus = new MockEventBus();
		activity.setBus(bus);
		
		username = activity.getUsernameView();
		password = activity.getPasswordView();
		btnLogin = activity.getLoginButton();
		dialog = activity.getDialog();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testTheTruth(){
		assertEquals(2, 1+1);
		Assert.assertNotNull(activity);
	}
	
	/**
	 * Tests the initial configuration for this activity
	 */
	public void testInitialSetup(){
		
		Assert.assertEquals(false, dialog.isShowing());
		Assert.assertEquals(0, username.getText().length());
		Assert.assertEquals(0, password.getText().length());
		Assert.assertFalse(activity.isLoggedIn());
	}
	
	public void testLogin() throws InterruptedException{
		
		final String usr = "fernandohur";
		final String pwd = "pdaw123";
		
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("BUTTON:"+btnLogin);
				username.setText(usr);
				password.setText(pwd);
				btnLogin.performClick();
			}
		});
		
		getInstrumentation().waitForIdleSync();
		//activity.onClick(btnLogin);
		
		RequestLogin loginEvent = (RequestLogin)bus.getEvent();
		
		Assert.assertEquals(usr,loginEvent.getData().first);
		Assert.assertEquals(pwd,loginEvent.getData().second);
		Assert.assertTrue(dialog.isShowing());
		
	}
	
	public void testOnReceivedLoginEventSuccessful(){
				
		ResponseLogin event = new ResponseLogin(true);
		activity.onLoginResponse(event);
		Assert.assertEquals(true, activity.isLoggedIn());
		Assert.assertEquals(false, activity.getDialog().isShowing());
	}
	
	public void testOnReceivedLoginEventFailed(){
		ResponseLogin event = new ResponseLogin(false);
		activity.onLoginResponse(event);
		Assert.assertEquals(false, activity.isLoggedIn());
		Assert.assertEquals(false, activity.getDialog().isShowing());
	}
	

	
}





