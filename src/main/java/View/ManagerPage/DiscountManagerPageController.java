package View.ManagerPage;

import Controller.BossController;
import Controller.GoodController;
import Model.Boss;
import Model.Discount;
import View.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.Date;
import java.util.ResourceBundle;

public class DiscountManagerPageController implements Initializable {

    public Button editDiscount;
    public Button addDiscount;
    public Button removeButton;
    public TableColumn<Discount, Integer> amountColumn;
    public TableColumn<Discount, Integer> percentColumn;
    public TableColumn<Discount, Date> dateColumn;
    public TableColumn<Discount, String> idColumn;
    public TableView<Discount> discountsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set column value factory
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("maxAmount"));
        percentColumn.setCellValueFactory(new PropertyValueFactory<>("discountPercent"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("exposeDate"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("discountID"));

        //initial table items
        discountsTable.getItems().addAll(BossController.getAllDiscount());

    }

    public void updateTable(){
        discountsTable.getItems().clear();
        discountsTable.getItems().addAll(BossController.getAllDiscount());
    }


    public void remove(ActionEvent actionEvent) {
        if(discountsTable.getSelectionModel().getSelectedItem() != null){
            BossController.removeDiscount(discountsTable.getSelectionModel().getSelectedItem());
            updateTable();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Should select a discount.").show();
        }
    }

    public void addDiscount(ActionEvent actionEvent) {

            Stage stage = new Stage();
            stage.setScene(new Scene(new ChangeDiscountPane(this,null)));
            stage.show();
    }
}
