package com.taxigol.restz.async;

/**
 * Represents an Async task that will be run and it's response will be delivered some
 * time in the future
 * @author fhurtad
 *
 * @param <T>
 */
public interface Task<T> {

	/**
	 * When this method is run, the actual task starts
	 * @return the return object type
	 */
	public T execute();
	/**
	 * When the task finishes succesfully this method is run
	 * @param result
	 */
	public void onSuccess(T result);
}
