package com.realtime_verification.bagit.backend;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by vaal on 4/7/2015.
 */
public class HttpGetPost {
	private static final String TAG = "HTTP_GET_POST";

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
			Log.e(TAG + " ClientProtocolException ", e.getMessage());
		} catch (IOException e) {
			Log.e(TAG + " IOException ", e.getMessage());
		}

		Log.d(" BUILDER ", " " + builder.toString());
		return builder.toString();
	}

	public static String GET(String url) {

		String result = "";

		HttpGet httpGet = new HttpGet(url);

		HttpClient httpClient = new DefaultHttpClient();

		HttpResponse httpResponse = null;
		try {

			httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				try {
					String resp_body = EntityUtils.toString(httpResponse.getEntity());
					result = resp_body;
				} catch (Exception e) {
					Log.e(TAG +" GET ", e.getMessage());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// Handle response back from script.
		if (httpResponse != null) {


		} else { // Error, no response.

		}

		return result;
	}

	public static String POST(String url, List<NameValuePair> data) {

		String result = "";

		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);
		HttpResponse httpResponse = null;

		//Encoding POST data
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(data));
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				try {
					String resp_body = EntityUtils.toString(httpResponse.getEntity());
					result = resp_body;
				} catch (Exception e) {
					Log.e(TAG +" Post ", e.getMessage());
				}
			}

		} catch (UnsupportedEncodingException e) {
			// log exception
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
