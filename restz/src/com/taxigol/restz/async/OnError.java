package com.taxigol.restz.async;

public interface OnError{

	/**
	 * This method is called when a task is completed with error.
	 * @param result tipically this will we an Exception
	 */
	public void onError(Throwable result);
}
