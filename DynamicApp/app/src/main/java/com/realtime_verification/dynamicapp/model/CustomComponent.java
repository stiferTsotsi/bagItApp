package com.realtime_verification.dynamicapp.model;

/**
 * Created by vaal on 3/26/2015.
 */
public class CustomComponent {

	private int id;
	private int labelId;
	private String label;
	private String name;
	private String type;
	private String priority;
	private String value;
	private String readOnly;
	private String required;

	public CustomComponent(int id,int labelId,String label, String name, String type,
	                       String priority,
	                       String value, String readOnly, String required) {
		setId(id);
		setLabelId(labelId);
		setLabel(label);
		setName(name);
		setType(type);
		setPriority(priority);
		setValue(value);
		setReadOnly(readOnly);
		setRequired(required);
	}

	public int getId() {
		return id;
	}

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}
}
