package com.realtimeverification.app;

import java.util.ArrayList;

/**
 * Created by vaal on 3/20/2015.
 */
public class CustomButton {
	private int id;
	private String iconLocation;
	private String name;
	private String link;
	private ArrayList<CustomButton> buttons;
	private ArrayList<ButtonStyle> styles;

	public CustomButton(int id, String iconLocation, String name, String link, ArrayList<ButtonStyle> styles) {
		setId(id);
		setIconLocation(iconLocation);
		setName(name);
		setLink(link);
		setStyles(styles);
	}

	public ArrayList<ButtonStyle> getStyles() {
		return styles;
	}

	public void setStyles(ArrayList<ButtonStyle> styles) {
		this.styles = styles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIconLocation() {
		return iconLocation;
	}

	public void setIconLocation(String iconLocation) {
		this.iconLocation = iconLocation;
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

	public ArrayList<CustomButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<CustomButton> buttons) {
		this.buttons = buttons;
	}

}
