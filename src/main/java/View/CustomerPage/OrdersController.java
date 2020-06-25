package View.CustomerPage;

import Controller.AccountController;
import Controller.CustomerController;
import Controller.Exeptions.InvalidIDException;
import Model.BuyLog;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    @FXML
    private Button orderShowPageButton;

    @FXML
    private Label result_Label;

    @FXML
    private TextField IdTextField;

    @FXML
    private Button ratePageButton;

    @FXML
    private Pane tablePane;

    @FXML
    private TableView<BuyLog> table;

    @FXML
    private TableColumn<BuyLog, String> logCol;

    @FXML
    private Pane ratePane;

    @FXML
    private Button rateButton;

    @FXML
    private Label rateResult;

    @FXML
    private TextField rateTextFied;

    @FXML
    private Pane showOrderPane;

    @FXML
    private VBox orderVBox;

    @FXML
    private Label LogID_VBox;

    @FXML
    private Label date_VBox;

    @FXML
    private Label goodName_VBox;

    @FXML
    private Label seller_VBox;

    @FXML
    private Label price_VBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ratePane.setVisible(false);
        showOrderPane.setVisible(false);
        logCol.setCellValueFactory(new PropertyValueFactory<>("LogID"));
        ObservableList<BuyLog> logList = FXCollections.observableArrayList();
        for (BuyLog buyLog : ((Customer) AccountController.loggedInUser).getAllBuyLogs()) {
            logList.add(buyLog);
        }
        table.setItems(logList);
        ratePageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tablePane.setVisible(false);
                ratePane.setVisible(true);
                rateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            CustomerController.rateProduct(IdTextField.getText().trim(), Integer.parseInt(rateTextFied.getText().trim()), ((Customer)AccountController.loggedInUser));
                        } catch (Exception e) {
                            e.printStackTrace();
                            rateResult.setText("you have entered wrong ID or wrong Rate number");
                        }
                    }
                });
            }
        });
        orderShowPageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
                    BuyLog log =CustomerController.getBugLogWithId(IdTextField.getText().trim(),((Customer)AccountController.loggedInUser));
                    tablePane.setVisible(false);
                    showOrderPane.setVisible(true);
                    LogID_VBox.setText("log ID :"+log.getLogID());
                    date_VBox.setText("date: "+log.getDate().toString());
                    goodName_VBox.setText("good's Name: "+log.getGoodsBought().getName());
                    seller_VBox.setText("seller name: "+log.getSellerUserName());
                    price_VBox.setText("price paid: "+log.getPricePaid());
                }
                catch (InvalidIDException e){
                    System.out.println("Invalid ID");
                    result_Label.setText("invalid ID");
                }
            }
        });
    }
}
