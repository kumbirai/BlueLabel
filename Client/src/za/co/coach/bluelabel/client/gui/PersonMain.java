/**
 * za.co.coach.bluelabel.client.gui<br>
 * 
 * <p><b>Title:</b> PersonMain<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 7:56:41 PM
 */
package za.co.coach.bluelabel.client.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import za.co.coach.bluelabel.client.gui.main.MainPresenter;

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
 * <p><b>Title:</b> PersonMain<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 7:56:41 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class PersonMain extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		PersonMainFactory factory = new PersonMainFactory();
		MainPresenter mainPresenter = factory.getMainPresenter();
		mainPresenter.showSearchPersons(true);
		Scene scene = new Scene(mainPresenter.getView(), 800, 600);
		scene.getStylesheets().addAll(PersonMain.class.getResource("styles.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Contact Management");
		stage.show();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

}
