package View.IndividualGoodPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CommentController implements Initializable {

    private String username;
    private String comment;
    private boolean bought;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField commentField;

    @FXML
    private Label buyStatus;

    public CommentController(String username, String comment, boolean bought) {
        this.username = username;
        this.comment = comment;
        this.bought = bought;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText(username);
        commentField.setText(comment);
        if(bought) {
            buyStatus.setText("bought");
        } else {
            buyStatus.setText("not bought");
        }
    }

    public Parent getComment() throws IOException {
        URL url = new File("src\\main\\resources\\GUIFiles\\good-comments.fxml").toURI().toURL();
        return FXMLLoader.load(url);
    }

    public void setProperties(String username, String comment, boolean bought) {
        this.username = username;
        this.comment = comment;
        this.bought = bought;
    }
}
