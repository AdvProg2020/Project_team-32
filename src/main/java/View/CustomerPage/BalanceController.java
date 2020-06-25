package View.CustomerPage;

import Controller.AccountController;
import Model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class BalanceController implements Initializable {
    public AnchorPane paneClass;
    @FXML
    private ImageView image;
    @FXML
    private Label balance;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        balance.setText(String.valueOf(((Customer) AccountController.loggedInUser).getCredit()));
    }
}
