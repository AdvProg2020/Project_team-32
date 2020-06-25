import View.IndividualGoodPage.CommentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class CommentTest extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("testing");
        URL url = new File("src\\main\\resources\\GUIFiles\\good-comments.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
