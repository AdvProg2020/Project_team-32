package View.RegisterPage;

import Controller.AccountController;
import Controller.Exeptions.UserDoesNotExistException;
import Controller.Exeptions.WrongPasswordException;
import Model.Boss;
import Model.Customer;
import Model.Person;
import Model.Seller;
import View.Main;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
                Person person = AccountController.login(usernameField.getText(),passwordField.getText());
                URL url = null;
                if(person instanceof Boss){
                    url = new File("src\\main\\resources\\GUIFiles\\manager-tab-pane.fxml").toURI().toURL();
                }
                else if(person instanceof Customer){
                    url = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\CustomerPage.fxml").toURI().toURL();
                }
                else if(person instanceof Seller){
                    url = new File("src\\main\\resources\\GUIFiles\\Seller-fxml-pages\\SellerMenu.fxml").toURI().toURL();
                }
                Scene scene = new Scene(FXMLLoader.load(url));
                Main.primaryStage.setScene(scene);
            } catch (WrongPasswordException e) {
                usernameField.getStyleClass().add("inputChoiceError");
                usernameField.setText("Wrong Password");
            } catch (UserDoesNotExistException e) {
                usernameField.getStyleClass().add("inputChoiceError");
                usernameField.setText("Username Does'nt Exist");
            } catch (IOException e) {
                System.err.println("error in load files.");
            }
        }
    }
}
