package com.taxigol.taxi.helpers;

import java.util.LinkedList;
import java.util.Queue;

import com.google.common.eventbus.EventBus;

public class MockEventBus extends EventBus {

	private Queue<Object> eventQueue;
	
	public MockEventBus() {
		eventQueue = new LinkedList<Object>();
	}
	
	@Override
	public void post(Object obj) {
		eventQueue.add(obj);
	}
	
	@Override
	public void register(Object object) {
		// do nothing
	}
	
	public void unregister(Object arg0) {
		// do nothing
	};
	
	public Object getEvent(){
		return eventQueue.poll();
	}
	
}
