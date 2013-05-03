package com.taxigol.taxi.controllers;

import com.taxigol.taxi.controllers.async.Task;
import com.taxigol.taxi.controllers.async.TaskFactory;

public abstract class Controller {
	
	public <T> void runAsync(Task<T> task){
		TaskFactory.run(task);
	}
	
}
