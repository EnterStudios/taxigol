package com.taxigol.taxi.events.response;

import com.taxigol.taxi.events.request.HasData;

public class ResponseLogin extends HasData<Boolean> {

	public ResponseLogin(Boolean data) {
		super(data);
	}
	
	public boolean isLoggedIn(){
		return getData();
	}

}
