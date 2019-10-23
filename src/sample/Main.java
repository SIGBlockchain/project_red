package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public GridPane GuiGrid = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Aurum Project Red");
        primaryStage.setScene(MainWindowSetup());
        primaryStage.show();
    }

    Scene MainWindowSetup(){
        // add info row to main GridPane
        InfoRow.setupInfoRow(GuiGrid);

        // add Send Aurum fields
        SendAurumRow.setupSendAurumRow(GuiGrid);

        // add last row
        MiscRow.setupMiscRow(GuiGrid);

        // return new Scene using GridPane GuiGrid with all of its additions
        return new Scene(GuiGrid,800,800);
    }


}