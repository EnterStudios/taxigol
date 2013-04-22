package com.taxigol.restz.async;

/**
 * Represents an Async task that will be run and it's response will be delivered some
 * time in the future
 * @author fhurtad
 *
 * @param <T>
 */
public abstract class Task<T> {

	/**
	 * When this method is run, the actual task starts
	 * @return the return object type
	 */
	public abstract T execute() throws Exception;
	/**
	 * When the task finishes succesfully this method is run
	 * @param result
	 */
	public abstract void onSuccess(T result);
	
	/**
	 * This method is excecuted if the Task raises an Exception while trying to run it
	 * @param exception
	 */
	public void onFailure(Throwable exception){
		exception.printStackTrace();
	}
}
