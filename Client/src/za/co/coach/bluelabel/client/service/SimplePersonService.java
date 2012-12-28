/**
 * za.co.coach.bluelabel.client.service<br>
 * 
 * <p><b>Title:</b> SimplePersonService<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 8:29:46 PM
 */
package za.co.coach.bluelabel.client.service;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
 * <p><b>Title:</b> SimplePersonService<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 8:29:46 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class SimplePersonService implements PersonService {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Map<String, Serializable> message = new HashMap<String, Serializable>();
	private String theServer;
	private Socket client;
	private Map<Integer, Person> contacts;

	/**
	 * 
	 */
	public SimplePersonService() {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("Client.properties"));
			theServer = props.getProperty("host");
			if (theServer == null)
				theServer = "127.0.0.1";
		} catch (IOException ioe) {
			System.err.println("SimplePersonService#SimplePersonService Failed with an Exception [IOException] - " + ioe.toString());
			theServer = "127.0.0.1";
		}
		try {
			connectToServer();
			getStreams();
		} catch (IOException ex) {
			System.err.println("SimplePersonService#SimplePersonService Failed with an Exception [IOException] - " + ex.toString());
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * updateContacts
	 * 
	 * @param results
	 */
	private void updateContacts(List<Person> results, boolean search) {
		try {
			if (search) {
				message = (Map<String, Serializable>) input.readObject();
				String result = (String) message.get(Constants.RESULT);

				List<Person> target = (ArrayList<Person>) message.get(Constants.TARGET);
				if (target != null)
					results.addAll(target);
				//System.out.printf("SimplePersonService#updateContacts ::\nresult - %s\nresults - %s.\n", result, results);

				if (Constants.SUCCESSFUL.equalsIgnoreCase(result)) {
					contacts = new HashMap<Integer, Person>();
					for (Person person : results) {
						contacts.put(new Integer(person.getAddressID()), person);
					}
				}
			} else {
				results.addAll(contacts.values());
			}
		} catch (ClassNotFoundException ex) {
			System.err.println("SimplePersonService#updateContacts Failed with an Exception [ClassNotFoundException] - " + ex.toString());
		} catch (IOException ex) {
			System.err.println("SimplePersonService#updateContacts Failed with an Exception [IOException] - " + ex.toString());
		}
	}

	/* (non-Javadoc)
	 * @see za.co.coach.bluelabel.client.service.PersonService#displayAll(boolean)
	 */
	@Override
	public List<Person> displayAll(boolean search) {
		List<Person> results = new ArrayList<Person>();
		if (search) {
			message.put(Constants.ACTION, Constants.DISPLAY_ALL);
			sendData(message);
		}
		updateContacts(results, search);
		return results;
	}

	/* (non-Javadoc)
	 * @see za.co.coach.bluelabel.client.service.PersonService#addPerson(za.co.coach.bluelabel.entities.Person)
	 */
	@Override
	public Person addPerson(Person person) {
		List<Person> results = new ArrayList<Person>();
		message.put(Constants.ACTION, Constants.CREATE);
		message.put(Constants.TARGET, person);
		sendData(message);
		updateContacts(results, true);
		return person;
	}

	/* (non-Javadoc)
	 * @see za.co.coach.bluelabel.client.service.PersonService#updatePerson(za.co.coach.bluelabel.entities.Person)
	 */
	@Override
	public Person updatePerson(Person person) {
		List<Person> results = new ArrayList<Person>();
		message.put(Constants.ACTION, Constants.UPDATE);
		message.put(Constants.TARGET, person);
		sendData(message);
		updateContacts(results, true);
		return getPerson(person.getAddressID());
	}

	/* (non-Javadoc)
	 * @see za.co.coach.bluelabel.client.service.PersonService#deletePerson(za.co.coach.bluelabel.entities.Person)
	 */
	@Override
	public Person deletePerson(Person person) {
		List<Person> results = new ArrayList<Person>();
		message.put(Constants.ACTION, Constants.DELETE);
		message.put(Constants.TARGET, person);
		sendData(message);
		updateContacts(results, true);
		return new Person();
	}

	/* (non-Javadoc)
	 * @see za.co.coach.bluelabel.client.service.PersonService#getPerson(java.lang.Integer)
	 */
	@Override
	public Person getPerson(Integer contactId) {
		//System.out.printf("SimplePersonService#getPerson :: Contact id is %s and 'contacts' contains:\n%s.\n", contactId, contacts);
		if (contactId == null)
			return new Person();
		else
			return contacts.get(contactId);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * runClient
	 * 
	 */
	public void runClient() {
		try {
			connectToServer();
			getStreams();
		} catch (EOFException eofException) {
			displayMessage("\nClient terminated connection");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * connectToServer
	 * 
	 * @throws IOException
	 */
	private void connectToServer() throws IOException {
		displayMessage("Attempting connection\n");

		// create Socket to make connection to server
		client = new Socket(InetAddress.getByName(theServer), 12345);

		// display connection information
		displayMessage("Connected to: " + client.getInetAddress().getHostName());
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getStreams
	 * 
	 * @throws IOException
	 */
	private void getStreams() throws IOException {
		// set up output stream for objects
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();

		// set up input stream for objects
		input = new ObjectInputStream(client.getInputStream());

		displayMessage("\nGot I/O streams\n");
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * closeConnection
	 * 
	 */
	private void closeConnection() {
		displayMessage("\nClosing connection");

		try {
			output.close();
			input.close();
			client.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * sendData
	 * 
	 * @param message
	 */
	public void sendData(Map<String, Serializable> message) {
		try {
			output.writeObject(message);
			output.flush();
			//displayMessage("\nCLIENT>>> " + message);
		} catch (IOException ioException) {
			System.out.println("\nError writing object");
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * displayMessage
	 * 
	 * @param messageToDisplay
	 */
	private void displayMessage(String messageToDisplay) {
		System.out.println(messageToDisplay);
	}
}