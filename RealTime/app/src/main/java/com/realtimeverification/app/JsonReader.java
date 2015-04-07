//package com.realtimeverification.app;
//
//import android.content.Context;
//import android.util.Log;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.StatusLine;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
///**
// * Created by vaal on 3/20/2015.
// */
//public class JsonReader {
//
//	private ArrayList<CustomButton> buttons;
//	private ArrayList<CustomSetting> settings;
//	private ArrayList<PageData> pageDataArrayList;
//	private static final String TAG = "JSON READER ";
//	private static JsonReader sJsonReader;
//
//	private JsonReader(Context context) {
//
//	}
//
//	public static JsonReader get(Context c) {
//		if (sJsonReader == null) {
//			sJsonReader = new JsonReader(c.getApplicationContext());
//		}
//		return sJsonReader;
//	}
//
//	private String getData(String url) {
//
//		StringBuilder builder = new StringBuilder();
//		HttpClient client = new DefaultHttpClient();
//		HttpGet httpGet = new HttpGet(url);
//
//		try {
//			HttpResponse response = client.execute(httpGet);
//			StatusLine statusLine = response.getStatusLine();
//			int statusCode = statusLine.getStatusCode();
//
//			if (statusCode == 200) {
//				HttpEntity entity = response.getEntity();
//				InputStream content = entity.getContent();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
//				String line;
//
//				while ((line = reader.readLine()) != null) {
//					builder.append(line);
//				}
//			} else {
//				Log.e(TAG, " Failed to download file");
//			}
//
//		} catch (ClientProtocolException e) {
//			Log.e(TAG + "ClientProtocolException ", e.getMessage());
//		} catch (IOException e) {
//			Log.e(TAG + " IOException ", e.getMessage());
//		}
//
//		Log.d(" BUILDER ", " " + builder.toString());
//
//		return builder.toString();
//	}
//
//	public ArrayList<PageData> getButtons(String url) {
//		buttons = new ArrayList<CustomButton>();
//		settings = new ArrayList<CustomSetting>();
//		pageDataArrayList = new ArrayList<PageData>();
//
//		CustomButton myCustomButton;
////		CustomSetting myCustomSettings;
//
//		JSONObject jsonObject;
//		String iconLocation, btnName, btnLink;
//
//		try {
//			jsonObject = new JSONObject(getData(url));
//			JSONArray btnJsonArray = (JSONArray) jsonObject.get("buttons");
//			JSONArray settingsJsonArray = (JSONArray) jsonObject.get("settings");
//
//			Log.d("Reaches ", " ***************** CustomButton Count: " + btnJsonArray.length());
//
//			for (int i = 0; i < btnJsonArray.length(); i++) {
//				iconLocation = (String) ((JSONObject) btnJsonArray.get(i)).get("icon_location");
//				btnName = (String) ((JSONObject) btnJsonArray.get(i)).get("name");
//				btnLink = (String) ((JSONObject) btnJsonArray.get(i)).get("link");
//
//
//				ArrayList<CustomParameter> parameters = new ArrayList<CustomParameter>();
//				String uid, xx;
//
//				JSONArray paramsArray = (JSONArray) (((JSONObject) btnJsonArray.get(i)).get("parameters"));
//				for (int j = 0; j < paramsArray.length(); j++) {
//					uid = (String) ((JSONObject) paramsArray.get(j)).get("uid");
//					xx = (String) ((JSONObject) paramsArray.get(j)).get("xx");
//
//					Log.d("Reaches ", " ***************** uid: " + uid);
//					parameters.add(new CustomParameter(uid, xx));
//				}
//				myCustomButton = new CustomButton(iconLocation, btnName, btnLink, parameters);
//				buttons.add(myCustomButton);
//				Log.d(TAG, " Count " + buttons.size());
//			}//Get CustomButton
//
//
//			String sName, sLink;
//			for (int i = 0; i < settingsJsonArray.length(); i++) {
//				sName = (String) ((JSONObject)settingsJsonArray.get(i)).get("settingName");
//				sLink = (String) ((JSONObject)settingsJsonArray.get(i)).get("settingLink");
//
//				ArrayList<CustomParameter> parameters = new ArrayList<CustomParameter>();
//				String uid, xx;
//
//				JSONArray paramsArray = (JSONArray) (((JSONObject)settingsJsonArray.get(i)).get
//						("settingParameters"));
//				for(int j =0; j < paramsArray.length(); j++){
//					uid = (String) ((JSONObject)paramsArray.get(j)).get("uid");
//					xx = (String) ((JSONObject)paramsArray.get(j)).get("xx");
//
//					Log.d(TAG, " SName: " + sName);
//
//					parameters.add(new CustomParameter(uid,xx));
//				}
////				myCustomSettings = new CustomSetting(sName,sLink,parameters);
////				settings.add(myCustomSettings);
//			}// Get settings
//
////			pageDataArrayList.add(new PageData(buttons,settings));
//
//		} catch (Exception e) {
//			Log.e(TAG, " JSON " + e.getMessage());
//		}
//
//		return pageDataArrayList;
//	}
//
//}
