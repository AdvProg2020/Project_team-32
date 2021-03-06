package View.ManagerPage;

import com.sun.jndi.toolkit.url.Uri;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerTabPaneController implements Initializable {
    public Tab infoTab;
    public Tab userMangerTab;
    public Tab productManagerTab;
    public Tab discountManagerTab;
    public Tab requestManagerTab;
    public Tab categoryManagerTab;
    public Tab logoutTab;
    public Tab bankLimitsTab;
    public Tab buyLogManagerTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            //set URLs
            URL infoPaneUrl = new File("src\\main\\resources\\GUIFiles\\personal-info.fxml").toURI().toURL();
            URL userManagerUrl = new File("src\\main\\resources\\GUIFiles\\user-manager-page.fxml").toURI().toURL();
            URL productManagerUrl = new File("src\\main\\resources\\GUIFiles\\product-manager-page.fxml").toURI().toURL();
            URL discountMangerUrl = new File("src\\main\\resources\\GUIFiles\\discount-manager-page.fxml").toURI().toURL();
            URL categoryManagerUrl = new File("src\\main\\resources\\GUIFiles\\category-manager.fxml").toURI().toURL();
            URL requestManagerUrl = new File("src\\main\\resources\\GUIFiles\\request-manager-page.fxml").toURI().toURL();
            URL logoutUrl = new File("src\\main\\resources\\GUIFiles\\logout-page.fxml").toURI().toURL();
            URL bankLimitsUrl = new File("src\\main\\resources\\GUIFiles\\BankLimits.fxml").toURI().toURL();
            URL buyLogManagerUrl = new File("src/main/resources/GUIFiles/ManagerMenu/buyLog-manager-page.fxml").toURI().toURL();


            //set tab's contents
            discountManagerTab.setContent(FXMLLoader.load(discountMangerUrl));
            productManagerTab.setContent(FXMLLoader.load(productManagerUrl));
            infoTab.setContent(FXMLLoader.load(infoPaneUrl));
            userMangerTab.setContent(FXMLLoader.load(userManagerUrl));
            categoryManagerTab.setContent(FXMLLoader.load(categoryManagerUrl));
            requestManagerTab.setContent(FXMLLoader.load(requestManagerUrl));
            logoutTab.setContent(FXMLLoader.load(logoutUrl));
            bankLimitsTab.setContent(FXMLLoader.load(bankLimitsUrl));
            buyLogManagerTab.setContent(FXMLLoader.load(buyLogManagerUrl));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
