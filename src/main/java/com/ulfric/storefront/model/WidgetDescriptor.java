package com.ulfric.storefront.model;

import java.util.Map;

public class WidgetDescriptor extends Named {

	private String type;
	private Map<String, Property> properties;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, Property> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Property> properties) {
		this.properties = properties;
	}

}
