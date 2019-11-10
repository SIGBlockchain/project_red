package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

// 2nd row of the GUI.
public class SendAurumRow {

    // sets up row 2 of GUI
    // everything that is going to be set into the grid, add it to mainGrid
    public static void setupSendAurumRow(GridPane mainGrid){
        // Labels for text fields
        Text sendTo = new Text("Send To");
        Text amount = new Text("Amount");

        // text fields
        TextField sendToField = new TextField();
        TextField amountField = new TextField();

        // button
        Button send = new Button("Send");

        send.setOnAction((event) -> {
            InfoRow.RunClientExe("--send",amountField.getText(),"--to",sendToField.getText());
        });

        // add to mainGrid
        mainGrid.add(sendTo, 0, 2);
        mainGrid.add(sendToField, 0, 3);
        mainGrid.add(amount, 1, 2);
        mainGrid.add(amountField, 1, 3);
        mainGrid.add(send, 2, 3);
    }

}