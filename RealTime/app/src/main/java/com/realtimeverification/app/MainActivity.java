package com.realtimeverification.app;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by vaal on 3/22/2015.
 */
public class MainActivity extends ActionBarActivity implements DialogInterface.OnClickListener{

	private ArrayList<CustomButton> buttons;
	private JsonButtons jsonButtons;
	private ArrayList<CustomSetting> settings;
	private Bitmap[] bitmaps;
	private ProgressDialog progressDialog;
	private RelativeLayout layout;
	private ScrollView scroll;
	private RelativeLayout relativeLayout;
	private NetworkConnectivity networkConnectivity;
//	private CustomAlertDialog alert = new CustomAlertDialog();
	private Boolean isConnectedToInternet;
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new RelativeLayout(MainActivity.this);
		jsonButtons = JsonButtons.get(getApplicationContext());
		String param;
		if(setUpInternetConnection()){

			if(GlobalVariables.URL == null){
				param = "https://www.realtimeverification.co.za/k_app/appPage.php";
			}else{
				param = GlobalVariables.URL;
			}
			new RetrieveData().execute(param);

			scroll = new ScrollView(this);
			scroll.setBackgroundColor(R.drawable.transparent_grey_bottom_border_line);
			scroll.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
					.FILL_PARENT,
					RelativeLayout.LayoutParams.FILL_PARENT));

			relativeLayout = new RelativeLayout(MainActivity.this);
			relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup
					.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		}else{
			return;
		}
	}

	public boolean setUpInternetConnection() {
		networkConnectivity = new NetworkConnectivity(getApplicationContext());
		isConnectedToInternet = networkConnectivity.isConnectedToInternet();
		if (!isConnectedToInternet) {

			AlertDialog ad = new AlertDialog.Builder(MainActivity.this)
					.setMessage("Please connect to a working Internet Connection")
					.setIcon(R.drawable.images)
					.setTitle("Internet Connection error")
					.setPositiveButton("OK", MainActivity.this)
					.setCancelable(false)
					.create();

			ad.show();

			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		new PopulateMenu(menu).execute();

		return true;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch(which){
			case DialogInterface.BUTTON_POSITIVE: // OK
//				startActivity(intentToSignIn);
				break;
			default:
				// nothing
				break;
		}
	}

	private class PopulateMenu extends AsyncTask<String, String, ArrayList<CustomSetting>>{

		private final Menu item;

		public PopulateMenu(Menu item) {
			this.item = item;
		}

		@Override
		protected void onPostExecute(ArrayList<CustomSetting> s) {
			super.onPostExecute(s);
			if(s !=null){
				for(int i =0; i < s.size();i++){
					item.add(0,i,Menu.NONE,s.get(i).getName());
					Log.d("LEBOHANG: ", " ******** ");
				}
			}
		}

		@Override
		protected ArrayList<CustomSetting> doInBackground(String... params) {
			settings =jsonButtons.getSettings("https://www.realtimeverification.co.za/k_app/appAction.php");
			Log.d("Lebo" ," &&&&&& "+ settings.size());
			return settings;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	private class RetrieveData extends AsyncTask<String, String, ArrayList<CustomButton>> {
		@Override
		protected ArrayList<CustomButton> doInBackground(String... params) {
			buttons = jsonButtons.getButtons(params[0]);
			bitmaps = new Bitmap[buttons.size()];

			for (int i = 0; i < bitmaps.length; i++) {
				try {
					URL url = new URL("https://" + buttons.get(i).getIconLocation());
					Bitmap bmt = getIcon(url);
					bitmaps[i] = bmt;
				} catch (Exception e) {
					Log.e("URL Exception: ", e.getMessage(), e.getCause());
				}
			}
			return buttons;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setMessage(Html.fromHtml("Loading..."));
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}


		@TargetApi(Build.VERSION_CODES.KITKAT)
		@Override
		protected void onPostExecute(final ArrayList<CustomButton> b) {
			super.onPostExecute(b);
			progressDialog.dismiss();

//			int sW = Integer.parseInt(b.get(0).getStyles().get(0).getStrokeWidth());
			String cCode = b.get(0).getStyles().get(0).getStrokeColor();
			String bg = b.get(0).getStyles().get(0).getBgColor();

			Drawable d = getResources().getDrawable(R.drawable.rounded_button);
			GradientDrawable gd =(GradientDrawable) d.getCurrent();
			gd.setColor(Color.parseColor(bg));
			gd.setStroke(3, Color.parseColor(cCode));


			// 157ctk030315 695ctk240215 27560715

			if(b !=null){
				for (int i = 0; i < b.size(); i++) {
					final int index = i;
					Drawable drawable = new BitmapDrawable(getResources(),
							Bitmap.createScaledBitmap(bitmaps[i],100,100,true));

					final RelativeLayout.LayoutParams buttonParams1 =
							new RelativeLayout.LayoutParams(
									RelativeLayout.LayoutParams.FILL_PARENT,

									RelativeLayout.LayoutParams.WRAP_CONTENT);

					final Button myButton = new Button(MainActivity.this);
					myButton.setText(b.get(i).getName());
					myButton.setId(b.get(i).getId());
					myButton.setBackground(gd);
					myButton.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
					myButton.setTextColor(Color.parseColor(b.get(0).getStyles().get(0).getTextColor()));
					myButton.setPadding(5, 5, 0, 5);

					myButton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {

							String url = b.get(index).getLink();
							GlobalVariables.URL = "https://"+url;
							Intent intent = new Intent(MainActivity.this,ActivityAction.class);
							startActivity(intent);
						}

					});
					int cnt = b.get(i).getId();

					if (index > 0) {
						buttonParams1.addRule(RelativeLayout.BELOW, cnt - 1);
					}
					buttonParams1.addRule(RelativeLayout.ALIGN_TOP);
					buttonParams1.setMargins(15, 10, 15, 5);

					relativeLayout.addView(myButton, buttonParams1);
				}
				scroll.removeAllViews();
				scroll.addView(relativeLayout);
				setContentView(scroll);
			}
		}
	}

	public Bitmap getIcon(URL url) {
		Bitmap bmp = null;
		try {
			bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		} catch (IOException e) {
			Log.e("GET ICON", e.getMessage(), e.getCause());
		}
		return bmp;
	}

}