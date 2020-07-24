package View.CustomerPage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SingleAuctionController implements Initializable {
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
                // todo add thi price to max price
            }
        });
        chatPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //todo open this individual auction chat  page
            }
        });

    }
}
