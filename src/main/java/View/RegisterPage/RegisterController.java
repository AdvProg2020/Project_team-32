package View.RegisterPage;

import Server.Controller.AccountController;
import Server.Controller.Exeptions.DuplicateUsernameException;
import View.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    public ChoiceBox roleChoicer;
    public ObservableList<String> roles =FXCollections.observableArrayList("Seller","Customer");
    public Button signUpButton;
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField repeatPasswordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleChoicer.setItems(roles);
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
        repeatPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) resetField(repeatPasswordField);
            }
        });
        roleChoicer.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) resetField(roleChoicer);
            }
        });
    }

    public void register(ActionEvent actionEvent) {
        if(usernameField.getText().equals("")){
            usernameField.getStyleClass().add("inputChoiceError");
            usernameField.setText("Can't be empty");
        }
        else if(passwordField.getText().equals("")){
            passwordField.getStyleClass().add("inputChoiceError");
        }
        else if(!passwordField.getText().equals(repeatPasswordField.getText())){
            passwordField.getStyleClass().add("inputChoiceError");
            repeatPasswordField.getStyleClass().add("inputChoiceError");
        }
        else if(roleChoicer.getSelectionModel().isEmpty()){
            roleChoicer.getStyleClass().add("inputChoiceError");
        }
        else {
            HashMap<String, Object> inputs = new HashMap<>();
            inputs.put("username" , usernameField.getText());
            inputs.put("userType" , (String) roleChoicer.getSelectionModel().getSelectedItem());
            inputs.put("password" , passwordField.getText());
            Client.sendMessage("register", inputs);

            usernameField.setText("");
            passwordField.setText("");
            repeatPasswordField.setText("");

             /*   new Alert(Alert.AlertType.INFORMATION, "Registered successfully.").show();

                usernameField.getStyleClass().add("inputChoiceError");
                usernameField.setText("already used.");*/
        }

    }

    public void resetField(Node field) {
        field.getStyleClass().remove("inputChoiceError");
        if(field instanceof TextField) {
            ((TextField) field).setText("");
        }
    }
}
