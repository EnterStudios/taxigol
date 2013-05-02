package com.taxigol.taxi.events;

public abstract class AsyncCallback<T> {

	public abstract void onSuccess(T result);
	public void onFailure(Throwable throwable){
		throwable.printStackTrace();
	}
}
