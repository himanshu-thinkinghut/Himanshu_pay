package com.elotouch.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Dialog {

	public static void showDialog(Context mcontext, String title, String message) {
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mcontext);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						
					}
				  });
			
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	
	}
	
}
