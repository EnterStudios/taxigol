package com.taxigol.taxi.views.widgets;

import com.taxigol.taxi.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Dialog {


	public final static void showAccept(String title, String message, Context context, DialogInterface.OnClickListener clickListener)
	{
		new AlertDialog.Builder(context)
			.setTitle(title)
			.setMessage(message)
			.setIcon(android.R.drawable.ic_dialog_info)
			.setPositiveButton(android.R.string.yes, clickListener)
			 .setNegativeButton(android.R.string.no, null).show();
		
	}
	
	public final static AlertDialog showMessage(String title, String message, Context context)
	{
		return new AlertDialog.Builder(context)
			.setTitle(title)
			.setMessage(message)
			.setIcon(android.R.drawable.ic_dialog_info).show();
		
	}
	
	public final static AlertDialog showInputDialog(String title, String message, Context context, final OnInputDialogOkClicked onInputDialogOkClicked){
		
		LayoutInflater inflater = LayoutInflater.from(context);
		
		View v = inflater.inflate(com.taxigol.taxi.R.layout.dialog_input, null);
		
		final TextView userInput = (TextView)v
				.findViewById(R.id.editText1);
		
		return new AlertDialog.Builder(context)
		
		.setCancelable(true)
		.setNegativeButton(android.R.string.no,null)
		.setPositiveButton(android.R.string.yes, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				onInputDialogOkClicked.onClicked(userInput.getText().toString());
			}
		})
		.setTitle(title)
		.setMessage(message)
		.setView(v)
		.show();
	}
	
	

	
}