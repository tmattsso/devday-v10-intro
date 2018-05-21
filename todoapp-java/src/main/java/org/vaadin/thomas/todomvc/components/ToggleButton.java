package org.vaadin.thomas.todomvc.components;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;

@Tag("paper-toggle-button")
@HtmlImport("bower_components/paper-toggle-button/paper-toggle-button.html")
public class ToggleButton extends Div {

	private static final long serialVersionUID = -1L;

	public ToggleButton() {

		getElement().synchronizeProperty("checked", "checked-changed");
		getElement().addPropertyChangeListener("checked", e -> {
			fireEvent(new ToggleButtonToggleEvent(this, true));
		});
	}

	public void setChecked(boolean checked) {
		getElement().setProperty("checked", checked);
	}

	public boolean isChecked() {
		return getElement().getProperty("checked", false);
	}

	public Registration addToggleListener(ComponentEventListener<ToggleButtonToggleEvent> listener) {
		return addListener(ToggleButtonToggleEvent.class, (ComponentEventListener<ToggleButtonToggleEvent>) listener);
	}

	private class ToggleButtonToggleEvent extends ComponentEvent<ToggleButton> {

		private static final long serialVersionUID = -1L;

		public ToggleButtonToggleEvent(ToggleButton source, boolean fromClient) {
			super(source, fromClient);
		}
	}
}
