package View.IndividualGoodPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CommentFactory {

    public static VBox getComment(String username, String commentText, boolean bought) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = new File("src\\main\\resources\\GUIFiles\\good-comments.fxml").toURI().toURL();
        System.out.println(url);
        VBox comment = (VBox) fxmlLoader.load(url);
        ((CommentController)fxmlLoader.getController()).setProperties(username, commentText, bought);
        return comment;
    }

}
