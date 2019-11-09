package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// 1st row of GUI pertains to the drawn out vision of GUI.
public class InfoRow {

    static ArrayList<String> clientInfo = new ArrayList<>();     // stores the user info

    // sets up row 1 of the GUI will modify the GridPane
    // everything that is going to be set into the grid, add it to mainGrid
    public static void setupInfoRow(GridPane mainGrid){
        // Labels for text fields
        Text walletLabel                   = new Text("Aurum Wallet");
        Text balanceLabel                  = new Text("Balance");
        Text stateNonceLabel               = new Text("State Nonce");
        Text producerMessageLabel          = new Text("Message from Producer");

        // text fields
        Label walletTextfield              = new Label(clientInfo.get(0));
        Label stateNonceTextfield          = new Label(clientInfo.get(1));
        Label balanceTextfield             = new Label(clientInfo.get(2));
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

    public static void runClientExe(String param1){
        String cmd = System.getProperty("user.dir") + "\\aurum_client.exe";
        try{
            System.out.println("cmd: "+ cmd + " param 1: " + param1);
            Process process = new ProcessBuilder(cmd,param1).start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;

            // we get the info in the order of
            // wallet address
            // state nonce
            // balance
            while((line = br.readLine()) != null){
                System.out.println(line);
                String[] strArr = line.split(": ");
                clientInfo.add(strArr[1]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}