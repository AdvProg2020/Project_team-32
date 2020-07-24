package View.ManagerPage;

import Server.Controller.BossController;
import Server.Model.Discount;
import Server.Model.Message;
import View.Client;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
        updateTable();

        stageToShow = new Stage();

    }

    public void updateTable() {
        discountsTable.getItems().clear();
        discountsTable.getItems().addAll(getAllDiscount());
    }

    private ArrayList<Discount> getAllDiscount() {
        Client.sendMessage("get all discounts", new HashMap<>());
        Message serverAnswer = Client.getMessage();
        return (ArrayList<Discount>) serverAnswer.get("all discounts");
    }


    public void remove(ActionEvent actionEvent) {
        if (discountsTable.getSelectionModel().getSelectedItem() != null) {
            HashMap<String , Object> inputs = new HashMap<>();
            inputs.put("discountId", discountsTable.getSelectionModel().getSelectedItem().getDiscountID());
            Client.sendMessage("remove discount", inputs);
            Message serverAnswer = Client.getMessage();
            if(serverAnswer.get("status").equals("discount does not exist")){
                new Alert(Alert.AlertType.WARNING, "discount does not exist.").show();
            }
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
