package com.taxigol.taxi.controllers;

import android.content.Context;

import com.taxigol.restz.async.Task;
import com.taxigol.restz.async.TaskFactory;
import com.taxigol.taxi.events.CallBackEvent;

public abstract class Controller {

	protected Context context;
	
	public Controller(Context context) {
		this.context = context;
	}
	
	public <T> void runAsync(Task<T> task){
		TaskFactory.runAsync(task);
	}
	
	public <T> void runAsync(CallbackEventTask<T> task){
		TaskFactory.runAsync(task);
	}
	
	abstract class CallbackEventTask<T> extends Task<T>
	{
		private CallBackEvent<T, ?> innerEvent;
		public CallbackEventTask(CallBackEvent<T, ?> event) {
			this.innerEvent = event;
		}
		public void onSuccess(T result) {
			innerEvent.getCb().onSuccess(result);
		};
		@Override
		public void onFailure(Throwable exception) {
			innerEvent.onError(exception);
		}
	}

}
