package com.realtimeverification.app;

import java.util.ArrayList;

/**
 * Created by vaal on 3/20/2015.
 */
public class CustomSetting {
	private String name;
	private String link;
	private ArrayList<CustomSetting> settings;
	private ArrayList<CustomParameter> parameters;

	public CustomSetting(String name, String link, ArrayList<CustomParameter> parameters) {
		setName(name);
		setLink(link);
		setSettings(settings);
		setParameters(parameters);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public ArrayList<CustomParameter> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<CustomParameter> parameters) {
		this.parameters = parameters;
	}

	public ArrayList<CustomSetting> getSettings() {
		return settings;
	}

	public void setSettings(ArrayList<CustomSetting> settings) {
		this.settings = settings;
	}
}
