/**
 * za.co.coach.bluelabel.client.gui.detail<br>
 * 
 * <p><b>Title:</b> PersonDetailPresenter<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 8:12:31 PM
 */
package za.co.coach.bluelabel.client.gui.detail;

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
 * <p><b>Title:</b> PersonDetailPresenter<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 8:12:31 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class PersonDetailPresenter {
	private PersonDetailView view;
	private PersonService personService;
	private MainPresenter mainPresenter;

	public PersonDetailPresenter(PersonDetailView view, MainPresenter mainPresenter, PersonService personService) {
		this.view = view;
		this.mainPresenter = mainPresenter;
		this.personService = personService;
		this.view.setPresenter(this);
	}

	public PersonDetailView getView() {
		return view;
	}

	public void setPerson(final Integer contactId) {
		view.setPerson(null);
		final Task<Person> loadTask = new Task<Person>() {
			protected Person call() throws Exception {
				return personService.getPerson(contactId);
			}
		};

		loadTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
			public void changed(ObservableValue<? extends Worker.State> source, Worker.State oldState, Worker.State newState) {
				if (newState.equals(Worker.State.SUCCEEDED)) {
					view.setPerson(loadTask.getValue());
				}
			}
		});

		new Thread(loadTask).start();
	}

	public void cancel() {
		mainPresenter.showSearchPersons(false);
	}

	public void save() {
		final Person updatedPerson = view.getPerson();
		final Task<Person> saveTask = new Task<Person>() {
			protected Person call() throws Exception {
				if (updatedPerson.getAddressID() != 0)
					return personService.updatePerson(updatedPerson);
				else
					return personService.addPerson(updatedPerson);
			}
		};

		saveTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
			public void changed(ObservableValue<? extends Worker.State> source, Worker.State oldState, Worker.State newState) {
				if (newState.equals(Worker.State.SUCCEEDED)) {
					mainPresenter.showSearchPersons(false);
				}
			}
		});

		new Thread(saveTask).start();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * delete
	 * 
	 */
	public void delete() {
		final Person selectedPerson = view.getPerson();
		final Task<Person> deleteTask = new Task<Person>() {
			protected Person call() throws Exception {
				return personService.deletePerson(selectedPerson);
			}
		};

		deleteTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
			public void changed(ObservableValue<? extends Worker.State> source, Worker.State oldState, Worker.State newState) {
				if (newState.equals(Worker.State.SUCCEEDED)) {
					mainPresenter.showSearchPersons(false);
				}
			}
		});

		new Thread(deleteTask).start();
	}
}
