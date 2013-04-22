package com.taxigol.taxi.events;

import com.taxigol.restz.async.OnError;
import com.taxigol.restz.async.OnSuccess;

public abstract class CallBackEvent<T,D> {

	private OnSuccess<T> onSuccess;
	private OnError onError;
	private D data;	
	
	public CallBackEvent(D data, OnSuccess<T> onSuccess) {
		this.onSuccess = onSuccess;
		this.data = data;
		onError = new OnError() {
			
			@Override
			public void onError(Throwable result) {
				result.printStackTrace();
			}
		};
	}
	
	public CallBackEvent(D data, OnSuccess<T> onSuccess, OnError onError){
		this.onError = onError;
		this.data = data;
		this.onSuccess = onSuccess;
	}
	
	public void onError(Throwable error){
		onError.onError(error);
	}
	
	public OnSuccess<T> getCb() {
		return onSuccess;
	}
	
	public D getData(){
		return data;
	}
}
