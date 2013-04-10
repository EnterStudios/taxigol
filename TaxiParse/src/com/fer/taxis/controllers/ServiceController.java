package com.fer.taxis.controllers;

import java.io.IOException;

import android.content.Context;

import com.fer.taxis.activities.ConfirmacionActivity.ConfirmacionHandler;
import com.fer.taxis.model.IdProvider;
import com.fer.taxis.model.services.TaxiServiceService;
import com.fer.taxis.model.services.TaxiServiceService.State;

public class ServiceController extends Controller implements ConfirmacionHandler{

	private TaxiServiceService client;
	private IdProvider idProvider;
	
	public ServiceController(IdProvider idProvider,Context activity, TaxiServiceService client) {
		super(activity);
		this.client = client;
		this.idProvider = idProvider;
	}
	
	@Override
	public void onConfirmar(String serviceId) {
		try {
			client.update(State.confirmado, serviceId, idProvider.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
