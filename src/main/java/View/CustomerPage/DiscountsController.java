package View.CustomerPage;

import Server.Controller.AccountController;
import Server.Model.Customer;
import Server.Model.Discount;
import Server.Model.Message;
import View.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DiscountsController implements Initializable {
    @FXML
    private TableView<Discount> discountTable;

    @FXML
    private TableColumn<Discount, String> ID_col;

    @FXML
    private TableColumn<Discount, String> initDate_col;

    @FXML
    private TableColumn<Discount, String> expDate_col;

    @FXML
    private TableColumn<Discount, Integer> discountPercent_col;

    @FXML
    private TableColumn<Discount, Integer> maxAmount_col;

    @FXML
    private TableColumn<Discount, Integer> count_col;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        PurchaseController.passTime();
        ID_col.setCellValueFactory(new PropertyValueFactory<>("DiscountID"));
        initDate_col.setCellValueFactory(new PropertyValueFactory<>("InitialDateString"));
        expDate_col.setCellValueFactory(new PropertyValueFactory<>("ExposeDateString"));
        discountPercent_col.setCellValueFactory(new PropertyValueFactory<>("DiscountPercentString"));
        maxAmount_col.setCellValueFactory(new PropertyValueFactory<>("MaxAmountString"));
        count_col.setCellValueFactory(new PropertyValueFactory<>("UseCountString"));
        ObservableList<Discount> discountList = FXCollections.observableArrayList();
        HashMap<String, Object> input = new HashMap<>();
        Client.sendMessage("get customer discounts", input);
        System.out.println("fuck");
        Message message = Client.getMessage();
        System.out.println(message);
        if (message.get("status").equals("successful")) {
            for (Discount discount : ((HashMap<Discount,Integer>)message.get("discounts")).keySet()) {
                discountList.add(discount);
            }
            discountTable.getItems().clear();
            discountTable.getItems().addAll(discountList);
        }

    }
}
