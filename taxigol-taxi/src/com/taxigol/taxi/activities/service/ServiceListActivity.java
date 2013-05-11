package com.taxigol.taxi.activities.service;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.taxigol.taxi.App;
import com.taxigol.taxi.R;
import com.taxigol.taxi.activities.fragments.ServiceFragmentAdapter;
import com.taxigol.taxi.events.ServicesChangedEvent;
import com.taxigol.taxi.events.request.RequestServices;
import com.viewpagerindicator.TitlePageIndicator;

public class ServiceListActivity extends FragmentActivity {

	private ServiceFragmentAdapter adapter;
	private ViewPager pager;
	private EventBus bus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_list);
		
		bus = ((App)getApplication()).getEventBus();
		
		adapter = new ServiceFragmentAdapter(getSupportFragmentManager());
		pager = (ViewPager)findViewById(R.id.pager);
		pager.setAdapter(adapter);
		
		TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		bus.register(this);
		bus.post(new RequestServices());
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		bus.unregister(this);
	}

	@Subscribe
	public void onServicesListChanged(ServicesChangedEvent event){
		adapter.setServices(event.getData());
	}

}
