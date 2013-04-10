package com.taxigol.restz.async;

import android.os.AsyncTask;

/**
 * Helper class to run AzyncTasks
 * @author fhurtad
 *
 * @param <T> the return object's TYPE
 */
public class TaskFactory<T> extends AsyncTask<Task<T>, Integer, T> {

	private Task<T> handler;
	
	protected T doInBackground(Task<T>... params) {
		try {
			return params[0].execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	};
	
	@SuppressWarnings("unchecked")
	private void run(Task<T> task){
		this.handler = task;
		execute(task);
	}
	
	protected void onPostExecute(T result) {
		handler.onSuccess(result);
	};
	
	
	/**
	 * This is the preferred method that should be called to create the Task
	 * @param task the task to be executed
	 * @param handler the handler that receives the respone
	 */
	public static <P> void runAsync(Task<P> task){
		TaskFactory<P> asyncTask = new TaskFactory<P>();
		asyncTask.run(task);
	}

}
