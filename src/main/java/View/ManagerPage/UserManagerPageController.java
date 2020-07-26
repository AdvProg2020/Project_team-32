package View.ManagerPage;

import Server.Controller.AccountController;
import Server.Controller.BossController;
import Server.Controller.Exeptions.DuplicateUsernameException;
import Server.Model.Message;
import Server.Model.Person;
import View.Client;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
    public TableColumn<Person, String> statusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("passWord"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneID"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        System.out.println("initial table");
        updateTable();
    }

    private ArrayList<Person> getAllPerson() {
        Client.sendMessage("get all persons", new HashMap<>());
        Message serverAnswer = Client.getMessage();
        return (ArrayList<Person>) serverAnswer.get("all persons");
    }

    public void remove() {
        if (usersTable.getSelectionModel().getSelectedItem() != null) {
            HashMap<String, Object> inputs = new HashMap<>();
            inputs.put("username", usersTable.getSelectionModel().getSelectedItem().getUserName());
            Client.sendMessage("remove user", inputs);
            Message serverAnswer = Client.getMessage();
            if (serverAnswer.get("status").equals("username does not exist exception")) {
                new Alert(Alert.AlertType.WARNING, "user does not exist.").show();
            }
            updateTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Should select a user.").show();
        }
    }

    public void addManager() {

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

        if (usernameResult.isPresent()) { //check if button ok clicked

            username = usernameResult.get();

            //to get password
            Optional<String> passwordResult = passwordDialog.showAndWait();

            if (passwordResult.isPresent()) {
                password = passwordResult.get();

                HashMap<String, Object> inputs = new HashMap<>();
                inputs.put("username", username);
                inputs.put("password", password);
                Client.sendMessage("create manager", inputs);
                Message serverAnswer = Client.getMessage();
                updateTable();
                if (serverAnswer.get("status").equals("duplicate username")) {
                    new Alert(Alert.AlertType.WARNING, "username is already taken.").show();
                }
            }

        }

    }


    public void updateTable() {
        usersTable.getItems().clear();
        usersTable.getItems().addAll(FXCollections.observableArrayList(getAllPerson()));
    }

    public void addSupporter(ActionEvent actionEvent) {

        String username;
        String password;

        TextInputDialog usernameDialog = new TextInputDialog();
        usernameDialog.setHeaderText("enter username:");
        usernameDialog.setTitle("Create Supporter:");

        TextInputDialog passwordDialog = new TextInputDialog();
        passwordDialog.setHeaderText("enter password:");
        passwordDialog.setTitle("Create Supporter:");


        //to get username
        Optional<String> usernameResult = usernameDialog.showAndWait();

        if (usernameResult.isPresent()) { //check if button ok clicked

            username = usernameResult.get();

            //to get password
            Optional<String> passwordResult = passwordDialog.showAndWait();

            if (passwordResult.isPresent()) {
                password = passwordResult.get();

                HashMap<String, Object> inputs = new HashMap<>();
                inputs.put("username", username);
                inputs.put("password", password);
                Client.sendMessage("create supporter", inputs);
                Message serverAnswer = Client.getMessage();
                updateTable();
                if (serverAnswer.get("status").equals("duplicate username")) {
                    new Alert(Alert.AlertType.WARNING, "username is already taken.").show();
                }
            }

        }

    }
}
