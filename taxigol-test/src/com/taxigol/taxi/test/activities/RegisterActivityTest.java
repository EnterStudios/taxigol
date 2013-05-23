package com.taxigol.taxi.test.activities;

import java.util.List;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

import com.taxigol.taxi.R;
import com.taxigol.taxi.activities.RegisterActivity;
import com.taxigol.taxi.events.RegistrationEvent;
import com.taxigol.taxi.helpers.MockEventBus;

public class RegisterActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity>{

	private MockEventBus bus;
	private RegisterActivity activity;
	
	private TextView txtName;
	private TextView txtPassword;
	private TextView txtPasswordConfirm;
	private TextView txtCedula;
	private TextView txtPlaca;
	private Button btnRegister;
	
	public RegisterActivityTest() {
		super(RegisterActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		bus = new MockEventBus();
		activity = getActivity();
		activity.setEventBus(bus);
		
		//Get the widget fields
		txtName = activity.getNameView();
		txtPassword = activity.getPasswordView();
		txtPasswordConfirm = activity.getPassConfirmView();
		txtCedula = activity.getCedulaView();
		txtPlaca = activity.getPlaca();
		btnRegister = activity.getRegisterButton();
	}
	
	public void testInitialSetup(){
		
		assertEmptyText(txtName);
		assertEmptyText(txtCedula);
		assertEmptyText(txtPassword);
		assertEmptyText(txtPasswordConfirm);
		assertEmptyText(txtPlaca);
		
		assertFalse(activity.getProgressDialog().isShowing());
	}
	
	public void testOnRegisterCorrectly(){
		
		final String name = "Carlos Duque";
		final String pass = "mypass123";
		final String cedula = "1020761351";
		final String placa = "VEG954";
		
		setValuesOnUiThread(name, pass, pass, cedula, placa);
		
		getInstrumentation().waitForIdleSync();
		Assert.assertEquals(0, activity.getErrors().size());
		
		RegistrationEvent reg = (RegistrationEvent) bus.getEvent();
		assertRegistration(reg, name, pass, cedula, placa);		
		
		assertTrue(activity.getProgressDialog().isShowing());
	}
	
	
	
	public void testOnRegisterWithWrongPasswords(){
		
		final String name = "carlos dux";
		final String pass = "mypass123";
		final String passConfirm = "mypass";
		final String cedula = "1020761351";
		final String placa = "abc123";
		
		setValuesOnUiThread(name, pass, passConfirm, cedula, placa);
		
		getInstrumentation().waitForIdleSync();
		Object nullEvent =  bus.getEvent();
		assertEquals(null, nullEvent);
		
		List<String> errors = activity.getErrors();
		assertContainsError(errors, R.string.error_passwords_match);	
		
		assertFalse(activity.getProgressDialog().isShowing());
	}
	
	public void testOnRegisterWithShortName(){
		
		final String name = "carl";
		final String pass = "mypass123";
		final String passConfirm = "mypass123";
		final String cedula = "1020761351";
		final String placa = "abc123";
		
		setValuesOnUiThread(name, pass, passConfirm, cedula, placa);
		
		getInstrumentation().waitForIdleSync();
		Object nullEvent =  bus.getEvent();
		assertEquals(null, nullEvent);
		
		List<String> errors = activity.getErrors();
		assertContainsError(errors, R.string.error_name_short);	
		
		assertFalse(activity.getProgressDialog().isShowing());
	}
	
	public void testOnRegisterWithWrongPlaca(){
		
		final String name = "carlos dux";
		final String pass = "mypass123";
		final String passConfirm = "mypass123";
		final String cedula = "1020761351";
		final String placa = "abc2239";
		
		setValuesOnUiThread(name, pass, passConfirm, cedula, placa);
		
		getInstrumentation().waitForIdleSync();
		Object nullEvent =  bus.getEvent();
		assertEquals(null, nullEvent);
		
		List<String> errors = activity.getErrors();
		assertContainsError(errors, R.string.error_placa_regex);	
		
		assertFalse(activity.getProgressDialog().isShowing());
	}
	
	//
	// Helper methods
	//
	
	void setValuesOnUiThread(final String name, final String pass, final String passConfirm, final String cedula, final String placa){
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				txtName.setText(name);
				txtPassword.setText(pass);
				txtPasswordConfirm.setText(passConfirm);
				txtCedula.setText(cedula);
				txtPlaca.setText(placa);
				btnRegister.performClick();
			}
		});
	}
	
	void assertContainsError(List<String> errors,int resId){
		assertTrue(errors.contains(activity.getString(resId)));
	}
		
	void assertEmptyText(TextView txtView){
		Assert.assertEquals(txtView.getText().length(), 0);
	}
	
	void assertRegistration(RegistrationEvent r,String name, String pass, String cedula, String placa){
		Assert.assertEquals(r.getName(), name);
		Assert.assertEquals(r.getCedula(), cedula);
		Assert.assertEquals(r.getPlaca(), placa );
		Assert.assertEquals(r.getPassword(), pass);
	}

}











