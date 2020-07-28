package View.CustomerPage;

import Server.Model.Chat.ChatBox;
import Server.Model.Message;
import View.ChatPage.GUIModels.ChatStage;
import View.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.AbstractList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SingleAuctionController implements Initializable {
    public ImageView img;
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

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                HashMap<String,Object> inout = new HashMap<>();
                inout.put("ID" ,ID);
                Client.sendMessage("update auction", inout);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")){
                    String s = String.valueOf(message.get("price"));
                    currentPrice.setText(s);
                }
                else if (message.get("status").equals("auction ended")){
                    currentPrice.setText("this auction ended");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        confirmPrice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String in =newPrice.getText().trim();
                if (in.matches("\\d+")){
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("credit",Integer.parseInt(in));
                    hashMap.put("id",getID());
                    Client.sendMessage("set auction credit",hashMap);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")){
                        currentPrice.setText(String.valueOf(message.get("credit")));
                    }else if (message.get("status").equals("you don't have enough money in your purse")){
                        showError("you don't have enough money in your purse");
                    }else if (message.get("status").equals("you entered less than others")){
                        currentPrice.setText(String.valueOf(message.get("credit")));
                        showError("you entered less than others");
                    }
                }else {
                    showError("wrong input");
                }
            }
        });
        chatPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HashMap<String,Object> input = new HashMap<>();
                input.put("id",getID());
                Client.sendMessage("get chatbox auction",input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")){
                    ChatBox chatBox = (ChatBox) message.get("chatbox");
                    new ChatStage(chatBox).show();
                }else if (message.get("status").equals("something went wrong")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("something went wrong");
                    alert.show();
                }
            }
        });

    }

    private void showError(String value) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(value);
        alert.show();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
