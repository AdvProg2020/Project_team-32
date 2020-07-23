package View.ManagerPage;

import Server.Controller.GoodController;
import Server.Model.Good;
import Server.Model.Message;
import View.Client;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ProductManagerPageController implements Initializable {

    public TableView<Good> productsTable;
    public TableColumn<Good, String> idColumn;
    public TableColumn<Good, String> nameColumn;
    public TableColumn<Good, String> categoryColumn;
    public TableColumn<Good, String> statusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("goodID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryString"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("goodStatus"));

        //set table item
        updateTable();

    }

    public void remove(ActionEvent actionEvent) {
        if(productsTable.getSelectionModel().getSelectedItem() != null){

            HashMap<String , Object> inputs = new HashMap<>();
            inputs.put("product id",productsTable.getSelectionModel().getSelectedItem().getGoodID());
            Client.sendMessage("remove product by manager",inputs);
            Message serverAnswer = Client.getMessage();
            if(serverAnswer.get("status").equals("null pointer exception")){
                new Alert(Alert.AlertType.WARNING, "product does not exist.").show();
            }
            updateTable();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Should select a product.").show();
        }
    }

    private void updateTable() {
        productsTable.getItems().clear();
        productsTable.getItems().addAll(getAllGoods());
    }

    private ArrayList<Good> getAllGoods(){
        Client.sendMessage("get all goods",new HashMap<>());
        Message serverAnswer = Client.getMessage();
        return (ArrayList<Good>) serverAnswer.get("all goods");
    }

}
