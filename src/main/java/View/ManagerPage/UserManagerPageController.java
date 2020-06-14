package View.ManagerPage;

import Controller.AccountController;
import Controller.BossController;
import Controller.Exeptions.DuplicateUsernameException;
import Model.Person;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserManagerPageController implements Initializable {

    public Button addManagerButton;
    public Button removeButton;
    public TableColumn<Person, String> usernameColumn;
    public TableColumn<Person, String> passwordColumn;
    public TableColumn<Person, String> emailColumn;
    public TableColumn<Person, String> phoneColumn;
    public TableView<Person> usersTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("passWord"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneID"));
        usersTable.setItems(FXCollections.observableArrayList(Person.allPersons));
    }

    public void remove(){
        if(usersTable.getSelectionModel().getSelectedItem() != null){
            AccountController.deleteUser(usersTable.getSelectionModel().getSelectedItem());
            updateTable();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Should select a user.").show();
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
                    updateTable();
                } catch (DuplicateUsernameException e) {
                    new Alert(Alert.AlertType.WARNING,"username is already taken.").show();
                }
            }

        }

    }


    public void updateTable(){
        usersTable.getItems().clear();
        usersTable.getItems().addAll(FXCollections.observableArrayList(Person.allPersons));
    }

}
