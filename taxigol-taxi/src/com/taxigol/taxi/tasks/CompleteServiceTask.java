package com.taxigol.taxi.tasks;

import android.os.AsyncTask;

import com.taxigol.taxi.model.services.TaxiServiceService;

public class CompleteServiceTask extends AsyncTask<Object, Integer, Void> {

	TaxiServiceService client;
	
	public CompleteServiceTask(TaxiServiceService client) {
		this.client = client;
	}
	
	@Override
	protected Void doInBackground(Object... params) {
		// TODO Auto-generated method stub
		return null;
	}
}
