/**
 * za.co.coach.bluelabel.entities<br>
 * 
 * <p><b>Title:</b> Person<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 12:39:42 PM
 */
package za.co.coach.bluelabel.entities;

import java.io.Serializable;

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
 * <b>Title:</b> Person<br>
 * <b>Description:</b>
 * </p>
 * 
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 12:39:42 PM<br>
 * @version 1.0<br>
 * 
 *          <b>Revision:</b>
 * 
 */
public class Person implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -520227266724397858L;

	private int addressID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;

	/**
	 * 
	 */
	public Person() {
		super();
	}

	/**
	 * @param addressID
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 */
	public Person(int addressID, String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.addressID = addressID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Getter for the <code>addressID</code> attribute.
	 * 
	 * @return int - value of the attribute <code>addressID</code>.
	 */
	public int getAddressID() {
		return this.addressID;
	}

	/**
	 * Setter for the <code>addressID</code> attribute.
	 * 
	 * @param int addressID
	 */
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	/**
	 * Getter for the <code>firstName</code> attribute.
	 * 
	 * @return String - value of the attribute <code>firstName</code>.
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Setter for the <code>firstName</code> attribute.
	 * 
	 * @param String
	 *            firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the <code>lastName</code> attribute.
	 * 
	 * @return String - value of the attribute <code>lastName</code>.
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Setter for the <code>lastName</code> attribute.
	 * 
	 * @param String
	 *            lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for the <code>email</code> attribute.
	 * 
	 * @return String - value of the attribute <code>email</code>.
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Setter for the <code>email</code> attribute.
	 * 
	 * @param String
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for the <code>phoneNumber</code> attribute.
	 * 
	 * @return String - value of the attribute <code>phoneNumber</code>.
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Setter for the <code>phoneNumber</code> attribute.
	 * 
	 * @param String
	 *            phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Person [addID=%d, FNum=%s, LNme=%s, email=%s, PNum=%s]\n", this.addressID, this.firstName, this.lastName, this.email,
				this.phoneNumber);
	}
}