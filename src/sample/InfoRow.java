package sample;

import javafx.scene.layout.GridPane;

// 1st row of GUI pertains to the drawn out vision of GUI.
public class InfoRow {

    // sets up row 1 of the GUI will modify the GridPane
    // everything that is going to be set into the grid, add it to mainGrid
    public static void setupInfoRow(GridPane mainGrid){
        // Labels for text fields
        Text walletLabel                   = new Text("Aurum Wallet");
        Text balanceLabel                  = new Text("Balance");
        Text stateNonceLabel               = new Text("State Nonce");
        Text producerMessageLabel          = new Text("Message from Producer");

        // text fields
        Textfield walletTextfield          = new TextField();
        Textfield balanceTextfield         = new Textfield();
        Textfield stateNonceTextfield      = new Textfield();
        Textfield producerMessageTextfield = new Textfield();

        // add all to grid
        mainGrid.add(walletLabel, 0, 0);
        mainGrid.add(walletTextfield, 1, 0);
        mainGrid.add(balanceLabel, 0, 1);
        mainGrid.add(balanceTextfield, 1, 1);
        mainGrid.add(stateNonceLabel, 0, 2);
        mainGrid.add(stateNonceTextfield, 1, 2);
        mainGrid.add(producerMessageLabel, 0, 3);
        mainGrid.add(producerMessageTextfield, 1, 3);
    }
}