package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

// 1st row of GUI pertains to the drawn out vision of GUI.
public class InfoRow {

    // sets up row 1 of the GUI will modify the GridPane
    // everything that is going to be set into the grid, add it to mainGrid
    public static void setupInfoRow(GridPane mainGrid) {
        // Labels for text fields
        Text walletLabel = new Text("Aurum Wallet");
        Text balanceLabel = new Text("Balance");
        Text stateNonceLabel = new Text("State Nonce");
        Text producerMessageLabel = new Text("Message from Producer");

        // text fields
        TextField walletTextfield = new TextField();
        TextField balanceTextfield = new TextField();
        TextField stateNonceTextfield = new TextField();
        TextField producerMessageTextfield = new TextField();

        // add all to grid
        mainGrid.add(walletLabel, 0, 0);
        mainGrid.add(walletTextfield, 0, 1);
        mainGrid.add(balanceLabel, 1, 0);
        mainGrid.add(balanceTextfield, 1, 1);
        mainGrid.add(stateNonceLabel, 2, 0);
        mainGrid.add(stateNonceTextfield, 2, 1);
        mainGrid.add(producerMessageLabel, 0, 7);
        mainGrid.add(producerMessageTextfield, 0, 8);
    }
}