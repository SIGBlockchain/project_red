package sample;

import com.sun.jna.Native;
import javafx.application.Application;
import javafx.geometry.Insets;
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
        GuiGrid.setPadding(new Insets(10, 10, 10, 10));
        GuiGrid.setVgap(80);        // Vgap and Hgap are subject to change as project progresses
        GuiGrid.setHgap(80);

        //************************************** uncomment to see example of grid after commenting out the following 3 functions

//        for(int i = 0; i < 6; i++){
//            for(int j = 0; j < 4; j++){
//                Label l1 = new Label("test" + i);
//                GuiGrid.add(l1,i,j);
//            }
//        }
        //********************************************

        // add info row to main GridPane
        InfoRow.setupInfoRow(GuiGrid);

        //---------------------------------------------------------------------------------- awesome.go practice
//        Go.Awesome awesome = (Go.Awesome) Native.load(
//                "awesome", Go.Awesome.class);
//
//        System.out.printf("awesome.Add(12, 99) = %s\n", awesome.Add(12, 99));
//        System.out.printf("awesome.Cosine(1.0) = %s\n", awesome.Cosine(1.0));
        //----------------------------------------------------------------------------------------

        // add Send Aurum fields
        SendAurumRow.setupSendAurumRow(GuiGrid);

        // add last row
        MiscRow.setupMiscRow(GuiGrid);

        // return new Scene using GridPane GuiGrid with all of its additions
        return new Scene(GuiGrid,800,800);                                  // size of the scene is subject to change as well
    }
}