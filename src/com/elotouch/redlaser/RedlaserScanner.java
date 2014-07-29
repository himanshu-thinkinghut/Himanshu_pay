package com.elotouch.redlaser;

import java.util.ArrayList;

import com.ebay.redlasersdk.BarcodeResult;
import com.ebay.redlasersdk.BarcodeScanActivity;
import com.ebay.redlasersdk.RedLaserExtras;
import com.elotouch.paypoint.R;
import com.elotouch.redlaser.BarcodeListAdapter;
import com.elotouch.redlaser.Options;
import com.elotouch.redlaser.RLSampleScannerActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class RedlaserScanner extends Activity {

	private String udid;
	SharedPreferences prefs;
	SharedPreferences.Editor prefsEditor;
	
	BarcodeListAdapter listAdapter;
	boolean isLaunchingAnotherActivity = false;
	
	private static final String SAVED_INSTANCE_LIST = "BarcodeList";
	
    @SuppressWarnings(value = "unchecked")
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setTitle("RedLaser Sample App");
		setContentView(R.layout.redlaserscanner);
		
		// by default enable all barcode types 
		prefs = this.getSharedPreferences(Options.OPTIONS_PREFERENCE, MODE_PRIVATE);
		prefsEditor = prefs.edit();
		if (!prefs.contains(Options.OPTIONS_EAN13))
			prefsEditor.putBoolean(Options.OPTIONS_EAN13, true);
		if (!prefs.contains(Options.OPTIONS_EAN8))
			prefsEditor.putBoolean(Options.OPTIONS_EAN8, true);
		if (!prefs.contains(Options.OPTIONS_EAN5))
			prefsEditor.putBoolean(Options.OPTIONS_EAN5, true);
		if (!prefs.contains(Options.OPTIONS_EAN2))
			prefsEditor.putBoolean(Options.OPTIONS_EAN2, true);
		if (!prefs.contains(Options.OPTIONS_UPCE))
			prefsEditor.putBoolean(Options.OPTIONS_UPCE, true);

		if (!prefs.contains(Options.OPTIONS_CODE128))
			prefsEditor.putBoolean(Options.OPTIONS_CODE128, true);
		if (!prefs.contains(Options.OPTIONS_CODE39))
			prefsEditor.putBoolean(Options.OPTIONS_CODE39, true);
		if (!prefs.contains(Options.OPTIONS_CODE93))
			prefsEditor.putBoolean(Options.OPTIONS_CODE93, true);
		if (!prefs.contains(Options.OPTIONS_ITF))
			prefsEditor.putBoolean(Options.OPTIONS_ITF, true);

		if (!prefs.contains(Options.OPTIONS_CODABAR))
			prefsEditor.putBoolean(Options.OPTIONS_CODABAR, true);
		if (!prefs.contains(Options.OPTIONS_DATABAR))
			prefsEditor.putBoolean(Options.OPTIONS_DATABAR, true);
		if (!prefs.contains(Options.OPTIONS_DATABAREXPANDED))
			prefsEditor.putBoolean(Options.OPTIONS_DATABAREXPANDED, true);

		if (!prefs.contains(Options.OPTIONS_QRCODE))
			prefsEditor.putBoolean(Options.OPTIONS_QRCODE, true);
		if (!prefs.contains(Options.OPTIONS_DATAMATRIX))
			prefsEditor.putBoolean(Options.OPTIONS_DATAMATRIX, true);
		if (!prefs.contains(Options.OPTIONS_PDF417))
			prefsEditor.putBoolean(Options.OPTIONS_PDF417, true);
		prefsEditor.commit();
		
		
		TextView udidView = (TextView) findViewById(R.id.udid_string);
		udid = RedLaserExtras.getDeviceID(getContentResolver());
		udidView.setText("UDID: "+udid);
		
		ArrayList<BarcodeResult> savedValues = null;
        if (savedInstanceState != null)
        {
        	savedValues = (ArrayList<BarcodeResult>) savedInstanceState.getSerializable(SAVED_INSTANCE_LIST);
        }
        
        ListView barcodeList = (ListView) findViewById(R.id.listView1);
		listAdapter = new BarcodeListAdapter(this, savedValues);
		barcodeList.setAdapter(listAdapter);
		
    }
    
    @Override
   protected void onResume()
    {
    	super.onResume();
    	
		Button singleScanButton = (Button)findViewById(R.id.btn_singlescan);
		Button multiScanButton = (Button)findViewById(R.id.btn_multiscan);
		Button optionsButton = (Button)findViewById(R.id.btn_options);
		Button clearListButton = (Button)findViewById(R.id.btn_clearList);
		
		singleScanButton.setOnClickListener(singleScanButtonListener);
		multiScanButton.setOnClickListener(multiScanButtonListener);
		optionsButton.setOnClickListener(optionsButtonListener);
		clearListButton.setOnClickListener(clearListButtonListener);

		isLaunchingAnotherActivity = false;
		
		// If the ReadyState isn't one of the 'ready' states, we can't scan. Here, we just
		// disable the scan buttons, but in your app you may want to do something else.
		// The important point is to call checkReadyStatus() before launching the scan activity.
		// The scan activity will show error dialogs if it can't scan, but it's not a great
		// user experience.
		RedLaserExtras.RLScannerReadyState state = RedLaserExtras.checkReadyStatus(getBaseContext());
		singleScanButton.setEnabled(false);
		multiScanButton.setEnabled(false);

		switch (state)
		{
		case EvalModeReady:
			Log.i("SCANNER STATE", "Evaluation license.");
			singleScanButton.setEnabled(true);
			multiScanButton.setEnabled(true);
			break;
			
		case LicensedModeReady:
			Log.i("SCANNER STATE", "Fully registered license.");
			singleScanButton.setEnabled(true);
			multiScanButton.setEnabled(true);
			break;

		case BadLicense:
			Log.i("SCANNER STATE", "Bad license. Unable to scan.");
			break;
			
		case NoCamera:
			Log.i("SCANNER STATE", "No camera found.  Unable to scan.");
			break;
			
			// RedLaser will still try to scan in the "Unsupported Hardware" case, but 
			// unless the vendor has updated their software/OS/kernel scanning is likely to not work.
			// We still allow scanning so that when an unsupported vendor *does* update their
			// software apps can choose to override the UnsupportedHardware result. 
		case UnsupportedHardware:
			Log.i("SCANNER STATE", "Unsupported hardware.");
			break;

		case ScanLimitReached:
			Log.i("SCANNER STATE", "Scan limit reached. Unable to scan.");
			break;
			
		case APILevelTooLow:			
		case MissingPermissions:
		case UnknownState:
			break;
		}
    }
	
	protected void onSaveInstanceState(Bundle outState)
	{
		outState.putSerializable(SAVED_INSTANCE_LIST, listAdapter.getResultList());
	}
	
	OnClickListener singleScanButtonListener = new OnClickListener() 
	{
		public void onClick(View v) 
		{
			try {
				if (!isLaunchingAnotherActivity)
				{
					isLaunchingAnotherActivity = true;
					Intent scanIntent = new Intent(RedlaserScanner.this,RLSampleScannerActivity.class);
					scanIntent.putExtra(RLSampleScannerActivity.INTENT_MULTI_SCAN, false);
					startActivityForResult(scanIntent,1);
					Log.d("RLSample", "Starting RedLaser Scan.");
				}
			} catch(Exception e)
			{
				Log.d("RLSample",e.getLocalizedMessage()+" "+e.getCause());
			}
		}
	};
	
	OnClickListener multiScanButtonListener = new OnClickListener() 
	{
		public void onClick(View v) 
		{
			try {
				if (!isLaunchingAnotherActivity)
				{
					isLaunchingAnotherActivity = true;
					Intent scanIntent = new Intent(RedlaserScanner.this,RLSampleScannerActivity.class);
					scanIntent.putExtra(RLSampleScannerActivity.INTENT_MULTI_SCAN, true);
					startActivityForResult(scanIntent,1);
				}
			} catch(Exception e)
			{
				Log.d("RLSample",e.getLocalizedMessage()+" "+e.getCause());
			}
		}
	};
	
	OnClickListener optionsButtonListener = new OnClickListener() 
	{
		public void onClick(View v) 
		{
			if (!isLaunchingAnotherActivity)
			{
				isLaunchingAnotherActivity = true;
				Intent optionsIntent = new Intent(RedlaserScanner.this, Options.class);
				startActivity(optionsIntent);
			}
		}
	};
	
	OnClickListener clearListButtonListener = new OnClickListener() 
	{
		public void onClick(View v) 
		{
			listAdapter.clearList();
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		// We came from the scanning activity; the return intent contains a RESULT_EXTRA key
		// whose value is an ArrayList of BarcodeResult objects that we found while scanning.
		// Get the list of objects and add them to our list view.
		if (resultCode == RESULT_OK) 
		{			
			ArrayList<BarcodeResult> barcodes = data.getParcelableArrayListExtra(BarcodeScanActivity.RESULT_EXTRA);
			if (barcodes != null)
			{
				listAdapter.addBarcodes(barcodes);	
			}
		}
	}
}
