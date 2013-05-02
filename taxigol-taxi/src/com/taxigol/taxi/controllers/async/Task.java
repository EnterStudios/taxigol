package com.taxigol.taxi.controllers.async;

public interface Task<T> {
	
	public T execute() throws Exception;
	public void onSuccess(T result);
	public void onFailure(Throwable throwable);
}
