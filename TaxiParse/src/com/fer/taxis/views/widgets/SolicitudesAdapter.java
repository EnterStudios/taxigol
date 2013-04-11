package com.fer.taxis.views.widgets;

import java.util.ArrayList;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fer.taxis.R;
import com.fer.taxis.activities.ServiciodeTaxiDetailActivity;
import com.fer.taxis.model.Service;

public class SolicitudesAdapter extends BaseAdapter {

	private ArrayList<Service> solicitudes;

	public SolicitudesAdapter(ArrayList<Service> solicitudes) {
		this.solicitudes = solicitudes;
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
	public View getView(int position, View view, final ViewGroup parent) {
		if (view == null) {

			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.list_view_item_solicitudes, parent, false);
			view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					
					Intent intent = new Intent(parent.getContext(),ServiciodeTaxiDetailActivity.class);
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
