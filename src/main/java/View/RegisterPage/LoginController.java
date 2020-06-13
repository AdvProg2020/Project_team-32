package View.RegisterPage;

import Controller.AccountController;
import Controller.Exeptions.UserDoesNotExistException;
import Controller.Exeptions.WrongPasswordException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public PasswordField passwordField;
    public TextField usernameField;
    public Button signInButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) resetField(usernameField);
            }
        });
        passwordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) resetField(passwordField);
            }
        });
    }

    public void resetField(Node field) {
        field.getStyleClass().remove("inputChoiceError");
        if(field instanceof TextField) {
            ((TextField) field).setText("");
        }
    }

    public void login(ActionEvent actionEvent) {
        if(usernameField.getText().equals("")){
            usernameField.getStyleClass().add("inputChoiceError");
            usernameField.setText("Can't be empty");
        }
        else if(passwordField.getText().equals("")){
            passwordField.getStyleClass().add("inputChoiceError");
        }
        else{
            try {
                AccountController.login(usernameField.getText(),passwordField.getText());
            } catch (WrongPasswordException e) {
                usernameField.getStyleClass().add("inputChoiceError");
                usernameField.setText("Wrong Password");
            } catch (UserDoesNotExistException e) {
                usernameField.getStyleClass().add("inputChoiceError");
                usernameField.setText("Username Does'nt Exist");
            }
        }
    }
}
