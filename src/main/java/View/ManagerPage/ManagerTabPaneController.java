package View.ManagerPage;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            URL infoPaneUrl = new File("src\\main\\resources\\GUIFiles\\personal-info.fxml").toURI().toURL();
            infoTab.setContent(FXMLLoader.load(infoPaneUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
