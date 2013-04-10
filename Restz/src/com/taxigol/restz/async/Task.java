package com.taxigol.restz.async;

import android.os.AsyncTask;

public class Task<T> extends AsyncTask<TaskRunnable<T>, Integer, T> {

	private FinishedHandler<T> handler;
	
	protected T doInBackground(TaskRunnable<T>... params) {
		return params[0].execute();
	};
	
	@SuppressWarnings("unchecked")
	private void run(TaskRunnable<T> task, FinishedHandler<T> handler){
		this.handler = handler;
		execute(task);
	}
	
	protected void onPostExecute(T result) {
		handler.onResult(result);
	};
	
	public interface FinishedHandler<T>{
		void onResult(T result);
	}
	
	public static <P> void runAsync(TaskRunnable<P> task, FinishedHandler<P> handler){
		Task<P> asyncTask = new Task<P>();
		asyncTask.run(task, handler);
	}

}
