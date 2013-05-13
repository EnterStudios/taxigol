package com.taxigol.taxi.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.ActivityLoader;
import com.taxigol.taxi.controllers.async.DefaultTask;
import com.taxigol.taxi.controllers.async.Task;
import com.taxigol.taxi.events.NewServiceArrivedEvent;
import com.taxigol.taxi.events.ServiceChangedEvent;
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

public class ServiceController extends Controller{

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
				System.out.println("Servicio confirmado:"+result);
				getEventBus().post(new ResponseConfirmarServicio(result));
			}
			@Override
			public void onFailure(Throwable throwable) {
				onRequestServices(new RequestServices());
				getEventBus().post(new ResponseConfirmarServicio(null));
			}
		});
	}
	
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
		for (Service s : services) {
			if (s.getId()==serviceId){
				getEventBus().post(new ResponseService(s));
			}
		}
	}
	
	/**
	 * Notifies subscribers that the services have changed 
	 */
	public void sendServicesChangedEvent(){
		getEventBus().post(new ServicesChangedEvent(services));
	}
	
	/**
	 * Notifies subscribers that there is a service that has either changed or is new
	 * @param service
	 */
	public void sendServiceChangedEvent(Service service){
		boolean newService = true;
		for (Service s : services) {
			if (s.getId()==service.getId()){
				s = service;
				newService = false;
			}
		}
		if (newService){
			services.add(service);
		}
		sendServicesChangedEvent();
		getEventBus().post(new ServiceChangedEvent(service));
	}
	
	@Subscribe
	public void onTaxiServiceReceived(TaxiServiceReceivedEvent event){
		String serviceId = event.getServiceId();
		loadService(serviceId);
	}
	

	/**
	 * Load service retrieves and updates the Services
	 * @param serviceId
	 */
	public void loadService(final String serviceId){
		runAsync(new DefaultTask<List<Service>>(){
			@Override
			public List<Service> execute() throws Exception {
				return client.getAll(idProvider.getId());
			}
			@Override
			public void onSuccess(List<Service> result) {
				int id = Integer.parseInt(serviceId);

				services = result;
				sendServicesChangedEvent();
				for (Service service : result) {
					if (service.getId()==id){
						
						sendNewServiceArrivedEvent(service);
					}
				}
			}
		});
	}

	public void sendNewServiceArrivedEvent(Service service) {
		getEventBus().post(new NewServiceArrivedEvent(service));
	}
}




