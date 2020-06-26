package View;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public Tab registerTab;
    public Tab loginTab;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //set URLs
            URL registerUrl = new File("src\\main\\resources\\GUIFiles\\RegisterPage.fxml").toURI().toURL();
            URL loginUrl = new File("src\\main\\resources\\GUIFiles\\LoginPage.fxml").toURI().toURL();

            //set contents
            registerTab.setContent(FXMLLoader.load(registerUrl));
            loginTab.setContent(FXMLLoader.load(loginUrl));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
