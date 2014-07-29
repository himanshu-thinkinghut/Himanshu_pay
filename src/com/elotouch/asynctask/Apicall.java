package com.elotouch.asynctask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.elotouch.apicall.HttpClientSingleton;
import com.elotouch.apicall.JSONSingleObjectDecode;

import android.content.Context;

public class Apicall {

	final Context mContext;
	
	public Apicall(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	
	public LoginResponse getLogin(String username, String password)
			throws Error {
		return getLoginCall(username, password);
	}

	private LoginResponse getLoginCall(String email, String password)
			throws Error {

		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", email);
			jsonObject.put("password", password);
			HttpPost postMethod = new HttpPost("https://manage.sfp.elopaypoint.com/"
					+ "authenticateuser");
			// postMethod.setParams(httpParameters);
			String jsonResponse = null;
			postMethod.setEntity(new StringEntity(jsonObject.toString()));
			postMethod.addHeader("Content-Type", "application/json");
			postMethod.setHeader("Accept", "application/json");

			HttpResponse response = HttpClientSingleton.getHttpClientInstace().execute(postMethod);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				jsonResponse = EntityUtils.toString(resEntity);

				JSONSingleObjectDecode objectjson = new JSONSingleObjectDecode();
				return objectjson.getStatus(jsonResponse);

			} else {
				//throw new iCashError(response.getStatusLine().getReasonPhrase(),"Server Error");
			}

		} catch (ClientProtocolException e) {
			//throw new iCashError("Server is unavailable.", "Server Error");
		} catch (ConnectTimeoutException e) {
			//throw new iCashError("Server timeout.", "Server Error");
		} catch (SocketTimeoutException e) {
			//throw new iCashError("Server timeout.", "Server Error");
		} catch (MalformedURLException e) {
			//throw new iCashError(e.toString(), "MalformedURLException");
		} catch (IOException e) {
			//throw new iCashError("Server is unavailable.", "Server Error");
		} catch (JSONException e) {
			//throw new iCashError("Error parsing data return from server", "JSON Parser");
			//throw new iCashError("Server error occured. Please try again later", "Server Error");
		}
		return null;

	}
}
