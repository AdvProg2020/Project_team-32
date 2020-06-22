package View.goodsPage;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class GoodsTabPaneController implements Initializable {

    @FXML
    private Tab returnToMainMenuTab;

    @FXML
    private Tab viewCategoriesTab;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            URL viewCategoriesPage = new File("src\\main\\resources\\GUIFiles\\ViewCategoriesPage.fxml").toURI().toURL();
            viewCategoriesTab.setContent(FXMLLoader.load(viewCategoriesPage));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
