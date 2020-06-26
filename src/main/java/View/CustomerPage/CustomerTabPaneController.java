package View.CustomerPage;

import Controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerTabPaneController implements Initializable {
    public TabPane tabPane;
    public Tab  userMangerTab;
    public Tab cartTab;
    public Tab ordersTab;
    public Tab balanceTab;
    public Tab discountsTab;
    public Tab logOutTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URL infoPaneUrl = null;
        URL cartPageURL = null;
        URL ordersPageURL =null;
        URL balancePageURl =null;
        URL discountsPageURL =null;
        URL logOutURL = null;
        try {
            infoPaneUrl = new File("src\\main\\resources\\GUIFiles\\personal-info.fxml").toURI().toURL();
            cartPageURL = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\CartPage.fxml").toURI().toURL();
            ordersPageURL = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\OrdersPage.fxml").toURI().toURL();
            balancePageURl = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\BalancePage.fxml").toURI().toURL();
            discountsPageURL = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\DiscountsPage.fxml").toURI().toURL();
            logOutURL = new File("src\\main\\resources\\GUIFiles\\logout-page.fxml").toURI().toURL();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URL finalInfoPaneUrl = infoPaneUrl;
        URL finalCartPageURL = cartPageURL;
        URL finalOrdersPageURL = ordersPageURL;
        URL finalBalancePageURl = balancePageURl;
        URL finalDiscountsPageURL = discountsPageURL;
        URL finalLogOut = logOutURL;
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {;
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {

                try {
                    if (newTab.equals(userMangerTab)){
                        userMangerTab.setContent(FXMLLoader.load(finalInfoPaneUrl));
                    }
                    else if (newTab.equals(cartTab)){
                        cartTab.setContent(FXMLLoader.load(finalCartPageURL));
                    }
                    else if (newTab.equals(ordersTab)){
                        ordersTab.setContent(FXMLLoader.load(finalOrdersPageURL));
                    }
                    else if (newTab.equals(balanceTab)){
                        balanceTab.setContent(FXMLLoader.load(finalBalancePageURl));
                    }
                    else if (newTab.equals(discountsTab)){
                        discountsTab.setContent(FXMLLoader.load(finalDiscountsPageURL));
                    }else if (newTab.equals(logOutTab)){
                        logOutTab.setContent(FXMLLoader.load(finalLogOut));
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
