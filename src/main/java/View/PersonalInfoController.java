package View;

import Controller.AccountController;
import Model.Guest;
import Model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalInfoController implements Initializable {
   
    public Button phoneEditButton;
    public Button lastnameEditButton;
    public Button firstnameEditButton;
    public Button emailEditButton;
    public TextField emailText;
    public TextField firstNameText;
    public TextField lastNameText;
    public TextField phoneText;
    public Text welcomeText;
    public TextField passwordText;
    public TextField usernameText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Person user = AccountController.loggedInUser;
        if(user!=null && !(user instanceof Guest)){
            passwordText.setText(user.getPassWord());
            usernameText.setText(user.getUserName());
            welcomeText.setText("Hi," + user.getUserName());
            phoneText.setText(user.getPhoneID());
            lastNameText.setText(user.getLastName());
            firstNameText.setText(user.getFirstName());
            emailText.setText(user.geteMail());
        }
        emailText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                    resetFields(emailText);
            }
        });

        lastNameText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                    resetFields(lastNameText);
            }
        });

        firstNameText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                    resetFields(firstNameText);
            }
        });

        phoneText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                    resetFields(phoneText);
            }
        });

    }

    private void resetFields(TextField field) {
        field.getStyleClass().remove("inputChoiceError");
        field.setText("");
    }

    public void enableEmailText(ActionEvent actionEvent) {
        emailText.setDisable(false);
    }

    public void enableFirstText(ActionEvent actionEvent) {
        firstNameText.setDisable(false);
    }

    public void enableLastText(ActionEvent actionEvent) {
        lastNameText.setDisable(false);
    }

    public void enablePhoneText(ActionEvent actionEvent) {
        phoneText.setDisable(false);
    }


    public void changeInformation(ActionEvent actionEvent) {

        //validation
        if(!textValidation(emailText.getText(),".+@.+\\..+")){
            emailText.getStyleClass().add("inputChoiceError");
            emailText.setText("invalid mail");
        }
        if(!textValidation(phoneText.getText(),"\\d+")){
            phoneText.getStyleClass().add("inputChoiceError");
            phoneText.setText("invalid phone number");
            return;
        }

        Person user = AccountController.loggedInUser;
        if(user != null){
            //change user information
            user.seteMail(emailText.getText());
            user.setPhoneID(phoneText.getText());
            user.setFirstName(firstNameText.getText());
            user.setLastName(lastNameText.getText());
        }

        //reset text fields to disable mode
        lastNameText.setDisable(true);
        firstNameText.setDisable(true);
        phoneText.setDisable(true);
        emailText.setDisable(true);

    }

    private boolean textValidation(String text, String regex) {
        return text.matches(regex);
    }


}
