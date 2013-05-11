package com.taxigol.taxi.events.request;

import android.util.Pair;

public class RequestLogin extends HasData<Pair<String, String>> {

	/**
	 * 
	 * @param data Pair=login,password
	 */
	public RequestLogin(Pair<String, String> data) {
		super(data);
	}

}
