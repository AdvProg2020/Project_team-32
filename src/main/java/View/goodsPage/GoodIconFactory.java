package View.goodsPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GoodIconFactory {

    public static Pane createIcon(String name, String imageAddress, float rate) throws FileNotFoundException {
        Pane goodIcon = new Pane();
        goodIcon.getStylesheets().add("GUIFiles/goodsItemStyle.css");
        VBox box = new VBox(10);
        box.setPadding(new Insets(10, 10, 10, 10));
        FileInputStream inputStream = new FileInputStream(imageAddress);
        Image image = new Image(inputStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(150);
        HBox nameBox = new HBox();
        HBox rateBox = new HBox();
        Label nameOfProduct = new Label(name);
        Label rateOfProduct = new Label(Float.toString(rate));
        nameBox.getChildren().addAll(new Label("name"), nameOfProduct);
        rateBox.getChildren().addAll(new Label("rate"), rateOfProduct);
        box.getChildren().addAll(imageView, nameBox, rateBox);
        goodIcon.getChildren().add(box);
        box.setAlignment(Pos.CENTER);

        box.getStyleClass().add("mainPane");
        nameBox.getStyleClass().add("HBox");
        rateBox.getStyleClass().add("HBox");

        return goodIcon;
    }

}
