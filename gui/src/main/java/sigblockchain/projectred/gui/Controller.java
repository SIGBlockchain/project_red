package sigblockchain.projectred.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

	private static final Logger LOG = LogManager.getLogger(Controller.class.getName());

	@FXML
	private GridPane transactionsPane;

	@FXML
	private StackPane stack;

	@FXML
	private Label title;

	@FXML
	private TextField balanceView;

	@FXML
	private TextField addressView;

	@FXML
	private Button refresh;

	@FXML
	private void start(ActionEvent event) {
		LOG.info("Starting controller");
		System.out.println("Starting controller");
	}
}
