package View.SupporterPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupporterTabPaneController {
    public Tab customerTab;
    public Tab logoutTab;

    public void initialize(URL location, ResourceBundle resources) {
        try {

            //set URLs
            URL customerUrl = new File("src\\main\\resources\\GUIFiles\\personal-info.fxml").toURI().toURL();
            URL logoutUrl = new File("src\\main\\resources\\GUIFiles\\logout-page.fxml").toURI().toURL();

            //set tab's contents
            customerTab.setContent(FXMLLoader.load(customerUrl));
            logoutTab.setContent(FXMLLoader.load(logoutUrl));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}