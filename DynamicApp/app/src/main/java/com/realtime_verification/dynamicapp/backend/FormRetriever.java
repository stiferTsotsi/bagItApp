package com.realtime_verification.dynamicapp.backend;

import android.content.Context;
import android.util.Log;

import com.realtime_verification.dynamicapp.model.CustomComponent;
import com.realtime_verification.dynamicapp.model.CustomLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vaal on 3/26/2015.
 */
public class FormRetriever {

	private String TAG = "FORM RETRIEVER";
	private static FormRetriever frmRetriever;

	private FormRetriever(Context context) {
	}

	public static FormRetriever get(Context context) {
		if (frmRetriever == null) {
			frmRetriever = new FormRetriever(context.getApplicationContext());
		}
		return frmRetriever;
	}

	public ArrayList<CustomLayout> getLayouts(String url){
		ArrayList<CustomLayout> layouts = new ArrayList<>();

		JSONObject jsonObject;
		String layoutName, layoutAction, layoutMethod;
		try{

			jsonObject = new JSONObject(HttpGetPost.getJsonString(url));
			layoutName = (String)jsonObject.get("FormName");
			layoutAction = (String)jsonObject.get("FormAction");
			layoutMethod = (String)jsonObject.get("FormMethod");
			JSONArray jsonArray = (JSONArray)jsonObject.get("components");

			int id,labelId;
			String cLabel,cName,cType,cPriority,cValue,cReadOnly,cRequired;
			ArrayList<CustomComponent> components = new ArrayList<>();
			for(int i =0; i < jsonArray.length(); i++){
				id = ((JSONObject)jsonArray.get(i)).getInt("component_id");
				labelId = ((JSONObject) jsonArray.get(i)).getInt("label_id");
				cLabel = (String) ((JSONObject)jsonArray.get(i)).get("label");
				cName = (String) ((JSONObject)jsonArray.get(i)).get("name");
				cType = (String) ((JSONObject)jsonArray.get(i)).get("type");
				cPriority = (String) ((JSONObject)jsonArray.get(i)).get("priority");
				cValue = (String) ((JSONObject)jsonArray.get(i)).get("value");
				cReadOnly = (String) ((JSONObject)jsonArray.get(i)).get("readOnly");
				cRequired = (String) ((JSONObject)jsonArray.get(i)).get("required");

				components.add(new CustomComponent(id,labelId,cLabel,cName,cType,cPriority,cValue,
						cReadOnly,
						cRequired));
			}
			layouts.add(new CustomLayout(layoutName,layoutAction,layoutMethod,components));

		}catch (Exception e){
			Log.e(TAG," GET LAYOUTS "+ e.getMessage());
		}

		return layouts;
	}

}
