package com.taxigol.taxi.test.services;

import java.util.List;

import junit.framework.TestCase;
import co.fernandohur.restz.DefaultRestz;
import co.fernandohur.restz.Restz;

import com.taxigol.taxi.model.Service;
import com.taxigol.taxi.model.State;
import com.taxigol.taxi.model.Taxi;
import com.taxigol.taxi.model.services.impl.TaxiServiceImpl;
import com.taxigol.taxi.model.services.impl.TaxiServiceServiceImpl;
import com.taxigol.taxi.test.Properties;

public class TestServiceServiceImpl extends TestCase{

	private TaxiServiceServiceImpl service;
	private TaxiServiceImpl taxiService;

	public TestServiceServiceImpl() {
		Restz client = new DefaultRestz();
		String baseUrl = Properties.server_url;
		service = new TaxiServiceServiceImpl(client, baseUrl);
		taxiService = new TaxiServiceImpl(baseUrl, client);
	}

	public void testDeleteAll() throws Exception{
		service.create("calle 132 a # 19-43", "123");
		
		List<Service> services = service.getAll();
		assertTrue(services.size()>=1);
		
		service.deleteAll();
		int size = service.getAll().size();
		assertEquals(0, size);
	}

	public void testCreateService() throws Exception{
		service.deleteAll();
		String address = "Calle 132 a No. 19-43";
		service.create(address, "code");
		List<Service> services = service.getAll();
		assertEquals(1,services.size());
		assertEquals(address, services.get(0).getAddress());
		assertEquals(services.get(0).getState(),State.pendiente.toString());
		
	}

	public void testCreateConfirmarService() throws Exception{
		service.deleteAll();
		Service s = service.create("# abc", "99");
		Taxi t = taxiService.auth("123");
		service.confirmarServicio(""+s.getId(), t.getId());

		s = service.get(s.getId()+"");
		assertEquals(s.getState(),State.confirmado.toString());
	}

	public void testCreateConfirmarCumplirService() throws Exception{

		//PRE
		service.deleteAll();
		Taxi t = taxiService.auth("123");

		//1. Crear
		Service s = service.create("a", "99");

		//2. Confirmar
		service.confirmarServicio(""+s.getId(), t.getId());

		//3. Cumplido
		service.cumplirServicio(s.getId()+"", t.getId(), "99");

		//reload the service
		s = service.get(s.getId()+"");

		assertEquals(State.cumplido.toString(), s.getState());

	}

	public void testCrearConfirmarCumpirShouldThrowException() throws Exception{
		//PRE
		service.deleteAll();
		Taxi t = taxiService.auth("123");

		//1. Crear
		Service s = service.create("a", "99");

		//2. Confirmar
		service.confirmarServicio(""+s.getId(), t.getId());

		//3. Cumplido
		service.cumplirServicio(s.getId()+"", t.getId(), "99");
		
		//4. Cancelar should throw Exception
		try{
			service.cancelarServicio(s.getId()+"", t.getId()+"");
			fail();
		}catch(Exception e){
			// OH my!... all good :)
		}
	}






}





