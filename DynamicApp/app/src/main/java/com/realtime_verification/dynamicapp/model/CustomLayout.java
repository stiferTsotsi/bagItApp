package com.realtime_verification.dynamicapp.model;

import java.util.ArrayList;

/**
 * Created by vaal on 3/26/2015.
 */
public class CustomLayout {

	private String name;
	private String action;
	private String method;
	private ArrayList<CustomComponent> components;

	public CustomLayout(String name, String action, String method, ArrayList<CustomComponent> components) {
		setName(name);
		setAction(action);
		setMethod(method);
		setComponents(components);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public ArrayList<CustomComponent> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<CustomComponent> components) {
		this.components = components;
	}
}
