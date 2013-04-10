package com.fer.taxis.controllers;

import java.io.IOException;

import android.content.Context;
import android.location.Location;

import com.fer.taxis.activities.MapActivity.MapHandler;
import com.fer.taxis.model.IdProvider;
import com.fer.taxis.model.LocationReceiver;
import com.fer.taxis.model.LocationReceiver.Handler;
import com.fer.taxis.model.services.PositionService;

public class PositionController extends Controller implements Handler, MapHandler {

	private LocationReceiver receiver;
	private PositionService client;
	private IdProvider idProvider;
	
	public PositionController(IdProvider idProvider, Context context, PositionService client) {
		super(context);
		this.receiver = new LocationReceiver(context);
		this.client = client;
		this.idProvider = idProvider;
		receiver.addHandler(this);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		try {
			client.updatePosition(idProvider.getId(), location.getLatitude(), location.getLongitude());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void register(Handler locationHandler) {
		receiver.addHandler(locationHandler);
	}
	
	@Override
	public Location getLocation() {
		return receiver.getLocation();
	}

}
