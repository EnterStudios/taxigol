package com.taxigol.restz.async;

public interface OnSuccess<T> {

	/**
	 * This method is called when an async task completes successfully
	 * @param result
	 */
	public void onSuccess(T result);
}
