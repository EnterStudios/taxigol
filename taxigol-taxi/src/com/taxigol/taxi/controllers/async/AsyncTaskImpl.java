package com.taxigol.taxi.controllers.async;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskImpl<T> extends AsyncTask<Task<T>, Integer, T>{

	private boolean hasError;
	private Task<T> task;
	
	public AsyncTaskImpl() {
		hasError = false;
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		if (values.length>0){
			Log.d("Progress Update", values[0]+"");
		}
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
