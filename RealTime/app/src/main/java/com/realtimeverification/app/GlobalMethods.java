package com.realtimeverification.app;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by vaal on 3/24/2015.
 */
public class GlobalMethods {

	private final static String TAG ="Global Methods";

	public static String getJsonString(String url) {

		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();

			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;

				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(TAG, " Failed to download file");
			}

		} catch (ClientProtocolException e) {
			Log.e(TAG + "ClientProtocolException ", e.getMessage());
		} catch (IOException e) {
			Log.e(TAG + " IOException ", e.getMessage());
		}

		Log.d(" BUILDER ", " " + builder.toString());

		return builder.toString();
	}
}
