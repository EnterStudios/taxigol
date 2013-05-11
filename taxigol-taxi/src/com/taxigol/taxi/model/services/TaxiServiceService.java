package com.taxigol.taxi.model.services;

import java.util.List;

import com.taxigol.taxi.model.Service;

public interface TaxiServiceService extends IService<Service>{

	
	/**
	 * Creates a Service
	 * @param address
	 * @param verificationCode
	 * @return the service created
	 * @throws Exception
	 */
	public Service create(String address, String verificationCode) throws Exception;
		
	/**
	 * Deletes all the services
	 * Note: does not work in production
	 * @throws Exception
	 */
	public void deleteAll() throws Exception;
	
	/**
	 * Returns all the services
	 * @return
	 * @throws Exception
	 */
	public List<Service> getAll() throws Exception;
	
	/**
	 * Returns a Service given its ID
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Service get(String id) throws Exception;
	
	/**
	 * Marca un servicio como confirmado
	 * @param serviceId
	 * @param taxiId
	 * @throws Exception
	 */
	public Service confirmarServicio(String serviceId, String taxiId) throws Exception;
	/**
	 * Marca un servicio como cumplido
	 * @param serviceId
	 * @param taxiId
	 * @param verificationCode
	 * @throws Exception
	 */
	public Service cumplirServicio(String serviceId, String taxiId, String verificationCode) throws Exception;
	/**
	 * 
	 * @param taxiId
	 * @return returns a list of all the services confirmed by the taxi
	 * @throws Exception 
	 */
	public List<Service> getAll(String taxiId) throws Exception;
	
	/**
	 * Cancels a service
	 * @param serviceId
	 * @param taxiId
	 * @throws Exception
	 */
	public Service cancelarServicio(String serviceId, String taxiId) throws Exception;
}
