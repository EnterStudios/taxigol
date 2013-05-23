package com.taxigol.taxi.events;

public class RegistrationEvent {

	private String name;
	private String cedula;
	private String password;
	private String placa;
	
	public RegistrationEvent(CharSequence name, CharSequence cedula, CharSequence password,
			CharSequence placa) {
		super();
		this.name = name.toString();
		this.cedula = cedula.toString();
		this.password = password.toString();
		this.placa = placa.toString();
	}
	
	public String getName() {
		return name;
	}

	public String getCedula() {
		return cedula;
	}

	public String getPassword() {
		return password;
	}

	public String getPlaca() {
		return placa;
	}
	
	
}
