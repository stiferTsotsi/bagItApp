package com.realtimeverification.app;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity1 extends ListActivity {

	ActionBar actionBar;
	private ArrayList<CustomButton> buttons;
	private ArrayList<CustomParameter> btnParams;
	private ArrayList<CustomSetting> settings;
	private ArrayList<PageData> pageDataArrayList;
	private JsonButtons jsonButtons;
	private LayoutAdapter layoutAdapter;
	private String[] spinnerArray;
	private Bitmap[] bitmaps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		jsonButtons = JsonButtons.get(getApplicationContext());

		new RetrieveData().execute();

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private class RetrieveData extends AsyncTask<String, String, ArrayList<CustomButton>> {
		@Override
		protected ArrayList<CustomButton> doInBackground(String... params) {
			buttons = jsonButtons.getButtons("https://www.realtimeverification.co.za/k_app/appPage.php");
			bitmaps = new Bitmap[buttons.size()];
			for (int i = 0; i < bitmaps.length; i++) {
				try {
					URL url = new URL("https://"+buttons.get(i).getIconLocation());
					Bitmap bmt = getIcon(url);
					bitmaps[i] = bmt;
				} catch (Exception e) {
					Log.e("URL Exception: ", e.getMessage(), e.getCause());
				}

			}
			for(int i =0; i <bitmaps.length;i++){
				Log.d("TESTING ICONS ", "####@@@@####@@@@ " + bitmaps[i].toString());
			}
			return buttons;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(ArrayList<CustomButton> b) {
			super.onPostExecute(b);
			layoutAdapter = new LayoutAdapter(b);
			setListAdapter(layoutAdapter);
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

	private class LayoutAdapter extends ArrayAdapter<CustomButton> {
		public LayoutAdapter(ArrayList<CustomButton> data) {
			super(getApplicationContext(), 0, data);
			Log.d("YES", " ************* count " + getCount());
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {


			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.list_items, null);
			}

			try {
				CustomButton p = getItem(position);
				Button button = (Button) convertView.findViewById(R.id.btn);
				button.setText(p.getName());

				Drawable d = new BitmapDrawable(getResources(), bitmaps[position]);
//				d.setBounds(10,200,0,100);

				button.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

			} catch (Exception ex) {
				Log.e("GET VIEW", " " + ex.getMessage());
			}

			return convertView;
		}
	}

}
