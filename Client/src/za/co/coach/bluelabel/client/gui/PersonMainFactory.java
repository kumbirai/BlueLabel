/**
 * za.co.coach.bluelabel.client.gui<br>
 * 
 * <p><b>Title:</b> PersonMainFactory<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 7:58:12 PM
 */
package za.co.coach.bluelabel.client.gui;

import za.co.coach.bluelabel.client.gui.detail.PersonDetailPresenter;
import za.co.coach.bluelabel.client.gui.detail.PersonDetailView;
import za.co.coach.bluelabel.client.gui.main.MainPresenter;
import za.co.coach.bluelabel.client.gui.main.MainView;
import za.co.coach.bluelabel.client.gui.search.PersonSearchPresenter;
import za.co.coach.bluelabel.client.gui.search.PersonSearchView;
import za.co.coach.bluelabel.client.service.PersonService;
import za.co.coach.bluelabel.client.service.SimplePersonService;

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
 * <p><b>Title:</b> PersonMainFactory<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 7:58:12 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class PersonMainFactory {
	private MainPresenter mainPresenter;
	private PersonSearchPresenter personSearchPresenter;
	private PersonDetailPresenter personDetailPresenter;
	private PersonService personService;

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getMainPresenter
	 * 
	 * @return
	 */
	public MainPresenter getMainPresenter() {
		if (mainPresenter == null) {
			MainView view = new MainView();
			mainPresenter = new MainPresenter(view);
			mainPresenter.setPersonDetailPresenter(getPersonDetailPresenter());
			mainPresenter.setPersonSearchPresenter(getPersonSearchPresenter());
		}
		return mainPresenter;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getPersonSearchPresenter
	 * 
	 * @return
	 */
	public PersonSearchPresenter getPersonSearchPresenter() {
		if (personSearchPresenter == null) {
			PersonSearchView view = new PersonSearchView();
			personSearchPresenter = new PersonSearchPresenter(view, getMainPresenter(), getPersonService());
		}
		return personSearchPresenter;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getPersonDetailPresenter
	 * 
	 * @return
	 */
	public PersonDetailPresenter getPersonDetailPresenter() {
		if (personDetailPresenter == null) {
			PersonDetailView view = new PersonDetailView();
			personDetailPresenter = new PersonDetailPresenter(view, getMainPresenter(), getPersonService());
		}
		return personDetailPresenter;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getPersonService
	 * 
	 * @return
	 */
	public PersonService getPersonService() {
		if (personService == null) {
			personService = new SimplePersonService();
		}
		return personService;
	}
}