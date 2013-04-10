package com.fer.taxis.controllers;

import android.content.Context;
import android.location.Location;

import com.fer.taxis.activities.MapActivity.MapHandler;
import com.fer.taxis.model.IdProvider;
import com.fer.taxis.model.LocationReceiver;
import com.fer.taxis.model.LocationReceiver.Handler;
import com.fer.taxis.model.Position;
import com.fer.taxis.model.services.PositionService;
import com.taxigol.restz.async.Task;

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
	public void onLocationChanged(final Location location) {
		runAsync(new Task<Position>() {
			@Override
			public Position execute() throws Exception {
				return client.updatePosition(idProvider.getId(), location.getLatitude(), location.getLongitude());
			}
			@Override
			public void onSuccess(Position result) {
				System.out.println("Position updated");
			}
		});
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
