package View;

import Server.Controller.*;
import Server.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
//        URL url = new File("src\\main\\resources\\GUIFiles\\manager-tab-pane.fxml").toURI().toURL();
        URL url = new File("src\\main\\resources\\GUIFiles\\main-page.fxml").toURI().toURL();
  //      URL url = new File("src\\main\\resources\\GUIFiles\\Seller-fxml-pages\\SellerMenu.fxml").toURI().toURL();
//        URL url = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\CustomerPage.fxml").toURI().toURL();

        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("helloWorld");
        primaryStage.setScene(new Scene(root,788,688));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Controller.importData();
        System.out.println(Person.allPersons);
        Controller.initialize();
        launch(args);
        Controller.exportData();
    }

}
