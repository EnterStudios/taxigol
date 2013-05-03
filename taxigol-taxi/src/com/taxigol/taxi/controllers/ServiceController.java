package com.taxigol.taxi.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.ActivityLoader;
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
	private ActivityLoader loader;
	
	public ServiceController(ActivityLoader activityLoader, IdProvider idProvider,TaxiServiceService client) {
		this.client = client;
		this.idProvider = idProvider;
		services = new ArrayList<Service>();
		loader = activityLoader;
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
	
	/**
	 * Upon receiving a ConfirmServiceEvent, take's the serviceId and updates it
	 * as <code>confirmado</code> in the server
	 * @param event
	 */
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
	/**
	 * Upon receiving a ConfirmServiceEvent, take's the serviceId and updates it
	 * as <code>confirmado</code> in the server
	 * @param event
	 */
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

	/**
	 * Updates the list of services in the controller.
	 * <ol>
	 * 	<li>Makes a GET to the server returning all the Services</li>
	 * 	<li>Updates the <code>services</code> variable</li>
	 * </ol>
	 */
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
	
	/**
	 * This method is executed when a taxi service request is sent and received.
	 * The method takes the ServiceReceivedEvent's data as the serviceId, loads
	 * the service with the given id and displays a <code>ConfirmacionActivity</code>
	 * @param event
	 */
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
				if (result!=null){
					loader.load(ConfirmacionActivity.class, 
							ConfirmacionActivity.EXTRA_ADDRESS,result.getAddress(), 
							ConfirmacionActivity.EXTRA_ID, result.getId()+"");
				}
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
