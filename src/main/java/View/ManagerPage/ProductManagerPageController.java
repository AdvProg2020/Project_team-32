package View.ManagerPage;

import Controller.AccountController;
import Controller.BossController;
import Controller.GoodController;
import Model.Good;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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
        productsTable.getItems().addAll(Good.getAllGoods());

    }

    public void remove(ActionEvent actionEvent) {
        if(productsTable.getSelectionModel().getSelectedItem() != null){
            GoodController.deleteGood(productsTable.getSelectionModel().getSelectedItem());
            updateTable();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Should select a product.").show();
        }
    }

    private void updateTable() {
        productsTable.getItems().clear();
        productsTable.getItems().addAll(Good.getAllGoods());
    }
}
