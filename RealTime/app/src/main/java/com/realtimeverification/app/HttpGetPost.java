package com.realtimeverification.app;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by vaal on 3/24/2015.
 */
public class HttpGetPost {

	public HttpGetPost() {
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
					Log.d("Response : ***********", " " + resp_body);
				} catch (Exception e) {
					Log.e("sometag", e.getMessage());
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

		Log.d("HTTPGETPOST: "," "+ url+ data.toString());
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
					Log.d("Response : ***********", " " + resp_body);
				} catch (Exception e) {
					Log.e("Post TAG", e.getMessage());
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
