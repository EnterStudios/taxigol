package com.taxigol.taxi.events;


public abstract class CallBackEvent<T,D> {

	private AsyncCallback<T> callBack;
	private D data;	
	
	public CallBackEvent(D data, AsyncCallback<T> cb) {
		this.callBack = cb;
		this.data = data;
	}
	
	public void onSuccess(T result){
		callBack.onSuccess(result);
	}
	
	public void onError(Throwable error){
		callBack.onFailure(error);
	}
	
	public AsyncCallback<T> getCb() {
		return callBack;
	}
	
	public D getData(){
		return data;
	}
}
