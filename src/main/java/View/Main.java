package View;

import Controller.Controller;
import Model.Category;
import Model.Good;
import Model.Person;
import Model.Seller;
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
        URL url = new File("src\\main\\resources\\GUIFiles\\SellerMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("helloWorld");
        primaryStage.setScene(new Scene(root,788,688));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        new Good("khiyar","123",new Seller("ali","123"),"salma",new Category("hey",null,null),null,null,0);
        launch(args);

    }

}
