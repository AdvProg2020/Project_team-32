package View.CustomerPage;

import Server.Controller.AccountController;
import Server.Controller.Controller;
import Server.Model.Customer;
import Server.Model.Message;
import Server.Server;
import View.BankServer;
import View.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static View.CustomerPage.PurchaseControllerFXML.handleAccountID;

public class BalanceController implements Initializable {
    public AnchorPane paneClass;
    @FXML
    private ImageView image;
    @FXML
    private Label balance;

    @FXML
    private TextField textBalance;
    @FXML
    private  Button takeMoneyButton;
    @FXML
    private Button addButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        balance.setOnMouseEntered(event -> Controller.sound(1));
        if (AccountController.loggedInUser instanceof Customer){
            takeMoneyButton.setVisible(false);
        }
        HashMap<String, Object> input = new HashMap<>();
        Client.sendMessage("getCredit", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            balance.setText(String.valueOf((float)message.get("credit")));
        }
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                float[] price = new float[1];
                price[0] = Float.parseFloat(textBalance.getText().trim());
                PurchaseControllerFXML purchaseControllerFXML = new PurchaseControllerFXML();
                String result =   purchaseControllerFXML.bankServer(price,"withdraw");
                if (result.equals("done successfully")){
                    HashMap<String, Object> input = new HashMap<>();
                    input.put("price",price[0]);
                    Client.sendMessage("transfer from bank to purse", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("you added money to your purse");
                        alert.show();
                        balance.setText(String.valueOf((float)message.get("credit")));
                    }
                }
                    //todo bank server get and send
            }
        });
        takeMoneyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                float[] price = new float[1];
                price[0] = Float.parseFloat(textBalance.getText().trim());
                PurchaseControllerFXML purchaseControllerFXML = new PurchaseControllerFXML();
                String result =   purchaseControllerFXML.bankServer(price,"deposit");
                if (result.equals("done successfully")){
                    HashMap<String, Object> input = new HashMap<>();
                    input.put("price",price[0]);
                    Client.sendMessage("transfer from purse to bank", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("you picked money from your purse");
                        alert.show();
                        balance.setText(String.valueOf((float)message.get("credit")));
                    }else {
                        System.out.println("you dont have enough reamining money in  your purse");
                    }
                }
                //todo bank server get and send
            }
        });
    }
}
