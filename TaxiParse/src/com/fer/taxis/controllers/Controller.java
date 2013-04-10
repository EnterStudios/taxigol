package com.fer.taxis.controllers;

import com.taxigol.restz.async.Task;
import com.taxigol.restz.async.TaskRunnable;
import com.taxigol.restz.async.Task.FinishedHandler;

import android.content.Context;

public abstract class Controller {

	protected Context context;
	
	public Controller(Context context) {
		this.context = context;
	}
	
	public <T> void runAsync(TaskRunnable<T> task, FinishedHandler<T> handler){
		Task.runAsync(task, handler);
	}

}
