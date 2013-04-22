package com.taxigol.taxi.controllers;

import android.content.Context;
import android.location.Location;

import com.taxigol.restz.async.Task;
import com.taxigol.taxi.activities.MapActivity.MapHandler;
import com.taxigol.taxi.model.IdProvider;
import com.taxigol.taxi.model.LocationReceiver;
import com.taxigol.taxi.model.LocationReceiver.Handler;
import com.taxigol.taxi.model.Position;
import com.taxigol.taxi.model.services.PositionService;

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
		if (idProvider.getId()!=null){
			runAsync(new Task<Position>() {
				@Override
				public Position execute() throws Exception {
					return client.updatePosition(idProvider.getId(), location.getLatitude(), location.getLongitude());
				}
				@Override
				public void onSuccess(Position result) {}
			});
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
