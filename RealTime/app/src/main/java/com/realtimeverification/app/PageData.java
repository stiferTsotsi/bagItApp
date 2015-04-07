package com.realtimeverification.app;

import java.util.ArrayList;

/**
 * Created by vaal on 3/20/2015.
 */
public class PageData {
	private ArrayList<CustomButton> customButtons;
	private ArrayList<CustomSetting> customSettings;

	public PageData(ArrayList<CustomButton> customButtons, ArrayList<CustomSetting> customSettings) {
		setCustomButtons(customButtons);
		setCustomSettings(customSettings);
	}

	public ArrayList<CustomButton> getCustomButtons() {
		return customButtons;
	}

	public void setCustomButtons(ArrayList<CustomButton> customButtons) {
		this.customButtons = customButtons;
	}

	public ArrayList<CustomSetting> getCustomSettings() {
		return customSettings;
	}

	public void setCustomSettings(ArrayList<CustomSetting> customSettings) {
		this.customSettings = customSettings;
	}
}
