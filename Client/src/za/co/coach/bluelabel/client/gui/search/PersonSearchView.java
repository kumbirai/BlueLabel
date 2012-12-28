/**
 * za.co.coach.bluelabel.client.gui.search<br>
 * 
 * <p><b>Title:</b> PersonSearchView<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 8:19:12 PM
 */
package za.co.coach.bluelabel.client.gui.search;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import za.co.coach.bluelabel.client.model.PersonModel;
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
 * <p><b>Title:</b> PersonSearchView<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 8:19:12 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class PersonSearchView extends VBox {
	private PersonSearchPresenter presenter;
	private TextField searchField;
	private ObservableList<PersonModel> phoneEntries;
	private TableView<PersonModel> table;

	/**
	 * 
	 */
	public PersonSearchView() {
		buildView();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setPresenter
	 * 
	 * @param presenter
	 */
	public void setPresenter(PersonSearchPresenter presenter) {
		this.presenter = presenter;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setSearchResults
	 * 
	 * @param searchResults
	 */
	public void setSearchResults(List<Person> searchResults) {
		if (phoneEntries != null)
			phoneEntries.clear();
		if (searchResults != null) {
			phoneEntries = FXCollections.observableArrayList();
			for (Person person : searchResults) {
				phoneEntries.add(new PersonModel(person));
			}
			if (table != null)
				table.setItems(phoneEntries);
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * buildView
	 * 
	 */
	protected void buildView() {
		setSpacing(10);
		HBox menuBar = new HBox(10);

		Button newButton = new Button("New");
		newButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				presenter.newPerson();
			}
		});

		menuBar.getChildren().add(newButton);

		getChildren().add(menuBar);

		table = new TableView<PersonModel>();

		TableColumn<PersonModel, String> firstNameCol = new TableColumn<PersonModel, String>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		firstNameCol.setPrefWidth(150);

		TableColumn<PersonModel, String> lastNameCol = new TableColumn<PersonModel, String>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		lastNameCol.setPrefWidth(200);

		TableColumn<PersonModel, String> emailCol = new TableColumn<PersonModel, String>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory("email"));
		emailCol.setPrefWidth(200);

		TableColumn<PersonModel, String> phoneNumberCol = new TableColumn<PersonModel, String>("Phone Number");
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
		phoneNumberCol.setPrefWidth(200);

		table.getColumns().setAll(firstNameCol, lastNameCol, emailCol, phoneNumberCol);

		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				PersonModel person = (PersonModel) newValue;
				if (person != null) {
					presenter.personSelected(person.getAddressID());
				}
			}
		});

		BorderPane.setMargin(table, new Insets(10, 10, 10, 10));
		VBox.setVgrow(table, Priority.ALWAYS);
		getChildren().add(table);
	}
}