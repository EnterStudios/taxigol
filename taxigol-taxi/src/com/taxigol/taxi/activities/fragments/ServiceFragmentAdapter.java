package com.taxigol.taxi.activities.fragments;

import java.util.ArrayList;
import java.util.List;

import com.taxigol.taxi.model.Service;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ServiceFragmentAdapter extends FragmentPagerAdapter{

	private List<Service> services;
	
	public ServiceFragmentAdapter(FragmentManager fm) {
		super(fm);
		services = new ArrayList<Service>();
	}
	
	public void setServices(List<Service> services) {
		this.services = services;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return services.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return services.get(position).getAddress();
	}
	
	@Override
	public Fragment getItem(int index) {
		ServiceFragment frag =  new ServiceFragment();
		frag.setService(services.get(index));
		return frag;
	}
}
