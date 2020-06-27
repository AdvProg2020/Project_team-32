package View.IndividualGoodPage;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CommentFactory {

    public static VBox getComment(String username, String commentText, boolean bought) throws IOException {
        /*
        FXMLLoader fxmlLoader = new FXMLLoader();

        URL url = new File("src\\main\\resources\\GUIFiles\\good-comments.fxml").toURI().toURL();
        System.out.println(url);
        VBox comment = (VBox) fxmlLoader.load(url);
        ((CommentController)fxmlLoader.getController()).setProperties(username, commentText, bought);
        return comment;
        */

        VBox comment = new VBox(10);
        HBox usernameBox = new HBox();
        Label usernameLabel = new Label("username");
        Label usernameLabel1 = new Label(username);
        usernameBox.getChildren().addAll(usernameLabel, usernameLabel1);

        Label commentField = new Label(commentText);
        Label buyStatus = new Label();
        if(bought) {
            buyStatus.setText("bought");
        } else {
            buyStatus.setText("not bought");
        }

        comment.getChildren().addAll(usernameBox, commentField, buyStatus);

        comment.getStylesheets().add("GUIFiles/comment-stylesheet.css");
        comment.getStyleClass().add("commentVBox");
        comment.setAlignment(Pos.CENTER);
        usernameBox.getStyleClass().add("usernameHBox");
        usernameBox.setSpacing(10);
        usernameBox.setAlignment(Pos.CENTER);
        commentField.getStyleClass().add("commentField");

        return comment;
    }

}
