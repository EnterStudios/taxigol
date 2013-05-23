package com.taxigol.taxi.events;

import com.taxigol.taxi.events.request.HasData;

public class RegisterFailedEvent extends HasData<String>{

	public RegisterFailedEvent(String data) {
		super(data);
	}
	
	public String getMessage(){
		return getData();
	}

}
