package View.ManagerPage;

import Controller.BossController;
import Model.Discount;
import View.ManagerPage.GUIModels.ChangeDiscountPane;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
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
    private Stage stageToShow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set column value factory
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("maxAmount"));
        percentColumn.setCellValueFactory(new PropertyValueFactory<>("discountPercent"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("exposeDate"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("discountID"));

        //initial table items
        discountsTable.getItems().addAll(BossController.getAllDiscount());

        stageToShow = new Stage();

    }

    public void updateTable() {
        discountsTable.getItems().clear();
        discountsTable.getItems().addAll(BossController.getAllDiscount());
    }


    public void remove(ActionEvent actionEvent) {
        if (discountsTable.getSelectionModel().getSelectedItem() != null) {
            BossController.removeDiscount(discountsTable.getSelectionModel().getSelectedItem());
            updateTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Should select a discount.").show();
        }
    }

    public void addDiscount(ActionEvent actionEvent) {
        stageToShow.setScene(new Scene(new ChangeDiscountPane(this, null)));
        stageToShow.show();
    }

    public void closeStage() {
        stageToShow.close();
    }

    public void editDiscount(ActionEvent actionEvent) {
        if (discountsTable.getSelectionModel().getSelectedItem() != null) {
            Discount discount = discountsTable.getSelectionModel().getSelectedItem();
            stageToShow.setScene(new Scene(new ChangeDiscountPane(this, discount)));
            stageToShow.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Should select a discount.").show();
        }
    }

}
