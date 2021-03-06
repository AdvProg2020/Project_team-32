package View.goodsPage;

import Server.Model.*;
import View.IndividualGoodPage.IndividualGoodPageFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
            if (inputStream == null) {
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
            nameBox.setAlignment(Pos.CENTER);
            HBox rateBox = new HBox();
            rateBox.setAlignment(Pos.CENTER);
            Label nameOfProduct = new Label(name);
            Label rateOfProduct = new Label(Float.toString(point));
            nameBox.getChildren().addAll(new Label("name"), nameOfProduct);
            rateBox.getChildren().addAll(new Label("rate"), rateOfProduct);

            HBox sellersbox = new HBox();
            sellersbox.setAlignment(Pos.CENTER);
            sellersbox.getChildren().add(new Label("sellers"));
            VBox sellers = new VBox();
            for (Seller seller : good.getSellers()) {
                sellers.getChildren().add(new Label(seller.getUserName()));
            }
            sellersbox.getChildren().add(sellers);

            box.getChildren().addAll(imageView, nameBox, rateBox, sellersbox);
            goodIcon.getChildren().add(box);
            box.setAlignment(Pos.CENTER);
            box.getStyleClass().add("mainPane");
            nameBox.getStyleClass().add("HBox");
            rateBox.getStyleClass().add("HBox");
            return goodIcon;
        }
    }

}
