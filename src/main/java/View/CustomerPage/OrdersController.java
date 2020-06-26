package View.CustomerPage;

import Controller.AccountController;
import Controller.Controller;
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
    private TableView<BuyLog> table;

    @FXML
    private TableColumn<BuyLog, String> logCol;

    @FXML
    private Button rateButton;

    @FXML
    private Label rateResult;

    @FXML
    private TextField rateTextFied;

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
    @FXML
    private VBox rateVbox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderShowPageButton.setOnMouseClicked(event -> Controller.sound(3));
        rateButton.setOnMouseClicked(event -> Controller.sound(3));
        ratePageButton.setOnMouseClicked(event -> Controller.sound(3));

        orderShowPageButton.setOnMouseEntered(event -> Controller.sound(1));
        rateButton.setOnMouseEntered(event -> Controller.sound(1));
        ratePageButton.setOnMouseEntered(event -> Controller.sound(1));

        price_VBox.setOnMouseEntered(event -> Controller.sound(2));
        seller_VBox.setOnMouseEntered(event -> Controller.sound(2));
        goodName_VBox.setOnMouseEntered(event -> Controller.sound(2));
        date_VBox.setOnMouseEntered(event -> Controller.sound(2));
        LogID_VBox.setOnMouseEntered(event -> Controller.sound(2));
        rateResult.setOnMouseEntered(event -> Controller.sound(2));
        result_Label.setOnMouseEntered(event -> Controller.sound(2));




        orderVBox.setVisible(false);
        rateVbox.setVisible(false);
        logCol.setCellValueFactory(new PropertyValueFactory<>("LogID"));
        ObservableList<BuyLog> logList = FXCollections.observableArrayList();
        for (BuyLog buyLog : ((Customer) AccountController.loggedInUser).getAllBuyLogs()) {
            logList.add(buyLog);
        }
        table.setItems(logList);
        ratePageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                table.setVisible(false);
                rateVbox.setVisible(true);
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
                    table.setVisible(false);
                    orderVBox.setVisible(true);
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
