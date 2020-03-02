package sigblockchain.projectred.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

	private static final Logger LOG = LogManager.getLogger(Controller.class.getName());

	@FXML
	private StackPane stack;

	@FXML
	private Pane accountsPane;

	@FXML
	private Pane keysPane;

	@FXML
	private void navToAccounts(ActionEvent event) {
		LOG.info("Switching to Accounts Pane");
		System.out.println("Starting controller");
		var children = stack.getChildren();
		children.forEach(node -> {
			node.setVisible(false);
			node.setDisable(true);
		});

		accountsPane.setVisible(true);
		accountsPane.setDisable(false);
	}

	@FXML
	private void navToKeysPane(ActionEvent event) {
		LOG.info("Switching to keys Pane");
		System.out.println("Starting controller");
		var children = stack.getChildren();
		children.forEach(node -> {
			node.setVisible(false);
			node.setDisable(true);
		});

		keysPane.setVisible(true);
		keysPane.setDisable(false);

	}
}
