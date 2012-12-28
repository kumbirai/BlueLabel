/**
 * za.co.coach.bluelabel.client.gui.search<br>
 * 
 * <p><b>Title:</b> PersonSearchPresenter<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 8:17:51 PM
 */
package za.co.coach.bluelabel.client.gui.search;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import za.co.coach.bluelabel.client.gui.main.MainPresenter;
import za.co.coach.bluelabel.client.service.PersonService;
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
 * <p><b>Title:</b> PersonSearchPresenter<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 8:17:51 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class PersonSearchPresenter {
	private PersonSearchView view;
	private MainPresenter mainPresenter;
	private PersonService personService;

	/**
	 * @param view
	 * @param mainPresenter
	 * @param personService
	 */
	public PersonSearchPresenter(PersonSearchView view, MainPresenter mainPresenter, PersonService personService) {
		this.view = view;
		this.mainPresenter = mainPresenter;
		this.personService = personService;
		this.view.setPresenter(this);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * getView
	 * 
	 * @return
	 */
	public PersonSearchView getView() {
		return view;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * search
	 * 
	 * @param search
	 */
	public void search(final boolean search) {
		final Task<List<Person>> searchTask = new Task<List<Person>>() {
			protected List<Person> call() throws Exception {
				return personService.displayAll(search);
			}
		};

		searchTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
			public void changed(ObservableValue<? extends Worker.State> source, Worker.State oldState, Worker.State newState) {
				if (newState.equals(Worker.State.SUCCEEDED)) {
					view.setSearchResults(searchTask.getValue());
				}
			}
		});

		new Thread(searchTask).start();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * newPerson
	 * 
	 */
	public void newPerson() {
		mainPresenter.showPersonDetail(null);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * personSelected
	 * 
	 * @param personId
	 */
	public void personSelected(Integer personId) {
		mainPresenter.showPersonDetail(personId);
	}
}