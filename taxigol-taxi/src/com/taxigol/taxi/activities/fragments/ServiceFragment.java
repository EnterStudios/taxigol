package com.taxigol.taxi.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.taxigol.taxi.R;
import com.taxigol.taxi.model.Service;

public class ServiceFragment extends Fragment {

	private Service service;
	private View view;
	private GoogleMap map;
	
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    view = inflater.inflate(R.layout.activity_serviciodetaxi_detail,
	        container, false);
	    Fragment mapFragment = getFragmentManager().findFragmentById(R.id.service_show_map);
	    SupportMapFragment supportMap = (SupportMapFragment)mapFragment;
	    map = supportMap.getMap();
	    LatLng pos = new LatLng(service.getLatitude(),service.getLongitude());
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
	    return view;
	  }
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public Service getService() {
		return service;
	}
}
