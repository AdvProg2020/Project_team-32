package View;

import Server.Controller.Controller;
import Server.Controller.BossController;
import Server.Controller.Exeptions.DuplicateUsernameException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public Tab registerTab;
    public Tab loginTab;
    public Tab productPageTab;
    public Tab offPageTab;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        while (!Controller.isBossCreated){
            addManager();
        }

        try {
            //set URLs
            URL registerUrl = new File("src\\main\\resources\\GUIFiles\\RegisterPage.fxml").toURI().toURL();
            URL loginUrl = new File("src\\main\\resources\\GUIFiles\\LoginPage.fxml").toURI().toURL();
            URL productPageUrl = new File("src\\main\\resources\\GUIFiles\\GoodsPage.fxml").toURI().toURL();
            URL offPageUrl = new File("src\\main\\resources\\GUIFiles\\OffsPage.fxml").toURI().toURL();


            //set contents
            registerTab.setContent(FXMLLoader.load(registerUrl));
            loginTab.setContent(FXMLLoader.load(loginUrl));
            productPageTab.setContent(FXMLLoader.load(productPageUrl));
            offPageTab.setContent(FXMLLoader.load(offPageUrl));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addManager(){

        String username;
        String password;

        TextInputDialog usernameDialog = new TextInputDialog();
        usernameDialog.setHeaderText("enter username:");
        usernameDialog.setTitle("Create Manager:");

        TextInputDialog passwordDialog = new TextInputDialog();
        passwordDialog.setHeaderText("enter password:");
        passwordDialog.setTitle("Create Manager:");


        //to get username
        Optional<String> usernameResult = usernameDialog.showAndWait();

        if(usernameResult.isPresent()){ //check if button ok clicked

            username = usernameResult.get();

            //to get password
            Optional<String> passwordResult = passwordDialog.showAndWait();

            if(passwordResult.isPresent()){
                password = passwordResult.get();
                try {
                   BossController.createManager(username,password);
                   Controller.isBossCreated = true;
                } catch (DuplicateUsernameException e) {
                    new Alert(Alert.AlertType.WARNING,"username is already taken.").show();
                }
            }

        }

    }


}
