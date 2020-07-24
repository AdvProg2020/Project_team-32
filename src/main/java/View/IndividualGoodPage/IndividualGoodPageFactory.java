package View.IndividualGoodPage;

import Server.Model.*;
import View.Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndividualGoodPageFactory {

    public static Pane createGoodPage(Good good) throws IOException {

        Pane goodPage = new Pane();
        HBox mainBox = new HBox();
        mainBox.setPadding(new Insets(10,10,10,10));
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(20);
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
            nameBox.setAlignment(Pos.CENTER);
            Label nameLable = new Label("name");
            Label name = new Label(good.getName());
            nameBox.getChildren().addAll(nameLable, name);
            HBox sellersBox = new HBox();
            sellersBox.setAlignment(Pos.CENTER);
            Label sellersName = new Label("sellers");
            sellersBox.getChildren().add(sellersName);
            VBox sellers = new VBox();
            sellers.setAlignment(Pos.CENTER);
            for (Seller seller : good.getSellers()) {
                sellers.getChildren().add(new Label(seller.getFirstName() + " " + seller.getLastName()));
            }
            sellersBox.getChildren().add(sellers);
            HBox rateBox = new HBox();
            rateBox.setAlignment(Pos.CENTER);
            Label rateLabel = new Label("rate");
            Label rate = new Label(Double.toString(good.getPoint()));
            rateBox.getChildren().addAll(rateLabel, rate);
            properties.getChildren().addAll(imageView, nameBox, sellersBox, rateBox);
            properties.setAlignment(Pos.CENTER);
            VBox comments = new VBox();
            comments.setAlignment(Pos.CENTER);
            comments.setSpacing(10);
            comments.getChildren().add(new Label("Comments"));

            HashMap<String , Object> arg0 = new HashMap<>();
            arg0.put("good", good);
            Client.sendMessage("get all comments", arg0);
            List<Comment> allComments = (List<Comment>) Client.getMessage().get("all comments");
            for (Comment comment : allComments) {
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
                        Client.sendMessage("get loggedInUser", new HashMap<>());
                        Person loggedInUser = (Person) Client.getMessage().get("user");
                        HashMap<String, Object> arg = new HashMap<>();
                        arg.put("comment", new Comment(loggedInUser.getUserName(),good,commentFiled.getText(),"sending","aa"));
                        arg.put("good", good);
                        Client.sendMessage("add comment", arg);
                        comments.getChildren().add(CommentFactory.getComment(loggedInUser.getUserName(),commentFiled.getText(), true ));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
                commentBox.getChildren().addAll(commentFiled, submit);
                window.setScene( new Scene(commentBox));
                window.showAndWait();
            });

            VBox sellersBox1 = new VBox();
            for (String s : good.getSellerAndPrices().keySet()) {
                CheckBox checkBox = new CheckBox();
                checkBox.setText(s + " : " + good.getSellerAndPrices().get(s));

                checkBox.setOnAction(e -> {

                    Client.sendMessage("get loggedInUser", new HashMap<>());
                    Person loggedInUser = (Person) Client.getMessage().get("user");
                    if (checkBox.isSelected()) {
                        if (loggedInUser instanceof Guest) {
                            Guest guest = ((Guest) loggedInUser);
                            for (ShoppingBasket shoppingBasket : guest.getShoppingBaskets()) {
                                if (shoppingBasket.getGood().getGoodID().equals(good.getGoodID())) {
                                    break;
                                }
                            }
                            
                            HashMap<String, Object> arg2 = new HashMap<>();
                            arg2.put("id", s);
                            Client.sendMessage("get person by id" ,arg2);
                            Seller seller = (Seller)Client.getMessage().get("person");
                            HashMap<String, Object> arg1 = new HashMap<>();
                            arg1.put("shopping basket", new ShoppingBasket(good, seller));
                            Client.sendMessage("add to shopping baskets", new HashMap<>());
                        } else if (loggedInUser instanceof Customer) {
                            Customer customer = ((Customer) loggedInUser);
                            for (ShoppingBasket shoppingBasket : customer.getShoppingBaskets()) {
                                if (shoppingBasket.getGood().getGoodID().equals(good.getGoodID())) {
                                    break;
                                }
                            }
                            HashMap<String, Object> arg2 = new HashMap<>();
                            arg2.put("id", s);
                            Client.sendMessage("get person by id" ,arg2);
                            Seller seller = ((Seller)Client.getMessage().get("person"));
                            HashMap<String, Object> arg1 = new HashMap<>();
                            arg1.put("good", good);
                            arg1.put("seller", seller);
                            //arg1.put("shopping basket", new ShoppingBasket(good, seller));
                            Client.sendMessage("add to shopping baskets", arg1);
                            
                        }

                    } else if (!checkBox.isSelected()){
                        if (loggedInUser instanceof Guest) {
                            Guest guest = ((Guest) loggedInUser);
                            ArrayList<ShoppingBasket> toRemove = new ArrayList<>();
                            for (ShoppingBasket shoppingBasket : guest.getShoppingBaskets()) {
                                if (shoppingBasket.getGood().getGoodID().equals(good.getGoodID())) {
                                    toRemove.add(shoppingBasket);
                                }
                            }
                            HashMap<String, Object> arg2 = new HashMap<>();
                            arg2.put("list", toRemove);
                            Client.sendMessage("remove from shopping basket", arg2);
                        } else if (loggedInUser instanceof Customer) {
                            Customer customer = ((Customer) loggedInUser);
                            ArrayList<ShoppingBasket> toRemove = new ArrayList<>();
                            for (ShoppingBasket shoppingBasket : customer.getShoppingBaskets()) {
                                if (shoppingBasket.getGood().getGoodID().equals(good.getGoodID())) {
                                    toRemove.add(shoppingBasket);
                                }
                            }
                            HashMap<String, Object> arg2 = new HashMap<>();
                            arg2.put("list", toRemove);
                            Client.sendMessage("remove from shopping basket", arg2);
                        }
                    }
                });

                sellersBox1.getChildren().add(checkBox);
            }
            mainBox.getChildren().addAll(properties, comments, addComment,sellersBox1);

            goodPage.setPrefWidth(600);
            goodPage.setPrefHeight(600);
            goodPage.getStylesheets().add("GUIFiles/goodPageStylesheet.css");
            goodPage.getStyleClass().add("goodPage");
            comments.getStyleClass().add("commentsBox");
            goodPage.getChildren().add(mainBox);
            return goodPage;
        }
    }

}
