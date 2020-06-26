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
import java.util.HashMap;

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
        Controller.initialize();
//        Boss boss = new Boss("1","1");
//        Controller.isBossCreated =true;
//        Seller seller = new Seller("2","2");
//        ArrayList<String> properties = new ArrayList<>();
//        properties.add("brand");
//        Good sib = new Good("sibfruit","sib",seller,"comp",
//                new Category("x",properties,Category.rootCategory),"chert",null,100);
//        Good.confirmedGoods.add(sib);
//        seller.getSellingGoods().add(sib);

        launch(args);

    }

}
