package com.google.youtube.client.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.youtube.client.event.chromeless.StopMediaEvent;
import com.google.youtube.client.event.chromeless.handler.StopMediaHandler;
import com.google.youtube.client.ui.controls.api.IStopControl;
import com.google.youtube.client.ui.controls.common.AbstractButtonWrapper;

public class StopButton extends AbstractButtonWrapper implements IStopControl {

	public StopButton(Button button) {
		super(button);
	}

	@Override
	public HandlerRegistration addStopHandler(StopMediaHandler handler) {
		return addHandler(handler, StopMediaEvent.getType());
	}

	@Override
	protected void fireEvent(ClickEvent event) {
		StopMediaEvent.fire(StopButton.this);
	}
}