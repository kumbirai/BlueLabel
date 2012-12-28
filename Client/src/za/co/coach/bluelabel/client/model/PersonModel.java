/**
 * za.co.coach.bluelabel.client.model<br>
 * 
 * <p><b>Title:</b> PersonModel<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 10:53:52 AM
 */
package za.co.coach.bluelabel.client.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
 * <p><b>Title:</b> PersonModel<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 10:53:52 AM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public final class PersonModel {
	private IntegerProperty addressID;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty email;
	private StringProperty phoneNumber;

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getAddressID
	 * 
	 * @return
	 */
	public int getAddressID() {
		return addressIDProperty().get();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setAddressID
	 * 
	 * @param value
	 */
	public void setAddressID(int value) {
		addressIDProperty().set(value);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * addressIDProperty
	 * 
	 * @return
	 */
	public IntegerProperty addressIDProperty() {
		if (addressID == null)
			addressID = new SimpleIntegerProperty(this, "addressID");
		return addressID;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getFirstName
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstNameProperty().get();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setFirstName
	 * 
	 * @param value
	 */
	public void setFirstName(String value) {
		firstNameProperty().set(value);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * firstNameProperty
	 * 
	 * @return
	 */
	public StringProperty firstNameProperty() {
		if (firstName == null)
			firstName = new SimpleStringProperty(this, "firstName");
		return firstName;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setLastName
	 * 
	 * @param value
	 */
	public void setLastName(String value) {
		lastNameProperty().set(value);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getLastName
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastNameProperty().get();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * lastNameProperty
	 * 
	 * @return
	 */
	public StringProperty lastNameProperty() {
		if (lastName == null)
			lastName = new SimpleStringProperty(this, "lastName");
		return lastName;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setEmail
	 * 
	 * @param value
	 */
	public void setEmail(String value) {
		emailProperty().set(value);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getEmail
	 * 
	 * @return
	 */
	public String getEmail() {
		return emailProperty().get();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * emailProperty
	 * 
	 * @return
	 */
	public StringProperty emailProperty() {
		if (email == null)
			email = new SimpleStringProperty(this, "email");
		return email;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setPhoneNumber
	 * 
	 * @param value
	 */
	public void setPhoneNumber(String value) {
		phoneNumberProperty().set(value);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getPhoneNumber
	 * 
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumberProperty().get();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * phoneNumberProperty
	 * 
	 * @return
	 */
	public StringProperty phoneNumberProperty() {
		if (phoneNumber == null)
			phoneNumber = new SimpleStringProperty(this, "phoneNumber");
		return phoneNumber;
	}

	/**
	 * @param addressID
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 */
	public PersonModel(int addressID, String firstName, String lastName, String email, String phoneNumber) {
		setAddressID(addressID);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhoneNumber(phoneNumber);
	}

	/**
	 * 
	 */
	public PersonModel() {
		setAddressID(0);
		setFirstName("");
		setLastName("");
		setEmail("");
		setPhoneNumber("");
	}

	/**
	 * @param person
	 */
	public PersonModel(Person person) {
		setAddressID(person.getAddressID());
		setFirstName(person.getFirstName());
		setLastName(person.getLastName());
		setEmail(person.getEmail());
		setPhoneNumber(person.getPhoneNumber());
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

		person.setAddressID(addressIDProperty().get());
		person.setFirstName(firstNameProperty().get());
		person.setLastName(lastNameProperty().get());
		person.setEmail(emailProperty().get());
		person.setPhoneNumber(phoneNumberProperty().get());

		return person;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("PersonModel: %s %s.\n", firstName.getValue(), lastName.getValue());
	}
}