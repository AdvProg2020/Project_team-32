package View.CustomerPage;

import Server.Model.Message;
import View.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SingleAuctionController implements Initializable {
    private String ID ;
    @FXML
    private Label currentPrice;

    @FXML
    private TextField newPrice;

    @FXML
    private Button confirmPrice;

    @FXML
    private Button chatPage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //todo reset  currentprice by time
            }
        }).start();
        confirmPrice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int price =Integer.parseInt(newPrice.getText());
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("credit",price);
                hashMap.put("id",getID());
                Client.sendMessage("set auction credit",hashMap);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")){
                    currentPrice.setText(String.valueOf(message.get("credit")));
                }else {

                }
            }
        });
        chatPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //todo with maziyar

            }
        });

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
