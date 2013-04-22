package com.taxigol.taxi.controllers;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.common.eventbus.Subscribe;
import com.taxigol.restz.async.OnSuccess;
import com.taxigol.restz.async.Task;
import com.taxigol.taxi.activities.ServiciodeTaxiListActivity.ServiceHandler;
import com.taxigol.taxi.events.CancelServiceEvent;
import com.taxigol.taxi.events.CompleteServiceEvent;
import com.taxigol.taxi.events.ConfirmServiceEvent;
import com.taxigol.taxi.events.FindServiceEvent;
import com.taxigol.taxi.events.GetAllServicesEvent;
import com.taxigol.taxi.model.IdProvider;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.model.services.TaxiServiceService;

public class ServiceController extends Controller implements ServiceHandler{

	private TaxiServiceService client;
	private IdProvider idProvider;
	private List<Service> services;
	
	public ServiceController(IdProvider idProvider,Context activity, TaxiServiceService client) {
		super(activity);
		this.client = client;
		this.idProvider = idProvider;
		services = new ArrayList<Service>();		
	}
	
	
	/**
	 * Returns a list of all the events confirmed by the current taxi tho the event's callback method
	 * @param event
	 */
	@Subscribe 
	public void getAllServices(GetAllServicesEvent event){
		runAsync(new CallbackEventTask<List<Service>>(event) {
			public List<Service> execute() throws Exception {
				return client.getAll(idProvider.getId());
			}
		});
	}
	
	/**
	 * Cancels the event specified in the CancelServiceEvent's data
	 * @param event the event's data is the Service's id
	 */
	@Subscribe
	public void cancelService(final CancelServiceEvent event){
		runAsync(new CallbackEventTask<Void>(event) {
			@Override
			public Void execute() throws Exception {
				client.cancelarServicio(event.getData(), idProvider.getId());
				return null;
			}
		});
	}
	
	@Subscribe
	public void confirmService(ConfirmServiceEvent event) {
		final String serviceId = event.getData();
		runAsync(new CallbackEventTask<Void>(event) {
			@Override
			public Void execute() throws Exception {
				client.confirmarServicio(serviceId, idProvider.getId());
				return null;
			}
		});
	}
	
	@Subscribe
	public void completeService(CompleteServiceEvent event){
		final String serviceId = event.getData().first;
		final String verificationCode = event.getData().second;
		
		runAsync(new CallbackEventTask<Void>(event) {
			@Override
			public Void execute() throws Exception {
				client.cumplirServicio(serviceId, idProvider.getId(), verificationCode);
				return null;
			}
		});
	}

	public void getNewService(final String idServicio, final OnSuccess<Service> onSuccess) {
		runAsync(new Task<Service>() {
			@Override
			public Service execute() throws Exception {
				return client.get(idServicio);
			}
			public void onSuccess(Service result) {
				onSuccess.onSuccess(result);
			};
		});
	}

	@Override
	public void getServices(final OnSuccess<List<Service>> cb) {
		runAsync(new Task<List<Service>>() {
			@Override
			public List<Service> execute() throws Exception {
				return client.getAll(idProvider.getId()); 
			}
			@Override
			public void onSuccess(List<Service> result) {
				services = result;
				cb.onSuccess(result);
			}
		});
	}

	@Subscribe
	public void findService(FindServiceEvent event) {
		
		for (Service s : services) {
			if (event.getData().equals(s.getId()+"")){
				event.getCb().onSuccess(s);
				return;
			}
		}
		event.getCb().onSuccess(null);
	}

}
