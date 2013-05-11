package com.taxigol.taxi.events.request;

/**
 * 
 * @author mono
 *
 * @param <T>
 */
public class HasData<T> {

	private T data;
	
	public HasData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data){
		this.data = data;
	}
}
