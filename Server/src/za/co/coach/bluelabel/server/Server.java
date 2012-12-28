/**
 * za.co.coach.bluelabel.server<br>
 * 
 * <p><b>Title:</b> Server<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 11:48:22 AM
 */
package za.co.coach.bluelabel.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import za.co.coach.bluelabel.constants.Constants;
import za.co.coach.bluelabel.entities.Person;
import za.co.coach.bluelabel.process.ProcessAction;

/**
 * <p>
 * <b>Purpose:</b><br>
 * <br>
 * 
 * Copyright Notice<br>
 * ================<br>
 * This file contains proprietary information. Copying or reproduction without
 * prior written approval is prohibited.<br>
 * Copyright (c) 2012<br>
 * 
 * <p>
 * <b>Title:</b> Server<br>
 * <b>Description:</b>
 * </p>
 * 
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 11:48:22 AM<br>
 * @version 1.0<br>
 * 
 *          <b>Revision:</b>
 * 
 */
public class Server {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private int counter = 1;
	private Map<String, Serializable> message = new HashMap<String, Serializable>();

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Server application = new Server();
		application.runServer();
	}

	/**
	 * 
	 */
	public Server() {
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * runServer
	 * 
	 */
	public void runServer() {
		try {
			server = new ServerSocket(12345, 100);

			while (true) {
				try {
					waitForConnection();
					getStreams();
					processConnection();
				} catch (EOFException eofException) {
					displayMessage("\nServer terminated connection");
				} finally {
					closeConnection();
					++counter;
				}
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * waitForConnection
	 * 
	 * @throws IOException
	 */
	private void waitForConnection() throws IOException {
		displayMessage("Waiting for connection\n");
		connection = server.accept();
		displayMessage("Connection " + counter + " received from: " + connection.getInetAddress().getHostName());
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
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();

		// set up input stream for objects
		input = new ObjectInputStream(connection.getInputStream());

		displayMessage("\nGot I/O streams\n");
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * processConnection
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void processConnection() throws IOException {
		String action = new String();
		while (true) {
			try {
				message = (Map<String, Serializable>) input.readObject();
				action = (String) message.get(Constants.ACTION);
				if (Constants.DISPLAY_ALL.equalsIgnoreCase(action)) {
					message = ProcessAction.displayAll();
					sendData(message);
				} else if (Constants.CREATE.equalsIgnoreCase(action)) {
					Person person = (Person) message.get(Constants.TARGET);
					message = ProcessAction.addPerson(person);
					sendData(message);
				} else if (Constants.UPDATE.equalsIgnoreCase(action)) {
					Person person = (Person) message.get(Constants.TARGET);
					message = ProcessAction.updatePerson(person);
					sendData(message);
				} else if (Constants.DELETE.equalsIgnoreCase(action)) {
					Person person = (Person) message.get(Constants.TARGET);
					message = ProcessAction.deletePerson(person);
					sendData(message);
				}
				//Clear message since we have already sent it back and we don't want to do a double update.
				message = new HashMap<String, Serializable>();
			} catch (ClassNotFoundException classNotFoundException) {
				displayMessage("\nUnknown object type received");
			}
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * closeConnection
	 * 
	 */
	private void closeConnection() {
		displayMessage("\nTerminating connection\n");

		try {
			output.close();
			input.close();
			connection.close();
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
	private void sendData(Map<String, Serializable> message) {
		try {
			output.writeObject(message);
			output.flush();
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