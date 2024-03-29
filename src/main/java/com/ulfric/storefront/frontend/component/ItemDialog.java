package com.ulfric.storefront.frontend.component;

import com.ulfric.storefront.model.Described;
import com.ulfric.storefront.model.Event;
import com.ulfric.storefront.model.Item;
import com.ulfric.storefront.model.Session;
import com.ulfric.storefront.repositories.SessionRepository;
import com.ulfric.storefront.services.AnalyticsService;
import com.ulfric.storefront.vaadin.button.ContrastButton;
import com.ulfric.storefront.vaadin.button.SecondaryContrastButton;
import com.ulfric.storefront.vaadin.button.TertiaryContrastButton;
import com.ulfric.storefront.vaadin.dialog.DialogLayout;
import com.ulfric.storefront.vaadin.dialog.StorefrontDialog;
import com.ulfric.storefront.vaadin.margin.Line;
import com.ulfric.storefront.vaadin.text.CourierTitle;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ItemDialog extends Composite<StorefrontDialog> {

	public ItemDialog(String category, Item item, Session session, SessionRepository sessions, AnalyticsService analytics) {
		Event event = new Event();
		event.setInteraction(true);
		event.setName("view_item");
		event.getDetails().put("source_category", category);
		event.getDetails().put("item_name", item.getName());
		event.getDetails().put("item_path", item.getPath());
		analytics.record(session.getAnalyticsId(), event);

		VerticalLayout layout = new DialogLayout();

		HorizontalLayout titleRow = new HorizontalLayout();
		titleRow.setWidth("100%");
		titleRow.getStyle().set("flex-direction", "row");
		titleRow.getStyle().set("flex-wrap", "wrap-reverse");
		titleRow.add(new CourierTitle(item.getName()));
		Button button = new TertiaryContrastButton();
		button.setIcon(new Icon(VaadinIcons.CLOSE)); // TODO properly align
		button.getElement().getThemeList().add("small");
		button.getStyle().set("padding-top", "1.25em");
		titleRow.add(button);
		layout.add(titleRow);

		Div description = Described.render(item.getDescription());
		description.setSizeFull();
		description.setHeight("400%");
		description.getStyle().set("overflow", "auto");
		description.getStyle().set("border-style", "solid");
		description.getStyle().set("border-color", "var(--lumo-contrast-10pct)");
		description.getStyle().set("border-width", "2px 0");
		layout.add(description);

		Div buttons = new Div();
		buttons.setSizeFull();

		HorizontalLayout purchaseRow = new HorizontalLayout();
		purchaseRow.setWidth("100%");
		purchaseRow.getStyle().set("flex-direction", "row");
		purchaseRow.getStyle().set("flex-wrap", "wrap-reverse");

		Button addToCart = new SecondaryContrastButton(); // TODO record user adds to card. Ensure this is recorded differently depending on the presence of one click
		addToCart.setText("Add to cart");

		Button purchase = new ContrastButton(); // TODO record user buys with one click
		purchase.setText("Buy with one click");

		purchaseRow.add(addToCart, purchase);
		purchaseRow.setFlexGrow(1, addToCart);
		purchaseRow.setFlexGrow(7, purchase);

		buttons.add(purchaseRow);

		HorizontalLayout orRow = new HorizontalLayout();
		orRow.setWidth("100%");

		Label or = new Label("OR");
		or.getStyle().set("text-align", "center");
		orRow.add(new Line(), or, new Line());

		buttons.add(orRow);

		TextField giftUsername = new TextField(); // TODO record user focuses on gift field
		giftUsername.setWidth("100%");
		giftUsername.setMaxLength(16);
		giftUsername.setPlaceholder("Recipient's Minecraft username");
		HorizontalLayout gifttt = new HorizontalLayout();
		Label buyAsAGift = new Label("BUY AS A GIFT");
		buyAsAGift.getStyle().set("font-weight", "600");
		buyAsAGift.getStyle().set("margin-top", "0.25em");
		gifttt.add(buyAsAGift, new Icon(VaadinIcons.GIFT));
		Button go = new Button(gifttt); // TODO record user buys gift
		go.getElement().getThemeList().add("small");
		go.getElement().getThemeList().add("contrast");
		go.getElement().getThemeList().add("tertiary");
		go.addClassName("pointer");
		giftUsername.setSuffixComponent(go);
		buttons.add(giftUsername);

		layout.add(buttons);

		getContent().add(layout);
	}

	public void open() {
		getContent().open();
	}

}
