package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

// 3rd row that will be used for messages from producer and future additions
public class MiscRow {

    public static void setupMiscRow(GridPane grid){
        // Labels for text fields


        // text fields

        // update button
        Button updateBtn = new Button("Update");
        grid.add(updateBtn, 1,2);

    }
}