package com.realtimeverification.app;

import java.util.ArrayList;

/**
 * Created by vaal on 3/23/2015.
 */
public class ButtonStyle {
	private int width;
	private int height;
	private String paddingLeft;
	private String paddingRight;
	private String paddingBottom;
	private String paddingTop;
	private String textSize;
	private String textColor;
	private String bgColor;
	private String strokeColor;
	private String strokeWidth;
	private ArrayList<ButtonStyle> buttonStyles;

	public ArrayList<ButtonStyle> getButtonStyles() {
		return buttonStyles;
	}

	public void setButtonStyles(ArrayList<ButtonStyle> buttonStyles) {
		this.buttonStyles = buttonStyles;
	}

	public ButtonStyle(int width, int height, String paddingLeft, String paddingRight, String paddingBottom, String paddingTop, String textSize, String textColor, String bgColor, String strokeColor, String strokeWidth) {
		this.width = width;
		this.height = height;
		this.paddingLeft = paddingLeft;
		this.paddingRight = paddingRight;
		this.paddingBottom = paddingBottom;
		this.paddingTop = paddingTop;
		this.textSize = textSize;
		this.textColor = textColor;
		this.bgColor = bgColor;
		this.strokeColor = strokeColor;
		this.strokeWidth = strokeWidth;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(String paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public String getPaddingBottom() {
		return paddingBottom;
	}

	public void setPaddingBottom(String paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	public String getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(String paddingRight) {
		this.paddingRight = paddingRight;
	}

	public String getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(String paddingTop) {
		this.paddingTop = paddingTop;
	}

	public String getTextSize() {
		return textSize;
	}

	public void setTextSize(String textSize) {
		this.textSize = textSize;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}

	public String getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(String strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
}
