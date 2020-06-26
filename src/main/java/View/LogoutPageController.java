package View;

import Controller.AccountController;
import Controller.Controller;
import View.BossPage.RequestManagerMenus.ShowRequestDetailCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LogoutPageController {
    public void signOut(ActionEvent actionEvent) {

        AccountController.logout();
        try {
            URL url = new File("src\\main\\resources\\GUIFiles\\main-page.fxml").toURI().toURL();
            Parent parent = FXMLLoader.load(url);
            Main.primaryStage.setScene(new Scene(parent));
        } catch (IOException e) {
            System.err.println("error loading files.");
        }

    }
}
