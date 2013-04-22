package com.taxigol.taxi.controllers;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.common.eventbus.Subscribe;
import com.taxigol.restz.async.Task;
import com.taxigol.taxi.events.GetAllMapObjectsEvent;
import com.taxigol.taxi.model.MapObject;
import com.taxigol.taxi.model.services.MapObjectService;

public class MapObjectController extends Controller {

	private List<MapObject> mapObjects;
	private MapObjectService service;
	
	public MapObjectController(Context context, MapObjectService service) {
		super(context);
		this.service = service;
		mapObjects = new ArrayList<MapObject>();
		
	}
	
	@Subscribe
	public void getAllMapObjects(final GetAllMapObjectsEvent event){
		runAsync(new Task<List<MapObject>>() {
			@Override
			public List<MapObject> execute() throws Exception {
				return service.getAll();
			}
			@Override
			public void onSuccess(List<MapObject> result) {
				mapObjects = result;
				event.getCb().onSuccess(mapObjects);
			}
		});
	}

}
