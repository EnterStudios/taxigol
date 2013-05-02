package com.taxigol.taxi.controllers.async;

import com.taxigol.taxi.events.CallBackEvent;

public abstract class CallbackEventTask<T> implements Task<T>{

	private CallBackEvent<T, ?> event;
	
	public CallbackEventTask(CallBackEvent<T, ?> event) {
		this.event = event;
	}
	
	public void onSuccess(T result) {
		event.onSuccess(result);
	};
	@Override
	public void onFailure(Throwable throwable) {
		event.onError(throwable);
	}
	
}
