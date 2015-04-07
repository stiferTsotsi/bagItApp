package com.realtime_verification.dynamicapp.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.realtime_verification.dynamicapp.R;
import com.realtime_verification.dynamicapp.backend.FormRetriever;
import com.realtime_verification.dynamicapp.model.CustomComponent;
import com.realtime_verification.dynamicapp.model.CustomLayout;
import com.realtime_verification.dynamicapp.util.AppVariables;

import java.util.ArrayList;


public class ActivitySplash extends Activity {

	private FormRetriever retriever;
	private ProgressDialog dialog;
	private String TAG ="ACTIVITY SPLASH";
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		retriever = FormRetriever.get(getApplicationContext());
		new RetrieveForm().execute("https://www.realtimeverification.co.za/k_app/appForm.php");
		intent = new Intent(this,ActivityTest.class);
	}

	private class RetrieveForm extends AsyncTask<String, String, ArrayList<CustomLayout>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(ActivitySplash.this);
			dialog.setMessage("Initialising application");
			dialog.setIndeterminate(false);
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected ArrayList<CustomLayout> doInBackground(String... params) {
			ArrayList<CustomLayout> layouts;
			layouts = retriever.getLayouts(params[0]);
			return layouts;
		}

		@Override
		protected void onPostExecute(ArrayList<CustomLayout> layouts) {
			super.onPostExecute(layouts);
			dialog.dismiss();

			if(layouts !=null){
				AppVariables.CUSTOM_LAYOUTS = layouts;
				startActivity(intent);
			}

		}
	}

}
