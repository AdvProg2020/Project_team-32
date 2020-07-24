package View.ManagerPage;

import Server.Model.Message;
import View.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BankLimitsController implements Initializable {
    @FXML
    private Button confirmWage;

    @FXML
    private Button confirmLimit;

    @FXML
    private TextField wageText;

    @FXML
    private TextField limitText;

    @FXML
    private Label wageLable;

    @FXML
    private Label limitLable;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Client.sendMessage("get wage", new HashMap<>());
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            wageLable.setText (String.valueOf(message.get("wage")));
        }

        Client.sendMessage("get limit", new HashMap<>());
        message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            limitLable.setText(String.valueOf(message.get("limit")));
        }
        confirmWage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String wage =wageText.getText().trim();
                HashMap<String,Object> input = new HashMap<>();
                input.put("wage",wage);
                Client.sendMessage("set wage", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    wageLable.setText(wage);
                }    else {
                    showError("wrong input");
                }
            }
        });
        confirmLimit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String limit = limitText.getText().trim();
                HashMap<String, Object> input = new HashMap<>();
                input.put("limit", limit);
                Client.sendMessage("set limit", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    wageLable.setText(limit);
                } else {
                    showError("wrong input");
                }
            }
        });
    }

    private void showError(String wage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(wage);
        alert.show();
    }
}
