package View;

import Controller.*;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        URL url = new File("src\\main\\resources\\GUIFiles\\manager-tab-pane.fxml").toURI().toURL();
        //URL url = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\CustomerPage.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("helloWorld");
        primaryStage.setScene(new Scene(root,788,688));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        new Good("khiyar","123",new Seller("alii","123"),"salma",new Category("hey",null,Category.rootCategory),null,null,0);
        new Customer("ali","123");
        new Customer("ioo","234");
        System.out.println(BossController.getAllDiscount());
        launch(args);
    }

}
