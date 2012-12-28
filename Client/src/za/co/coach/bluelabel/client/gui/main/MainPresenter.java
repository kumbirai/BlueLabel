/**
 * za.co.coach.bluelabel.client.gui.main<br>
 * 
 * <p><b>Title:</b> MainPresenter<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 8:05:54 PM
 */
package za.co.coach.bluelabel.client.gui.main;

import za.co.coach.bluelabel.client.gui.detail.PersonDetailPresenter;
import za.co.coach.bluelabel.client.gui.search.PersonSearchPresenter;

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
 * <p><b>Title:</b> MainPresenter<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 8:05:54 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class MainPresenter {
	private MainView view;
	private PersonSearchPresenter personSearchPresenter;
	private PersonDetailPresenter personDetailPresenter;

	/**
	 * @param view
	 */
	public MainPresenter(MainView view) {
		this.view = view;
		this.view.setPresenter(this);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setView
	 * 
	 * @param view
	 */
	public void setView(MainView view) {
		this.view = view;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setPersonSearchPresenter
	 * 
	 * @param personSearchPresenter
	 */
	public void setPersonSearchPresenter(PersonSearchPresenter personSearchPresenter) {
		this.personSearchPresenter = personSearchPresenter;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setPersonDetailPresenter
	 * 
	 * @param personDetailPresenter
	 */
	public void setPersonDetailPresenter(PersonDetailPresenter personDetailPresenter) {
		this.personDetailPresenter = personDetailPresenter;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getView
	 * 
	 * @return
	 */
	public MainView getView() {
		return view;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * showSearchPersons
	 * 
	 * @param search
	 */
	public void showSearchPersons(boolean search) {
		personSearchPresenter.search(search);
		view.setContent(personSearchPresenter.getView());
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * showPersonDetail
	 * 
	 * @param personId
	 */
	public void showPersonDetail(Integer personId) {
		personDetailPresenter.setPerson(personId);
		view.setContent(personDetailPresenter.getView());
	}
}