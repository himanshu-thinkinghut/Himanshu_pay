/**
 * 
 */
package com.elotouch.asynctask;

import android.content.Context;
import android.os.AsyncTask;

/**
 * @author Guest
 *
 */
public class LoginTask extends AsyncTask<Void, Void, Error> {

	private static final String TAG = LoginTask.class.getName();
	//Context
	final Context context;
	//AsyncTaskCompleteListener
	private AsyncTaskCompleteListener<LoginResponse> listener = null;
	String username;
	String password;
	LoginResponse mLoginStatus;
	
	public LoginTask(Context ctx, AsyncTaskCompleteListener<LoginResponse> listener, String username, String password) 
	{
		this.context = ctx;
		this.listener = listener;
		this.username = username;
		this.password = password;
		
	}
	
	@Override
	protected void onPreExecute()
	{
		if(listener!=null)
			listener.onStarted();
		
	}

	protected Error doInBackground(Void... unused) 
	{		
	
		Apicall mapiCall = new Apicall(context);
		try 
		{
			mLoginStatus =  mapiCall.getLogin(username, password);
			
			
		}
		catch (Error e) 
		{
			
			return e;
			
		}
		return  null;
		
	}

	protected void onPostExecute(Error e) 
	{
		
		if(mLoginStatus!=null)
		{
			if(listener!=null)
			{
				listener.onStoped();
				listener.onTaskComplete(mLoginStatus);
				listener = null;
			}
		}
		else 
		{		
			if(listener!=null)
			{
				if(e!=null)
				{
					
					listener.onStopedWithError(e);
				}
				
				listener = null;
			}
			
		}

	}

}
