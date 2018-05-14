package com.vaadin.starter.skeleton;

import java.util.function.Consumer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.starter.data.Todo;

public class TodoLine extends HorizontalLayout {

	private static final long serialVersionUID = -670798909345627146L;

	private final Checkbox checkBox;
	private final Paragraph nameText;
	private final Todo todo;

	public TodoLine(Todo t, Runnable updateFunction, Consumer<Todo> deleteFunction) {

		addClassName("todo-line");

		todo = t;
		checkBox = new Checkbox(t.isDone());
		nameText = new Paragraph(t.getName());

		final Button deleteButton = new Button(VaadinIcons.TRASH.create());
		deleteButton.addClassName("delete");
		deleteButton.getElement().setAttribute("theme", "tertiary");

		add(checkBox, nameText, deleteButton);
		setSpacing(true);
		setWidth("100%");
		setFlexGrow(2, nameText);
		setDefaultVerticalComponentAlignment(Alignment.CENTER);

		updateStyles();

		checkBox.addValueChangeListener(e -> {
			t.setDone(e.getValue());
			updateStyles();
			updateFunction.run();
		});
		
		deleteButton.addClickListener(e -> deleteFunction.accept(todo));
	}

	private void updateStyles() {
		nameText.removeClassName("done");
		if (todo.isDone()) {
			nameText.addClassName("done");
		}
	}
}
