package sigblockchain.projectred.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

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
        System.out.println("The Block Chain Account GUI is open now.");
    }
}
