package com.elotouch.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

public class NetworkDetector {

	private static NetworkDetector sInstance;
	private final Context mContext;
	private ConnectivityManager connectivity;

	/**
	 * 
	 * @param context
	 */
	private NetworkDetector(Context context) {
		mContext = context.getApplicationContext();
		connectivity = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public static NetworkDetector init(Context context) {
		if (sInstance == null) 
		{
			synchronized (NetworkDetector.class) 
			{
				if(sInstance == null)
			        sInstance = new NetworkDetector(context);
			}
		}
		return sInstance;
	}

	/**
	 * Check Internet Connectivity
	 * 
	 * @return
	 */
	public boolean isNetworkAvailable() {
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}

		}
		return false;
	}

	public boolean isUsingWiFi() {
		NetworkInfo wifiInfo = connectivity
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiInfo.getState() == NetworkInfo.State.CONNECTED
				|| wifiInfo.getState() == NetworkInfo.State.CONNECTING) {
			return true;
		}

		return false;
	}

	public String FormatIP() {
		WifiManager wifiManager = (WifiManager) mContext
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		if (wifiInfo != null) {
			int ipAddress = wifiInfo.getIpAddress();
			return Formatter.formatIpAddress(ipAddress);
		}
		return "";
	}

	public String getIPaddress() {
		String ipAddress = "";
		if (isUsingWiFi()) {
			ipAddress = FormatIP();
		}

		return ipAddress;
	}

	public boolean isLanConnected() {
		NetworkInfo wifiInfo = connectivity
				.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);

		if (wifiInfo.getState() == NetworkInfo.State.CONNECTED
				|| wifiInfo.getState() == NetworkInfo.State.CONNECTING) {
			return true;
		}

		return false;
	}
	
}
