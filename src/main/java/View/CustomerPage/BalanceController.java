package View.CustomerPage;

import Server.Controller.AccountController;
import Server.Controller.Controller;
import Server.Model.Customer;
import Server.Model.Message;
import View.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
//                ((Customer)AccountController.loggedInUser).setCredit(((Customer) AccountController.loggedInUser).getCredit()+Float.parseFloat(textBalance.getText()));
//                balance.setText(String.valueOf(((Customer) AccountController.loggedInUser).getCredit()));
//                System.out.println("wdhaifudbj");
                //todo bank server get and send
            }
        });
        takeMoneyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //todo bank server get and send
            }
        });
    }
}
