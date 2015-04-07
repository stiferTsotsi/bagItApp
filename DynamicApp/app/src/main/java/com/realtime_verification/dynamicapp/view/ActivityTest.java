package com.realtime_verification.dynamicapp.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.realtime_verification.dynamicapp.R;
import com.realtime_verification.dynamicapp.model.CustomComponent;
import com.realtime_verification.dynamicapp.model.CustomLayout;
import com.realtime_verification.dynamicapp.util.AppVariables;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaal on 3/26/2015.
 */
public class ActivityTest extends ActionBarActivity {

	private String TAG = "ACTIVITY TEST";
	private Intent pictureActionIntent = null;
	protected static final int CAMERA_REQUEST = 0;
	protected static final int GALLERY_PICTURE = 1;
	private String selectedImagePath;
	private Bitmap bitmap;
	private List<EditText> editTextList;
	private List<Spinner> spinnerList;
	private List<RadioGroup> radioGroupList;
	private List<RadioButton[]> radioButtonList;
	private List<CheckBox> checkBoxList;
	private List<NameValuePair> data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		editTextList = new ArrayList<>();
		spinnerList = new ArrayList<>();
		radioGroupList = new ArrayList<>();
		radioButtonList = new ArrayList<>();
		checkBoxList = new ArrayList<>();
		setUpUI();
	}

	public void getIDs() {
		if (AppVariables.CUSTOM_LAYOUTS != null) {
			ArrayList<CustomLayout> layouts = AppVariables.CUSTOM_LAYOUTS;

			ArrayList<CustomComponent> components = layouts.get(0).getComponents();

			int counter = 0;
			for (int i = 0; i < components.size(); i++) {
				if (components.get(i).getType().equals("textField") || components.get(i).getType()
						.equals("textbox")) {
					counter++;
				}
			}
		}
	}

	private int countButtons() {
		ArrayList<CustomComponent> components = AppVariables.CUSTOM_LAYOUTS.get(0).getComponents();
		int cnt = 0;
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).getType().equals("button")) {
				cnt++;
			}
		}
		return cnt;
	}

	private int countEditTexts() {
		ArrayList<CustomComponent> components = AppVariables.CUSTOM_LAYOUTS.get(0).getComponents();
		int cnt = 0;
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).getType().equals("textField") || components.get(i).getType().equals("password")) {
				cnt++;
			}
		}
		return cnt;
	}

	private String[] removeNullString(String[] array) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				list.add(array[i]);
			}
		}
		array = new String[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	private int[] removeNullInteger(int[] array) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				list.add(array[i]);
			}
		}
		array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	public void setUpUI() {
		int cnt = 0;
		int txtCnt = 0;

		RelativeLayout relativeLayout, appLayout;
		ScrollView scrollView = new ScrollView(this);
		scrollView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
				.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT));


		appLayout = new RelativeLayout(this);
		relativeLayout = new RelativeLayout(this);


		LinearLayout textLayout = new LinearLayout(this);
		textLayout.setOrientation(LinearLayout.HORIZONTAL);

		if (AppVariables.CUSTOM_LAYOUTS != null) {
			ArrayList<CustomLayout> layouts = AppVariables.CUSTOM_LAYOUTS;
			final ArrayList<CustomComponent> components = layouts.get(0).getComponents();

			ActionBar actionBar = getSupportActionBar();
			actionBar.setTitle(layouts.get(0).getName());
			actionBar.setIcon(R.drawable.icon);

			int id;
			int labelId;
			if (components != null) {
				String[] componentType = new String[components.size()];
				for (int i = 0; i < componentType.length; i++) {
					final int c = i;

					componentType[i] = components.get(i).getType();
					if (componentType[i].equals("textbox") || componentType[i].equals
							("numeric") || componentType[i].equals("password") || componentType[i]
							.equals("textField") || componentType[i].equals("email")) {
						id = components.get(i).getId();
						labelId = components.get(i).getLabelId();

						RelativeLayout.LayoutParams editParams = new RelativeLayout

								.LayoutParams(
								RelativeLayout.LayoutParams.FILL_PARENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);

						RelativeLayout.LayoutParams textParams = new RelativeLayout

								.LayoutParams(
								RelativeLayout.LayoutParams.WRAP_CONTENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);

						textParams.addRule(RelativeLayout.LEFT_OF, id);
						editParams.addRule(RelativeLayout.BELOW, id - 1);


						if (id == 1) {
							textParams.addRule(RelativeLayout.ALIGN_TOP);
						}

						textParams.setMargins(0, 60, 0, 0);
						editParams.setMargins(15, 10, 15, 0);


						EditText editText = new EditText(this);
						editText.setId(id);
						editText.setHint("Enter " + components.get(i).getName());

						TextView textView = new TextView(this);
						textView.setId(labelId);
						textView.setText(components.get(i).getLabel());

						if (components.get(i).getType().equals("password")) {
							editText.setInputType(InputType
									.TYPE_TEXT_VARIATION_WEB_PASSWORD);
							editText.setTransformationMethod(PasswordTransformationMethod
									.getInstance());
						} else if (components.get(i).getType().equals("numeric")) {
							editText.setInputType(InputType.TYPE_CLASS_NUMBER);
						} else if (components.get(i).getType().equals("textbox")) {
							editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
						} else if (components.get(i).getType().equals("email")) {
							editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
						} else {
							editText.setInputType(InputType.TYPE_CLASS_TEXT);
						}


						relativeLayout.addView(textView, textParams);
						relativeLayout.addView(editText, editParams);
						editTextList.add(editText);

					} else if (componentType[i].equals("select")) {
						id = components.get(i).getId();

						RelativeLayout.LayoutParams selectParams = new RelativeLayout
								.LayoutParams(
								RelativeLayout.LayoutParams.FILL_PARENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);

						LinearLayout linearLayout = new LinearLayout(this);
						linearLayout.setId(id);
						linearLayout.setOrientation(LinearLayout.HORIZONTAL);


						TextView title = new TextView(this);
						title.setText(components.get(i).getLabel());
						title.setId(id * i + 1);
						title.setGravity(Gravity.LEFT);

						Spinner spinner = new Spinner(this);
						spinner.setId(id * i);
						spinner.setTag(components.get(i).getName());

						String[] spinnerArray = components.get(i).getValue().split("\\|");

						ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
						spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						spinner.setAdapter(spinnerArrayAdapter);

						linearLayout.addView(title);
						linearLayout.addView(spinner, selectParams);

						selectParams.addRule(RelativeLayout.BELOW, id - 1);

						if (id == 1) {
							selectParams.addRule(RelativeLayout.ALIGN_TOP);
						}

						relativeLayout.addView(linearLayout, selectParams);
						spinnerList.add(spinner);

					} else if (componentType[i].equals("radio")) {
						id = components.get(i).getId();
						labelId = components.get(i).getLabelId();
						String[] radioArray = components.get(i).getValue().split("\\|");

						RelativeLayout.LayoutParams radioParams = new RelativeLayout
								.LayoutParams(
								RelativeLayout.LayoutParams.FILL_PARENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);

						LinearLayout linea = new LinearLayout(this);
						linea.setId(id);
						linea.setOrientation(LinearLayout.HORIZONTAL);

						RadioGroup rg = new RadioGroup(this);
						rg.setOrientation(LinearLayout.HORIZONTAL);
						rg.setTag(components.get(i).getName());
						RadioButton[] rb = new RadioButton[radioArray.length];
						for (int x = 0; x < radioArray.length; x++) {
							rb[x] = new RadioButton(this);
							rb[x].setText(radioArray[x]);
							rg.addView(rb[x]);
							if (x == 0) {
								rb[x].setChecked(true);
							}
						}

						radioButtonList.add(rb);

						TextView textView = new TextView(this);
						textView.setText(components.get(i).getLabel());
						textView.setId(labelId);
						textView.setGravity(Gravity.LEFT);

						radioParams.addRule(RelativeLayout.BELOW, id - 1);
						radioParams.addRule(RelativeLayout.LAYOUT_DIRECTION_LTR);

						radioGroupList.add(rg);

						if (id == 1) {
							radioParams.addRule(RelativeLayout.ALIGN_TOP);
						}

						linea.addView(textView);
						linea.addView(rg);
						relativeLayout.addView(linea, radioParams);

					} else if (componentType[i].equals("checkbox")) {
						id = components.get(i).getId();

						CheckBox checkBox = new CheckBox(this);
						checkBox.setId(id);
						checkBox.setText(components.get(i).getLabel());
						checkBox.setTag(components.get(i).getValue());

						RelativeLayout.LayoutParams checkParams = new RelativeLayout.LayoutParams(
								RelativeLayout.LayoutParams.FILL_PARENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);

						checkParams.addRule(RelativeLayout.BELOW, id - 1);

						if (id == 1) {
							checkParams.addRule(RelativeLayout.ALIGN_TOP);
						}
						relativeLayout.addView(checkBox, checkParams);
						checkBoxList.add(checkBox);

					} else if (componentType[i].equals("hidden")) {
						TextView textView = new TextView(this);
						id = components.get(i).getId();
						textView.setId(id);
						textView.setVisibility(View.INVISIBLE);

						RelativeLayout.LayoutParams rel = new RelativeLayout.LayoutParams
								(RelativeLayout.LayoutParams.WRAP_CONTENT,
										RelativeLayout.LayoutParams.WRAP_CONTENT);

						rel.addRule(RelativeLayout.BELOW, id - 1);
						if (id == 1) {
							rel.addRule(RelativeLayout.ALIGN_TOP);
						}

						relativeLayout.addView(textView, rel);
					} else if (componentType[i].equals("file")) {
						id = components.get(i).getId();

						Button button = new Button(this);
						button.setId(id);
						button.setText("Select " + components.get(i).getLabel());
						button.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								startDialog();
							}
						});

						RelativeLayout.LayoutParams fileParams = new RelativeLayout.LayoutParams(
								RelativeLayout.LayoutParams.FILL_PARENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);

						fileParams.addRule(RelativeLayout.BELOW, id - 1);
						if (id == 1) {
							fileParams.addRule(RelativeLayout.ALIGN_TOP);
						}

						relativeLayout.addView(button, fileParams);

					} else if (componentType[i].equals("button")) {
						cnt++;
						id = components.get(i).getId();

						RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
								RelativeLayout.LayoutParams.FILL_PARENT,
								RelativeLayout.LayoutParams.WRAP_CONTENT);

						Button button = new Button(this);
						button.setId(id);
						button.setText(components.get(i).getLabel());
						button.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								data = new ArrayList<>();
								if (components.get(c).getName().equals("submit")) {

									String valuePair;

									for (int x = 0; x < editTextList.size(); x++) {
										valuePair = editTextList.get(x).getHint().toString().replace("Enter ", "");
										data.add(new BasicNameValuePair(valuePair,
												editTextList.get(x).getText().toString()));
									}

									for (int x = 0; x < spinnerList.size(); x++) {
										valuePair = spinnerList.get(x).getTag().toString();
										data.add(new BasicNameValuePair(valuePair,
												spinnerList.get(x).getSelectedItem().toString()));
									}

									for (int x = 0; x < radioGroupList.size(); x++) {
										valuePair = radioGroupList.get(x).getTag().toString();
										int rdoId = radioGroupList.get(x)
												.getCheckedRadioButtonId();

										RadioButton[] rdo = radioButtonList.get(x);
										data.add(new BasicNameValuePair(valuePair,
												rdo[rdoId - 1].getText().toString()));
									}

									for (int x = 0; x < checkBoxList.size(); x++) {
										valuePair = checkBoxList.get(x).getText().toString().toLowerCase();
										String val;
										if (checkBoxList.get(x).isChecked()) {
											val = checkBoxList.get(x).getTag().toString();
										} else {
											val = "0";
										}

										data.add(new BasicNameValuePair(valuePair, val));
									}

									Toast.makeText(getApplicationContext(), data.toString(),
											Toast.LENGTH_LONG).show();
								} else if (components.get(c).getName().equals("reset")) {

								}
							}
						});
						if (cnt == 1) {
							buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

						} else {
							buttonParams.addRule(RelativeLayout.ABOVE, id - 1);
						}
						relativeLayout.addView(button, buttonParams);
					} else {

					}
				}
				setContentView(relativeLayout);
			}
		}
	}

	private void startDialog() {
		AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
		myAlertDialog.setTitle("Upload Pictures Option");
		myAlertDialog.setMessage("How do you want to set your picture?");

		myAlertDialog.setPositiveButton("Gallery",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						pictureActionIntent = new Intent(
								Intent.ACTION_GET_CONTENT, null);
						pictureActionIntent.setType("image/*");
						pictureActionIntent.putExtra("return-data", true);
						startActivityForResult(pictureActionIntent,
								GALLERY_PICTURE);
					}
				});

		myAlertDialog.setNegativeButton("Camera",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						pictureActionIntent = new Intent(
								android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(pictureActionIntent,
								CAMERA_REQUEST);

					}
				});
		myAlertDialog.show();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// get image from gallery
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == GALLERY_PICTURE) {
			if (resultCode == RESULT_OK) {
				if (data != null) {
					// our BitmapDrawable for the thumbnail
					BitmapDrawable bmpDrawable = null;
					// try to retrieve the image using the data from the intent
					Cursor cursor = getContentResolver().query(data.getData(),
							null, null, null, null);
					if (cursor != null) {

						cursor.moveToFirst();

						int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
						String fileSrc = cursor.getString(idx);
						bitmap = BitmapFactory.decodeFile(fileSrc);
						AppVariables.BITMAP = bitmap;

					} else {
						AppVariables.BITMAP = bitmap;
					}

				} else {
					Toast.makeText(getApplicationContext(), "Cancelled",
							Toast.LENGTH_SHORT).show();
				}
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(getApplicationContext(), "Cancelled",
						Toast.LENGTH_SHORT).show();
			}
			// get image from camera
		} else if (requestCode == CAMERA_REQUEST) {
			if (resultCode == RESULT_OK) {
				if (data.hasExtra("data")) {

					// retrieve the bitmap from the intent
					bitmap = (Bitmap) data.getExtras().get("data");


					Cursor cursor = getContentResolver()
							.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
									new String[]{
											MediaStore.Images.Media.DATA,
											MediaStore.Images.Media.DATE_ADDED,
											MediaStore.Images.ImageColumns.ORIENTATION},
									MediaStore.Images.Media.DATE_ADDED, null, "date_added ASC");
					if (cursor != null && cursor.moveToFirst()) {
						do {
							Uri uri = Uri.parse(cursor.getString(cursor
									.getColumnIndex(MediaStore.Images.Media.DATA)));
							selectedImagePath = uri.toString();
						} while (cursor.moveToNext());
						cursor.close();
					}

					Log.e("path: ", selectedImagePath);

					AppVariables.BITMAP = bitmap;

				} else if (data.getExtras() == null) {

					Toast.makeText(getApplicationContext(),
							"No extras to retrieve!", Toast.LENGTH_SHORT)
							.show();
				}

			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(getApplicationContext(), "Cancelled",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
