package View.goodsPage;

import Controller.AccountController;
import Model.Customer;
import Model.Good;
import Model.Guest;
import Model.Seller;
import View.IndividualGoodPage.IndividualGoodPageController;
import View.IndividualGoodPage.IndividualGoodPageFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GoodIconFactory {

    public static Pane createIcon(Good good) {
        String name = good.getName();
        String imageAddress = good.getImageAddress();
        float point = good.getPoint();
        Pane goodIcon = new Pane();
        goodIcon.setOnMouseClicked(e -> {
            try {
                Stage window = new Stage();
                Scene scene = new Scene(IndividualGoodPageFactory.createGoodPage(good));
                window.setScene(scene);
                window.setTitle(good.getName() + " page");
                window.showAndWait();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        goodIcon.getStylesheets().add("GUIFiles/goodsItemStyle.css");
        VBox box = new VBox(10);
        box.setPadding(new Insets(10, 10, 10, 10));
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(imageAddress);
        } finally {
            if(inputStream == null) {
                try {
                    inputStream = new FileInputStream("src\\main\\resources\\GUIFiles\\noImageAvailable.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            Image image = new Image(inputStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(150);
            HBox nameBox = new HBox();
            HBox rateBox = new HBox();
            Label nameOfProduct = new Label(name);
            Label rateOfProduct = new Label(Float.toString(point));
            nameBox.getChildren().addAll(new Label("name"), nameOfProduct);
            rateBox.getChildren().addAll(new Label("rate"), rateOfProduct);


            VBox sellersBox = new VBox();
            for (String s : good.getSellerAndPrices().keySet()) {
                CheckBox checkBox = new CheckBox();
                checkBox.setOnAction(e -> {
                    if(checkBox.isSelected()) {

                        if(AccountController.loggedInUser instanceof Guest) {

                        } else if (AccountController.loggedInUser instanceof Customer) {

                        }

                    } else {

                        if(AccountController.loggedInUser instanceof Guest) {

                        } else if (AccountController.loggedInUser instanceof Customer) {

                        }
                    }
                });
                checkBox.setText(s + " : " + good.getSellerAndPrices().get(s));
                sellersBox.getChildren().add(checkBox);
            }

            box.getChildren().addAll(imageView, nameBox, rateBox, sellersBox);
            goodIcon.getChildren().add(box);
            box.setAlignment(Pos.CENTER);
            box.getStyleClass().add("mainPane");
            nameBox.getStyleClass().add("HBox");
            rateBox.getStyleClass().add("HBox");
            return goodIcon;
        }
    }

}
