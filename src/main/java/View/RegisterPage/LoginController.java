package View.RegisterPage;

import Server.Controller.AccountController;
import Server.Controller.Exeptions.UserDoesNotExistException;
import Server.Controller.Exeptions.WrongPasswordException;
import Server.Model.*;
import View.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
                if (newValue) resetField(usernameField);
            }
        });
        passwordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) resetField(passwordField);
            }
        });
    }

    public void resetField(Node field) {
        field.getStyleClass().remove("inputChoiceError");
        if (field instanceof TextField) {
            ((TextField) field).setText("");
        }
    }

    public void login(ActionEvent actionEvent) {
        if (usernameField.getText().equals("")) {
            usernameField.getStyleClass().add("inputChoiceError");
            usernameField.setText("Can't be empty");
        } else if (passwordField.getText().equals("")) {
            passwordField.getStyleClass().add("inputChoiceError");
        } else {

            HashMap<String, Object> inputs = new HashMap<>();
            inputs.put("username", usernameField.getText());
            inputs.put("password", passwordField.getText());

            Client.sendMessage("login", inputs);
            System.out.println("salam");
            Message serverAnswer = Client.getMessage();
            System.out.println(serverAnswer.get("status"));
            if (serverAnswer.get("status").equals("successful")) {
                System.out.println("hhehehe");
                try {
                    URL url = null;
                    if (serverAnswer.get("account type").equals("boss")) {
                        url = new File("src\\main\\resources\\GUIFiles\\manager-tab-pane.fxml").toURI().toURL();
                        Client.user= (Boss)serverAnswer.get("user");
                    } else if (serverAnswer.get("account type").equals("customer")) {
                        url = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\CustomerPage.fxml").toURI().toURL();
                        Client.user= (Customer)serverAnswer.get("user");
                    } else if (serverAnswer.get("account type").equals("seller")) {
                        url = new File("src\\main\\resources\\GUIFiles\\Seller-fxml-pages\\SellerMenu.fxml").toURI().toURL();
                        Client.user= (Seller)serverAnswer.get("user");
                    }

                    //set scene
                    Scene scene = null;
                    scene = new Scene(FXMLLoader.load(url));
                    Client.primaryStage.setScene(scene);


                } catch (IOException e) {
                    System.err.println("error in loading scene.");
                    e.printStackTrace();
                }


            } else if (serverAnswer.get("status").equals("wrong password")) {
                usernameField.getStyleClass().add("inputChoiceError");
                usernameField.setText("Wrong Password");

            } else if (serverAnswer.get("status").equals("user does not exist")) {
                usernameField.getStyleClass().add("inputChoiceError");
                usernameField.setText("Username Does'nt Exist");
            }

        }
    }
}
