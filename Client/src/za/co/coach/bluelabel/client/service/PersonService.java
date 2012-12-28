/**
 * za.co.coach.bluelabel.client.service<br>
 * 
 * <p><b>Title:</b> PersonService<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 8:22:48 PM
 */
package za.co.coach.bluelabel.client.service;

import java.util.List;

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
 * <p><b>Title:</b> PersonService<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 8:22:48 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public interface PersonService {
	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * displayAll
	 * 
	 * @param search
	 * @return
	 */
	List<Person> displayAll(boolean search);

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * addPerson
	 * 
	 * @param person
	 * @return
	 */
	Person addPerson(Person person);

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * updatePerson
	 * 
	 * @param person
	 * @return
	 */
	Person updatePerson(Person person);

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * deletePerson
	 * 
	 * @param person
	 * @return
	 */
	Person deletePerson(Person person);

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getPerson
	 * 
	 * @param contactId
	 * @return
	 */
	Person getPerson(Integer contactId);
}