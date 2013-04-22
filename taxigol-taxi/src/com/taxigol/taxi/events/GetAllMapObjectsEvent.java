package com.taxigol.taxi.events;

import java.util.List;

import com.taxigol.restz.async.OnSuccess;
import com.taxigol.taxi.model.MapObject;

public class GetAllMapObjectsEvent extends CallBackEvent<List<MapObject>, Void> {

	public GetAllMapObjectsEvent(OnSuccess<List<MapObject>> onSuccess) {
		super(null, onSuccess);
		
	}

}
