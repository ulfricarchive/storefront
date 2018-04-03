package com.ulfric.storefront.vaadin.button;

import com.vaadin.flow.dom.ThemeList;

public class ContrastButton extends PrimaryButton {

	public ContrastButton() {
		ThemeList themes = getElement().getThemeList();
		themes.add("contrast");
	}

}
