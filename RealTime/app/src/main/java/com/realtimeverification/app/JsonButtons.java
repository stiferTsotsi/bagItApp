package com.realtimeverification.app;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by vaal on 3/22/2015.
 */
public class JsonButtons {

	private String TAG = "JSON BUTTONS ";
	private ArrayList<CustomButton> buttons;
	private static JsonButtons bRetriever;

	private JsonButtons(Context context) {
	}

	public static JsonButtons get(Context c) {
		if (bRetriever == null) {
			bRetriever = new JsonButtons(c.getApplicationContext());
		}
		return bRetriever;
	}

	private String getJsonString(String url) {

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

	public String POST(String url) {
		Log.d(TAG,"URL: "+ url);
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);

		try {
			HttpResponse response = client.execute(httpPost);
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

	public ArrayList<CustomButton> getButtons(String url) {

		buttons = new ArrayList<>();
		ArrayList<ButtonStyle> styles = new ArrayList<>();
		JSONObject jsonObject;
		int id, width, height;
		String icon, name, link, pLeft, pRight, pBottom, pTop, textSize, textColor, bgColor,
				strokeWidth,
				strokeColor;

		try {
			jsonObject = new JSONObject(getJsonString(url));
			JSONArray button = (JSONArray) jsonObject.get("buttons");
			JSONArray style = (JSONArray) jsonObject.get("buttonStyle");

			for (int i = 0; i < style.length(); i++) {
				width = ((JSONObject) style.get(i)).getInt("width");
				height = ((JSONObject) style.get(i)).getInt("height");
				pLeft = (String) ((JSONObject) style.get(i)).get("paddingLeft");
				pRight = (String) ((JSONObject) style.get(i)).get("paddingRight");
				pTop = (String) ((JSONObject) style.get(i)).get("paddingTop");
				pBottom = (String) ((JSONObject) style.get(i)).get("paddingBottom");
				textSize = (String) ((JSONObject) style.get(i)).get("textSize");
				textColor = (String) ((JSONObject) style.get(i)).get("textColour");
				bgColor = (String) ((JSONObject) style.get(i)).get("backgroundColour");
				strokeWidth = (String) ((JSONObject) style.get(i)).get("strokeWidth");
				strokeColor = (String) ((JSONObject) style.get(i)).get("strokeColour");

				ButtonStyle style1 = new ButtonStyle(width, height, pLeft, pRight,
						pBottom, pTop, textSize, textColor, bgColor, strokeColor, strokeWidth);
				styles.add(style1);

				for (ButtonStyle pp : styles) {
					Log.d(" TESTING *******", " " + pp.getBgColor());
				}
			}

			for (int i = 0; i < button.length(); i++) {
				id = ((JSONObject) button.get(i)).getInt("id");
				icon = (String) ((JSONObject) button.get(i)).get("link_icon");
				name = (String) ((JSONObject) button.get(i)).get("name");
				link = (String) ((JSONObject) button.get(i)).get("link");

				buttons.add(new CustomButton(id, icon, name, link, styles));
			}

		} catch (Exception e) {
			Log.d(TAG, e.getMessage());
		}
		return buttons;
	}
	public ArrayList<CustomSetting> getSettings(String url){

		ArrayList<CustomSetting> settings = new ArrayList<>();
		JSONObject jsonObject;

		String name,link;
		try{
			jsonObject = new JSONObject(getJsonString(url));

			JSONArray jsonArray = (JSONArray)jsonObject.get("action");
			for(int i =0; i < jsonArray.length(); i++){
				name = (String) (((JSONObject)jsonArray.get(i)).get("actionName"));
				link = (String) ((JSONObject)jsonArray.get(i)).get("actionLink");

				ArrayList<CustomParameter> parameters = new ArrayList<>();
				String p1,p2;
				JSONArray jsonArray1 = (JSONArray) (((JSONObject)jsonArray.get(i)).get
						("actionParameters"));

				ArrayList arr = new ArrayList();
				JSONObject keyArray = jsonArray1.getJSONObject(0);
				Iterator temp =keyArray.keys();
				while (temp.hasNext()){
					String current = (String) temp.next();
					arr.add(current);
				}
				if(arr !=null){
					GlobalVariables.SETTINGS_PARAMETERS = new String[arr.size()];
					for(int s =0; s < arr.size(); s++){
						GlobalVariables.SETTINGS_PARAMETERS[s] = arr.get(s).toString();
					}
				}
				for(int j =0;j < jsonArray1.length(); j++){
					p1 = (String) (((JSONObject)jsonArray1.get(j)).get(arr.get(0).toString()));
					p2 = (String) ((JSONObject)jsonArray1.get(j)).get(arr.get(1).toString());
					parameters.add(new CustomParameter(p1,p2));
				}
				settings.add(new CustomSetting(name,link,parameters));
			}

		}catch (Exception e){
			Log.e(TAG, " Get Settings: " + e.getMessage());
		}

		return settings;
	}
}
