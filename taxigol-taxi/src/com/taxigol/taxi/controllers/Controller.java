package com.taxigol.taxi.controllers;

import com.google.common.eventbus.EventBus;
import com.taxigol.taxi.controllers.async.Task;
import com.taxigol.taxi.controllers.async.TaskFactory;

public abstract class Controller {
	
	private EventBus eventBus;
	
	public <T> void runAsync(Task<T> task){
		TaskFactory.run(task);
	}
	
	public void setEventBus(EventBus bus){
		this.eventBus = bus;
	}
	
	public EventBus getEventBus() {
		return eventBus;
	}
	
}
