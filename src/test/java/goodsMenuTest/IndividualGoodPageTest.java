package goodsMenuTest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class IndividualGoodPageTest extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("testing");
        URL url = new File("src\\main\\resources\\GUIFiles\\individual-good-page.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        primaryStage.setScene(new Scene(FXMLLoader.load(url)));
        primaryStage.show();
    }
}
