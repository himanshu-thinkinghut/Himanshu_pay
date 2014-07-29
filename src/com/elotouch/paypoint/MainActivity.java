package com.elotouch.paypoint;

import com.elotouch.asynctask.AsyncTaskCompleteListener;
import com.elotouch.asynctask.LoginResponse;
import com.elotouch.asynctask.LoginTask;
import com.elotouch.barcodescanner.BarcodeScanner;
import com.elotouch.util.NetworkDetector;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener, OnTouchListener {

	ImageView btnLogin;
	EditText etUserName, etPassword;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        setupUI(); 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void setupUI() {

		etUserName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
    	
	    btnLogin = (ImageView) findViewById(R.id.btnlogin);
		
	    btnLogin.setOnClickListener(this);
		etUserName.setOnTouchListener(this);
 		etPassword.setOnTouchListener(this);
//		
//		etUserName.requestFocus();
//		setTextWatcher(etUserName);
//		setTextWatcher(etPassword);
		
	}
    
    public void setTextWatcher(final EditText mEditText) {
    	
    	mEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
    	
		

	}


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (v.getId() == btnLogin.getId()) {
			String uName =  etUserName.getText().toString() ; 
			String password =  etPassword.getText().toString();
			
			String uNameText =   "naveen.jain+1904@thinkinghut.com"; //etUserName.getText().toString();
			String passwordText = "naveen123";  // etPassword.getText().toString();
			
			if (NetworkDetector.init(getApplicationContext()).isNetworkAvailable())
			{
				new LoginTask(getApplicationContext(),new LoginTaskCompleteListner(),uName , password).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}
			
		}
		
}

	public class LoginTaskCompleteListner implements AsyncTaskCompleteListener<LoginResponse> {

		ProgressDialog progressDialog;
		
		@Override
		public void onStarted() {
			// TODO Auto-generated method stub
			if (progressDialog == null) {
                // in standard case YourActivity.this
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
		}

		@Override
		public void onTaskComplete(LoginResponse result) {
			// TODO Auto-generated method stub
			
			if (result != null) {
				
				if (result.getStatus().equalsIgnoreCase("success")) {
					
					Intent mIntent = new Intent(MainActivity.this,DashboardActivity.class);
					startActivity(mIntent);
					finish();
				
				} else if (result.getStatus().equalsIgnoreCase("failure")) {
					

				}
			}
		}

		@Override
		public void onStoped() {
			// TODO Auto-generated method stub
			try{
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
		}

		@Override
		public void onStopedWithError(Error e) {
			// TODO Auto-generated method stub
			//mDialog.dismiss();
		}
		
	}
	

	
}
