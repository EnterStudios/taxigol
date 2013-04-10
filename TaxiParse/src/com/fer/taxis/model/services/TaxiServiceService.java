package com.fer.taxis.model.services;

import java.io.IOException;
import java.util.List;

import com.fer.taxis.model.Service;

public interface TaxiServiceService {

	public enum State{
		// A penas se crea el servicio cuando no ha sido 
		// confirmado por ning�n taxi
		pendiente,
		//Cuando un taxista se compromete a atender el servicio pero
		//no ha llegado al cliente
		confirmado,
		//El taxista verifica el c�digo del cliente
		cumplido,
		//El taxista hab�a confirmado, pero nunca le llega al cliente
		abandonado,
		//El taxista llega, pero el cliente ya se hab�a ido
		cancelado
	}
	
	public Service create(String address, String verificationCode) throws IOException;
	public void update(State s, String serviceId) throws IOException;
	public void update(State s, String serviceId, String taxiId) throws IOException;
	public void update(State s, String serviceId, String taxiId, String verCode) throws IOException;
	public void deleteAll() throws IOException;
	public List<Service> getAll() throws IOException;
}
