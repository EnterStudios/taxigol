package com.taxigol.taxi.views.widgets;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taxigol.taxi.R;
import com.taxigol.taxi.activities.ServiciodeTaxiDetailActivity;
import com.taxigol.taxi.model.Service;

public class SolicitudesAdapter extends BaseAdapter {

	private List<Service> solicitudes;
	
	public void setSolicitudes(List<Service> services){
		solicitudes = services;
	}

	public SolicitudesAdapter() {
		this.solicitudes = new ArrayList<Service>();
	}

	@Override
	public int getCount() {
		return solicitudes.size();
	}

	@Override
	public Object getItem(int i) {
		return solicitudes.get(i);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View view, final ViewGroup parent) {
		if (view == null) {

			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.list_view_item_solicitudes, parent, false);
			view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					
					Intent intent = new Intent(parent.getContext(),ServiciodeTaxiDetailActivity.class);
					intent.putExtra(ServiciodeTaxiDetailActivity.EXTRA_SERVICE_ID, ""+solicitudes.get(position).getId());
					parent.getContext().startActivity(intent);
					return true;
				}
			});
		}

		String title = solicitudes.get(position).getAddress();
		String estado = solicitudes.get(position).getState();
		TextView txtTitle = (TextView)view.findViewById(R.id.txtDireccion);
		txtTitle.setText(title);
		TextView txtEstado = (TextView)view.findViewById(R.id.txtEstado);
		txtEstado.setText(estado);

		return view;
	}


}
