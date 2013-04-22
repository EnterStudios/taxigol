package com.taxigol.taxi.model;

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