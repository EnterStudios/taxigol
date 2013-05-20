package com.taxigol.taxi.controllers;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.ActivityLoader;
import com.taxigol.taxi.controllers.async.DefaultTask;
import com.taxigol.taxi.controllers.async.Task;
import com.taxigol.taxi.events.NewServiceArrivedEvent;
import com.taxigol.taxi.events.ServicesChangedEvent;
import com.taxigol.taxi.events.request.RequestConfirmarServicio;
import com.taxigol.taxi.events.request.RequestCumplirService;
import com.taxigol.taxi.events.request.RequestService;
import com.taxigol.taxi.events.request.RequestServices;
import com.taxigol.taxi.events.request.TaxiServiceReceivedEvent;
import com.taxigol.taxi.events.response.ResponseConfirmarServicio;
import com.taxigol.taxi.events.response.ResponseService;
import com.taxigol.taxi.model.IdProvider;
import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.model.services.TaxiServiceService;
import com.taxigol.taxi.receivers.ServicesBroadcastReceiver;

public class ServiceController extends Controller{

	private TaxiServiceService client;
	private IdProvider idProvider;
	private List<Service> services;
	private ActivityLoader loader;
	private BroadcastReceiver receiver;


	public ServiceController(ActivityLoader activityLoader, IdProvider idProvider,TaxiServiceService client) {
		this.client = client;
		this.idProvider = idProvider;
		services = new ArrayList<Service>();
		loader = activityLoader;

		receiver = initReceiver();
	} 

	/**
	 * initialized and returns a new broadcast receiver
	 * @return
	 */
	private BroadcastReceiver initReceiver() {
		return new ServicesBroadcastReceiver() {

			@Override
			public void onServiceSaved(int serviceId) {
				onRequestServices(new RequestServices());
			}

			@Override
			public void onNewServiceArrived(int serviceId) {
				onTaxiServiceReceived(new TaxiServiceReceivedEvent(""+serviceId));
			}
		};
	}


	/**
	 * This method is executed whenever a RequestServices event arrives.
	 * This method should update the list of services by requesting a new list to the server.
	 * Upon completion, a ServicesChangedEvent should be issued to notify all listeners that
	 * the list of services has changed
	 * @param event and event with no data.
	 */
	@Subscribe
	public void onRequestServices(RequestServices event){
		runAsync(new DefaultTask<List<Service>>() {
			@Override
			public List<Service> execute() throws Exception {
				return client.getAll(idProvider.getId());
			}
			@Override
			public void onSuccess(List<Service> result) {
				ServiceController.this.services = result;
				sendServicesChangedEvent();
			}
		});
	}

	/**
	 * This method is executed whenever a RequestConfrimarServicio arrives. Upon arriving, 
	 * the server should send an update to the server informing that a Service's state has changed from
	 * pendiente to confirmado, or i.o.w. that the current taxi has confirmed a pending taxi service.
	 * @param event an event that contains the service's id
	 */
	@Subscribe
	public void onRequestConfirmarService(RequestConfirmarServicio event){
		final int serviceId = event.getServiceId();
		runAsync(new Task<Service>() {
			@Override
			public Service execute() throws Exception {
				return client.confirmarServicio(""+serviceId, idProvider.getId());
			}
			@Override
			public void onSuccess(Service result) {
				getEventBus().post(new ResponseConfirmarServicio(result));
			}
			@Override
			public void onFailure(Throwable throwable) {
				onRequestServices(new RequestServices());
				getEventBus().post(new ResponseConfirmarServicio(null));
			}
		});
	}

	/**
	 * similar to onRequestConfirmarService, this method changes a service's state from confirmado to cumplido
	 * @param event
	 */
	@Subscribe
	public void onRequestCumplirService(RequestCumplirService event){
		final int serviceId = event.getServiceId();
		final String verificationCode = event.getVerificationCode();
		runAsync(new DefaultTask<Service>() {
			@Override
			public Service execute() throws Exception {
				return client.cumplirServicio(serviceId+"", idProvider.getId(), verificationCode);
			}
			@Override
			public void onSuccess(Service result) {
				getEventBus().post(new ResponseCumplirServicio(result));
			}
		});
	}


	@Subscribe
	public void onRequestService(RequestService event){
		int serviceId = event.getData();
		boolean found = false;
		for (Service s : services) {
			if (s.getId()==serviceId){
				found = true;
				getEventBus().post(new ResponseService(s));
			}
		}
		if (!found){
			getEventBus().post(new ResponseService(null));
		}
	}


	@Subscribe
	public void onTaxiServiceReceived(TaxiServiceReceivedEvent event){
		final String serviceId = event.getServiceId();
		runAsync(new DefaultTask<Service>(){

			@Override
			public Service execute() throws Exception {
				return client.get(serviceId);
			}
			@Override
			public void onSuccess(Service result) {
				sendNewServiceArrivedEvent(result);
			}
		});

	}


	/**
	 * Notifies subscribers that the services have changed 
	 */
	public void sendServicesChangedEvent(){
		getEventBus().post(new ServicesChangedEvent(services));
	}

	public void sendNewServiceArrivedEvent(Service service) {
		getEventBus().post(new NewServiceArrivedEvent(service));
	}

	public BroadcastReceiver getReceiver() {
		return receiver;
	}

	public IntentFilter getIntentFilter() {
		return new IntentFilter(ServicesBroadcastReceiver.INTENT_FILTER_ACTION);
	}

	public List<Service> getServices() {
		return services;
	}
}




