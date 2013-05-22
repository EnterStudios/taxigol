package com.taxigol.taxi.events.request;

import android.util.Pair;

public class RequestLogin extends HasData<Pair<String, String>> {

	/**
	 * 
	 * @param data Pair=login,password
	 */
	public RequestLogin(String login, String password) {
		super(new Pair<String,String>(login, password));
	}

	public String getLogin() {
		return getData().first;
	}
	
	public String getPassword(){
		return getData().second;
	}
	

}
