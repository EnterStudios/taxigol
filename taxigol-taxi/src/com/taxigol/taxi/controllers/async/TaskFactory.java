package com.taxigol.taxi.controllers.async;


public class TaskFactory {

	@SuppressWarnings("unchecked")
	public static <T> void run(final Task<T> task){
		
		AsyncTaskImpl<T> asyncTask = new AsyncTaskImpl<T>();
		asyncTask.execute(task);
		
	}
}

