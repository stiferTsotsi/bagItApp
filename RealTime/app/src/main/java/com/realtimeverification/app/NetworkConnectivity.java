package com.realtimeverification.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by vaal on 3/25/2015.
 */
public class NetworkConnectivity {
	private Context context;

	public NetworkConnectivity(Context context){
		this.context = context;
	}

	/**
	 * Check for all possible Internet Providers
	 * @return connection status (boolean)
	 */
	public boolean isConnectedToInternet(){
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivityManager !=null){
			NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
			if(info !=null){
				for (int i =0; i <info.length; i++){
					if(info[i].getState() == NetworkInfo.State.CONNECTED){
						return true;
					}
				}
			}
		}
		return false;
	}
}