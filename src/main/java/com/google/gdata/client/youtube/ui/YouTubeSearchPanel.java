package com.google.gdata.client.youtube.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

public class YouTubeSearchPanel extends CustomizableUIComposite {

	public static final String SEARCH_BOX_STYLE = "youtube-search-box";
	public static final String SEARCH_BUTTON_STYLE = "youtube-search-button";
	public static final String SEARCH_PANEL_STYLE = "youtube-search-panel";

	private static final YouTubeMessages youTubeMessages = GWT.create(YouTubeMessages.class);

	private FlowPanel flowPanel;

	private TextBox textBox;
	
	public static interface SearchHandler extends EventHandler {
		void onPage(SearchEvent event);
	}

	public static class SearchEvent extends GwtEvent<SearchHandler> {

		private static Type<SearchHandler> TYPE;

		public static void fire(YouTubeSearchPanel source, String search) {
			if (TYPE != null) {
				SearchEvent event = new SearchEvent(search);
				source.fireEvent(event);
			}
		}

		public static Type<SearchHandler> getType() {
			return TYPE != null ? TYPE : (TYPE = new Type<SearchHandler>());
		}

		private String search;

		protected SearchEvent(String search) {
			this.search = search;
		}

		@Override
		public final Type<SearchHandler> getAssociatedType() {
			return (Type<SearchHandler>) TYPE;
		}

		public String getSearch() {
			return search;
		}

		@Override
		protected void dispatch(SearchHandler handler) {
			handler.onPage(this);
		}
	}

	public YouTubeSearchPanel() {
		flowPanel = new FlowPanel();
		initWidget(flowPanel);
		flowPanel.setStyleName(SEARCH_PANEL_STYLE);
		prepareUI();
	}

	public HandlerRegistration addPagingHandler(SearchHandler handler) {
		return addHandler(handler, SearchEvent.getType());
	}

	public void setSearchText(String text) {
		this.textBox.setText(text);
	}

	protected TextBox constructTextBox() {
		return new TextBox();
	}
	
	protected Button constructButton(String label) {
		return ensureUIConstructor().constructButton(label);
	}
	
	public void prepareUI() {
		textBox = constructTextBox();
		textBox.setStyleName(SEARCH_BOX_STYLE);
		flowPanel.add(textBox);

		Button search = constructButton(youTubeMessages.search());
		search.addStyleName(SEARCH_BUTTON_STYLE);

		search.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SearchEvent.fire(YouTubeSearchPanel.this, textBox.getValue());
			}
		});
		flowPanel.add(search);
	}
}