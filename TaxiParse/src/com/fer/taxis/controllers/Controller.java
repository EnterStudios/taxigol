package com.fer.taxis.controllers;

import android.content.Context;

import com.taxigol.restz.async.Task;
import com.taxigol.restz.async.TaskFactory;

public abstract class Controller {

	protected Context context;
	
	public Controller(Context context) {
		this.context = context;
	}
	
	public <T> void runAsync(Task<T> task){
		TaskFactory.runAsync(task);
	}

}
