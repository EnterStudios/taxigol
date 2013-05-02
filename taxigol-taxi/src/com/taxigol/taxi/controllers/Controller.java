package com.taxigol.taxi.controllers;

import android.content.Context;

import com.taxigol.taxi.controllers.async.Task;
import com.taxigol.taxi.controllers.async.TaskFactory;

public abstract class Controller {

	protected Context context;
	
	public Controller(Context context) {
		this.context = context;
	}
	
	public <T> void runAsync(Task<T> task){
		TaskFactory.run(task);
	}
	
}
