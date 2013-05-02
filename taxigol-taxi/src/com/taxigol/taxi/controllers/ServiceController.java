package com.taxigol.taxi.controllers;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;

import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.activities.ConfirmacionActivity;
import com.taxigol.taxi.activities.ServiciodeTaxiListActivity.ServiceHandler;
import com.taxigol.taxi.controllers.async.CallbackEventTask;
import com.taxigol.taxi.controllers.async.DefaultTask;
import com.taxigol.taxi.events.AsyncCallback;
import com.taxigol.taxi.events.CancelServiceEvent;
import com.taxigol.taxi.events.CompleteServiceEvent;
import com.taxigol.taxi.events.ConfirmServiceEvent;
import com.taxigol.taxi.events.FindServiceEvent;
import com.taxigol.taxi.events.GetAllServicesEvent;
import com.taxigol.taxi.events.ServiceReceivedEvent;
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

	@Override
	public void getServices(final AsyncCallback<List<Service>> cb) {
		runAsync(new DefaultTask<List<Service>>() {
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
	public void onServiceReceived(ServiceReceivedEvent event){
		final String serviceId = event.getData();
		runAsync(new DefaultTask<Service>() {
			@Override
			public Service execute() throws Exception {
				return client.get(serviceId);
			}
			@Override
			public void onSuccess(Service result) {
				Intent i = new Intent(context,ConfirmacionActivity.class);
				i.putExtra(ConfirmacionActivity.EXTRA_ADDRESS,result.getAddress());
				i.putExtra(ConfirmacionActivity.EXTRA_ID, serviceId);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
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
