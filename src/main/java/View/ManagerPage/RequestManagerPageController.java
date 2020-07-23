package View.ManagerPage;

import Server.Controller.RequestController;
import Server.Model.Category;
import Server.Model.Message;
import Server.Model.Request;
import View.Client;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class RequestManagerPageController implements Initializable {

    public TableColumn<Request, String> requestTypeColumn;
    public TableColumn<Request, String> sellerColumn;
    public TableColumn<Request, String> detailsColumn;
    public TableView<Request> requestsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        requestTypeColumn.setCellValueFactory(new PropertyValueFactory<>("requestType"));
        sellerColumn.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("request"));

        updateTable();


    }

    private void updateTable() {
        requestsTable.getItems().clear();
        requestsTable.getItems().addAll(getAllRequest());
    }

    private Collection<? extends Request> getAllRequest() {
        Client.sendMessage("get all requests", new HashMap<>());
        Message serverAnswer = Client.getMessage();
        return (Collection<? extends Request>) serverAnswer.get("all requests");
    }

    public void remove(ActionEvent actionEvent) {
        if (requestsTable.getSelectionModel().getSelectedItem() != null) {
            HashMap<String, Object> inputs = new HashMap<>();
            inputs.put("request index", requestsTable.getSelectionModel().getSelectedIndex());
            Client.sendMessage("remove request", inputs);
            updateTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Should select a request.").show();
        }
    }

    public void acceptRequest(ActionEvent actionEvent) {
        if (requestsTable.getSelectionModel().getSelectedItem() != null) {
            HashMap<String, Object> inputs = new HashMap<>();
            inputs.put("request index", requestsTable.getSelectionModel().getSelectedIndex());
            Client.sendMessage("accept request", inputs);
            String serverAnswer = (String) Client.getMessage().get("status");
            if(serverAnswer.equals("error")){
                new Alert(Alert.AlertType.WARNING, "can't accept request.");
            }
            updateTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Should select a request.").show();
        }
    }
}
