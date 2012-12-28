/**
 * za.co.coach.bluelabel.client.gui.main<br>
 * 
 * <p><b>Title:</b> MainView<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Copyright (c) 2012<br>
 * @author Kumbirai 'Coach' Mundangepfupfu
 * @date 12 Nov 2012 8:11:26 PM
 */
package za.co.coach.bluelabel.client.gui.main;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
 * <p><b>Title:</b> MainView<br>
 * <b>Description:</b> </p>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 12 Nov 2012 8:11:26 PM<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 *					
 */
public class MainView extends BorderPane {
	private MainPresenter presenter;
	private BorderPane contentArea;

	/**
	 * 
	 */
	public MainView() {
		buildView();
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setPresenter
	 * 
	 * @param presenter
	 */
	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * setContent
	 * 
	 * @param content
	 */
	public void setContent(Node content) {
		contentArea.setCenter(content);
	}

	/**
	 * @author Kumbirai 'Coach' Mundangepfupfu - 12 Nov 2012
	 * 
	 * buildView
	 * 
	 */
	protected void buildView() {
		VBox topArea = new VBox(5);
		topArea.getStyleClass().add("header");

		Label titleLabel = new Label("Contacts");
		titleLabel.getStyleClass().add("title");
		topArea.getChildren().add(titleLabel);

		Label tagLine = new Label("Contact Management System");
		tagLine.getStyleClass().add("tag-line");
		topArea.getChildren().add(tagLine);

		setTop(topArea);

		// we use this contentPane just so we can add style (i.e. padding)
		contentArea = new BorderPane();
		contentArea.getStyleClass().add("body");
		setCenter(contentArea);
	}
}