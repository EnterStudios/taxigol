package com.fer.taxis.controllers;

import android.content.Context;

import com.fer.taxis.activities.PanicActivity.PanicHandler;
import com.fer.taxis.model.IdProvider;
import com.fer.taxis.model.Panic;
import com.fer.taxis.model.services.PanicService;
import com.taxigol.restz.async.OnSuccess;
import com.taxigol.restz.async.Task;

public class PanicController extends Controller implements PanicHandler{

	private PanicService service;
	private IdProvider idProvider;
	
	public PanicController(IdProvider idProvider,Context context, PanicService panic) {
		super(context);
		this.service = panic;
		this.idProvider = idProvider;
	}
	
	@Override
	public void onPanicTouched(final OnSuccess<Void> panic) {
		runAsync(new Task<Panic>() {
			@Override
			public Panic execute() throws Exception {
				return service.createPanic(idProvider.getId());
			}
			public void onSuccess(Panic result) {
				panic.onSuccess(null);
			};
		});
	}
	
}
