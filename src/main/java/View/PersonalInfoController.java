package View;

import Controller.AccountController;
import Model.Guest;
import Model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
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
    public ImageView imageView;

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
            emailText.setText(user.getEmail());
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
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = new Stage();
                FileChooser fileChooser = new FileChooser();
                try {
                    File selectedFile = fileChooser.showOpenDialog(stage);
                    URL image = selectedFile.toURI().toURL();
                    imageView.setImage(new Image(String.valueOf(image)));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

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
        }
        if(!textValidation(emailText.getText(),".+@.+\\..+") || !textValidation(phoneText.getText(),"\\d+"))
            return;

        Person user = AccountController.loggedInUser;
        if(user != null){
            //change user information
            user.setEmail(emailText.getText());
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
