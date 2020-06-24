package View.CustomerPage;

import Controller.AccountController;
import Model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BalanceController implements Initializable {

    @FXML
    private Label balance;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        balance.setText(String.valueOf(((Customer) AccountController.loggedInUser).getCredit()));
    }
}
