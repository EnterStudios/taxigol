package com.taxigol.taxi.events.response;

import com.taxigol.taxi.events.request.HasData;
import com.taxigol.taxi.model.Driver;

public class RegisterSuccessfulEvent extends HasData<Driver>{

	public RegisterSuccessfulEvent(Driver data) {
		super(data);
	}

}
