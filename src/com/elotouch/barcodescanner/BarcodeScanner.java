package com.elotouch.barcodescanner;

import com.elotouch.paypoint.MainActivity;
import com.elotouch.paypoint.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.Button;


public class BarcodeScanner extends Activity implements View.OnClickListener {

	Button scanButton ;
	public BarcodeReader barcodereader = new BarcodeReader(); //Instance of Barcode Reader Class.
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.barcoderscanner);
        setupUI();
	}
	
	public void setupUI () {
		scanButton = (Button) findViewById(R.id.btnScan);
		scanButton.setOnClickListener(this);
		
	}
	
	public void TurnOnBCR()
	{		
		barcodereader.turnOnLaser();	

	}
	public void TurnOffBCR()
	{
		barcodereader.turnOnLaser();
	}

	//Load JNI from the library project. Refer MainActivity.java from library project elotouchBarcodeReader.
	// In constructor we are loading .so file for Barcode Reader.
	static {
	   System.loadLibrary("barcodereaderjni");
	}  

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		barcodereader.turnOnLaser();
		
	}

	

}
