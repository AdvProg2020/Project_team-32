package View.CustomerPage;

import Server.Model.Message;
import Server.Model.Person;
import Server.Model.Supporter;
import View.Client;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ShowSupporterPageController implements Initializable {
    public TableView<Supporter> supporterTable;
    public TableColumn<Supporter, String> usernameColumn;
    public TableColumn<Supporter, String> statusColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        updateTable();

    }

    @FXML
    private void updateTable() {
        supporterTable.getItems().clear();
        supporterTable.getItems().addAll(FXCollections.observableArrayList(getAllSupporter()));
    }

    private ArrayList<Supporter> getAllSupporter() {
        Client.sendMessage("get all supporters", new HashMap<>());
        Message serverAnswer = Client.getMessage();
        return (ArrayList<Supporter>) serverAnswer.get("all supporters");
    }

    public void startChat(ActionEvent actionEvent) {
    }
}
