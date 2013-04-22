package com.taxigol.taxi.model.services;

import java.io.IOException;
import java.util.List;

import com.taxigol.taxi.model.Service;

public interface TaxiServiceService extends IService<Service>{

	
	/**
	 * Creates a Service
	 * @param address
	 * @param verificationCode
	 * @return the service created
	 * @throws IOException
	 */
	public Service create(String address, String verificationCode) throws IOException;
		
	/**
	 * Deletes all the services
	 * Note: does not work in production
	 * @throws IOException
	 */
	public void deleteAll() throws IOException;
	
	/**
	 * Returns all the services
	 * @return
	 * @throws IOException
	 */
	public List<Service> getAll() throws IOException;
	
	/**
	 * Returns a Service given its ID
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public Service get(String id) throws IOException;
	
	/**
	 * Marca un servicio como confirmado
	 * @param serviceId
	 * @param taxiId
	 * @throws IOException
	 */
	public void confirmarServicio(String serviceId, String taxiId) throws IOException;
	/**
	 * Marca un servicio como cumplido
	 * @param serviceId
	 * @param taxiId
	 * @param verificationCode
	 * @throws IOException
	 */
	public void cumplirServicio(String serviceId, String taxiId, String verificationCode) throws IOException;
	/**
	 * 
	 * @param taxiId
	 * @return returns a list of all the services confirmed by the taxi
	 * @throws IOException 
	 */
	public List<Service> getAll(String taxiId) throws IOException;
	
	/**
	 * Cancels a service
	 * @param serviceId
	 * @param taxiId
	 * @throws IOException
	 */
	public void cancelarServicio(String serviceId, String taxiId) throws IOException;
}
