package View.IndividualGoodPage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndividualGoodPageController implements Initializable {

    @FXML
    private VBox mainVBox;

    @FXML
    private VBox commentsVBox;

    @FXML
    private Label nameLabel;

    @FXML
    private Label sellerLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText("fish");
        sellerLabel.setText("ali sharifi");
        priceLabel.setText("120");

        try {
            FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\GUIFiles\\fish.jpg");
            Image image = new Image(fileInputStream);
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {

            commentsVBox.getChildren().add(CommentFactory.getComment("ali", "hello", true));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
