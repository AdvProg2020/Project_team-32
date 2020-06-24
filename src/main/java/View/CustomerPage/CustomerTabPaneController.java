package View.CustomerPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerTabPaneController implements Initializable {

    public Tab  userMangerTab;
    public Tab cartTab;
    public Tab ordersTab;
    public Tab balanceTab;
    public Tab discountsTab;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URL infoPaneUrl = null;
        URL cartPageURL = null;
        URL ordersPageURL =null;
        URL balancePageURl =null;
        URL discountsPageURL =null;
        try {
            infoPaneUrl = new File("src\\main\\resources\\GUIFiles\\personal-info.fxml").toURI().toURL();
            cartPageURL = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\CartPage.fxml").toURI().toURL();
            ordersPageURL = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\OrdersPage.fxml").toURI().toURL();
            balancePageURl = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\BalancePage.fxml").toURI().toURL();
            discountsPageURL = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\DiscountsPage.fxml").toURI().toURL();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            userMangerTab.setContent(FXMLLoader.load(infoPaneUrl));
            cartTab.setContent(FXMLLoader.load(cartPageURL));
            ordersTab.setContent(FXMLLoader.load(ordersPageURL));
            balanceTab.setContent(FXMLLoader.load(balancePageURl));
            discountsTab.setContent(FXMLLoader.load(discountsPageURL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
