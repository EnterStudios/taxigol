package com.taxigol.taxi.helpers;

import java.util.LinkedList;
import java.util.Queue;

import android.util.Log;

import com.google.common.eventbus.EventBus;

public class MockEventBus extends EventBus {

	private Queue<Object> eventQueue;
	
	public MockEventBus() {
		eventQueue = new LinkedList<Object>();
	}
	
	@Override
	public void post(Object obj) {
		eventQueue.add(obj);
		Log.d(this.getClass().toString(), "added object to evenQueue");
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
