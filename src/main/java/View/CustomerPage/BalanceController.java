package View.CustomerPage;

import Server.Controller.AccountController;
import Server.Controller.Controller;
import Server.Model.Customer;
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
import java.util.ResourceBundle;

public class BalanceController implements Initializable {
    public AnchorPane paneClass;
    @FXML
    private ImageView image;
    @FXML
    private Label balance;

    @FXML
    private TextField textBalance;

    @FXML
    private Button applyButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        balance.setOnMouseEntered(event -> Controller.sound(1));
        balance.setText(String.valueOf(((Customer) AccountController.loggedInUser).getCredit()));
        applyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Customer)AccountController.loggedInUser).setCredit(((Customer) AccountController.loggedInUser).getCredit()+Float.parseFloat(textBalance.getText()));
                balance.setText(String.valueOf(((Customer) AccountController.loggedInUser).getCredit()));
                System.out.println("wdhaifudbj");
            }
        });
    }
}
