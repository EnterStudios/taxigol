package com.taxigol.taxi.controllers.async;

import android.os.AsyncTask;

public class AsyncTaskImpl<T> extends AsyncTask<Task<T>, Integer, T>{

	private boolean hasError;
	private Task<T> task;
	
	public AsyncTaskImpl() {
		hasError = false;
	}
	
	@Override
	protected T doInBackground(Task<T>... params) {
		try {
			this.task = params[0];
			return task.execute();
		} catch (Exception e) {
			hasError = true;
		}
		return null;
	}
	
	protected void onPostExecute(T result) {
		if (!hasError){
			task.onSuccess(result);
		}
	};

}
