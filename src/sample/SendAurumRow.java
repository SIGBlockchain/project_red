package sample;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

// 2nd row of the GUI.
public class SendAurumRow {

    // sets up row 2 of GUI
    // everything that is going to be set into the grid, add it to mainGrid


    public static void setupSendAurumRow(GridPane grid){
//        grid grid = new grid();
        // Labels for text fields
        Text sendTo = new Text("Send To");
        Text amount = new Text("Amount");
        // text fields
        TextField sendToField = new TextField();
        TextField amountField = new TextField();
        // button
        Button send = new Button("Send");
        //Add everything to pane
        grid.add(sendTo, 0, 0);
        grid.add(sendToField, 0, 1);
        grid.add(amount, 1, 0);
        grid.add(amountField, 1, 1);
        grid.add(send, 2, 1);


        // add to mainGrid
    }

}