package View.ManagerPage;

import Controller.GoodController;
import Controller.RequestController;
import Model.Request;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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
        requestsTable.getItems().addAll(RequestController.getAllRequest());
    }

    public void remove(ActionEvent actionEvent) {
        if(requestsTable.getSelectionModel().getSelectedItem() != null){
            RequestController.removeRequest(requestsTable.getSelectionModel().getSelectedItem());
            updateTable();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Should select a request.").show();
        }
    }

    public void acceptRequest(ActionEvent actionEvent) {
        if(requestsTable.getSelectionModel().getSelectedItem() != null){
            try {
                RequestController.acceptRequest(requestsTable.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateTable();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Should select a request.").show();
        }
    }
}
