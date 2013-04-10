package com.fer.taxis.views.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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

	
}