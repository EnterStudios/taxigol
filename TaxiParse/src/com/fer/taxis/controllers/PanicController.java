package com.fer.taxis.controllers;

import java.io.IOException;

import com.fer.taxis.activities.PanicActivity.PanicHandler;
import com.fer.taxis.model.IdProvider;
import com.fer.taxis.model.services.PanicService;

import android.content.Context;

public class PanicController extends Controller implements PanicHandler{

	private PanicService service;
	private IdProvider idProvider;
	
	public PanicController(IdProvider idProvider,Context context, PanicService panic) {
		super(context);
		this.service = panic;
		this.idProvider = idProvider;
	}
	
	@Override
	public void onPanicTouched() {
		try {
			service.createPanic(idProvider.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
