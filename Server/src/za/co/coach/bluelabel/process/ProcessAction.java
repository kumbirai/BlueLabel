/**
 * za.co.coach.bluelabel.process<br>
 * 
 * <p><b>Title:</b> ProcessAction<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 10:22:37 AM
 */
package za.co.coach.bluelabel.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import za.co.coach.bluelabel.SQLQueries.AddressBookQueries;
import za.co.coach.bluelabel.constants.Constants;
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
 * <p><b>Title:</b> ProcessAction<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 10:22:37 AM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class ProcessAction {
	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * displayAll
	 * 
	 * @return
	 */
	public static Map<String, Serializable> displayAll() {
		AddressBookQueries addressBookQueries = new AddressBookQueries();
		ArrayList<Person> results = new ArrayList<Person>();
		Map<String, Serializable> message = new HashMap<String, Serializable>();
		results.addAll(addressBookQueries.getAllPeople());

		message.put(Constants.RESULT, Constants.SUCCESSFUL);
		message.put(Constants.TARGET, results);

		return message;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * addPerson
	 * 
	 * @param person
	 * @return
	 */
	public static Map<String, Serializable> addPerson(Person person) {
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		String email = person.getEmail();
		String phoneNumber = person.getPhoneNumber();

		AddressBookQueries addressBookQueries = new AddressBookQueries();
		int result = addressBookQueries.addPerson(firstName, lastName, email, phoneNumber);

		if (result > 0) {
			return displayAll();
		} else {
			Map<String, Serializable> message = new HashMap<String, Serializable>();
			message.put(Constants.RESULT, Constants.FAILURE);
			message.put(Constants.TARGET, "There was a general failure whilst trying to add new person");

			return message;
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * updatePerson
	 * 
	 * @param person
	 * @return
	 */
	public static Map<String, Serializable> updatePerson(Person person) {
		int addressID = person.getAddressID();
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		String email = person.getEmail();
		String phoneNumber = person.getPhoneNumber();

		AddressBookQueries addressBookQueries = new AddressBookQueries();
		int result = addressBookQueries.updatePerson(addressID, firstName, lastName, email, phoneNumber);

		if (result > 0) {
			return displayAll();
		} else {
			Map<String, Serializable> message = new HashMap<String, Serializable>();
			message.put(Constants.RESULT, Constants.FAILURE);
			message.put(Constants.TARGET, "There was a general failure whilst trying to update person");

			return message;
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * deletePerson
	 * 
	 * @param person
	 * @return
	 */
	public static Map<String, Serializable> deletePerson(Person person) {
		int addressID = person.getAddressID();

		AddressBookQueries addressBookQueries = new AddressBookQueries();
		int result = addressBookQueries.deletePerson(addressID);

		if (result > 0) {
			return displayAll();
		} else {
			Map<String, Serializable> message = new HashMap<String, Serializable>();
			message.put(Constants.RESULT, Constants.FAILURE);
			message.put(Constants.TARGET, "There was a general failure whilst trying to delete person");

			return message;
		}
	}
}