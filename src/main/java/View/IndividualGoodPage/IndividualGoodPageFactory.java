package View.IndividualGoodPage;

import Controller.AccountController;
import Model.Comment;
import Model.Good;
import Model.Seller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IndividualGoodPageFactory {

    public static Pane createGoodPage(Good good) throws IOException {

        Pane goodPage = new Pane();
        HBox mainBox = new HBox();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(good.getImageAddress());
        } finally {
            if(fileInputStream == null) {
                try {
                    fileInputStream = new FileInputStream("src\\main\\resources\\GUIFiles\\noImageAvailable.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            Image image = new Image(fileInputStream);
            ImageView imageView = new ImageView(image);
            VBox properties = new VBox(10);
            HBox nameBox = new HBox();
            Label nameLable = new Label("name");
            Label name = new Label(good.getName());
            nameBox.getChildren().addAll(nameLable, name);
            HBox sellersBox = new HBox();
            Label sellersName = new Label("sellers");
            sellersBox.getChildren().add(sellersName);
            VBox sellers = new VBox();
            for (Seller seller : good.getSellers()) {
                sellers.getChildren().add(new Label(seller.getFirstName() + " " + seller.getLastName()));
            }
            sellersBox.getChildren().add(sellers);
            HBox rateBox = new HBox();
            Label rateLabel = new Label("rate");
            Label rate = new Label(Double.toString(good.getPoint()));
            rateBox.getChildren().addAll(rateLabel, rate);
            properties.getChildren().addAll(imageView, nameBox, sellersBox, rateBox);

            VBox comments = new VBox();
            comments.getChildren().add(new Label("Comments"));
            for (Comment comment : good.getAllComments()) {
                comments.getChildren().add(CommentFactory.getComment(comment.getUserName(), comment.getCommentString(), comment.getCommenterBoughtGood()));
            }

            Button addComment = new Button("add comment");
            addComment.setOnAction(e -> {
                Stage window = new Stage();
                window.setTitle("enter comment");
                VBox commentBox = new VBox();
                TextField commentFiled = new TextField("enter comment");
                Button submit = new Button("submit");
                submit.setOnAction(f -> {
                    try {
                        comments.getChildren().add(CommentFactory.getComment(AccountController.loggedInUser.getUserName(),commentFiled.getText(), true ));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
                commentBox.getChildren().addAll(commentFiled, submit);
                window.setScene( new Scene(commentBox));
                window.showAndWait();
            });

            mainBox.getChildren().addAll(properties, comments, addComment);

            goodPage.setPrefWidth(600);
            goodPage.setPrefHeight(600);


            goodPage.getChildren().add(mainBox);
            return goodPage;
        }
    }

}
