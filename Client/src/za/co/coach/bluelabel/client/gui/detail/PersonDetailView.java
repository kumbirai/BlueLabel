/**
 * za.co.coach.bluelabel.client.gui.detail<br>
 * 
 * <p><b>Title:</b> PersonDetailView<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 8:15:24 PM
 */
package za.co.coach.bluelabel.client.gui.detail;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import za.co.coach.bluelabel.entities.Person;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * Copyright Notice<br>
 * ================<br>
 * This file contains proprietary information.
 * Copying or reproduction without prior written approval is prohibited.<br>
 * Copyright (c) 2012<br>
 * 
 * <p><b>Title:</b> PersonDetailView<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 8:15:24 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class PersonDetailView extends GridPane {
	private PersonDetailPresenter presenter;
	private Integer personId;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField emailField;
	private TextField phoneNumberField;

	/**
	 * 
	 */
	public PersonDetailView() {
		buildView();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setPresenter
	 * 
	 * @param presenter
	 */
	public void setPresenter(PersonDetailPresenter presenter) {
		this.presenter = presenter;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getPerson
	 * 
	 * @return
	 */
	public Person getPerson() {
		Person person = new Person();
		person.setAddressID(personId);
		person.setFirstName(firstNameField.getText());
		person.setLastName(lastNameField.getText());
		person.setEmail(emailField.getText());
		String phoneNumber = phoneNumberField.getText();
		person.setPhoneNumber(phoneNumber != null ? phoneNumber.replaceFirst("^(\\d{3})(\\d{3})(\\d{4})$", "$1 $2 $3") : "");
		return person;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setPerson
	 * 
	 * @param person
	 */
	public void setPerson(Person person) {
		if (person != null) {
			personId = person.getAddressID();
			firstNameField.setText(person.getFirstName());
			lastNameField.setText(person.getLastName());
			emailField.setText(person.getEmail());
			phoneNumberField.setText(person.getPhoneNumber());
		} else {
			personId = 0;
			firstNameField.setText("");
			lastNameField.setText("");
			emailField.setText("");
			phoneNumberField.setText("");
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * buildView
	 * 
	 */
	protected void buildView() {
		setHgap(10);
		setVgap(10);
		int row = 0;

		add(new Label("First name"), 0, row);
		firstNameField = new TextField();
		firstNameField.setPrefColumnCount(20);
		add(firstNameField, 1, row);

		row++;

		add(new Label("Last name"), 0, row);
		lastNameField = new TextField();
		lastNameField.setPrefColumnCount(20);
		add(lastNameField, 1, row);

		row++;

		add(new Label("Email Address"), 0, row);
		emailField = new TextField();
		emailField.setPrefColumnCount(20);
		add(emailField, 1, row);

		row++;

		add(new Label("Phone Number"), 0, row);
		phoneNumberField = new TextField();
		phoneNumberField.setPrefColumnCount(20);
		add(phoneNumberField, 1, row);

		row++;

		HBox buttonBar = new HBox(10);

		Button saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				presenter.save();
			}
		});
		buttonBar.getChildren().add(saveButton);

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				presenter.delete();
			}
		});
		buttonBar.getChildren().add(deleteButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				presenter.cancel();
			}
		});
		buttonBar.getChildren().add(cancelButton);

		add(buttonBar, 0, row, 1, 2);
	}
}
