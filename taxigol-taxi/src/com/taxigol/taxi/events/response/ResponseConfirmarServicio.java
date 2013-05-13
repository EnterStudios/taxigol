package com.taxigol.taxi.events.response;

import com.taxigol.taxi.events.request.HasData;
import com.taxigol.taxi.model.Service;

public class ResponseConfirmarServicio extends HasData<Service> {

	public ResponseConfirmarServicio(Service data) {
		super(data);
	}
	
	public boolean isError(){
		return getData()==null;
	}
	
	public Service getService(){
		return getData();
	}

}
