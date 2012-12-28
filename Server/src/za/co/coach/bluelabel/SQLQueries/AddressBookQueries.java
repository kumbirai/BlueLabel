/**
 * za.co.coach.bluelabel.SQLQueries<br>
 * 
 * <p><b>Title:</b> AddressBookQueries<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 1:08:49 PM
 */
package za.co.coach.bluelabel.SQLQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import za.co.coach.bluelabel.entities.Person;

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
 * <b>Title:</b> AddressBookQueries<br>
 * <b>Description:</b>
 * </p>
 * 
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 1:08:49 PM<br>
 * @version 1.0<br>
 * 
 *          <b>Revision:</b>
 * 
 */
public class AddressBookQueries {
	private static final String URL = "jdbc:derby:AddressBook;create=true";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "password";

	private Connection connection = null;
	private PreparedStatement selectAllPeople = null;
	private PreparedStatement selectPeopleByLastName = null;
	private PreparedStatement insertNewPerson = null;
	private PreparedStatement updatePerson = null;
	private PreparedStatement deletePerson = null;

	/**
	 * 
	 */
	public AddressBookQueries() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// query that selects all entries in the AddressBook
			selectAllPeople = connection.prepareStatement("SELECT * FROM Addresses");

			// query that selects entries with a specific last name
			selectPeopleByLastName = connection.prepareStatement("SELECT * FROM Addresses WHERE LastName = ?");

			// insert that adds a new entry into the database
			insertNewPerson = connection.prepareStatement("INSERT INTO Addresses " + "( FirstName, LastName, Email, PhoneNumber ) " + "VALUES ( ?, ?, ?, ? )");

			// insert that adds a new entry into the database
			updatePerson = connection.prepareStatement("UPDATE Addresses SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ? WHERE AddressID = ?");

			// insert that adds a new entry into the database
			deletePerson = connection.prepareStatement("DELETE FROM Addresses WHERE AddressID = ?");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * addPerson
	 * 
	 * @param fname
	 * @param lname
	 * @param email
	 * @param num
	 * @return
	 */
	public int addPerson(String fname, String lname, String email, String num) {
		int result = 0;

		try {
			insertNewPerson.setString(1, fname);
			insertNewPerson.setString(2, lname);
			insertNewPerson.setString(3, email);
			insertNewPerson.setString(4, num);

			// insert the new entry and return number of rows updated
			result = insertNewPerson.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}

		return result;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * updatePerson
	 * 
	 * @param id
	 * @param fname
	 * @param lname
	 * @param email
	 * @param num
	 * @return
	 */
	public int updatePerson(int id, String fname, String lname, String email, String num) {
		int result = 0;

		try {
			updatePerson.setString(1, fname);
			updatePerson.setString(2, lname);
			updatePerson.setString(3, email);
			updatePerson.setString(4, num);
			updatePerson.setInt(5, id);

			// update the entry and return number of rows updated
			result = updatePerson.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}

		return result;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * deletePerson
	 * 
	 * @param id
	 * @return
	 */
	public int deletePerson(int id) {
		int result = 0;

		try {
			deletePerson.setInt(1, id);

			// delete the entry and return number of rows deleted
			result = deletePerson.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}

		return result;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getAllPeople
	 * 
	 * @return
	 */
	public List<Person> getAllPeople() {
		ArrayList<Person> results = null;
		ResultSet resultSet = null;

		try {
			resultSet = selectAllPeople.executeQuery();
			results = new ArrayList<Person>();

			while (resultSet.next()) {
				results.add(new Person(resultSet.getInt("addressID"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet
						.getString("email"), resultSet.getString("phoneNumber")));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			}
		}

		return results;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getPeopleByLastName
	 * 
	 * @param name
	 * @return
	 */
	public List<Person> getPeopleByLastName(String name) {
		ArrayList<Person> results = null;
		ResultSet resultSet = null;

		try {
			selectPeopleByLastName.setString(1, name);

			resultSet = selectPeopleByLastName.executeQuery();

			results = new ArrayList<Person>();

			while (resultSet.next()) {
				results.add(new Person(resultSet.getInt("addressID"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet
						.getString("email"), resultSet.getString("phoneNumber")));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			}
		}

		return results;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * close
	 * 
	 */
	public void close() {
		try {
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}