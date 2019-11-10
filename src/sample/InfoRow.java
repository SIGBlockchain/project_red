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
    static Label walletTextfield;
    static Label stateNonceTextfield;
    static Label balanceTextfield;
    static Label producerMessageTextfield;

    // sets up row 1 of the GUI will modify the GridPane
    // everything that is going to be set into the grid, add it to mainGrid
    public static void setupInfoRow(GridPane mainGrid){
        // Labels for text fields
        Text walletLabel                   = new Text("Aurum Wallet");
        Text balanceLabel                  = new Text("Balance");
        Text stateNonceLabel               = new Text("State Nonce");
        Text producerMessageLabel          = new Text("Message from Producer");

        // text fields
        walletTextfield              = new Label("NONE");
        stateNonceTextfield          = new Label("NONE");
        balanceTextfield             = new Label("NONE");
        producerMessageTextfield     = new Label("CANNOT GET INFO");

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

    public static void RunClientExe(String param1, String param2, String param3, String param4){
        String cmd = System.getProperty("user.dir") + "\\aurum_client.exe";
        try{
            System.out.println("cmd: "+ cmd + " param 1: " + param1 +"\n\n");
            Process process;

            // attempting a transaction
            if(param1.compareTo("--send") == 0 && param3.compareTo("--to") == 0){
                System.out.printf("want to send %s to %s \n\n", param2, param4);
                process = new ProcessBuilder(cmd,param1,param2,param3,param4).start();
            }

            // else info or update so no need to use the param2,3, or 4
            else{
                process = new ProcessBuilder(cmd,param1).start();
            }

            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = "";

            switch(param1){
                case "--info":
                    if(br.read() == -1){
                        System.out.println("Failed to get wallet contents");
                        producerMessageTextfield.setText("Failed to get wallet contents");
                    }
                    while((line = br.readLine()) != null) {
                        System.out.println(line);
                        String[] strArr = line.split(": ");
                        clientInfo.add(strArr[1]);
                    }
                    // display values
                    walletTextfield.setText(clientInfo.get(0));
                    stateNonceTextfield.setText(clientInfo.get(1));
                    balanceTextfield.setText(clientInfo.get(2));
                    producerMessageTextfield.setText("STATUS: OK");
                    break;
                case "--update":
                    RunClientExe("--info",null,null,null);         // call for info to be displayed
                    break;
                case "--send":
                    while((line = br.readLine()) != null){
                        System.out.println(line);
                    }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}